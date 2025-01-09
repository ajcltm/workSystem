package org.hgtech.worksystem.controller;

import org.hgtech.worksystem.service.LogInfoService;
import org.hgtech.worksystem.service.WorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workSystem/logInfo")
public class LogInfoController {

    @Autowired
    LogInfoService service;

    @GetMapping("data/{wkId}")
    public String getData(@PathVariable int wkId, Model model) {
        model.addAttribute("logInfo" , service.getAll(wkId));
        return "logInfo :: logInfo";
    }
}