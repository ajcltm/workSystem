package org.hgtech.worksystem.repositoryTest;

import org.hgtech.worksystem.domain.LogInfoVO;
import org.hgtech.worksystem.domain.WorkInfoVO;
import org.hgtech.worksystem.repository.LogInfoRepository;
import org.hgtech.worksystem.repository.WorkInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class logInfoRepositoryTest {

    @Autowired
    LogInfoRepository repository;

    @Test
    public void selectAllTest() {
        List<LogInfoVO> logInfoVOS = repository.selectAll(1);
        for (LogInfoVO vo:logInfoVOS) {
            System.out.println(vo);
        }
    }
}
