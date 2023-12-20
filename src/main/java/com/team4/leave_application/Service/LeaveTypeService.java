package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveType;

import java.util.List;

public interface LeaveTypeService {
    List<String> findAllLeaveType();
    LeaveType findLeaveTypeByName(String name);
}
