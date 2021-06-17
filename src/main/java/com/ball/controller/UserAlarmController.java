package com.ball.controller;

import com.ball.service.AlarmService;
import com.ball.vo.UserVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/ajax/user")
public class UserAlarmController {
    @Setter(onMethod_=@Autowired)
    private AlarmService service;

//    @GetMapping("/user")
//    public void alarm(Model model){//userVo vo
//        model.addAttribute("user","user1");
//    }

    @ResponseBody
    @PostMapping(value = "/alarm") //userVo vo
    public ResponseEntity<HashMap<String, Object>> alarmList(String user) throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        // 게시글 화면 출력
        result.put("list", service.getTotal(user));
        return ResponseEntity.ok(result);
    }

}
