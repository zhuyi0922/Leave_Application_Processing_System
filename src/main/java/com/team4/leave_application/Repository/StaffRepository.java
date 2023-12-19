package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    List<Staff> findByManagerId(int managerid);

    Staff findById(int id);
}