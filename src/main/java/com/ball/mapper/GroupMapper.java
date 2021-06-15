package com.ball.mapper;

import com.ball.vo.GroupVO;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper {
    public void insertGroup(GroupVO vo);
    public GroupVO read(@Param("group_name") String group_name);
}
