package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveType;

import java.util.List;

public interface LeaveTypeService {
    List<String> findAllLeaveType();
    LeaveType findLeaveTypeByName(String name);
	LeaveType createLeaveType(LeaveType leaveType);
	void deleteLeaveType(LeaveType leaveType);
	LeaveType editLeaveType(LeaveType leaveType);
	List<String> findAllLeaveTypeIds();
	List<LeaveType> findAllLeaveTypes();
	LeaveType findLeaveTypeById(int leaveTypeId);
	List<LeaveType> findAllByTitle(String staffTitle);
	LeaveType findByTitleAndName(String staffTitle, String leaveTypeName);
	int calculateRemainLeaveChange(int maxLeaveDayPre, int maxLeaveDayAft);
	void editRemainLeave(int remainLeaveChange, LeaveType leaveType);
	void createRemainLeave(LeaveType leaveType);
	boolean existsByLeaveType(LeaveType leaveType);
}
