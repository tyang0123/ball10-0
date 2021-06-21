package com.ball.service;

import com.ball.vo.Criteria;
import com.ball.vo.GroupMessageVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class GroupMessageServiceTests {

    @Setter(onMethod_=@Autowired)
    private GroupMessageService service;

    @Test
    public void testGroupMessageRead(){
        Criteria cri = new Criteria();
        cri.setCriterionNumber(48L);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("criterionNumber",cri.getCriterionNumber());
        hashMap.put("group_id",1L);
        System.out.println(service.groupMessageRead(hashMap));
    }

    @Test
    public void testGroupMessageInsert(){
        GroupMessageVO vo = new GroupMessageVO();
        vo.setGroup_id(1L);
        vo.setUser_id("user1");
        vo.setGroup_message_content("서비스에서 테스트");
        service.groupMessageInsert(vo);
        System.out.println(vo);
    }

    @Test
    public void testGroupMessageDelete(){
        HashMap<String,Object> groupHash = new HashMap<>();
        groupHash.put("user_id","user1");
        groupHash.put("group_message_id",13L);
        service.groupMessageDelete(groupHash);
    }

    @Test
    public void testCntGroupMessage(){
        Criteria cri = new Criteria();
        cri.setCriterionNumber(48L);
        System.out.println(service.getMessageListPage(cri,1L));
    }
}
