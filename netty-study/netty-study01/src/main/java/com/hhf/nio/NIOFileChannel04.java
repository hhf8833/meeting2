package com.hhf.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author HP
 * 1. 使用 `FileChannel`（通道）和方法 `transferFrom`，完成文件的拷贝
 * 2. 拷贝一张图片
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("D:\\研一\\书籍\\学习笔记\\nettyImages\\0001.png");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\HP\\Desktop\\0002.jpg");
        //获取各个流对应的 FileChannel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        //使用 transferForm 完成拷贝
        destCh.transferFrom(sourceCh, 0, sourceCh.size());

        //关闭相关通道和流
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

}
