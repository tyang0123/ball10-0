package com.ball.controller;

import com.ball.service.AlarmService;
import com.ball.service.UserService;
import com.ball.vo.Criteria;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ajax/user")
public class UserAlarmController {
    @Setter(onMethod_=@Autowired)
    private AlarmService alarmService;

    @Setter(onMethod_=@Autowired)
    private UserService userService;

//    @GetMapping("/user")
//    public void user(Model model){//userVo vo
//        String userID = "user1";
//        model.addAttribute("nickName","유정짱이야");
//        model.addAttribute("alarmCount",alarmService.alarmCount(userID));
//        model.addAttribute("firstCriterionNumber",alarmService.getFirstCriterionNumber(userID));
//        model.addAttribute("userID",userID);
//        model.addAttribute("userJoinGroupList",userService.userJoinGroupList(userID));
//    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// 아래 - 알람 메세지 관련 부분
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ResponseBody
    @PostMapping (value = "/alarmMessage") //userVo vo
    public ResponseEntity<HashMap<String, Object>> userAlarmList(Long criterionNumber, String userID) throws Exception {
        log.info("user controller userAlarmList...........................................................");
        log.info(criterionNumber+" "+userID);
        Criteria cri = new Criteria(criterionNumber,10);
        HashMap<String, Object> result = new HashMap<>();
        System.out.println(alarmService.getListWithPage(cri,userID));
        // 알람 화면 출력
        result.put("list", alarmService.getListWithPage(cri,userID));
        return ResponseEntity.ok(result);
    }

    @ResponseBody
    @PostMapping (value = "/alarmCount") //userVo vo
    public ResponseEntity<HashMap<String, Object>> userAlarmCount(Long alarmID,String userID) throws Exception {
        HashMap<String, Object> result = new HashMap<>();

        // 새로운알람 읽음 처리 및 데이터 베이스 저장
        alarmService.modify(alarmID);
        result.put("alarmCount", alarmService.alarmCount(userID));
        return ResponseEntity.ok(result);
    }

}
