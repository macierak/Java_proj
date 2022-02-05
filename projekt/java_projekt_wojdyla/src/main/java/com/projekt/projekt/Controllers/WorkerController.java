package com.projekt.projekt.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkerController {
    
    @GetMapping("/workerpanel")
    public String workerpanel(Model model){

        return "workerpanel";
    }
}
