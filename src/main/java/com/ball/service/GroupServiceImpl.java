package com.ball.service;

import com.ball.mapper.GroupMapper;
import com.ball.vo.GroupVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService{
    @Setter(onMethod_=@Autowired)
    private GroupMapper mapper;

    @Override
    public void register(GroupVO group) {
        mapper.insertGroup(group);
    }

    @Override
    public GroupVO get(Long group_id) {
        return mapper.read(group_id);
    }

    @Override
    public void modify(GroupVO group) {
        mapper.update(group);
    }

    @Override
    public Long remove(Long group_id) {
        return null;
    }
}
