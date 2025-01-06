package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileIOService {
    final private String fileDir = "c://Users/ajcltm/uploads/workSystem/";

    @Autowired
    FileService fileService;

    public void save(MultipartFile file, Integer wfWkId, Integer wfLgId) throws IOException {

        String uuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        String saveName = uuid + "_" + fileName;
        String filePath = fileDir + saveName;
        String fileType = Files.probeContentType(Paths.get(fileDir).resolve(fileName).normalize());

//        파일저장
        file.transferTo(new File(filePath));

//        파일 정보 db 기록
        FileDTO fileDTO = FileDTO.builder().wfName(fileName).wfPath(filePath).wfType(fileType).wfWkId(wfWkId).wfLgId(wfLgId).build();
        fileService.register(fileDTO);
    }

    public File downloadFile(int id) {
//        파일 기록 가져오기
        FileDTO fileDTO = fileService.getByWfId(id);
//        파일 불러오기
        File file = new File(fileDTO.getWfPath());
        return file;
    }
}