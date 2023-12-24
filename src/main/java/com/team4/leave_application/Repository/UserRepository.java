package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.BindParam;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    
	User findByUsernameAndPassword(String username, String pwd);
	
	@Query("SELECT s2.firstName FROM User u JOIN u.staff s1 JOIN Staff s2 ON s1.managerId = s2.id WHERE u.userId = :uid")
	List<String> findManagerNamesByUID(@BindParam("uid") int uid);
	
	@Query("SELECT u FROM User u JOIN u.roleSet r WHERE r.name = :roleName")
    List<User> findAllUsersByRoleName(@Param("roleName") String roleName);
}
