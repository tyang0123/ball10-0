package com.ball.controller;

import com.ball.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
@RequestMapping("/notice/*")
@AllArgsConstructor
public class NoticeController {

    private NoticeService service;

    @GetMapping("/list")
    public String list(Model model){
        System.out.println("/notice/list 로 들어왔습니다.");
        model.addAttribute("list",service.readListNotice());
        return "notice/list";
    }
}
