package com.ball.service;

import com.ball.vo.UserVO;

public interface UserService {
    //login check
    public boolean userLoginCheck(String userId, String userPassword);
}
