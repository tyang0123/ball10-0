package com.ball.service;

import com.ball.vo.Criteria;
import com.ball.vo.GroupMessagePageVO;
import com.ball.vo.GroupMessageVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface GroupMessageService {
    public int groupMessageInsert(GroupMessageVO vo);
    public List<GroupMessageVO> groupMessageRead(HashMap<String,Object> messagePageHash);
    public int groupMessageDelete(HashMap<String,Object> groupHash);
    public GroupMessagePageVO getMessageListPage(Criteria cri, Long group_id);
}
