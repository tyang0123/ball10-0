package com.ball.mapper;

import com.ball.vo.Criteria;
import com.ball.vo.GroupMessageVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class GroupMessageMapperTests {
    @Setter(onMethod_= @Autowired)
    private GroupMessageMapper mapper;

    @Test
    public void testAutowired(){
        System.out.println(mapper);
    }

    @Test
    public void testInsertGroupMessage(){
        GroupMessageVO vo = new GroupMessageVO();

        vo.setGroup_id(1L);
        vo.setUser_id("user1");
        vo.setGroup_message_content("테스트로 넣습니다.");

        System.out.println(mapper.insertGroupMessage(vo));
    }

    @Test
    public void testReadGroupMessage(){
        Criteria cri = new Criteria();
        cri.setCriterionNumber(48L);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("criterionNumber",cri.getCriterionNumber());
        hashMap.put("group_id",1L);
        mapper.readGroupMessage(hashMap);
        System.out.println(mapper.readGroupMessage(hashMap));
    }

    @Test
    public void testDeleteGroupMessage(){
        HashMap<String, Object> groupHash = new HashMap<String, Object>();
        groupHash.put("user_id","user1");
        groupHash.put("group_message_id",19L);
        mapper.deleteGroupMessage(groupHash);
    }
}
