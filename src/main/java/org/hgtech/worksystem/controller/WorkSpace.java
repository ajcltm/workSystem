package org.hgtech.worksystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workSystem")
public class WorkSpace {

    @GetMapping("/workspace")
    public String getMain() {
        return "workspace";
    }
}
