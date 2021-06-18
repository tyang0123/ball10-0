package com.ball.controller;

import com.ball.service.GroupMessageService;
import com.ball.vo.GroupMessageVO;
import com.ball.vo.TimerVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/ajax/timer/*")
@Slf4j
public class TimerAjaxController {


    @PutMapping(value = "/put"
        ,consumes = "application/json; charset=UTF-8;"
        , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void insert(@RequestBody TimerVO vo){
        log.info("TimerVO: "+vo.toString());

//        int insertCount = messageService.groupMessageInsert(vo);
        int insertCount = 1;
        return ;
    }

    @PostMapping(value = "/post"
        ,consumes = "application/json; charset=UTF-8;"
        , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void test(@RequestParam("timer_date") @JsonFormat(pattern = "YYYY-MM-DDTHH:mm:ss") LocalDateTime timer_date){
        log.info(timer_date.toString());

    }

    @PutMapping(value = "/newput"
            ,consumes = "application/json; charset=UTF-8;"
            , produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void test2(@RequestBody String str){
        log.info(str);
    }
}
