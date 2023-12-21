package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Repository.LeaveTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypeServiceimpl implements LeaveTypeService{
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    @Transactional
    public List<String> findAllLeaveType(){
        return leaveTypeRepository.findAll().stream().map(leaveType -> leaveType.getLeaveTypeName()).toList();
    }
    @Transactional
    public LeaveType findLeaveTypeByName(String name){
        return leaveTypeRepository.findLeaveTypeByLeaveTypeName(name);
    }

    @Transactional
    public List<LeaveType> findAllLeaveTypeObj(){
        return leaveTypeRepository.findAll();
    }
}
