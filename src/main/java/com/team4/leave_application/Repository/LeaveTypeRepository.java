package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    List<LeaveType> findAll();

    LeaveType findLeaveTypeByLeaveTypeName(String name);
    
    @Query("SELECT lt.leaveTypeId FROM LeaveType lt")
	List<String> findAllLeaveTypeIds();
	
	void deleteByLeaveTypeId(int leaveTypeId);
}
