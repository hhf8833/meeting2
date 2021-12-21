package com.hhf.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author HP
 * 1. 使用 `FileChannel`（通道）和方法 `read、write`，完成文件的拷贝
 * 2. 拷贝一个文本文件 `1.txt`，放在项目下即可
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("d:\\file01.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file02.txt");
        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();
        while (true){
            byteBuffer.clear();
            int read =fileChannel.read(byteBuffer);
            if (read==-1){
                break;
            }
            byteBuffer.flip();
            fileOutputStreamChannel.write(byteBuffer);

        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
