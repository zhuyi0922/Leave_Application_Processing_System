package com.team4.leave_application.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.team4.leave_application.Model.User;

@Service
public interface AdminService {
	User createUser(User user);
	void deleteUser(int userId);
	User updateUser(int userId, User user);
	List<User> findAllUsers();
}
