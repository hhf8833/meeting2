package com.hhf.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import netscape.javascript.JSUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //通道就绪便可触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端："+ctx);
        //client在active里面写入并不能发送到服务器
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,server:你好啊！", CharsetUtil.UTF_8));

    }

    //当通道有读取事件的时候触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器回复的消息："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址是："+ctx.channel().remoteAddress());

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       ctx.close();
    }
}
