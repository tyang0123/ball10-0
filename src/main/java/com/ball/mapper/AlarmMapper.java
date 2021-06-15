package com.ball.mapper;

import com.ball.vo.AlarmVO;

import java.util.List;

public interface AlarmMapper {
    public void insert(AlarmVO alarm);
    public AlarmVO read(Long alarm_message_id);
    public List<AlarmVO> getList(String user_id);
    public void update(AlarmVO alarm);
}
