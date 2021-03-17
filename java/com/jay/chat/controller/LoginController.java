package com.jay.chat.controller;

import com.jay.chat.exception.UserNotFoundException;
import com.jay.chat.exception.WrongPasswordException;
import com.jay.chat.model.CommonResult;
import com.jay.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public CommonResult login(String username, String password, HttpServletResponse response){
        try{
            String token = userService.login(username, password);
            response.addHeader("token", token);
            return new CommonResult<String>(200, "登陆成功", token);
        } catch (UserNotFoundException e) {
            return new CommonResult(403, "用户不存在");
        } catch (WrongPasswordException e) {
            return new CommonResult(403, "密码错误");
        }
    }
}
