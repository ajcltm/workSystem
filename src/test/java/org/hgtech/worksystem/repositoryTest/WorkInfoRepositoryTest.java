package org.hgtech.worksystem.repositoryTest;

import org.hgtech.worksystem.domain.WorkInfoVO;
import org.hgtech.worksystem.repository.WorkInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WorkInfoRepositoryTest {

    @Autowired
    WorkInfoRepository repository;

    @Test
    public void selectAllTest() {
        List<WorkInfoVO> workInfoVO = repository.selectAll();
        for (WorkInfoVO workInfo:workInfoVO) {
            System.out.println(workInfo);
        }
    }
}
