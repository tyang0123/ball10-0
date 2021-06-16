package com.ball.controller;

import com.ball.service.GroupMessageService;
import com.ball.vo.GroupMessageVO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/group/*")
@Slf4j
public class GroupController {

    @Setter(onMethod_ = @Autowired)
    private GroupMessageService messageService;

    @GetMapping("/list")
    public String group(Model model) {
        model.addAttribute("list",messageService.groupMessageRead(1L));
        return "group/groupList";
    }
}
