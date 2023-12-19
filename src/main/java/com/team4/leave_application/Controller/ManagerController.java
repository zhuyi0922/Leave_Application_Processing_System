package com.team4.leave_application.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import com.team4.leave_application.Service.LeaveApplicationService;
import com.team4.leave_application.Model.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/manager")
public class ManagerController {
    @Autowired
    private LeaveApplicationService laService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {

    }

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
}
