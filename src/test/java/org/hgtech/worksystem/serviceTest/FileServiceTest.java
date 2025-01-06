package org.hgtech.worksystem.serviceTest;

import org.hgtech.worksystem.DTO.FileDTO;
import org.hgtech.worksystem.DTO.WorkDTO;
import org.hgtech.worksystem.service.FileService;
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
public class FileServiceTest {
    @Autowired
    FileService service;

    public FileDTO createDTO() {
//      VO 객체 생성
        FileDTO dto = FileDTO.builder()
                .wfId(createRandomNumber())
                .wfRegDate(createRandomDatetime())
                .wfModDate(createRandomDatetime())
                .wfName(createRandomString())
                .wfPath(createRandomString())
                .wfType(createRandomString())
                .wfWkId(createRandomNumber())
                .wfLgId(createRandomNumber())
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
//        service.register(createDTO());
//      id 속성 이름 확인 필요
//        Assertions.assertEquals(service.getLast().getWfId(), service.getByWfId(service.getLast().getWfId()).getWfId());
        System.out.println("wfId : " + service.getByWfId(50));
    }

    @Test
    public void selectByParentIdTest () {
        service.register(createDTO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(service.getLast().getWfWkId(), service.getByWfWkId(service.getLast().getWfWkId()).get(0).getWfWkId());
    }

    @Test
    public void selectByWfLgIdTest () {
        service.register(createDTO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(service.getLast().getWfLgId(), service.getByWfLgId(service.getLast().getWfLgId()).get(0).getWfLgId());
    }

    @Test
    public void deleteTest() {
        service.register(createDTO());
        int result = service.remove(service.getLast().getWfId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        service.register(createDTO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = service.getLast().getWfName();
        FileDTO dto = service.getLast();
        dto.setWfName(createRandomString());
        service.modify(dto);
        Assertions.assertNotEquals(input, service.getLast().getWfName());
    }
}
