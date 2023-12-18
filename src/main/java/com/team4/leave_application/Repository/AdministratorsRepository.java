package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorsRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    boolean existsByUsername(String username);
}