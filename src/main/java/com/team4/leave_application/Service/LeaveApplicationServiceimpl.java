package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.LeaveApplicationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class LeaveApplicationServiceimpl implements LeaveApplicationService {
	
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;
    
    @Transactional
    public LeaveApplication save(LeaveApplication leaveApplication){
        return leaveApplicationRepository.saveAndFlush(leaveApplication);
    }
    
    @Transactional
    public LeaveApplication findById(int id){
        return leaveApplicationRepository.findByLeaveApplicationId(id);
    }
    
    @Transactional
    public Page<LeaveApplication> findApplicationsByStaffInPage(Staff staff, PageRequest pageRequest){
        return leaveApplicationRepository.findAllByStaff(staff,pageRequest);
    }

    @Transactional
    public List<LeaveApplication> findApplicationsByStaff(Staff staff){
        return leaveApplicationRepository.findAllByStaff(staff);
    }

    @Transactional
    public List<LeaveApplication> findPendingApplicationByStaffID(int staffId) {
        return leaveApplicationRepository.findPendingApplicationByStaffId(staffId);
    }
    @Transactional
    public LeaveApplication update(LeaveApplication leaveApplication) {
        return leaveApplicationRepository.saveAndFlush(leaveApplication);
    }

	@Transactional
	public List<LeaveApplication> findApplicationsByStaffId(int staffid) {
		return leaveApplicationRepository.findApplicationsByStaffId(staffid);
	}

	@Transactional
	public LeaveApplication changeLeaveApplication(LeaveApplication leaveApplication) {
		return leaveApplicationRepository.saveAndFlush(leaveApplication);	
	}
	
	@Transactional
	public void deleteLeaveApplication(int leaveApplicationId) {
		leaveApplicationRepository.deleteById(leaveApplicationId);
	}
}
