package com.team4.leave_application.Repository;
import com.team4.leave_application.Model.OvertimeWork;
import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OvertimeWorkRepository extends JpaRepository<OvertimeWork, Integer> {
    List<OvertimeWork> findAllByStaffAndAndStatus(Staff staff, String status);
    OvertimeWork findByOtId(int id);
}
