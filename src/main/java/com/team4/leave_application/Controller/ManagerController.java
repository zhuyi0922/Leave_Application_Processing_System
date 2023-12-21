package com.team4.leave_application.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team4.leave_application.Service.LeaveApplicationService;
import com.team4.leave_application.Model.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {
    @Autowired
    private LeaveApplicationService laService;

    @InitBinder
    private void initBinder(WebDataBinder binder) { }

    @RequestMapping(value="/view_pending")
    public String pendingApplicationApproval(HttpSession session, Model model) {

        UserSession userSession = (UserSession) session.getAttribute("usersession");

        Map<Staff, List<LeaveApplication>> subordinatePendingApplication = new HashMap<>();

        for (Staff subordinate : userSession.getSubordinates()) {
            List<LeaveApplication> appList = laService.findPendingApplicationByStaffID(subordinate.getId());
            subordinatePendingApplication.put(subordinate, appList);
        }

        model.addAttribute("pendingapplist", subordinatePendingApplication);
        return "manager-pending-application";
    }
    
    // View subordinates history
    @RequestMapping(value="/application-history")
    public String subordinatesApplicationHistory(HttpSession session, Model model) {
    	UserSession userSession = (UserSession) session.getAttribute("usersession");
    	
    	// New staff-application hash map
    	Map <Staff, List<LeaveApplication>> subordinateHistory = new HashMap<>();
    	
    	for (Staff subordinate : userSession.getSubordinates()) {
    		subordinateHistory.put(subordinate, laService.findApplicationsByStaffId(subordinate.getId()));
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
    public String approveOrRejectApplication(@PathVariable int id, @RequestParam String approvalStatus) {
    	LeaveApplication  la = laService.findById(id);
    	if(approvalStatus == "APPROVE") {
    		la.setApplication_status(LeaveApplicationEventEnum.APPROVED);
    	}
    	else {
    		la.setApplication_status(LeaveApplicationEventEnum.REJECTED);
    	}
    	laService.changeLeaveApplication(la);
    	return "redirect:/manager/view_pending";
    }
    
    // Compensation leave management (TBA)
    @RequestMapping(value="/compensation")
    public String viewCompensationRequest (HttpSession session, Model model) {
    	Object compensationList = null; // to be updated
    	model.addAttribute("compensationrequestlist", compensationList);
    	return "manager-pending-compensation-request";
    }
}
