package org.hgtech.worksystem.serviceTest;

import org.hgtech.worksystem.DTO.MemberDTO;
import org.hgtech.worksystem.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService service;

    public MemberDTO createDTO() {
//      VO 객체 생성
        MemberDTO dto = MemberDTO.builder()
                .mbRegDate(createRandomDatetime())
                .mbModDate(createRandomDatetime())
                .mbUserName(createRandomString())
                .mbUserBirth(createRandomDate())
                .mbUserId(createRandomString())
                .mbUserPw(createRandomString())
                .build();
        return dto;
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
    public void registerTest() {
        int result = service.register(createDTO());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void selectLastTest() {
        service.register(createDTO());
        System.out.println(service.getLast());
    }

    @Test
    public void selectAllTest () {
        service.register(createDTO());
        service.register(createDTO());
        System.out.println(service.getAll());
    }

    @Test
    public void selectByIdTest () {
        service.register(createDTO());
//      id 속성 이름 확인 필요
        Assertions.assertEquals(service.getLast().getMbId(), service.getByMbId(service.getLast().getMbId()).getMbId());
    }

    @Test
    public void deleteTest() {
        service.register(createDTO());
        int result = service.remove(service.getLast().getMbId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        service.register(createDTO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = service.getLast().getMbUserId();
        MemberDTO dto = service.getLast();
        dto.setMbUserId(createRandomString());
        service.modify(dto);
        Assertions.assertNotEquals(input, service.getLast().getMbUserId());
    }
}
