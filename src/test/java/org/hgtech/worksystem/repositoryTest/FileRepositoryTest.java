package org.hgtech.worksystem.repositoryTest;

import org.hgtech.worksystem.domain.FileVO;
import org.hgtech.worksystem.repository.FileRepository;
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
public class FileRepositoryTest {
    @Autowired
    FileRepository repository;

    public FileVO createVO() {
//      VO 객체 생성
        FileVO vo = FileVO.builder()
                .wfId(createRandomNumber())
                .wfRegDate(createRandomDatetime())
                .wfModDate(createRandomDatetime())
                .wfName(createRandomString())
                .wfPath(createRandomString())
                .wfType(createRandomString())
                .wfWkId(createRandomNumber())
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
        Assertions.assertEquals(repository.selectLast().getWfId(), repository.selectByWfId(repository.selectLast().getWfId()).getWfId());
    }

    @Test
    public void selectByParentIdTest () {
        repository.insert(createVO());
//      Foriegn Id 확인 필요
        Assertions.assertEquals(repository.selectLast().getWfWkId(), repository.selectByWfWkId(repository.selectLast().getWfWkId()).get(0).getWfWkId());
    }

    @Test
    public void deleteTest() {
        repository.insert(createVO());
        int result = repository.delete(repository.selectLast().getWfId());
        Assertions.assertEquals(result,1);
    }

    @Test
    public void updateTest(){
        repository.insert(createVO());
//      수정하고자 하는 속성 이름과 해당 객체 확인 필요
        String input = repository.selectLast().getWfName();
        FileVO vo = repository.selectLast();
        vo.setWfName(createRandomString());
        repository.update(vo);
        Assertions.assertNotEquals(input, repository.selectLast().getWfName());
    }
}
