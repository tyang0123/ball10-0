package com.ball.mapper;

import com.ball.vo.GroupMessageVO;

import java.util.HashMap;
import java.util.List;

public interface GroupMessageMapper {
    public int insertGroupMessage(GroupMessageVO vo);
    public int deleteGroupMessage(HashMap<String,Object> groupHash); //user_id, group_message_id
    public List<GroupMessageVO> readGroupMessage(Long group_id); //본인 그룹 메세지만 가져 올 수 있도록
}
