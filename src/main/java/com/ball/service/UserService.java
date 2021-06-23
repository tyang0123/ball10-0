package com.ball.service;

import com.ball.vo.GroupVO;
import com.ball.vo.UserVO;

import java.util.List;

public interface UserService {
    //login check
    public boolean userLoginCheck(String userId, String userPassword);

    //get user nickname
    public String getUserNickname(String userID);

    //유저가 가입한 그룹 조회
    public List<GroupVO> userJoinGroupList(String userID);
}