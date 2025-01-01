package org.hgtech.worksystem.serviceTest;

import org.hgtech.worksystem.DTO.StakeholderDTO;
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
public class StakeholderServiceTest {

    @Autowired
    StakeholderService service;

    public StakeholderDTO createDTO() {
//      VO 객체 생성
        StakeholderDTO dto = StakeholderDTO.builder()
                .shId(createRandomNumber())
                .shRegDate(createRandomDatetime())
                .shModDate(createRandomDatetime())
                .shName(createRandomString())
                .shCompany(createRandomString())
                .shMobile(createRandomString())
                .shPhone(createRandomString())
                .shAddress(createRandomString())
                .shWkId(createRandomNumber())
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
        Assertions.assertEquals(service.getLast().getShId(), service.getByWfId(service.getLast().getShId()).getShId());
    }

    @Test
    public void selectByParentIdTest () {
        service.register(createDTO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(service.getLast().getShWkId(), service.getByWfWkId(service.getLast().getShWkId()).get(0).getShWkId());
    }

    @Test
    public void deleteTest() {
        service.register(createDTO());
        int result = service.remove(service.getLast().getShId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        service.register(createDTO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = service.getLast().getShName();
        StakeholderDTO dto = service.getLast();
        dto.setShName(createRandomString());
        service.modify(dto);
        Assertions.assertNotEquals(input, service.getLast().getShName());
    }
}
