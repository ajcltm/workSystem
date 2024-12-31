package org.hgtech.worksystem.repositoryTest;

import org.hgtech.worksystem.domain.FileVO;
import org.hgtech.worksystem.domain.StakeholderVO;
import org.hgtech.worksystem.repository.FileRepository;
import org.hgtech.worksystem.repository.StakeholderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class StakeholderRepositoryTest {
    @Autowired
    StakeholderRepository repository;

    public StakeholderVO createVO() {
//      VO 객체 생성
        StakeholderVO vo = StakeholderVO.builder()
                .shId(createRandomNumber())
                .shRegDate(createRandomDatetime())
                .shModDate(createRandomDatetime())
                .shName(createRandomString())
                .shMobile(createRandomString())
                .shPhone(createRandomString())
                .shEmail(createRandomString())
                .shAddress(createRandomString())
                .shWkId(createRandomNumber())
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
        Assertions.assertEquals(repository.selectLast().getShId(), repository.selectByShId(repository.selectLast().getShId()).getShId());
    }

    @Test
    public void selectByShWkIdTest () {
        repository.insert(createVO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(repository.selectLast().getShWkId(), repository.selectByShWkId(repository.selectLast().getShWkId()).get(0).getShWkId());
    }

    @Test
    public void deleteTest() {
        repository.insert(createVO());
        int result = repository.delete(repository.selectLast().getShId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        repository.insert(createVO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = repository.selectLast().getShName();
        StakeholderVO vo = repository.selectLast();
        vo.setShName(createRandomString());
        repository.update(vo);
        Assertions.assertNotEquals(input, repository.selectLast().getShName());
    }
}
