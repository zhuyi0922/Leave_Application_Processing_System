package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String pwd);
}
