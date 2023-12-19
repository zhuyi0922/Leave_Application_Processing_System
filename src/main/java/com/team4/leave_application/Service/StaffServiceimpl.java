package com.team4.leave_application.Service;

import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
