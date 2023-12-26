package com.team4.leave_application.Controller;

import com.team4.leave_application.Model.OvertimeWork;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Model.UserSession;
import com.team4.leave_application.Service.LeaveTypeService;
import com.team4.leave_application.Service.OvertimeWorkService;
import com.team4.leave_application.Service.RemainLeaveService;
import com.team4.leave_application.Service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CompensationController {

    @Autowired
    private OvertimeWorkService overtimeWorkService;
    @Autowired
    private RemainLeaveService remainLeaveService;
    @Autowired
    private LeaveTypeService leaveTypeService;
    @Autowired
    private StaffService staffService;

    @GetMapping("/staff/compensation-claim")
    public String ClaimCompensation(Model model){
        model.addAttribute("OTWork",new OvertimeWork());
        return "compensation-claim";
    }

    @PostMapping("/staff/compensation-claim")
    public String ClaimCompensation(@ModelAttribute("OTWork") OvertimeWork overtimeWork, HttpSession session){
        var usession = (UserSession) session.getAttribute("usession");
        var staff = (Staff) usession.getStaff();
        overtimeWork.setStaff(staff);
        overtimeWork.setStatus("APPIIED");
        overtimeWorkService.save(overtimeWork);
        return "redirect:/staff/compensation-claim";
    }

    @GetMapping("/manager/compensation-pending-process")
    public String PendingCompensation(HttpSession session,Model model){
        var usession = (UserSession) session.getAttribute("usession");
        var subordinates = usession.getSubordinates();
        Map<Staff, List<OvertimeWork>> OTClaims = new HashMap<>();
        for (Staff subordinate:subordinates){
            // this name is a bit weird, but keep in mind, use to find pending, not all
            var pendingclaims = overtimeWorkService.findAllByStaff(subordinate);
            OTClaims.put(subordinate,pendingclaims);
        }
        model.addAttribute("OTClaims",OTClaims);
        return "compensation-pending-process";
    }

    @PostMapping("/manager/compensation-process/{id}/{decision}")
    public String ProcessCompensation(@PathVariable("id") int id,@PathVariable("decision") String decision){
        var overtimeWork =  overtimeWorkService.findById(id);
        if (decision.equals("approve")){
            overtimeWork.setStatus("APPROVED");
            overtimeWorkService.save(overtimeWork);
            // besides the overtime record, we also need to update the staff's compensation leave
            var staff = overtimeWork.getStaff();
            staff.setOtHours(staff.getOtHours()+3);
            staffService.editStaff(staff);
            // if the staff has enough overtime hours, then we can convert it to compensation leave
            // and update the staff's compensation leave
            if (staff.getOtHours()>=6){
                var leaveType = leaveTypeService.findByTitleAndName(staff.getTitle(),"Compensation Leave");
                var remainleave = remainLeaveService.findRemainLeaveObj(staff,leaveType);
                remainleave.setRemainLeave(remainleave.getRemainLeave()+staff.getOtHours()/6);
                staff.setOtHours(staff.getOtHours()%6);
                remainLeaveService.updateRemainLeave(remainleave);
                staffService.editStaff(staff);
            }
        }
        else{
            overtimeWork.setStatus("REJECTED");
            overtimeWorkService.save(overtimeWork);
        }
        return "redirect:/manager/compensation-pending-process";
    }

}
