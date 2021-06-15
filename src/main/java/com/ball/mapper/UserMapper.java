package com.ball.mapper;

import com.ball.vo.UserVO;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
    public void insertUser(UserVO vo);

    public UserVO selectByIdAndPassword(@Param("user_id") String userId, @Param("user_password") String userPassword);

    public int updateUser(UserVO vo);
}
