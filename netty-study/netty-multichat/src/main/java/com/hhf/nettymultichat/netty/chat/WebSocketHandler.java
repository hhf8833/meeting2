package com.hhf.nettymultichat.netty.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class WebSocketHandler extends SimpleChannelInboundHandler<HttpObject> {
    WebSocketServerHandshaker socketServerHandshaker;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest){
            FullHttpRequest request = (FullHttpRequest)msg;
            if (!request.decoderResult().isSuccess() || !"websocket".equals(request.headers().get("Upgrade"))){
                DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
                if(httpResponse.status().code() != 200){
                    ByteBuf buf = Unpooled.copiedBuffer("请求异常", CharsetUtil.UTF_8);
                    httpResponse.content().writeBytes(buf);
                    buf.release();
                }
                ctx.writeAndFlush(httpResponse);
                return;
            }
            WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory("ws://localhost:8888/websocket", null, false);
             socketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(request);
             if (socketServerHandshaker ==null){
                 WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
             }else {
                 socketServerHandshaker.handshake(ctx.channel(),request);
             }
        }else if (msg instanceof WebSocketFrame){
            if (msg instanceof TextWebSocketFrame){
                //是content类型就传回
                ByteBuf content = ((TextWebSocketFrame) msg).content();
                ctx.writeAndFlush(content);
            }
            if (msg instanceof CloseWebSocketFrame){

                socketServerHandshaker.close(ctx.channel(),(CloseWebSocketFrame)msg);

            }
           // ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        }
    }
}
