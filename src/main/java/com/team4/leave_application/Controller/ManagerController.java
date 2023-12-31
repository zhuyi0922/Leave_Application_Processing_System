package com.team4.leave_application.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team4.leave_application.Service.EmailService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.team4.leave_application.Service.LeaveApplicationService;
import com.team4.leave_application.Model.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {
    @Autowired
    private LeaveApplicationService laService;
    @Autowired
    private EmailService emailService;
    static  final String subject = "Application Response";
    static final String message = "your application has been approved/rejected,please check it in the system.";

    @InitBinder
    private void initBinder(WebDataBinder binder) { }

    @RequestMapping(value="/home")
    public String managerHome() {
    	return "manager-home";
    }
    
    @RequestMapping(value="/view_pending")
    public String pendingApplicationApproval(HttpSession session, Model model) {

        UserSession userSession = (UserSession) session.getAttribute("usession");

        Map<Staff, List<LeaveApplication>> subordinatePendingApplication = new HashMap<>();

        for (Staff subordinate : userSession.getSubordinates()) {
            List<LeaveApplication> appList = laService.findPendingApplicationByStaffID(subordinate.getId());
            subordinatePendingApplication.put(subordinate, appList);
        }

        model.addAttribute("pendingapplist", subordinatePendingApplication);
        return "manager-pending-application";
    }

    // View subordinates history
    @RequestMapping(value="/subordinates-history",method = RequestMethod.GET)
    public String subordinatesApplicationHistory(HttpSession session, Model model) {
        UserSession userSession = (UserSession) session.getAttribute("usession");

        // New staff-application hash map
        Map <Staff, List<LeaveApplication>> subordinateHistory = new HashMap<>();

        for (Staff subordinate : userSession.getSubordinates()) {
            subordinateHistory.put(subordinate, laService.findApplicationsByStaff(subordinate));
            //subordinateHistory.put(subordinate, laService.findApplicationsByStaffId(subordinate.getId()));
        }

        model.addAttribute("subordinatehistory", subordinateHistory);
        return "manager-subordinate-application-history";
    }

    // Manager Use
    // View details of chosen pending id
    @GetMapping(value="/application/details/{id}")
    public String viewPendingApplicationDetailManager (@PathVariable int id, Model model) {
        LeaveApplication leaveApplication = laService.findById(id);
        model.addAttribute("leaveapplication", leaveApplication);
        return "manager-application-detail";
    }

    @PostMapping(value="application/edit/{id}")
    public String approveOrRejectApplication(@PathVariable int id, @RequestParam String approvalStatus,@ModelAttribute LeaveApplication app) {
        LeaveApplication  la = laService.findById(id);
        la.setResponseComment(app.getResponseComment());
        //can not just use == to compare string,it will compare the address of the string.
        // use equals() to compare the content of the string
        if(approvalStatus.equals("APPROVE")) {
            la.setApplication_status(LeaveApplicationEventEnum.APPROVED);
        }
        else {
            la.setApplication_status(LeaveApplicationEventEnum.REJECTED);
        }
        var date = new Date();
        la.setResponseDate(date);
        laService.changeLeaveApplication(la);
        // send email
        sendEmail(la.getStaff().getEmail(),subject,message);

        return "redirect:/manager/view_pending";
    }

    @Async
    public void sendEmail(String email,String subject,String message){
        emailService.sendSimpleMessage(email,subject,message);
    }

    // Compensation leave management (TBA)
    @RequestMapping(value="/compensation")
    public String viewCompensationRequest (HttpSession session, Model model) {
        Object compensationList = null; // to be updated
        model.addAttribute("compensationrequestlist", compensationList);
        return "manager-pending-compensation-request";
    }
}
