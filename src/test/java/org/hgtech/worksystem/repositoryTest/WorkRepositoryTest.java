package org.hgtech.worksystem.repositoryTest;

import org.hgtech.worksystem.domain.WorkVO;
import org.hgtech.worksystem.repository.WorkRepository;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class WorkRepositoryTest {

    @Autowired
    WorkRepository repository;

    public WorkVO createVO() {
//      VO 객체 생성 
        WorkVO vo = WorkVO.builder()
                .wkId(createRandomNumber())
                .wkRegDate(createRandomDatetime())
                .wkModDate(createRandomDatetime())
                .wkRepDate(createRandomDatetime())
                .wkTtl(createRandomString())
                .wkDsc(createRandomString())
                .wkDueDate(createRandomDatetime())
                .wkTag(createRandomString())
                .wkImp(createRandomString())
                .wkUser(createRandomNumber())
                .wkResUser(createRandomNumber())
                .wkParent(createRandomNumber())
                .wkRank(createRandomNumber())
                .build();
        return vo;
    }


    public String createRandomString() {
        return UUID.randomUUID().toString();
    }

    public int createRandomNumber() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public LocalDateTime createRandomDatetime() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    public LocalDate createRandomDate() {
        LocalDate now = LocalDate.now();
        return now;
    }


    @Test
    public void insertTest() {
        int result = repository.insert(createVO());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void selectLastTest() {
        repository.insert(createVO());
        System.out.println(repository.selectLast());
    }

    @Test
    public void selectAllTest () {
        repository.insert(createVO());
        repository.insert(createVO());
        System.out.println(repository.selectAll());
    }

    @Test
    public void selectByIdTest () {
        repository.insert(createVO());
//      id 속성 이름 확인 필요
        Assertions.assertEquals(repository.selectLast().getWkId(), repository.selectByWkId(repository.selectLast().getWkId()).getWkId());
    }

    @Test
    public void selectByParentIdTest () {
        repository.insert(createVO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(repository.selectLast().getWkParent(), repository.selectByParent(repository.selectLast().getWkParent()).get(0).getWkParent());
    }

    @Test
    public void deleteTest() {
        repository.insert(createVO());
        int result = repository.delete(repository.selectLast().getWkId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        repository.insert(createVO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = repository.selectLast().getWkTtl();
        WorkVO vo = repository.selectLast();
        vo.setWkTtl(createRandomString());
        repository.update(vo);
        Assertions.assertNotEquals(input, repository.selectLast().getWkTtl());
    }

}
