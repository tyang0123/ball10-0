package com.ball.mapper;


import com.ball.vo.Criteria;
import com.ball.vo.GroupVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class GroupMapperTests {

    @Setter(onMethod_ =@Autowired)
    private GroupMapper mapper;

    @Test
    public void testAutowired(){
        System.out.println("-----------------------------");
        System.out.println(mapper);
    }

    @Test
    public void testInsertGroup(){
        GroupVO vo = new GroupVO();
        vo.setUser_id_group_header("testmapper");
        vo.setGroup_name("테스트 그룹 이름");
        vo.setGroup_category("입시");
        vo.setGroup_is_secret(0);
        vo.setGroup_person_count(10);
        vo.setGroup_content("테스트 그룹을 생성합니다. 카테고리는 입시입니다. 테스트합니다. 테스트!!!!!!!!!!!!!!");

        mapper.insertGroup(vo);
    }
    @Test
    public void testread(){
        GroupVO vo = mapper.read("그룹이름1");
        System.out.println("===================");
        System.out.println(vo);

    }

    @Test
    public void testSelectGroupList(){
        Criteria cri = new Criteria();

        for (GroupVO groupVO : mapper.selectGroupList(cri)) {
            System.out.println(groupVO);
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        cri.setCriterionNumber(11L);
        cri.setAmount(5);
        for (GroupVO groupVO : mapper.selectGroupList(cri)) {
            System.out.println(groupVO);
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        cri.setCategory("토익");
        for (GroupVO groupVO : mapper.selectGroupList(cri)) {
            System.out.println(groupVO);
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        cri.setKeyword("1");
        cri.setCategory(null);
        for (GroupVO groupVO : mapper.selectGroupList(cri)) {
            System.out.println(groupVO);
        }
    }
}
