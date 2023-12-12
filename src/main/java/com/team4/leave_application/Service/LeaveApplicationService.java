package com.team4.leave_application.Service;


import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.LeaveApplicationDTO;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.LeaveApplicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveApplicationService {

    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private LeaveApplicationRepository leaveAppRepo;

    public List<LeaveApplication> findHistory(Staff staff){
        return leaveAppRepo.findAllByStaff(staff);
    }

    // maybe can return boolean
    public LeaveApplication createApplication(Staff staff, LeaveApplicationDTO applicationDTO){
        var application = new LeaveApplication();
        application.setStaff(staff);
        application.setState("Applied");
        modelMapper.map(applicationDTO,application);
        return leaveAppRepo.save(application);

    }

    public LeaveApplication getApplication(int id){
        var application = leaveAppRepo.findById(id);
        return application;
    }

    public boolean deleteApplication(int id){
        var application = leaveAppRepo.findById(id);
        if (application == null || application.getState().equals("Approved") || application.getState().equals("Deleted") ){
            return false;
        }
        application.setState("Deleted");
        return true;
    }

    public boolean cancelApplication(int id){
        var application = leaveAppRepo.findById(id);
        if (application == null || application.getState().equals("cancel")){
            return false;
        }
        application.setState("cancel");
        return true;
    }

}
