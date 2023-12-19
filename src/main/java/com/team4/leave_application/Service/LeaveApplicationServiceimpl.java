package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Repository.LeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveApplicationServiceimpl implements LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;
    public LeaveApplication save(LeaveApplication leaveApplication){
        return leaveApplicationRepository.save(leaveApplication);
    }

    public LeaveApplication findById(int id){
        return leaveApplicationRepository.findById(id);
    }

    public List<LeaveApplication> findPendingApplicationByStaffID(int staffId) {
        return leaveApplicationRepository.findPendingApplicationByStaffId(staffId);
    }
}
