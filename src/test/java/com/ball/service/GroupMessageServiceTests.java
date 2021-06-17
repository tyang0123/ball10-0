package com.ball.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class GroupMessageServiceTests {

    @Setter(onMethod_=@Autowired)
    private GroupMessageService service;

    @Test
    public void testGroupMessageRead(){
        System.out.println(service.groupMessageRead(1L));
    }
}
