package com.jay.chat.netty;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ConcurrentHashMap;

public class ChannelMap {
    private static ConcurrentHashMap<Long, Channel> channelMap = new ConcurrentHashMap<>();

    public static Channel getChannel(Long userId){
        if(channelMap.containsKey(userId)) return channelMap.get(userId);
        return null;
    }

    public static void addChannel(Long userId, Channel channel){
        channelMap.put(userId, channel);
    }

    public static void removeChannel(Long userId){
        if(channelMap.containsKey(userId)) channelMap.remove(userId);
    }

    public static void removeChannelById(String channelId){
        for(Long userId : channelMap.keySet()){
            if(channelMap.get(userId).id().asLongText().equals(channelId)){
                channelMap.remove(userId);
            }
        }
    }
}
