package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Model.Staff;

public interface RemainLeaveService {
    public int findRemainLeave(Staff staff, LeaveType leaveType);
}
