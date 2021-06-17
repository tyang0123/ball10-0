package com.ball.controller;

import com.ball.service.AlarmService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
    @Setter(onMethod_=@Autowired)
    private AlarmService service;

    @RequestMapping("/")
    public String home(){
        return "sample";
    }

    @GetMapping("/alarm")
    public String alarm(Model model){
        System.out.println("알람들어오나"+service.getTotal("user1"));
        model.addAttribute("list",service.getTotal("user1"));
        return "sample1";
    }



}

