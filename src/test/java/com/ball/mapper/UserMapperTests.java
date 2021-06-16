package com.ball.mapper;

import com.ball.vo.UserVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserMapperTests {
    @Setter(onMethod_=@Autowired)
    private UserMapper mapper;

    @Test
    public void testCreateUser(){
    }

    @Test
    public void testGetList(){
        mapper.getUserList().forEach(user -> log.info(user));
    }
}
