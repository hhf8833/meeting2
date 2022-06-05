package com.hhf.consumer.quicksart;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hhf.ServerAPI;
import org.springframework.stereotype.Component;

@Component
public class QuickstartConsumer {
    @Reference
    ServerAPI serverAPI;

    public void sendMessage(String message){
        System.out.println(serverAPI.sendMessage(message));
    }
}
