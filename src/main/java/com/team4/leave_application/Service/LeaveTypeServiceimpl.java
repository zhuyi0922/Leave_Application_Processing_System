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

    @Override
    public List<String> findAllLeaveType(){
        return leaveTypeRepository.findAll().stream().map(leaveType -> leaveType.getLeaveTypeName()).toList();
    }
    //zhuyi
    @Transactional

    @Override
    public List<LeaveType> findAllLeaveTypes(){
    	return leaveTypeRepository.findAll();
    }

    @Override
    public LeaveType findLeaveTypeByName(String name){
        return leaveTypeRepository.findLeaveTypeByLeaveTypeName(name);
    }

    @Transactional
    public List<LeaveType> findAllByTitle(String staffTitle){
        return leaveTypeRepository.findAllByStaffTitle(staffTitle);
    }
    @Override
    public LeaveType findLeaveTypeById(int leaveTypeId) {
    	return leaveTypeRepository.findById(leaveTypeId).orElse(null);
    }

    @Override
    public List<String> findAllLeaveTypeIds() {
    	return leaveTypeRepository.findAllLeaveTypeIds();
    }
    //ruixian
    @Override
    public LeaveType createLeaveType(LeaveType leaveType) {
    	return leaveTypeRepository.saveAndFlush(leaveType);
    }

    @Override
    public void deleteLeaveType(LeaveType leaveType) {
    	leaveTypeRepository.delete(leaveType);
    }

    @Override
    public LeaveType editLeaveType(LeaveType leaveType) {
    	return leaveTypeRepository.saveAndFlush(leaveType);
    }

    @Override
    public LeaveType findByTitleAndName(String staffTitle, String leaveTypeName) {
    	return leaveTypeRepository.findByStaffTitleAndLeaveTypeName(staffTitle, leaveTypeName);
    }

}
