package com.ball.service;

import com.ball.vo.GroupVO;

public interface GroupService {
    public void register (GroupVO group);   //insert
    public GroupVO get(Long group_id);      //조회
    public void modify (GroupVO group);     //수정
    public Long remove(Long group_id);      //삭제


}
