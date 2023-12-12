package com.team4.leave_application.Controller;

import com.team4.leave_application.Service.AuthoService;
import com.team4.leave_application.Service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AuthoService authoService;
    @Autowired
    private StaffService staffService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam String role, HttpServletRequest request){
        if(authoService.UserIsExit(username, role)){
            if(authoService.validateUser(username,password,role)){
                request.getSession().setAttribute("username", username);
                var staffrole = staffService.findStaff(username).getIsManager()? "manager" : "employee";
                //here need to specify the role
                return "redirect:/" + staffrole + "/home";
            }
            else{
                //return "password is invalid";
                return "login";
            }
        }
        else{
            //return "the user is not existed";
            return "login";
        }
    }
}
