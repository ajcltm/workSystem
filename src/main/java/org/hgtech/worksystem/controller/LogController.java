package org.hgtech.worksystem.controller;

import lombok.extern.java.Log;
import org.hgtech.worksystem.DTO.LogDTO;
import org.hgtech.worksystem.DTO.WorkDTO;
import org.hgtech.worksystem.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("workSystem/log")
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping("/register")
    public ResponseEntity<LogDTO> register(@ModelAttribute LogDTO logDTO) {
        logService.register(logDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(logDTO);
    }

    @GetMapping("remove")
    public ResponseEntity<String> remove(@RequestParam int lgId) {
        logService.remove((lgId));
        return ResponseEntity.status(HttpStatus.CREATED).body("Removed : " + lgId);
    }

    @PostMapping("modify")
    public ResponseEntity<LogDTO> modify(@ModelAttribute LogDTO logDTO) {
        logService.modify((logDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(logDTO);
    }
}

