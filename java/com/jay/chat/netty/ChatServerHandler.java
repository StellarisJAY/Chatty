package com.jay.chat.netty;

import com.alibaba.fastjson.JSON;
import com.jay.chat.common.MessageTypes;
import com.jay.chat.model.Message;
import com.jay.chat.service.ChatRecordService;
import com.jay.chat.util.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String jsonString = msg.text();
        ChatRecordService chatRecordService = (ChatRecordService)SpringUtil.getBean("chatRecordService");
        Message message = JSON.parseObject(jsonString, Message.class);
        // 用户上线，将该用户的channel添加到channelMap
        if(message.getType() == MessageTypes.LOGIN){
            Channel channel = ctx.channel();
            Long userId = message.getSenderId();
            if(userId != null) ChannelMap.addChannel(userId, channel);
        }
        // 用户点对点message，服务器转发到目标channel
        // 如果目标channel不可用，在数据库记录信息为未读信息
        else if(message.getType() == MessageTypes.MESSAGE){
            Long targetId = message.getTargetId();
            NioSocketChannel targetChannel = (NioSocketChannel) ChannelMap.getChannel(targetId);
            if(targetChannel != null){
                targetChannel.writeAndFlush(new TextWebSocketFrame(jsonString));
                chatRecordService.insertMessage(message);
            }
            else{
                chatRecordService.insertUnreadMessage(message);
            }
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        ChannelMap.removeChannelById(ctx.channel().id().asLongText());
        ctx.channel().close();
    }
}
