package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    public Staff findByUsername(String username);

    boolean existsByUsername(String username);
}