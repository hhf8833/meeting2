package com.hhf.provider.quickstart;

import com.alibaba.dubbo.config.annotation.Service;
import com.hhf.ServerAPI;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = ServerAPI.class)
public class QuickstartServerImpl implements ServerAPI{
    @Override
    public String sendMessage(String message) {
        System.out.println();
        return "quickStart-provider-message="+message;
    }
}
