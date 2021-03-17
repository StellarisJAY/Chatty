package com.jay.chat.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("用户不存在");
    }
}
