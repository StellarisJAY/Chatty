package com.jay.chat.netty.group;

import com.alibaba.fastjson.JSON;
import com.jay.chat.common.MessageTypes;
import com.jay.chat.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        String text = msg.toString();
        Message message = JSON.parseObject(text, Message.class);

        if(message.getType() == MessageTypes.LOGIN){

        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
