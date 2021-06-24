package com.ball.controller;

import com.ball.service.GroupService;
import com.ball.vo.GroupVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Controller
@RequestMapping("/group/list/ajax/*")
@Slf4j
public class GroupAjaxController {
    @Setter(onMethod_=@Autowired)
    private GroupService groupService;

    @GetMapping(value = "/list")
    public void passwordCheck (@PathVariable("group_id") Long group_id, Model model){
        System.out.println("아작스 컨트롤러에 진입이 되나");
        model.addAttribute("check",groupService.passwordCheck(group_id));

//        Long groupID = group_id;
//        model.addAttribute("group",groupService.passwordCheck(group_id));
//        model.addAttribute("groupID",groupID);

    }



}
