package com.team4.leave_application.Service;

import com.team4.leave_application.Model.Staff;
import com.team4.leave_application.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {
    Staff findStaffById(int id);
    List<Staff> findSubordinates(int managerid);
}
