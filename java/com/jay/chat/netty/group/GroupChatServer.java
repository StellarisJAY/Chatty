package com.jay.chat.netty.group;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GroupChatServer {

    private static int port;
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public GroupChatServer(){
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup(10);
    }

    @PostConstruct
    public void start(){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new GroupChatServerInitializer());

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("群聊服务器启动成功，绑定端口：" + port);
        } catch (InterruptedException e) {
            logger.error("群聊服务器启动失败");
            logger.error(e.getLocalizedMessage());
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
