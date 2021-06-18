package com.ball.mapper;

import com.ball.vo.Criteria;
import com.ball.vo.GroupMessageVO;
import com.ball.vo.GroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    public void insertGroup(GroupVO vo);
    public GroupVO readGroup(Long group_id); //하나만
    public List<GroupVO> selectGroupList(Criteria cri); // 그룹 리스트 조회
    public int updateGroup(GroupVO vo);

    public int getTotalCount(Criteria cri);
}
