package com.jay.chat.service;

import com.jay.chat.dao.UserDAO;
import com.jay.chat.exception.UserNotFoundException;
import com.jay.chat.exception.WrongPasswordException;
import com.jay.chat.model.User;
import com.jay.chat.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public String getUsername(Integer userId){
        return userDAO.getUsername(userId);
    }

    public List<Integer> getUserGroups(Integer userId){
        return userDAO.getUserGroups(userId);
    }

    public String login(String username, String password) throws UserNotFoundException, WrongPasswordException {
        User user = userDAO.getUserByName(username);
        // 检查用户是否存在
        if(user == null) throw new UserNotFoundException();
        // 检查密码是否正确
        if(!user.getPassword().equals(password)) throw new WrongPasswordException();
        String token = TokenUtil.createToken(user);
        return token;
    }

}
