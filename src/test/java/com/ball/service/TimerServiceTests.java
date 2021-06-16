package com.ball.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TimerServiceTests {

    @Test
    public void timeTest(){

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DATE, 30);
        System.out.println(cal);
        cal.add(Calendar.DATE, 1);
        System.out.println(cal);

        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.HOUR));
        Date now = cal.getTime();

        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 3);
        Date tomorrowDawn = cal.getTime();

        long diff = tomorrowDawn.getTime() - now.getTime();
        System.out.println(diff/(1000*60*60));
//        System.out.println( cal.get(Calendar.DATE)+" "+cal.get(Calendar.HOUR) +" "+cal.get(Calendar.MINUTE));

    }
}
