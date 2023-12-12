package com.team4.leave_application.Controller;

import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Service.LeaveApplicationService;
import com.team4.leave_application.Service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewApplication {
    @Autowired
    private LeaveApplicationService service;
    @Autowired
    private StaffService staffService;
    @GetMapping("/application/{id}")
    public String viewApplication(HttpServletRequest request, ModelAndView model, @PathVariable int id){
        var application1 = service.getApplication(id);

        model.addObject("application",application1);
        //model.addAttribute("start_date",application1.getStart_date());
        return "applicationDetail";
    }

    //still not sure the difference between model and modelMap
    @GetMapping("/history")
    public String viewHistory(HttpServletRequest request, ModelMap model){
        var staff = getUser(request);
        var applications = service.findHistory(staff);
        //If want to pass the list, addattribute may no@ work, should use put method.
        //this will pass to the view
        //pass the whole application obj to view.
        model.addAttribute("applications", applications);
        //applications.forEach(app -> app.get);
        return "history";
    }
    private Staff getUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        var staff = staffService.findStaff((String) session.getAttribute("username"));
        return staff;
    }
}
