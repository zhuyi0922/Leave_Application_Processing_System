package com.team4.leave_application.Service;

import java.util.List;
import com.team4.leave_application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team4.leave_application.Model.User;

@Service
public class AdminServiceimpl implements AdminService{
    
	@Autowired
	private UserRepository userRepository;
	
    @Override
    public User createUser(User user) {
    	return userRepository.save(user);
    }
    
    @Override
    public void deleteUser(int userId) {
    	userRepository.deleteById(userId);
    }
    
    @Override
    public User updateUser(int userId, User user) {
    	return userRepository.save(user);
    }
    
    @Override 
    public List<User> findAllUsers(){
    	return userRepository.findAll();
    }
}
