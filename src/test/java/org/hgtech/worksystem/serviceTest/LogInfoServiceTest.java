package org.hgtech.worksystem.serviceTest;

import org.hgtech.worksystem.DTO.LogInfoDTO;
import org.hgtech.worksystem.DTO.WorkInfoDTO;
import org.hgtech.worksystem.service.LogInfoService;
import org.hgtech.worksystem.service.WorkInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LogInfoServiceTest {

    @Autowired
    LogInfoService service;

    @Test
    public void workInfoServiceTest() {
        List<LogInfoDTO> result = service.getAll(1);
        for (LogInfoDTO logInfoDTO : result) {
            System.out.println(logInfoDTO);
        }
    }
}
