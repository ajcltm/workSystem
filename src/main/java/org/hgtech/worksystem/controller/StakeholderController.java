package org.hgtech.worksystem.controller;

import org.hgtech.worksystem.DTO.LogDTO;
import org.hgtech.worksystem.DTO.StakeholderDTO;
import org.hgtech.worksystem.service.LogService;
import org.hgtech.worksystem.service.StakeholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("workSystem/stakeholder")
public class StakeholderController {

    @Autowired
    StakeholderService stakeholderService;

    @PostMapping("/register")
    public ResponseEntity<StakeholderDTO> register(@ModelAttribute StakeholderDTO stakeholderDTO) {
        stakeholderService.register(stakeholderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(stakeholderDTO);
    }

    @GetMapping("remove")
    public ResponseEntity<String> remove(@RequestParam int shId) {
        stakeholderService.remove((shId));
        return ResponseEntity.status(HttpStatus.CREATED).body("Removed : " + shId);
    }

    @PostMapping("modify")
    public ResponseEntity<StakeholderDTO> modify(@ModelAttribute StakeholderDTO stakeholderDTO) {
        stakeholderService.modify((stakeholderDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(stakeholderDTO);
    }
}

