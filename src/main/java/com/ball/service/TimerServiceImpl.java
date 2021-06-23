package com.ball.service;

import com.ball.mapper.TimerMapper;
import com.ball.vo.TimerVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TimerServiceImpl implements TimerService{
    @Setter(onMethod_= @Autowired)
    private TimerMapper timerMapper;

    @Transactional
    @Override
    public TimerVO addNewTimerToDataBaseIfNotExist(String user_id) {
        TimerVO timerVO = new TimerVO();
        timerVO.setUser_id(user_id);

        TimerVO readVO = timerMapper.selectTodayTimer(timerVO);
        if(readVO != null){
            log.info(readVO.toString());
            return readVO;
        }

        timerMapper.insertTodayTimer(timerVO);
        return timerVO;
    }

    @Override
    public int modifyTimerAccumulatedDayTime(TimerVO vo) {
        return timerMapper.updateAccumulatedTime(vo);
    }


}