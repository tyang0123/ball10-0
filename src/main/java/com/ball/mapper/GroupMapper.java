package com.ball.mapper;

import com.ball.vo.Criteria;
import com.ball.vo.GroupVO;

import java.util.List;

public interface GroupMapper {
    public int insertGroup(GroupVO vo); //그룹 데이터 생성
    public List<GroupVO> selectGroupList(Criteria cri); // 그룹 리스트 조회
}
