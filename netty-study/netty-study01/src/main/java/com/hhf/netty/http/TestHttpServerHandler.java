package com.hhf.netty.http;

import com.hhf.netty.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest){
            System.out.println("msg 类型是："+httpObject.getClass());
            System.out.println("客户端地址："+ channelHandlerContext.channel().remoteAddress());
            //过滤到浏览器发来的http请求
            HttpRequest httpRequest = (HttpRequest) httpObject;
            URI uri = new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了/favicon.ico,不做回应");
                return;
            }
            //服务器回复信息给客户端，（http协议）
            ByteBuf content = Unpooled.copiedBuffer("hello ,我是服务器~", CharsetUtil.UTF_8);
            //构造一个http的回应即httpResponse
            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            channelHandlerContext.writeAndFlush(httpResponse);

        }
    }
}
