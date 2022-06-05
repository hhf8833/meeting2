package com.hhf.nettymultichat.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
public class ChatServerConfig {
    @Autowired
    WebSocketHandler webSocketHandler;
    @Bean(name = "bossGroup",destroyMethod = "shutdownGracefully" )
    public NioEventLoopGroup bossGroup(){
        return new NioEventLoopGroup(1);
    }
    @Bean(name = "workerGroup",destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup(){
        return new NioEventLoopGroup();
    }
    @Bean("bootstrap")
    public ServerBootstrap serverBootStrap(){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup(),workerGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new HttpServerCodec());
                        //异步写入大数据流的支持，既不消耗大量内存，也不获取OutOfMemoryError。
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new HttpObjectAggregator(1048576));
                        pipeline.addLast(webSocketHandler);
                        //pipeline.addLast(new WebSocketServerProtocolHandler("/websocket"));//握手操作
                    }
                });
        System.out.println("server .....");
        return serverBootstrap;
    }
}
