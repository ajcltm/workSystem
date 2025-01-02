package org.hgtech.worksystem.serviceTest;

import org.hgtech.worksystem.DTO.WorkDTO;
import org.hgtech.worksystem.domain.WorkVO;
import org.hgtech.worksystem.service.WorkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class WorkServiceTest {
    @Autowired
    WorkService service;

    public WorkDTO createDTO() {
//      VO 객체 생성
        WorkDTO dto = WorkDTO.builder()
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
        Assertions.assertEquals(service.getLast().getWkId(), service.getByWkId(service.getLast().getWkId()).getWkId());
    }

    @Test
    public void selectByParentIdTest () {
        service.register(createDTO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(service.getLast().getWkParent(), service.getByParent(service.getLast().getWkParent()).get(0).getWkParent());
    }

    @Test
    public void deleteTest() {
        service.register(createDTO());
        int result = service.remove(service.getLast().getWkId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        service.register(createDTO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = service.getLast().getWkTtl();
        WorkDTO dto = service.getLast();
        dto.setWkTtl(createRandomString());
        service.modify(dto);
        Assertions.assertNotEquals(input, service.getLast().getWkTtl());
    }

    @Test
    public void changeParent() {
        service.changeParent(5, 1);
    }
}
