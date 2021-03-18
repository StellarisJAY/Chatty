package com.jay.chat.netty;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户id与channel映射表
 * 用于记录每个用户的websocket通道
 * @author Jay
 */
public class ChannelMap {
    // 因为多线程访问，所以使用线程安全的hashmap
    private static ConcurrentHashMap<Long, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 通过id获取通道
     * @param userId 用户id
     * @return 用户channel
     */
    public static Channel getChannel(Long userId){
        if(channelMap.containsKey(userId)) return channelMap.get(userId);
        return null;
    }

    /**
     * 添加用户通道
     * @param userId 用户id
     * @param channel 通道
     */
    public static void addChannel(Long userId, Channel channel){
        channelMap.put(userId, channel);
    }

    /**
     * 根据channel id删除通道
     * @param channelId 通道id
     */
    public static void removeChannelById(String channelId){
        for(Long userId : channelMap.keySet()){
            if(channelMap.get(userId).id().asLongText().equals(channelId)){
                channelMap.remove(userId);
            }
        }
    }
}
