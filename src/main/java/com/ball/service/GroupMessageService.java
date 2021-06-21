package com.ball.service;

import com.ball.vo.GroupMessageVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface GroupMessageService {
    public int groupMessageInsert(GroupMessageVO vo);
    public List<GroupMessageVO> groupMessageRead(Long group_id);
    public int groupMessageDelete(HashMap<String,Object> groupHash);
}
