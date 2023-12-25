package com.team4.leave_application.Controller;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.LeaveApplicationEventEnum;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Model.UserSession;
import com.team4.leave_application.Service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
@RequestMapping("/staff")
public class StaffController {
    static  final String subject = "submit notification";
    static final String message = "you already submit an application, please wait for the response";
    @Autowired
    private RemainLeaveService remainLeaveService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private LeaveApplicationService leaveApplicationService;
    @Autowired
    private LeaveTypeService leaveTypeService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private StaffService staffService;
    
    @RequestMapping("/home")
    public String StaffHome() {
    	return "staff-home";
    }
    @GetMapping("/application/history")
    public String History(HttpSession session, Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "3") int size){
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        // do not use the getLeaveApplications(), it does not work!!!!!
        var applications = leaveApplicationService.findApplicationsByStaffInPage(staff, PageRequest.of(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", applications.getTotalPages());
        model.addAttribute("applications", applications);
        return "history";
    }

    @GetMapping("/application/apply")
    public String Apply(Model model,HttpSession session){
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        var typelist = leaveTypeService.findAllByTitle(staff.getTitle());
        model.addAttribute("typelist",typelist);

        model.addAttribute("leaveApplication",new LeaveApplication());
        return "apply";
    }
    @PostMapping("/application/apply")
    public String ApplyPost(HttpSession session,Model model,@ModelAttribute @Valid LeaveApplication application){
        //first get the staff
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        //second get the LeaveType object
        var leaveTypeName = application.getLeaveType().getLeaveTypeName();
        var LeaveType = leaveTypeService.findByTitleAndName(staff.getTitle(),leaveTypeName);
        application.setLeaveType(LeaveType);
        // get the acual leave days and remain leave days.
        var days = holidayService.calLeaveDays(application.getStart_date(),application.getEnd_date());
        var remaindays = remainLeaveService.findRemainLeave(staff,LeaveType);
        if (remaindays>=days){
            // enough leave
            // create application
            application.setStaff(staff);
            application.setApplication_status(LeaveApplicationEventEnum.APPLIED);
            application.setCreate_date(new Date());
            application.setCostLeaveDays(days);
            leaveApplicationService.save(application);

            // adjust the remain leave
            var remainleave= remainLeaveService.findRemainLeaveObj(staff,LeaveType);
            remainleave.setRemainLeave(remaindays-days);
            remainLeaveService.updateRemainLeave(remainleave);

            // send email to manager
            var managerid = staff.getManagerId();
            var manager = staffService.findStaffById(managerid);
            var managerEmail = manager.getEmail();
            emailService.sendSimpleMessage(managerEmail,subject,message);

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
                leaveApplicationService.update(application);
                // adjust the remain leave
                var days = application.getCostLeaveDays();
                var remainleave= remainLeaveService.findRemainLeaveObj(staff,application.getLeaveType());
                remainleave.setRemainLeave(remainleave.getRemainLeave()+days);
                remainLeaveService.updateRemainLeave(remainleave);
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
                leaveApplicationService.update(application);
                // adjust the remain leave
                var days = application.getCostLeaveDays();
                var remainleave= remainLeaveService.findRemainLeaveObj(staff,application.getLeaveType());
                remainleave.setRemainLeave(remainleave.getRemainLeave()+days);
                remainLeaveService.updateRemainLeave(remainleave);
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
    public String Edit(Model model,@PathVariable int id,HttpSession session){
        var application = leaveApplicationService.findById(id);
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        var typelist = leaveTypeService.findAllByTitle(staff.getTitle());
        model.addAttribute("typelist",typelist);
        model.addAttribute("leaveApplication",application);
        return "application-edit";
    }

    @PostMapping("/application/edit/{id}")
    public String Edit(@ModelAttribute("leaveApplication") @Valid LeaveApplication application, @PathVariable int id, BindingResult result){
        if (result.hasErrors()){
            return "application-edit";
        }
        // seems the backend is impossible to get the origin application from the frontend.
        var originApplication = leaveApplicationService.findById(id);
        // map the data from frontend to origin application
        originApplication.setStart_date(application.getStart_date());
        originApplication.setEnd_date(application.getEnd_date());
        originApplication.setReasons(application.getReasons());
        originApplication.setWorkDissemination(application.getWorkDissemination());
        originApplication.setContactDetails(application.getContactDetails());
        // also adjust the leave type
        var leaveTypeName = application.getLeaveType().getLeaveTypeName();
        var LeaveType = leaveTypeService.findByTitleAndName(originApplication.getStaff().getTitle(),leaveTypeName);
        application.setLeaveType(LeaveType);
        // set the status
        originApplication.setApplication_status(LeaveApplicationEventEnum.UPDATED);
        // set the cost leave days
        var days = holidayService.calLeaveDays(application.getStart_date(),application.getEnd_date());
        // get the origin cost first, and later change it.
        var origindays = originApplication.getCostLeaveDays();
        var remainleave= remainLeaveService.findRemainLeaveObj(originApplication.getStaff(),originApplication.getLeaveType());
        remainleave.setRemainLeave(remainleave.getRemainLeave()+origindays-days);
        remainLeaveService.updateRemainLeave(remainleave);
        // adjust the cost of application
        originApplication.setCostLeaveDays(days);
        leaveApplicationService.update(originApplication);
        return "redirect:/staff/application/history";

    }
    @ResponseBody
    @GetMapping("/application/validate")
    public  String Validate(HttpSession session,
                            @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd")Date start_date,
                            @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd")Date end_date,
                            @RequestParam("leave_type") String leaveTypeName){
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        var days = holidayService.calLeaveDays(start_date,end_date);
        var leaveType = leaveTypeService.findByTitleAndName(staff.getTitle(),leaveTypeName);
        var remaindays = remainLeaveService.findRemainLeave(staff,leaveType);
        // judge if the leave is overlap with the previous application
        if(leaveApplicationService.IsOverlap(staff,start_date,end_date)){
            return "*your leave is overlap with the previous application";
        }
        if (days > remaindays){
            return "*your remain " + leaveTypeName +" days is " + remaindays + " days" + ", not enough";
        }
        else{
            return "*this application will cost you "+ days + " days " + leaveTypeName;
        }
    }

}
