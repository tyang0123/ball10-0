package com.ball.mapper;

import com.ball.vo.AlarmVO;

import java.util.List;

public interface AlarmMapper {
    public void insert(AlarmVO alarm); //알람생성
    public AlarmVO read(Long alarm_message_id); //읽음 처리를 하기위한 하나를 불러온다
    public List<AlarmVO> getList(String user_id); //유저아이디로 그 사람의 알람리스트를 가져온다
    public void update(AlarmVO alarm); //읽음처리
}
