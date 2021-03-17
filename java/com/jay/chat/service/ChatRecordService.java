package com.jay.chat.service;

import com.jay.chat.dao.ChatRecordDAO;
import com.jay.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRecordService {
    @Autowired
    private ChatRecordDAO chatRecordDAO;

    public void insertUnreadMessage(Message message){
        chatRecordDAO.insertUnreadMessage(message);
    }

    public void insertMessage(Message message){
        chatRecordDAO.insertMessage(message);
    }

    public List<Message> getChatRecords(Long userId, Long friendId){
        return chatRecordDAO.getChatRecords(userId, friendId);
    }

    public Integer countUnreadMessages(Long userId, Long friendId){
        return chatRecordDAO.countUnreadMessage(userId, friendId);
    }
}
