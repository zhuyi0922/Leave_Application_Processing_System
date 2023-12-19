package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

    List<LeaveApplication> findAllByStaff(Staff staff);
    
    LeaveApplication findById(int id);

    @Query("SELECT a FROM LeaveApplication a JOIN a.staff s WHERE s.id = :eid AND (a.application_status = 'APPLIED' OR a.application_status = 'UPDATED')")
    List<LeaveApplication> findPendingApplicationByStaffId(@Param("eid") int staffId);

	@Query("SELECT a FROM LeaveApplication a WHERE a.staffId = :staffId")
    List<LeaveApplication> findApplicationsByStaffId(int staffId);
    
    

}