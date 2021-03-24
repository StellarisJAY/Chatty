package com.jay.chat.netty.group;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

public class GroupChannelMap {

    private static ConcurrentHashMap<Long, ChannelGroup> map = new ConcurrentHashMap<>();

    public static ChannelGroup getGroup(Long groupId){
        return map.containsKey(groupId) ? map.get(groupId) : null;
    }

    public static void addChannel(Long groupId, Channel channel){
        if(map.containsKey(groupId)){
            ChannelGroup channels = map.get(groupId);
            channels.add(channel);
        }
        else{
            DefaultChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            channels.add(channel);
            map.put(groupId, channels);
        }
    }

    public static void removeChannel(Channel channel){
        for(Long key : map.keySet()){
            ChannelGroup channels = map.get(key);

            channels.remove(channel);
        }
    }

    public static void writeAndFlush(Long groupId, WebSocketFrame frame){
        if(map.containsKey(groupId)){
            ChannelGroup channels = map.get(groupId);
            channels.forEach(channel -> {
                channel.writeAndFlush(frame);
            });
        }
    }
}
