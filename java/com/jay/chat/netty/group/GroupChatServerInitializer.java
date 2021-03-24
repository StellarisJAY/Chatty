package com.jay.chat.netty.group;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * 群聊服务器，通道初始化
 * @author Jay
 */
public class GroupChatServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 初始化新连接的channel的pipeline
     * @param ch channel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // http编解码器
        pipeline.addLast(new HttpServerCodec());
        // http Aggregator
        pipeline.addLast(new HttpObjectAggregator(8864));
        // 设置websocket连接路径
        pipeline.addLast(new WebSocketServerProtocolHandler("/group"));

        // 群聊转发handler
        pipeline.addLast(null);
    }
}
