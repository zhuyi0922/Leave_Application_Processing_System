package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    List<Staff> findByManagerId(int managerid);

    Staff findById(int id);
    
    @Query("SELECT DISTINCT s.Id FROM Staff s")
    List<String> findAllStaffIds();
    
    List<Staff> findByTitle(String staffTitle);


}