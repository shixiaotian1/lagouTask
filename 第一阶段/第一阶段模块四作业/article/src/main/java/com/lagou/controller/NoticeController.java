package com.lagou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {

    @RequestMapping("/index")
    public String toIndexPage(Model model){

        return "client/index.html";
    }
}
