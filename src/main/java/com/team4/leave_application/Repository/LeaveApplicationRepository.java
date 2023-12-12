package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

    List<LeaveApplication> findAllByStaff(Staff staff);
    LeaveApplication findById(int id);

}