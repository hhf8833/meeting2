package com.hhf.nettymultichat.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ChatServerApplication {

    //将bean返回的strap注入
    @Autowired
    @Qualifier("bootstrap")
    ServerBootstrap serverBootstrap;

    Channel channel;

    public void start() throws InterruptedException {
        System.out.println("netty启动");
        ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()){
                    System.out.println("监听成功");
                }else {
                    System.out.println("监听失败");
                }
            }
        });
        channel = channelFuture.channel().closeFuture().sync().channel();
        //channel = serverBootstrap.bind(8888).sync().channel().closeFuture().sync().channel();

    }
    public void close(){
        channel.close();
        channel.parent().close();
    }
}
