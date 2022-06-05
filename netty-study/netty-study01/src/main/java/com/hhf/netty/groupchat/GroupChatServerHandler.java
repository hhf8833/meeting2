package com.hhf.netty.groupchat;

import com.hhf.netty.groupchat.Utils.DataUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组，管理所有的channel
    //全局事件执行器，单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //一旦又channel连接就会调用
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将客户加入聊天的信息推送到其他在线的客户
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"加入聊天"+sdf.format(new Date())+"\n");
        channelGroup.add(channel);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将客户加入聊天的信息推送到其他在线的客户
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"离开了");
    }

    //表示channel处于活动状态，提示xx上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"上线了");
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离线了");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //获得当前发送消息的用户，该消息不会在服务器中显示
        Channel channel = channelHandlerContext.channel();
        //根据不同的情况回复不同消息
        channelGroup.forEach(ch -> {
            if (ch !=channel){
                ByteBuf byteBuf = Unpooled.copiedBuffer(s, CharsetUtil.UTF_8);
                DataUtil.change(byteBuf);
                ch.writeAndFlush("[客户]"+channel.remoteAddress()+"发送消息："+s+"\n");
            }else {
                ch.writeAndFlush("[自己]发送了消息"+s+"\n");
            }
        });
        //channelGroup.writeAndFlush("[客户]"+channel.remoteAddress()+"发送消息："+s+"\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
