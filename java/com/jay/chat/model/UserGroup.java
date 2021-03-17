package com.jay.chat.model;

import io.netty.channel.Channel;

import java.util.List;

public class UserGroup {
    private Integer groupId;
    private Integer creatorId;
    private String name;
    private Integer capacity;
    private List<Channel> channels;

    public UserGroup(){

    }

    public UserGroup(Integer groupId, Integer creatorId, String name, Integer capacity) {
        this.groupId = groupId;
        this.creatorId = creatorId;
        this.name = name;
        this.capacity = capacity;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
