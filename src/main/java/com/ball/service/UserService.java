package com.ball.service;

import com.ball.vo.UserVO;

public interface UserService {
    //login check
    public boolean userLoginCheck(String userId, String userPassword);

    //get user nickname
    public String getUserNickname(String userID);

    //get userID by email
    public String getUserId(String userEmail);

    //get Admin Email and password
    public UserVO getAdminEmailAndPW();
}
