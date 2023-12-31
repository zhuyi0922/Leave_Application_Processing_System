package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Model.RemainLeave;
import com.team4.leave_application.Model.Staff;

import jakarta.validation.Valid;

public interface RemainLeaveService {
    public int findRemainLeave(Staff staff, LeaveType leaveType);
    public RemainLeave findRemainLeaveObj(Staff staff, LeaveType leaveType);
    public void updateRemainLeave(RemainLeave remainLeave);
	public void updateRemainLeaves(LeaveType leaveType, int maxLeaveDay);
    public void deleteRemainLeaves(int remainLeaveId);
	public void deleteRemainLeavesByStaffAndLeaveType(Staff staff, @Valid LeaveType leaveType);
	public void deleteRemainLeavesByStaff(Staff staff);
}
