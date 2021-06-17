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
    @GetMapping("/message/{group_id}")
    public ResponseEntity<List<GroupMessageVO>> readMessage(@PathVariable("group_id") Long group_id){
        System.out.println("메세지 목록 확인");

        return new ResponseEntity<>(messageService.groupMessageRead(1L), HttpStatus.OK);
    }

    //메세지(댓글) 삭제
    @DeleteMapping("/message/{group_id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("group_id") Long group_id,String user_id, Long group_message_id){
        System.out.println("메세지 삭제 확인");
        HashMap<String,Object> groupHash = new HashMap<>();

        groupHash.put("user_id","user1");
        groupHash.put("group_message_id",21L);

        //추후에 밑에 있는 코드로 변경
//        groupHash.put("user_id",user_id);
//        groupHash.put("group_message_id",group_message_id);
        return messageService.groupMessageDelete(groupHash) == 1
                ? new ResponseEntity<>("success",HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
