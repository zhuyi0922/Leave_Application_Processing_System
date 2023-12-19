package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Model.RemainLeave;
import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemainLeaveRepository extends JpaRepository<RemainLeave, Integer> {
    RemainLeave findByStaffAndLeaveType(Staff staff, LeaveType leaveType);
}
