package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Model.RemainLeave;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.RemainLeaveRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemainLeaveServiceimpl implements RemainLeaveService{
    @Autowired
    private RemainLeaveRepository remainLeaveRepository;
    @Transactional
    public int findRemainLeave(Staff staff, LeaveType leaveType){
        var remainLeave = remainLeaveRepository.findByStaffAndLeaveType(staff, leaveType);
        return remainLeave.getRemainLeave();
    }
    @Transactional
    public RemainLeave findRemainLeaveObj(Staff staff, LeaveType leaveType){
        return remainLeaveRepository.findByStaffAndLeaveType(staff, leaveType);
    }
    @Transactional
    public void updateRemainLeave(RemainLeave remainLeave){
        remainLeaveRepository.saveAndFlush(remainLeave);
    }
    @Transactional
    public void updateRemainLeaves(LeaveType leaveType, int maxLeaveDay) {
        List<RemainLeave> leaveTypeRecords = remainLeaveRepository.findRemainLeaveByLeaveType(leaveType);
        for (RemainLeave record : leaveTypeRecords) {
            record.setRemainLeave(maxLeaveDay);
            remainLeaveRepository.save(record);
        }
    }
    @Transactional
    @Override
    public void deleteRemainLeaves(int remainLeaveId) {
    	remainLeaveRepository.deleteById(remainLeaveId);
    }
    @Transactional
    public void deleteRemainLeavesByStaffAndLeaveType(Staff staff, LeaveType leaveType) {
    	RemainLeave remainLeave = remainLeaveRepository.findByStaffAndLeaveType(staff, leaveType);
        staff.getRemainLeaves().remove(remainLeave);
    	remainLeaveRepository.delete(remainLeave);
    }
    @Transactional
	public void deleteRemainLeavesByStaff(Staff staff) {
    	remainLeaveRepository.deleteAllByStaff(staff);
    }
}
