package com.hhf.nio.groupchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author HP
 * 服务器可以监测用户上线，离线，并实现消息转发功能
 * 1.启动服务器并监听端口6667
 * 2.服务器接收客户端消息，并实现转发(处理上线和离线)
 * 注意：：configureBlocking要写在register前面
 *             serverSocketChannel.configureBlocking(false);
 *             serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
 *         读完要删掉，防止重复读
 *             iterator.remove();
 */
public class GroupChatServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final int PORT=6667;

    public GroupChatServer(){
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //监听
    public void listen(){
        while (true){
            try{
                int select = selector.select(2000);
                if (select>0){
                    //大于0说明有连接发生
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()){
                            //客户端连接
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println("客户端："+socketChannel.getRemoteAddress()+"上线");
                        }
                        if (selectionKey.isReadable()){
                            readMsg(selectionKey);
                        }
                        //这里记住读完要删掉，防止重复读
                        iterator.remove();
                    }
                }else {
                    System.out.println("等等连接");
                }
            }catch (IOException e) {
               e.printStackTrace();
            }finally {

            }
        }
    }

    //读取客户端消息
    public void readMsg (SelectionKey selectionKey){
        SocketChannel socketChannel =null;
        try {
            socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(byteBuffer);
            if (read >0){
                String msg = new String(byteBuffer.array());
                System.out.println("from 客户端" +msg);
                //读完消息之后还需要将其转发到其他客户端,这里注意在转发的时候要排除自己即发送方
                sendInfoToOtherClient(msg,socketChannel);
            }

        }catch (IOException e){
            try {
                System.out.println(socketChannel.getRemoteAddress()+"离线了。。");
                //取消注册并关闭通道
                selectionKey.cancel();
                socketChannel.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }
    }
    //转发消息到其他客户端
    public  void sendInfoToOtherClient(String msg , SocketChannel slef) throws IOException {
        System.out.println("服务器转发消息中。。。");
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel !=slef){
                SocketChannel target = (SocketChannel)targetChannel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                target.write(byteBuffer);
            }
        }

    }
    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
