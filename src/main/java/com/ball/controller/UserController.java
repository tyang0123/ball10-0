package com.ball.controller;

import com.ball.service.TimerService;
import com.ball.service.UserService;
import com.ball.vo.TimerVO;
import com.ball.vo.UserVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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
    public String loginPost(UserVO userVO, boolean user_checked
            , RedirectAttributes rAttr, HttpSession session
            ,@CookieValue(name = "userCookie", required = false) Cookie userCookie
            ,@CookieValue(name = "JSESSIONID", required = false) Cookie JSESSIONID
            , HttpServletResponse res){
        log.info("login post..............................."+user_checked);
        //login-password DB check
        boolean checkLoginResult = userService.userLoginCheck(userVO.getUser_id(), userVO.getUser_password());

        if(! checkLoginResult){// id, password 불일치시 다시 login
            log.info("...........redirect fail");
            rAttr.addFlashAttribute("errorMessage", "fail");
            return "redirect:/user/login";
        }


        //add user info to session
        session.setAttribute("userID", userVO.getUser_id());
        if(user_checked) { //로그인 상태 유지하면 userid를 쿠키에 저장함
            //보안을 위해 추후에 db에 세션ID와 userid를 DB에 저장하여 DB에서도 일치하는 여부를 따져봐야함
            //add user cookie

            userCookie = new Cookie("userCookie", userVO.getUser_id());
            userCookie.setMaxAge(60*60*24*365*10); //set cookie 10 years
            userCookie.setSecure(true);
            res.addCookie(userCookie);

            //session id를 cookie에 저장(브라우저 종료후에도 유지되게)
            JSESSIONID.setMaxAge(0);
            JSESSIONID = new Cookie("JSESSIONID",session.getId());
            JSESSIONID.setPath("/");
            JSESSIONID.setMaxAge(60*60*24*180);
            JSESSIONID.setHttpOnly(true);
            JSESSIONID.setSecure(true);
            res.addCookie(JSESSIONID);

            session.setMaxInactiveInterval(60*60*24*180); // session은 6개월로  기본 세션 시간은 24시간 (web.xml 에 기술함)
        }

        rAttr.addFlashAttribute("successLogin", "success");
        return "redirect:/user/login";
    }

    @GetMapping("/create")
    public String temp(){
        return "user/create";
    }

    @GetMapping("/user")
    public String userGet(HttpServletResponse response, HttpServletRequest request
            , @CookieValue(name = "timerCookie", required = false) Cookie timerCookie
            , Model model){

        String userID = String.valueOf(request.getSession().getAttribute("userID"));
        //add timer cookie
        if(timerCookie == null) {
            timerCookie = new Cookie("timerCookie", "00:00:00");
        }
        timerCookie.setMaxAge(remainSecondsFrom3AM());
        timerCookie.setSecure(false);
        TimerVO timerVO = timerService.addNewTimerToDataBaseIfNotExist(userID);
        if(timerVO != null && timerVO.getTimer_accumulated_day() != null){
            System.out.println("get TimerVO from DB: "+timerVO);
            timerCookie.setValue(timerVO.getTimer_accumulated_day().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
        response.addCookie(timerCookie);

        // user nickname DB에서 가져오기
        model.addAttribute("user_nickname",userService.getUserNickname(userID));



        return "user/user";
    }
}
