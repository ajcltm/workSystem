package org.hgtech.worksystem.controller;

import org.hgtech.worksystem.service.FileIOService;
import org.hgtech.worksystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/workSystem/files")
public class FileIOController {

    @Autowired
    FileIOService fileIOService;

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam List<MultipartFile> files, Integer wfWkId, Integer wfLgId) throws IOException {
        for (MultipartFile file : files) {
            fileIOService.save(file, wfWkId, wfLgId);
            }
        return ResponseEntity.ok("Files uploaded");
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam int wfId) throws IOException {
        File file = fileIOService.downloadFile(wfId);
        Resource resource = new FileSystemResource(file);
        String contentType = Files.probeContentType(Paths.get(file.getPath()));
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }
}
