package com.jay.chat.model;


public class Message {
    private Long messageId;
    private Long senderId;
    private Long targetId;
    private Integer type;
    private String content;
    private Long timestamp;

    public Message(Long messageId, Long senderId, Long targetId, Integer type, String content, Long timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.targetId = targetId;
        this.type = type;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
