package com.team4.leave_application.Service;

import com.team4.leave_application.Model.OvertimeWork;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.OvertimeWorkRepository;

import java.util.List;

public interface OvertimeWorkService {

    OvertimeWork save(OvertimeWork overtimeWork);

    List<OvertimeWork> findAllByStaff(Staff staff);

    OvertimeWork findById(int id);
}
