package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.RemainLeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemainLeaveServiceimpl implements RemainLeaveService{
    @Autowired
    private RemainLeaveRepository remainLeaveRepository;
    public int findRemainLeave(Staff staff, LeaveType leaveType){
        var remainLeave = remainLeaveRepository.findByStaffAndLeaveType(staff, leaveType);
        return remainLeave.getRemainLeave();
    }
}
