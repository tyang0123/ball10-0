package com.ball.service;

import com.ball.vo.Criteria;
import com.ball.vo.GroupJoinVO;
import com.ball.vo.GroupVO;

import java.util.List;

public interface GroupService {
    public GroupVO register (GroupVO group, String user_id);   //insert
    public GroupVO oneRead(Long group_id);        //하나만 조회
    public List<GroupVO> allRead(Criteria cri);    //전체 가져오기
    public void modify (GroupVO group);     //수정
    public int remove(Long group_id);      //삭제

    public void joinGroup(GroupJoinVO join);
    public List<GroupJoinVO> joinAllRead(Long group_id);



}
