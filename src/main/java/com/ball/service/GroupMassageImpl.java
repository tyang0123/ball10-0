package com.ball.service;

import com.ball.mapper.GroupMessageMapper;
import com.ball.vo.GroupMessageVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GroupMassageImpl implements GroupMessageService{
    @Setter(onMethod_=@Autowired)
    private GroupMessageMapper mapper;

    @Override
    public int groupMessageInsert(GroupMessageVO vo) {
        return mapper.insertGroupMessage(vo);
    }

    @Override
    public List<GroupMessageVO> groupMessageRead(Long group_id) {
        return mapper.readGroupMessage(group_id);
    }

    @Override
    public int groupMessageDelete(HashMap<String, Object> groupHash) {
        return mapper.deleteGroupMessage(groupHash);
    }
}
