package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String pwd);
}
