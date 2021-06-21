package com.ball.mapper;

import com.ball.vo.Criteria;
import com.ball.vo.GroupMessageVO;

import java.util.HashMap;
import java.util.List;

public interface GroupMessageMapper {
    public int insertGroupMessage(GroupMessageVO vo);
    public int deleteGroupMessage(HashMap<String,Object> groupHash); //group_id, group_message_id
    //Criteria cri, Long group_id
    public List<GroupMessageVO> readGroupMessage(HashMap<String,Object> messagePageHash); //본인 그룹 메세지만 가져 올 수 있도록
    //public List<GroupMessageVO> readGroupMessageWithPaging(Criteria cri, Long group_id);
    public int getCountByGroupMessage(Long group_id);
}
