package com.hhf.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        //简单举例buffer，大小为5的bufffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }
        //buffer读写切换的时候要注明;将其切换成读buffer
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
