package com.ball.service;

import com.ball.mapper.GroupMapper;
import com.ball.vo.Criteria;
import com.ball.vo.GroupJoinVO;
import com.ball.vo.GroupVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Setter(onMethod_=@Autowired)
    private GroupMapper mapper;

    @Transactional
    @Override
    public GroupVO register(GroupVO group, String user_id) {
        mapper.insertGroup(group);
        GroupJoinVO vo = new GroupJoinVO();
        vo.setUser_id(user_id);
        vo.setGroup_id(group.getGroup_id());
        mapper.joinGroup(vo);
        return group;
    }

    @Override
    public GroupVO oneRead(Long group_id) {
        return mapper.groupRead(group_id);
    }

    @Override
    public List<GroupVO> allRead(Criteria cri) {
        return mapper.selectGroupList(cri);
    }

    @Override
    public void modify(GroupVO group) {
        mapper.groupUpdate(group);
    }

    @Transactional
    @Override
    public int remove(Long group_id) {
        mapper.joinDelete(group_id);
        return mapper.groupDelete(group_id);
    }

    @Override
    public void joinGroup(GroupJoinVO join) {
        mapper.joinGroup(join);
    }

    @Override
    public int joinAllRead(Long group_id, String user_id) {

        return mapper.joinAllRead(group_id, user_id);
    }

//    public boolean groupCheck(Long group_id){
//
//        for(GroupJoinVO vo : mapper.joinAllRead(group_id)){
//            System.out.println("===================");
//            if(vo.getGroup_id() == group_id){
//                return false;
//            }
//        }
//        return true;
//    }

}
