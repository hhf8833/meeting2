package com.hhf.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {


        ChannelPipeline pipeline = socketChannel.pipeline();
        //HttpServerCodec是netty提供的处理http的编-解码器
        pipeline.addLast("MyHttpSeverCodec",new HttpServerCodec());
        pipeline.addLast("MyTestHttpServerHandler",new TestHttpServerHandler());
    }
}
