package com.ball.controller;


import com.ball.service.GroupService;
import com.ball.vo.Criteria;
import com.ball.vo.GroupVO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import com.ball.service.GroupMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/group/*")
@Slf4j
@AllArgsConstructor
public class GroupController {

//    private GroupMessageService messageService;
    @Setter(onMethod_=@Autowired)
    private GroupService groupService;

    @Setter(onMethod_=@Autowired)
    private GroupMessageService messageService;

    @GetMapping("/list")
    public String group(Model model) {
        System.out.println("그룹 전체 목록 조회");
        model.addAttribute("list",messageService.groupMessageRead(1L));
        return "group/groupList";
    }

    @GetMapping("/create")
    public String register(){
        System.out.println("그룹 생성 GetMapping에 들어오나");
        return "group/groupCreate";
    }
    @PostMapping("/create")
    public String register(GroupVO group, RedirectAttributes rttr){

        groupService.register(group);
        System.out.println("컨트롤러에 레지스터 값이 들어오나?"+group);
        return "redirect:/group/list";

    }
    @GetMapping({"/modify"})
    //@RequestParam("group_id") Long group_id
    public String get( Model model, @ModelAttribute("cri")Criteria cri){
        System.out.println("게시글 컨트롤러에서 데이터 하나 조회 / ");
        model.addAttribute("group", groupService.get(3L));
        System.out.println("두 번째 모디파이"+groupService.get(3L));
        return "group/groupModify";

    }
    @PostMapping("/modify")
    public String modify(GroupVO group, RedirectAttributes rttr, @ModelAttribute ("cri") Criteria cri){
        System.out.println("컨트롤러에서 수정이 들어오나 : "+ group);
        groupService.modify(group);
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("criterionNumber", cri.getCriterionNumber());
        return "redirect:/group/list";
    }
}
