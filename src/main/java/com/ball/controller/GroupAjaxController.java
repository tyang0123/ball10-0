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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/group/ajax/")
@Slf4j
public class GroupAjaxController {

    @Setter(onMethod_ = @Autowired)
    private GroupMessageService messageService;

    @PostMapping("/new")
    public ResponseEntity<String> insert(@RequestBody GroupMessageVO vo){
        System.out.println("들어오는지 확인");
        log.info("ReplyVO: "+vo);

        int insertCount = messageService.groupMessageInsert(vo);

        return insertCount == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //메세지(댓글) 목록 확인
    @GetMapping("/list")
    public ResponseEntity<List<GroupMessageVO>> readMessage(@RequestBody Long group_id){
        System.out.println("메세지 목록 확인");

        return new ResponseEntity<>(messageService.groupMessageRead(1L), HttpStatus.OK);
    }

    //메세지(댓글) 삭제
    @DeleteMapping(value = "/delete/{group_message_id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("group_message_id") Long group_message_id, String user_id){
        System.out.println("메세지 삭제 확인 : "+group_message_id + user_id);
        HashMap<String,Object> groupHash = new HashMap<>();

        groupHash.put("user_id",user_id);
        groupHash.put("group_message_id",group_message_id);

        return messageService.groupMessageDelete(groupHash) == 1
                ? new ResponseEntity<>("success",HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
