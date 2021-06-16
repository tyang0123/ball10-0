package com.ball.service;

import com.ball.mapper.UserMapper;
import com.ball.vo.UserVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Setter(onMethod_=@Autowired)
    private UserMapper userMapper;

    @Override
    public boolean userLoginCheck(String userId, String userPassword) {
        UserVO vo = userMapper.selectByIdAndPassword(userId, userPassword);
        if(vo != null)
            return true;
        return false;
    }
}
