package com.jay.chat.controller;

import com.jay.chat.model.Message;
import com.jay.chat.service.ChatRecordService;
import com.jay.chat.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/record")
public class ChatRecordController {
    @Autowired
    private ChatRecordService chatRecordService;

    @GetMapping("/all")
    public List<Message> getRecordByIds(@RequestParam(value = "friendId", required = true) Long friendId, HttpServletRequest request){
        Long userId = TokenUtil.getUserId(request.getHeader("token"));
        return chatRecordService.getChatRecords(userId, friendId);
    }

    @GetMapping("/unread")
    public Integer countUnreadMessages(@RequestParam("userId") Long userId, @RequestParam("friendId") Long friendId){
        return chatRecordService.countUnreadMessages(userId, friendId);
    }
}
