package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Model.RemainLeave;
import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RemainLeaveRepository extends JpaRepository<RemainLeave, Integer> {
	RemainLeave findByStaffAndLeaveType(Staff staff, LeaveType leaveType);
	List<RemainLeave> findByStaff(Staff staff);
	boolean existsByLeaveType(LeaveType leaveType);
	List<RemainLeave> findRemainLeaveByLeaveType(LeaveType leaveType);
	@Query("SELECT rl FROM RemainLeave rl WHERE rl.staff.title = :title")
	List<RemainLeave> findRemainLeaveByStaffTitle(@Param("title") String title);
	@Modifying
	@Query("DELETE FROM RemainLeave rl WHERE rl.staff = :staff")
	void deleteAllByStaff(@Param("staff") Staff staff);
}