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
    public String group(Long group_id ,Criteria cri, Model model) {
        System.out.println("컨트롤러 그룹 전체 목록 조회");
        model.addAttribute("list",messageService.groupMessageRead(1L));
        model.addAttribute("search", groupService.get(group_id));
        model.addAttribute("group", groupService.allRead(cri));
        System.out.println("컨트롤러에 cri가 들어오나 " +cri);
        int total = groupService.getTotal(cri);
        System.out.println("토탈값이 들어오나 "+total);
//        model.addAttribute("pageMaker", new Criteria(1L, total));
        System.out.println("검색어가 들어오나 "+ cri.getKeyword());
        System.out.println("카테고리가 들어오나 "+ cri.getCategory());


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
        System.out.println("컨트롤러에 레지스터 값이 들어오나?"+group.getGroup_category());
        rttr.addFlashAttribute("result", group.getGroup_id());
        return "redirect:/group/list";

    }
    @GetMapping("/modify")
    //@RequestParam("group_id") Long group_id
    public String get(Long group_id, Model model, @ModelAttribute("cri") Criteria cri){
        System.out.println("게시글 컨트롤러에서 데이터 하나 수정 / ");
        model.addAttribute("group", groupService.get(group_id));
        return "group/groupModify";
    }
    @GetMapping("/read")
    public String read(Long group_id, Model model){
        model.addAttribute("group", groupService.get(group_id));
        model.addAttribute("delete", groupService.remove(group_id));
        return "group/groupRead";
    }
    @PostMapping({"/list","/modify"})
    public String modify(GroupVO group, RedirectAttributes rttr, @ModelAttribute ("cri") Criteria cri){
        System.out.println("컨트롤러에서 수정이 들어오나 : "+ group);
        groupService.modify(group);
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("criterionNumber", cri.getCriterionNumber());
        rttr.addAttribute("category", cri.getCategory());
        rttr.addAttribute("keyword", cri.getKeyword());
        return "redirect:/group/list";
    }
}
