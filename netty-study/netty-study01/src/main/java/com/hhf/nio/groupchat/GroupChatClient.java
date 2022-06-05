package com.hhf.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author HP
 * 通过 Channel 可以无阻塞发送消息给其它所有用户，同时可以接受其它用户发送的消息（由服务器转发得到）
 * 1.连接服务器
 * 2.发送消息
 * 3.接收服务器的消息
 */
public class GroupChatClient {
    private final String HOST="127.0.0.1";
    private final int PORT = 6667;
    private SocketChannel socketChannel;
    private Selector selector;
    private String username;

    public GroupChatClient() throws IOException {
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));
        selector = Selector.open();
        //socketChannel.socket().bind(new InetSocketAddress(HOST,PORT));这里不能再监听了
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + " is ok...");
    }
    //向服务器发送消息
    public void sendInfo(String info){
        info = username + "说" + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //从服务器接收消息
    public void readInfo(){
        try {
            //没有消息将会阻塞在这里
            int readChannels = selector.select();
            if (readChannels>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        String msg = new String(byteBuffer.array());
                        System.out.println(msg.trim());
                    }
                }
                iterator.remove();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        GroupChatClient groupChatClient = new GroupChatClient();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    groupChatClient.readInfo();
                    try {
                        sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            groupChatClient.sendInfo(s);
        }

    }

}
