package org.hgtech.worksystem.repositoryTest;

import org.hgtech.worksystem.domain.MemberVO;
import org.hgtech.worksystem.domain.WorkVO;
import org.hgtech.worksystem.repository.MemberRepository;
import org.hgtech.worksystem.repository.WorkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    public MemberVO createVO() {
//      VO 객체 생성
        MemberVO vo = MemberVO.builder()
                .mbId(createRandomNumber())
                .mbRegDate(createRandomDatetime())
                .mbModDate(createRandomDatetime())
                .mbUserName(createRandomString())
                .mbUserBirth(createRandomDate())
                .mbUserId(createRandomString())
                .mbUserPw(createRandomString())
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
        Assertions.assertEquals(repository.selectLast().getMbId(), repository.selectByMbId(repository.selectLast().getMbId()).getMbId());
    }

    @Test
    public void deleteTest() {
        repository.insert(createVO());
        int result = repository.delete(repository.selectLast().getMbId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        repository.insert(createVO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = repository.selectLast().getMbUserId();
        MemberVO vo = repository.selectLast();
        vo.setMbUserId(createRandomString());
        repository.update(vo);
        Assertions.assertNotEquals(input, repository.selectLast().getMbUserId());
    }
}
