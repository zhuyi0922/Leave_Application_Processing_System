package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    List<LeaveType> findAll();

    LeaveType findLeaveTypeByLeaveTypeName(String name);
}
