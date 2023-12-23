package com.team4.leave_application.Service;

import com.team4.leave_application.Model.OvertimeWork;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.OvertimeWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OvertimeWorkimpl implements OvertimeWorkService{
    @Autowired
    private OvertimeWorkRepository OvertimeWorkRepository;

    @Override
    public OvertimeWork save(OvertimeWork overtimeWork) {
        return OvertimeWorkRepository.save(overtimeWork);
    }

    @Override
    public List<OvertimeWork> findAllByStaff(Staff staff) {
        return OvertimeWorkRepository.findAllByStaffAndAndStatus(staff,"APPIIED");
    }

    @Override
    public OvertimeWork findById(int id) {
        return OvertimeWorkRepository.findByOtId(id);
    }
}
