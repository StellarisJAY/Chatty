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

/**
 * 私聊服务器Handler，负责消息转发和消息记录
 * @author Jay
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    // logger
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 通道数据接收、转发
     * @param ctx 上下文
     * @param msg 消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg){
        // 获取消息字符串，前端发送的是json格式
        String jsonString = msg.text();
        // 获取聊天记录业务对象
        ChatRecordService chatRecordService = (ChatRecordService)SpringUtil.getBean("chatRecordService");
        // 解析消息字符串，获得消息对象
        Message message = JSON.parseObject(jsonString, Message.class);
        /*
            根据消息类型做出不同处理
         */
        if(message.getType() == MessageTypes.LOGIN){
            // 用户登录：记录用户channel
            Channel channel = ctx.channel();
            Long userId = message.getSenderId();
            if(userId != null) ChannelMap.addChannel(userId, channel);
            logger.info("用户：" + userId + "，建立连接，地址：" + channel.remoteAddress().toString());
        }
        else if(message.getType() == MessageTypes.MESSAGE){
            // 用户私聊信息转发
            Long targetId = message.getTargetId();
            // 尝试获取目标用户通道
            NioSocketChannel targetChannel = (NioSocketChannel) ChannelMap.getChannel(targetId);
            // 找到目标通道，写入通道，写入聊天记录
            if(targetChannel != null){
                targetChannel.writeAndFlush(new TextWebSocketFrame(jsonString));
                chatRecordService.insertMessage(message);
            }
            // 无目标通道，写入未读信息
            else{
                chatRecordService.insertUnreadMessage(message);
            }
        }
    }

    /**
     * 用户通道关闭处理
     * @param ctx 上下文
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        // 从channel映射表删除channel
        ChannelMap.removeChannelById(ctx.channel().id().asLongText());
        ctx.channel().close();
    }
}
