package com.ball.service;

import com.ball.vo.GroupVO;

import java.util.List;

public interface GroupService {
    public void register (GroupVO group);   //insert
    public GroupVO get(Long group_id);        //하나만 조회
    public List<GroupVO> allRead(GroupVO group);    //전체 가져오기
    public void modify (GroupVO group);     //수정
    public Long remove(Long group_id);      //삭제


}
