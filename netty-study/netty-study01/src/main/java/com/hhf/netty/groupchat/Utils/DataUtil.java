package com.hhf.netty.groupchat.Utils;

import io.netty.buffer.ByteBuf;

public  class DataUtil {
    public static volatile ByteBuf byteBuf;

    public static ByteBuf change(ByteBuf byteBuf){
        synchronized (DataUtil.class){
            byteBuf.writeBytes(byteBuf);
        }
        return byteBuf;
    }

}
