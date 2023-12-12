package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorsRepository extends JpaRepository<Administrator, Integer> {

    Administrator findByUsername(String username);

    boolean existsByUsername(String username);
}