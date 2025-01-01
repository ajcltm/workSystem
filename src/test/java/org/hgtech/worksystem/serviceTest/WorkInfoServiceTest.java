package org.hgtech.worksystem.serviceTest;

import org.hgtech.worksystem.DTO.WorkInfoDTO;
import org.hgtech.worksystem.service.WorkInfoService;
import org.hgtech.worksystem.service.WorkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WorkInfoServiceTest {

    @Autowired
    WorkInfoService service;

    @Test
    public void workInfoServiceTest() {
        List<WorkInfoDTO> result = service.getAll();
        for (WorkInfoDTO workInfoDTO : result) {
            System.out.println(workInfoDTO);
        }
    }

    @Test
    public void rankUpTest() {
        service.rankUp(5);
        List<WorkInfoDTO> result = service.getAll();
        for (WorkInfoDTO workInfoDTO : result) {
            System.out.println(workInfoDTO);
        }
    }
}
