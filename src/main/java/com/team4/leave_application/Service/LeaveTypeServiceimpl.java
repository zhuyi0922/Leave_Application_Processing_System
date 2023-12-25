package com.team4.leave_application.Service;

import com.team4.leave_application.Model.*;
import com.team4.leave_application.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveTypeServiceimpl implements LeaveTypeService{
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    @Autowired
    private RemainLeaveRepository remainLeaveRepository;
    @Autowired
    private StaffRepository staffRepository;
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
    
    public int calculateRemainLeaveChange(int maxLeaveDayPre, int maxLeaveDayAft) {
    	return (maxLeaveDayAft - maxLeaveDayPre);
    }
    
    public boolean existsByLeaveType(LeaveType leaveType) {
    	return remainLeaveRepository.existsByLeaveType(leaveType);
    }
    
    @Override
    public void createRemainLeave(LeaveType leaveType) {
    	List<Staff> staffList = staffRepository.findByTitle(leaveType.getStaffTitle());
    	List<RemainLeave> remainLeaves = new ArrayList<>();
    	for (Staff staff : staffList) {
    		RemainLeave remainLeave = new RemainLeave();
    		remainLeave.setLeaveType(leaveType);
            remainLeave.setStaff(staff);
            remainLeave.setRemainLeave(leaveType.getMaxLeaveDay());
            remainLeaves.add(remainLeave);
    	}
    	remainLeaveRepository.saveAll(remainLeaves);
    }
    
    @Transactional
    public void editRemainLeave(int remainLeaveChange, LeaveType leaveType) {
    	List<RemainLeave> leaveTypeRecords = remainLeaveRepository.findRemainLeaveByLeaveType(leaveType);
    	if (leaveTypeRecords != null && !leaveTypeRecords.isEmpty()) {
    		for (RemainLeave records : leaveTypeRecords) {
    			int newRemainLeave = records.getRemainLeave() + remainLeaveChange;    			
    			records.setRemainLeave(newRemainLeave);
    			remainLeaveRepository.save(records);
    		}
    	}
    }
}
