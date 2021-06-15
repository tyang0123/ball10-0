package com.ball.service;

import com.ball.vo.AlarmVO;

import java.util.List;

public interface AlarmService {
    public void register(AlarmVO alarm);
    public AlarmVO get(Long alarm_message_id);
    public List<AlarmVO> getTotal(String user_id);
    public void modify(AlarmVO alarm);
}
