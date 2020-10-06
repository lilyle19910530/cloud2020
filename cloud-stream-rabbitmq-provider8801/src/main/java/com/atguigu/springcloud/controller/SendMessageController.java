package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMassageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private IMassageProvider massageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return massageProvider.send();
    }
}
