package com.jay.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.jay.chat.*")
public class ChatAppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ChatAppApplication.class, args);
    }

}
