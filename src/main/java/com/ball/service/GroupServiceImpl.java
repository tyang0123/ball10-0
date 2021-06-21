package com.ball.service;

import com.ball.mapper.GroupMapper;
import com.ball.vo.Criteria;
import com.ball.vo.GroupVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return mapper.readGroup(group_id);
    }

    @Override
    public List<GroupVO> allRead(Criteria cri) {
        return mapper.selectGroupList(cri);
    }

    @Override
    public void modify(GroupVO group) {
        mapper.updateGroup(group);
    }

    @Override
    public int remove(Long group_id) {
        return mapper.groupDelete(group_id);
    }

    @Override
    public int getTotal(Criteria cri) {
        System.out.println("서비스에서 총 데이터 갯수는 : "+ cri);
        return 0;
    }
}
