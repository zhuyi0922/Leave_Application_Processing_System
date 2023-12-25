package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface LeaveApplicationService {
    public LeaveApplication save(LeaveApplication leaveApplication);
    public LeaveApplication findById(int id);
   

    public Page<LeaveApplication> findApplicationsByStaffInPage(Staff staff, PageRequest pageRequest);

    public List<LeaveApplication> findApplicationsByStaff(Staff staff);

    public LeaveApplication update(LeaveApplication leaveApplication);
    
	public List<LeaveApplication> findApplicationsByStaffId(int staffid);
	
	public List<LeaveApplication> findPendingApplicationByStaffID(int staffId);
	
	public LeaveApplication changeLeaveApplication(LeaveApplication leaveApplication);
	
	public void deleteLeaveApplication(LeaveApplication application);
	
	public void deleteAllLeaveApplicationsByStaff(Staff staff);

    public Page<LeaveApplication> findAll(PageRequest pageRequest);
    
    public boolean IsOverlap(Staff staff, Date start_date, Date end_date);
    
    public List<LeaveApplication> findApplicationsByLeaveTypeAndStaff(LeaveType leaveType, Staff staffPre);
}
