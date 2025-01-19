package org.hgtech.worksystem.controller;

import org.hgtech.worksystem.DTO.WorkDTO;
import org.hgtech.worksystem.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class workController {

    @Autowired
    WorkService workService;

    @PostMapping("workSystem/work/register")
    public ResponseEntity<WorkDTO> register(@ModelAttribute WorkDTO workDTO) {
        workService.register((workDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(workDTO);
    }

    @GetMapping("workSystem/work/remove")
    public ResponseEntity<String> remove(@RequestParam int wkId) {
        workService.remove((wkId));
        return ResponseEntity.status(HttpStatus.CREATED).body("Removed : " + wkId);
    }

    @PostMapping("workSystem/work/modify")
    public ResponseEntity<WorkDTO> modify(@ModelAttribute WorkDTO workDTO) {
        workService.modify((workDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(workDTO);
    }

    @GetMapping("workSystem/work/rankUp")
    public ResponseEntity<String> rankUp(@RequestParam int wkId) {
        workService.rankUp((wkId));
        return ResponseEntity.status(HttpStatus.CREATED).body("rankUp : " + wkId);
    }

    @GetMapping("workSystem/work/rankDown")
    public ResponseEntity<String> rankDown(@RequestParam int wkId) {
        workService.rankDown((wkId));
        return ResponseEntity.status(HttpStatus.CREATED).body("rankDown : " + wkId);
    }

    @GetMapping("workSystem/work/changeParent")
    public ResponseEntity<String> changeParent(@RequestParam(required = false) Integer parent, Integer wkId) {
        workService.changeParent(parent, wkId);
        return ResponseEntity.status(HttpStatus.CREATED).body("changeParent - parent : " + parent + " wkId : " + wkId );
    }
}
