package org.hgtech.worksystem.repositoryTest;

import org.hgtech.worksystem.domain.LogVO;
import org.hgtech.worksystem.domain.StakeholderVO;
import org.hgtech.worksystem.repository.LogRepository;
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
public class LogRepositoryTest {
    @Autowired
    LogRepository repository;

    public LogVO createVO() {
//      VO 객체 생성
        LogVO vo = LogVO.builder()
                .lgId(createRandomNumber())
                .lgRegDate(createRandomDatetime())
                .lgModDate(createRandomDatetime())
                .lgRepDate(createRandomDatetime())
                .lgDsc(createRandomString())
                .lgTag(createRandomString())
                .lgWkId(createRandomNumber())
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
        Assertions.assertEquals(repository.selectLast().getLgId(), repository.selectByLgId(repository.selectLast().getLgId()).getLgId());
    }

    @Test
    public void selectByLgWkIdTest () {
        repository.insert(createVO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(repository.selectLast().getLgWkId(), repository.selectByLgWkId(repository.selectLast().getLgWkId()).get(0).getLgWkId());
    }

    @Test
    public void deleteTest() {
        repository.insert(createVO());
        int result = repository.delete(repository.selectLast().getLgId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        repository.insert(createVO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = repository.selectLast().getLgTag();
        LogVO vo = repository.selectLast();
        vo.setLgTag(createRandomString());
        repository.update(vo);
        Assertions.assertNotEquals(input, repository.selectLast().getLgTag());
    }
}
