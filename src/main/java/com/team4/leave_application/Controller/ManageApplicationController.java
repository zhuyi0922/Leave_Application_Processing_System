package com.team4.leave_application.Controller;

import com.team4.leave_application.Model.LeaveApplicationDTO;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Service.LeaveApplicationService;
import com.team4.leave_application.Service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manageApplication")
public class ManageApplicationController {
    @Autowired
    private LeaveApplicationService service;
    @Autowired
    private StaffService staffService;

    @GetMapping("apply")
    public String Apply(Model model){
        // need to pass a form obj(dto) in the page, only in the process form method is not enough.
        //make a mistake before, the L uppercase
        model.addAttribute("leaveApplicationDTO", new LeaveApplicationDTO());
        return "apply";
    }

    //enter the url in browser means get request. so this method cannot be accessed by the url.
    @PostMapping("processapply")
    public String processApply(HttpServletRequest request, @ModelAttribute LeaveApplicationDTO applicationDTO){
        // a very common error, status = 400, but no action in the server side
        // typically occur in the form with Date type, must add some annotations in the model and DTO
        // beacause the form cannot bind the dto.
        var staff = getUser(request);
        var application = service.createApplication(staff,applicationDTO);
        var id = application.getId();
        return "redirect:application/" + id;
    }
    @PostMapping("cancelApplication")
    public String cancelApplication(@RequestParam int id){
        service.cancelApplication(id);
        return "redirect:/home";
    }

    @PostMapping("deleteApplication")
    public String deleteApplication(@RequestParam int id){
        service.cancelApplication(id);
        return "redirect:/home";
    }

    private Staff getUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        return staffService.findStaff((String) session.getAttribute("username"));
    }
}
