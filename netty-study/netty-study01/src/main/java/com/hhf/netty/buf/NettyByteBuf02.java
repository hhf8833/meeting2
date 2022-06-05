package com.hhf.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyByteBuf02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,byteBuf", CharsetUtil.UTF_8);
        if (byteBuf.hasArray()){
            //new String(byteBuf.toString(CharsetUtil.UTF_8))
            System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        }
    }
}
