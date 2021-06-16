package com.ball.service;

import com.ball.vo.TimerVO;

public interface TimerService {
    //NewTimer 생성 (쿠키가 생성될때)
    public TimerVO addNewTimerToDataBaseIfNotExist(String user_id);
}
