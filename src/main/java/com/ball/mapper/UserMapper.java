package com.ball.mapper;

import com.ball.vo.UserVO;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
    public int insertUser(UserVO vo);//유저 데이터 생성(회원등록)
    
    //유저 1명 정보 조회, 로그인시 사용
    public UserVO selectByIdAndPassword(@Param("user_id") String userId, @Param("user_password") String userPassword);

    //유저 정보 수정
    public int updateUser(UserVO vo);
}
