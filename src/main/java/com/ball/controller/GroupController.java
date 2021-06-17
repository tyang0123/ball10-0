package com.ball.controller;

import com.ball.service.GroupMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group/*")
@Slf4j
@AllArgsConstructor
public class GroupController {

    private GroupMessageService messageService;

    @GetMapping("/list")
    public String group(Model model) {
        model.addAttribute("list",messageService.groupMessageRead(1L));
        return "group/groupList";
    }
}
