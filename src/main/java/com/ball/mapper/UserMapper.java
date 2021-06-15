package com.ball.mapper;

import com.ball.vo.UserVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    public void createUser(UserVO vo); //회원가입
    public List<UserVO> getUserList();
}
