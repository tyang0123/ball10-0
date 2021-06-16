package com.ball.controller;


import com.ball.service.GroupService;
import com.ball.vo.Criteria;
import lombok.AllArgsConstructor;
import lombok.Setter;
import com.ball.service.GroupMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/group/*")
@Slf4j
@AllArgsConstructor
public class GroupController {

//    private GroupMessageService messageService;
    private GroupService groupService;

    @GetMapping("/list")
    public String group(Model model) {
//        model.addAttribute("list",messageService.groupMessageRead(1L));
        return "group/groupList";
    }
    @GetMapping("/read")
    public void get(@RequestParam("group_id") Long group_id, Model model, @ModelAttribute("cri")Criteria cri){
        System.out.println("게시글 컨트롤러에서 데이터 하나 조회 / "+ group_id);
        model.addAttribute("group_table", groupService.get(group_id));
    }
}
    private GroupMessageService messageService;

    @GetMapping("/list")
    public String group(Model model) {
        model.addAttribute("list",messageService.groupMessageRead(1L));
        return "group/groupList";
    }
}
