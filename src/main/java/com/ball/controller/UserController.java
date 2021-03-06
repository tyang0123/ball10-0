package com.ball.controller;

import com.ball.mail.service.MailService;
import com.ball.mail.vo.MailVO;
import com.ball.service.AlarmService;
import com.ball.service.TimerService;
import com.ball.service.UserService;
import com.ball.vo.Criteria;
import com.ball.vo.TimerVO;
import com.ball.vo.UserVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.util.HashMap;

@Controller
@Slf4j
@RequestMapping("/user/*")
public class UserController {
    @Setter(onMethod_=@Autowired)
    private AlarmService alarmService;

    @Setter(onMethod_= @Autowired)
    private UserService userService;

    @Setter(onMethod_=@Autowired )
    private TimerService timerService;

    @Setter(onMethod_=@Autowired )
    private MailService mailService;

    private String adminEmail;
    private String adminEmailPW;

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
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date tomorrowDawn = cal.getTime();

        long diff = tomorrowDawn.getTime() - now.getTime();
        return (int)(diff/1000);
    }

    @PostMapping("/login")
    public String loginPost(UserVO userVO, boolean user_remember
            , RedirectAttributes rAttr, HttpSession session
            ,@CookieValue(name = "userCookie", required = false) Cookie userCookie
            ,@CookieValue(name = "JSESSIONID", required = false) Cookie JSESSIONID
            , HttpServletResponse res){
        log.info("login post..............................."+user_remember);
        //login-password DB check
        boolean checkLoginResult = userService.userLoginCheck(userVO.getUser_id(), userVO.getUser_password());

        if(! checkLoginResult){// id, password ???????????? ?????? login
            log.info("...........redirect fail");
            rAttr.addFlashAttribute("errorMessage", "fail");
            return "redirect:/user/login";
        }
        //add user info to session
        session.setAttribute("userID", userVO.getUser_id());


        //add user cookie
        if(userCookie!=null){
            userCookie.setMaxAge(0);
            res.addCookie(userCookie);
        }
        userCookie = new Cookie("userCookie", userVO.getUser_id());
//        userCookie.setSecure(true); ?????? ?????????????????? ??????????????? ????????? true??? ?????? ??????
        userCookie.setPath("/");
        userCookie.setMaxAge(60*60*24);

        //session id??? cookie??? ??????(???????????? ??????????????? ????????????)
        if(JSESSIONID!=null){
            JSESSIONID.setMaxAge(0);
            res.addCookie(JSESSIONID);
        }
        JSESSIONID = new Cookie("JSESSIONID",session.getId());
        JSESSIONID.setPath("/");
        JSESSIONID.setHttpOnly(true);
        JSESSIONID.setSecure(true);

        if(user_remember) { //????????? ?????? ???????????? userid??? ????????? ?????????
            //????????? ?????? ????????? db??? ??????ID??? userid??? DB??? ???????????? DB????????? ???????????? ????????? ???????????????
            userCookie.setMaxAge(60*60*24*365*10); //set cookie 10 years
            JSESSIONID.setMaxAge(60*60*24*180);

            session.setMaxInactiveInterval(60*60*24*180); // session??? 6?????????  ?????? ?????? ????????? 24?????? (web.xml ??? ?????????)
        }
        res.addCookie(userCookie);
        res.addCookie(JSESSIONID);

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
        log.info("userget........................................................");

        /// alarm ?????? ??????
        String userID = String.valueOf(request.getSession().getAttribute("userID"));
        model.addAttribute("alarmCount",alarmService.alarmCount(userID));
        Long criterionNumber = alarmService.getFirstCriterionNumber(userID);
        model.addAttribute("firstCriterionNumber", criterionNumber==null? 0: criterionNumber+1);
        model.addAttribute("userID",userID);
        model.addAttribute("userJoinGroupList",userService.userJoinGroupList(userID));
        model.addAttribute("nickName",userService.getUserNickname(userID));

        //add timer cookie
        if(timerCookie != null) {
            timerCookie.setMaxAge(0);
        }
        timerCookie = new Cookie("timerCookie", "");
        timerCookie.setMaxAge(remainSecondsFrom3AM());
        timerCookie.setSecure(false);
        timerCookie.setPath("/");
        TimerVO timerVO = timerService.addNewTimerToDataBaseIfNotExist(userID);

        if(timerVO != null && timerVO.getTimer_accumulated_day() != null){ //?????? 2????????? ???????????? timer????????? ?????? ??????
            timerCookie.setValue(timerVO.getTimer_id()+"-"+timerVO.getTimer_is_play()+"-"
                    +timerVO.getTimer_accumulated_day().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }else{ //?????? ?????? ???????????? ????????? ????????? ?????? ??????
            timerCookie.setValue(timerVO.getTimer_id()+"-0-00:00:00");
        }
        response.addCookie(timerCookie);

        return "user/user";
    }


    @GetMapping("/findID")
    public void findIDGet(){}

    @GetMapping("/findPassword")
    public void findPasswordGet(){}

    private boolean prepareSendingAdminEmail(){
        UserVO emailVO = userService.getAdminEmailAndPW();
        if(emailVO == null) return false;
        adminEmail = emailVO.getUser_email();
        adminEmailPW = emailVO.getUser_password();
        mailService.setSendEmailID(adminEmail,adminEmailPW);
        return true;
    }

    @PostMapping("/findID")
    public String findIDPostSendEmailID(String user_email
            , RedirectAttributes rAttr){
        log.info("user findIDPostSendEmailID..................................."+user_email);

        String userID = userService.getUserId(user_email);

        if(userID == null){
            rAttr.addFlashAttribute("sendID", "????????? ???????????? ????????????. ?????? ??? ?????? ???????????? ?????????.");
            return "redirect:/user/findID";
        }

        ///// ???????????? ID?????? ???????????? ?????????
        if(adminEmail == null){
            log.info("admin email Setting..................");
            if(!prepareSendingAdminEmail()){
                rAttr.addFlashAttribute("sendID", "????????? ??????????????????. ??????????????? ??????????????????. 1");
                return "redirect:/user/findID";
            }
        }

        MailVO vo = new MailVO();
        vo.setReceive(user_email);
        vo.setSubject("10-0???????????? ???????????? ID??? ???????????????.");
        vo.setContent("???????????? ID??? < "+userID+" >?????????. 10-0?????? ???????????? ???????????? ????????????.\n https://10-0.imweb.me/");

        if(!mailService.sendEmail(vo)){
            rAttr.addFlashAttribute("sendID", "????????? ??????????????????. ??????????????? ??????????????????. 2");
            return "redirect:/user/findID";
        };

        rAttr.addFlashAttribute("sendID", "???????????? ID??? ?????????????????????.");
        return "redirect:/user/login";
    }

}
