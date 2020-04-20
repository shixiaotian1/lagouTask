package com.lagou.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class NoticeController {

    @GetMapping("/test")
    public String test(){
        return "Hello,test!";
    }
}
