package com.team4.leave_application.Service;

import com.team4.leave_application.Model.LeaveApplication;
import com.team4.leave_application.Model.LeaveApplicationEventEnum;
import com.team4.leave_application.Model.LeaveType;
import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.LeaveApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
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
	public void deleteLeaveApplication(LeaveApplication application) {
        application.getStaff().getLeaveApplications().remove(application);
		leaveApplicationRepository.delete(application);
        leaveApplicationRepository.flush();
	}

    @Transactional
    public Page<LeaveApplication> findAll(PageRequest pageRequest){
        return leaveApplicationRepository.findAll(pageRequest);
    }

    @Transactional
    public boolean IsOverlap(Staff staff, Date start_date, Date end_date){
        List<LeaveApplication> leaveApplications = leaveApplicationRepository.findAllByStaff(staff);
        var applications = leaveApplications.stream().filter(x -> x.getApplication_status().equals(LeaveApplicationEventEnum.REJECTED) || x.getApplication_status().equals(LeaveApplicationEventEnum.CANCELLED)).toList();
        for(LeaveApplication leaveApplication:applications){
            if(leaveApplication.getStart_date().compareTo(start_date)<=0&&leaveApplication.getEnd_date().compareTo(start_date)>=0){
                return true;
            }
            if(leaveApplication.getStart_date().compareTo(end_date)<=0&&leaveApplication.getEnd_date().compareTo(end_date)>=0){
                return true;
            }
            if(leaveApplication.getStart_date().compareTo(start_date)>=0&&leaveApplication.getEnd_date().compareTo(end_date)<=0){
                return true;
            }
        }
        return false;
    }
    
    @Transactional
    public List<LeaveApplication> findApplicationsByLeaveTypeAndStaff(LeaveType leaveType, Staff staff){
    	return leaveApplicationRepository.findAllByLeaveTypeAndStaff(leaveType, staff);
    }
    
    @Transactional
    public void deleteAllLeaveApplicationsByStaff(Staff staff) {
    	leaveApplicationRepository.deleteAllByStaff(staff);
    }

}
