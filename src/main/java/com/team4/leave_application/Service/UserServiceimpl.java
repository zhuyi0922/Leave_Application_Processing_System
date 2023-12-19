package com.team4.leave_application.Service;

import com.team4.leave_application.Model.User;
import com.team4.leave_application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    public User authenticate(String username, String pwd) {
        return userRepository.findByUsernameAndPassword(username, pwd);
    }
}
