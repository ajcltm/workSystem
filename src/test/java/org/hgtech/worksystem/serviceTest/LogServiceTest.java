package org.hgtech.worksystem.serviceTest;

import org.hgtech.worksystem.DTO.LogDTO;
import org.hgtech.worksystem.DTO.StakeholderDTO;
import org.hgtech.worksystem.service.LogService;
import org.hgtech.worksystem.service.StakeholderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class LogServiceTest {
    @Autowired
    LogService service;

    public LogDTO createDTO() {
//      VO 객체 생성
        LogDTO dto = LogDTO.builder()
                .lgId(createRandomNumber())
                .lgRegDate(createRandomDatetime())
                .lgModDate(createRandomDatetime())
                .lgRepDate(createRandomDatetime())
                .lgDsc(createRandomString())
                .lgTag(createRandomString())
                .lgWkId(createRandomNumber())
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
        Assertions.assertEquals(service.getLast().getLgId(), service.getByWfId(service.getLast().getLgId()).getLgId());
    }

    @Test
    public void selectByParentIdTest () {
        service.register(createDTO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(service.getLast().getLgWkId(), service.getByWfWkId(service.getLast().getLgWkId()).get(0).getLgWkId());
    }

    @Test
    public void deleteTest() {
        service.register(createDTO());
        int result = service.remove(service.getLast().getLgId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        service.register(createDTO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = service.getLast().getLgTag();
        LogDTO dto = service.getLast();
        dto.setLgTag(createRandomString());
        service.modify(dto);
        Assertions.assertNotEquals(input, service.getLast().getLgTag());
    }
}
