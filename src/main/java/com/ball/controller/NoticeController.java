package com.ball.controller;

import com.ball.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j
@RequestMapping("/notice/*")
@AllArgsConstructor
public class NoticeController {

    private NoticeService service;

    @GetMapping("/list")
    //userCookie(userId), HttpSession
    public String list(@CookieValue(name = "userCookie", required = false) Cookie userId,HttpSession session, Model model){
        //세션에서 userID 값 가져오기
        String userID = String.valueOf(session.getAttribute("userID"));
        System.out.println("session에서 가져온 값 : "+userID);
//        System.out.println("cookei에서 가져온 값 : "+userId.getValue());
        model.addAttribute("userID",userID);
        model.addAttribute("list",service.readListNotice());
        return "notice/list";
    }
}
