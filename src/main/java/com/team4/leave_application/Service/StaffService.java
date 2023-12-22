package com.team4.leave_application.Service;

import com.team4.leave_application.Model.Staff;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {
    Staff findStaffById(int id);
    List<Staff> findSubordinates(int managerid);
	List<String> findAllStaffIds();
	List<Staff> findAllStaff();
	Staff editStaff(Staff staff);
	Staff createStaff(Staff staff);
	void deleteStaff(Staff staff);
}
