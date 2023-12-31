package com.team4.leave_application.Service;

import com.team4.leave_application.Model.Role;
import com.team4.leave_application.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface UserService {
	
    User authenticate(String username, String password);

	User createUser(User user);

	void deleteUser(User user);

	User editUser(User user);

	User findByUsername(String username);
    
	Page<User> findAllUsers(PageRequest pageRequest);

	User findUser(int userId);

	List<Role> findRolesForUser(int userId);

	List<String> findRoleNamesForUser(int userId);
	
	List<String> findManagerNameByUID(int userId);

	void deleteUserById(int userId);
	
	public List<Integer> findAllStaffIdsByRoleName(String roleName);

	List<User> findUsersByRole(Role role);
}
