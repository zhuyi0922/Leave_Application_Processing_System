package com.team4.leave_application.Controller;

import com.team4.leave_application.Model.Role;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Model.UserSession;
import com.team4.leave_application.Service.StaffService;
import com.team4.leave_application.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.team4.leave_application.Model.User;
import java.util.List;

@Controller
public class CommonController {
    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    
    @GetMapping(value = {"/home", "/about"})
    public String about() {
    	return "about";
    }

    @RequestMapping(value = "/login/authenticate")
    public String authenticate(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model,
                               HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        User u = userService.authenticate(user.getUsername(), user.getPassword());
        if (u == null) {
            model.addAttribute("loginMessage", "Incorrect username/password");
            return "login";
        }

        UserSession userSession = new UserSession();
        userSession.setUser(u);
        userSession.setStaff(staffService.findStaffById(u.getStaff().getId()));
        List<Staff> subordinates = staffService.findSubordinates(u.getUserId());
        if (subordinates != null) {
            userSession.setSubordinates(subordinates);
        }
        session.setAttribute("usession", userSession);

        List<Role> roleIds = u.getRoleSet();
        System.out.println("Roles:");
        List<String> roles = roleIds.stream().map(x -> x.getName()).toList();

        // Done, let's redirect to respective page
        if (roles.contains("admin")) {
            return "redirect:/admin/home";
        }

        if (roles.contains("manager")) {
            return "redirect:/manager/home";
        }

        return "redirect:/staff/home";
    }


    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/no-accessability")
    public String noAccessabilityPage(){
        return "no-accessability";
    }
}
