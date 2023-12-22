package com.team4.leave_application.Service;

import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class StaffServiceimpl implements StaffService{
    @Autowired
    private StaffRepository staffRepository;

    public Staff findStaffById(int id){
        return staffRepository.findById(id);
    }
    public List<Staff> findSubordinates(int managerid){
        return staffRepository.findByManagerId(managerid);
    }
    
    public List<String> findAllStaffIds() {
    	return staffRepository.findAllStaffIds();
    }
    
    @Transactional
    public List<Staff> findAllStaff(){
    	return staffRepository.findAll();
    }
    
    @Transactional
    public Staff createStaff(Staff staff) {
    	return staffRepository.save(staff);
    }
    @Transactional
    public Staff editStaff(Staff staff) {
    	return staffRepository.save(staff);
    }
    
    @Transactional
    public void deleteStaff(Staff staff) {
    	staffRepository.delete(staff);
    }
}
