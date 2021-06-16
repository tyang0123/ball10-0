package com.ball.controller;

import com.ball.service.GroupMessageService;
import com.ball.vo.GroupMessageVO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/group/ajax/*")
@Slf4j
public class GroupAjaxController {

    @Setter(onMethod_ = @Autowired)
    private GroupMessageService messageService;

    @PostMapping(value = "/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.TEXT_PLAIN_VALUE} )
    public ResponseEntity<String> insert(@RequestBody GroupMessageVO vo){
        System.out.println("들어오는지 확인");
        log.info("ReplyVO: "+vo);

//        int insertCount = messageService.groupMessageInsert(vo);
//
//        return insertCount == 1
//                ? new ResponseEntity<>("success", HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return null;
    }

}
