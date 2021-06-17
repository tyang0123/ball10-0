package com.ball.service;

import com.ball.vo.GroupMessageVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupMessageService {
    public int groupMessageInsert(GroupMessageVO vo);
    public List<GroupMessageVO> groupMessageRead(Long group_id);
}
