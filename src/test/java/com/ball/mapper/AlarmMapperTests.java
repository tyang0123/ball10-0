package com.ball.mapper;

import com.ball.vo.AlarmVO;
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
public class AlarmMapperTests {
    @Setter(onMethod_=@Autowired)
    private AlarmMapper mapper;

    @Test
    public void testInsert(){
        AlarmVO vo = new AlarmVO();
        vo.setUser_id("user1");
        vo.setAlarm_message_content("그룹에 가입하세요! 같이 으쌰으쌰");
        vo.setAlarm_message_is_new((byte)1);
        mapper.insert(vo);
    }

    @Test
    public void testRead(){
        AlarmVO vo = mapper.read(100L);
        System.out.println(vo);
    }

    @Test
    public void testGetList(){
        mapper.getList("user1").forEach(i -> System.out.println(i));
    }
    @Test
    public void testUpdate(){
        AlarmVO vo = mapper.read(100L);
        vo.setAlarm_message_is_new((byte)0);
        mapper.update(vo);

    }

}
