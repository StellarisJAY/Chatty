package com.jay.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ChatServer {
    private int port = 8888;
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ChatServer(){
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
    }

    @PostConstruct
    private void start(){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChatServerInitializer());
        try{
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            if(channelFuture.isSuccess()) logger.info("聊天服务器启动成功，绑定端口：" + port);

        } catch (InterruptedException e) {
            logger.error("服务器启动失败，出现异常");
            logger.error(e.getLocalizedMessage());
        }
    }
}
