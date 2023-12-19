package com.team4.leave_application.Controller;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.LeaveApplicationEventEnum;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Model.UserSession;
import com.team4.leave_application.Service.HolidayService;
import com.team4.leave_application.Service.LeaveApplicationService;
import com.team4.leave_application.Service.RemainLeaveService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private RemainLeaveService remainLeaveService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private LeaveApplicationService leaveApplicationService;
    @GetMapping("/application/history")
    public String History(HttpSession session, Model model){
        var usession = (UserSession) session.getAttribute("usession");
        var applications = usession.getStaff().getLeaveApplications();
        model.addAttribute("applications", applications);
        return "history";
    }

    @GetMapping("/application/apply")
    public String Apply(Model model){
        model.addAttribute("leaveApplication", new LeaveApplication());
        return "apply";
    }
    @PostMapping("/application/apply")
    public String ApplyPost(HttpSession session,Model model,@ModelAttribute @Valid LeaveApplication application){
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        var days = holidayService.calLeaveDays(application.getStart_date(),application.getEnd_date());
        var leavetype = application.getLeaveType();
        if (remainLeaveService.findRemainLeave(staff,leavetype)>=days){
            // enough leave
            // create application
            application.setStaff(staff);
            application.setApplication_status(LeaveApplicationEventEnum.APPLIED);
            application.setCreate_date(new Date());
            leaveApplicationService.save(application);
            return "redirect:/staff/application/history";

        }
        else{
            model.addAttribute("error","not enough leave");
            return "apply";
        }
    }

    @GetMapping("/application/cancel/{id}")
    public String Cancel(HttpSession session, Model model, @PathVariable int id){
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        var application = leaveApplicationService.findById(id);
        if (application.getStaff().getId() == staff.getId()){
            // can cancel
            if (application.getApplication_status() == LeaveApplicationEventEnum.APPROVED){
                application.setApplication_status(LeaveApplicationEventEnum.CANCELLED);
                leaveApplicationService.save(application);
                return "redirect:/staff/application/history";
            }
            model.addAttribute("error","only approved application can be cancelled");
            return "redirect:/staff/application/history";
        }
        else {
            model.addAttribute("error","not your application");
            return "redirect:/staff/application/history";
        }
    }

    @GetMapping("/application/delete/{id}")
    public String Delete(HttpSession session,Model model,@PathVariable int id){
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        var application = leaveApplicationService.findById(id);
        if (application.getStaff().getId() == staff.getId()){
            if (application.getApplication_status() == LeaveApplicationEventEnum.APPLIED || application.getApplication_status() == LeaveApplicationEventEnum.UPDATED){
                application.setApplication_status(LeaveApplicationEventEnum.DELETED);
                leaveApplicationService.save(application);
                return "redirect:/staff/application/history";
            }
                model.addAttribute("error","only applied or updated application cannot be deleted");
                return "redirect:/staff/application/history";
        }
        else{
            model.addAttribute("error","not your application");
            return "redirect:/staff/application/history";
        }
    }
    @GetMapping("/application/edit/{id}")
    public String Edit(Model model,@PathVariable int id){
        var application = leaveApplicationService.findById(id);
        model.addAttribute("leaveApplication",application);
        return "application-edit";
    }
    @PostMapping("/application/edit/{id}")
    public String Edit(@ModelAttribute @Valid LeaveApplication application, @PathVariable int id, BindingResult result, HttpSession session){
        if (result.hasErrors()){
            return "application-edit";
        }
        application.setApplication_status(LeaveApplicationEventEnum.UPDATED);
        leaveApplicationService.save(application);
        return "redirect:/staff/application/history";
    }
}
