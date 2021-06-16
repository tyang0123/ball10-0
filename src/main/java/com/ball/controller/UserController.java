package com.ball.controller;

import com.ball.service.TimerService;
import com.ball.service.UserService;
import com.ball.vo.TimerVO;
import com.ball.vo.UserVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Controller
@Slf4j
@RequestMapping("/user/*")
public class UserController {
    @Setter(onMethod_= @Autowired)
    private UserService userService;

    @Setter(onMethod_=@Autowired )
    private TimerService timerService;

    @GetMapping("/login")
    public String loginGet(){
        log.info("login get...............................");
        return "user/login";
    }

    private int remainSecondsFrom3AM(){
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();

        if(cal.get(Calendar.HOUR_OF_DAY)>3) {
            cal.add(Calendar.DATE, 1);
        }
        cal.set(Calendar.HOUR_OF_DAY, 3);
        Date tomorrowDawn = cal.getTime();

        long diff = tomorrowDawn.getTime() - now.getTime();
        return (int)(diff/1000);
    }

    @PostMapping("/login")
    public String loginPost(HttpSession session, UserVO userVO, RedirectAttributes rAttr
                    , @CookieValue(name = "timerCookie", required = false) Cookie timerCookie
                    , HttpServletResponse res){
        log.info("register post...............................");
        //login-password DB check
        boolean checkLoginResult = userService.userLoginCheck(userVO.getUser_id(), userVO.getUser_password());
        if(checkLoginResult){ // login-password corresponded DB
            //add user info session
            session.setAttribute("userID", userVO.getUser_id());

            //add cookie
            if(timerCookie == null) {
                timerCookie = new Cookie("timerCookie", "0");
                timerCookie.setMaxAge(remainSecondsFrom3AM());
                timerCookie.setSecure(false);
                res.addCookie(timerCookie);
                TimerVO timerVO = timerService.addNewTimerToDataBaseIfNotExist(userVO.getUser_id());
                if(timerVO.getTimer_accumulated_day() != null){
                    timerCookie.setValue(timerVO.getTimer_accumulated_day().toString());
                }
            }

            System.out.println("=============="+timerCookie);
            return "redirect:/user/user";
        }

        rAttr.addFlashAttribute("errorMessage", "fail");
        return "redirect:/user/login";
    }

    @GetMapping("/create")
    public String temp(){
        return "redirect:/";
    }

    @GetMapping("/user")
    public void userGet(){

    }
}
