package com.ball.service;

import com.ball.mapper.GroupMessageMapper;
import com.ball.vo.Criteria;
import com.ball.vo.GroupMessagePageVO;
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
    public List<GroupMessageVO> groupMessageRead(HashMap<String, Object> messagePageHash) {
        return mapper.readGroupMessage(messagePageHash);
    }

    @Override
    public int groupMessageDelete(HashMap<String, Object> groupHash) {
        return mapper.deleteGroupMessage(groupHash);
    }

    @Override
    public GroupMessagePageVO getMessageListPage(Criteria cri, Long group_id) {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("criterionNumber",cri.getCriterionNumber());
        hashMap.put("group_id",group_id);
        return new GroupMessagePageVO(
                mapper.getCountByGroupMessage(group_id),
                mapper.readGroupMessage(hashMap)
        );
    }
}
