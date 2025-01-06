package org.hgtech.worksystem.controller;

import org.hgtech.worksystem.service.WorkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workSystem/workInfo")
public class WorkInfoController {

    @Autowired
    WorkInfoService service;

    @GetMapping("data/{mbId}")
    public String getData(@PathVariable int mbId, Model model) {
        model.addAttribute("workInfo" , service.getAll(mbId));
        return "workInfo :: workInfo";
    }
}
