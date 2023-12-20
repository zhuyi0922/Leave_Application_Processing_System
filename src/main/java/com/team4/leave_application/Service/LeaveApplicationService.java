package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveApplication;

import java.util.List;

public interface LeaveApplicationService {
    public LeaveApplication save(LeaveApplication leaveApplication);
    public LeaveApplication findById(int id);
    public List<LeaveApplication> findPendingApplicationByStaffID(int staffId);

    public List<LeaveApplication> findApplicationsByStaffId(int staffId);
}
