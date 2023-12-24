package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

    Page<LeaveApplication> findAllByStaff(Staff staff, Pageable pageable);

    List<LeaveApplication> findAllByStaff(Staff staff);
    
    LeaveApplication findByLeaveApplicationId(int id);

    @Query("SELECT a FROM LeaveApplication a JOIN a.staff s WHERE s.id = :eid AND (a.application_status = 'APPLIED' OR a.application_status = 'UPDATED')")
    List<LeaveApplication> findPendingApplicationByStaffId(@Param("eid") int staffId);

	@Query("SELECT a FROM LeaveApplication a WHERE a.staff = :staffId")
    List<LeaveApplication> findApplicationsByStaffId(int staffId);

    Page<LeaveApplication> findAll(Pageable pageable);




}