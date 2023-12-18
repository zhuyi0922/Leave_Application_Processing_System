package com.team4.leave_application.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeHomeController {

    @GetMapping("/home")
    public  String Index(){
        return "index";
    }
}

