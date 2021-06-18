package com.ball.controller;

import com.ball.service.AlarmService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/user")
public class UserAlarmController {
    @Setter(onMethod_=@Autowired)
    private AlarmService alarmService;

    @GetMapping("/user")
    public void user(Model model){//userVo vo
        String userID = "user1";
        model.addAttribute("alarmCount",alarmService.alarmCount(userID));
        model.addAttribute("userID",userID);
    }

    @ResponseBody
    @PostMapping (value = "/alarm") //userVo vo
    public ResponseEntity<HashMap<String, Object>> userAlarmList(String userID) throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        // 알람 화면 출력
        result.put("list", alarmService.getTotal(userID));
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
