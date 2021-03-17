package com.jay.chat.exception;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super("密码错误");
    }
}
