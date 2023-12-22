package com.team4.leave_application.Service;

import com.team4.leave_application.Model.Role;
import com.team4.leave_application.Model.User;
import com.team4.leave_application.Repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceimpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    public User authenticate(String username, String pwd) {
        return userRepository.findByUsernameAndPassword(username, pwd);
    }
    
    @Override
    public User findUser(int userId) {
    	return userRepository.findById(userId).orElse(null);
    }
    
    public List<Role> findRolesForUser(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        
        if (user == null) {
          return new ArrayList<Role>();
        }
        
        return user.getRoleSet();
      }
    
    public List<String> findRoleNamesForUser(int userId) {
        List<Role> roles = findRolesForUser(userId);
        
        List<String> roleNames = new ArrayList<>();
        roles.forEach(role -> roleNames.add(role.getName()));
        
        return roleNames;
      }
    
    public List<String> findManagerNameByUID(int userId) {
        return userRepository.findManagerNamesByUID(userId);
      }
    
    @Transactional
    @Override
    public User createUser(User user) {
    	return userRepository.save(user);
    }
    
    @Transactional
    @Override
    public void deleteUserById(int userId) {
    	User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setRoleSet(Collections.emptyList());
            userRepository.save(user);
            userRepository.delete(user);
        }
    }
    
    @Transactional
    @Override
    public User editUser(User user) {
    	return userRepository.save(user);
    }
    @Override
    public User findByUsername(String username) {
    	return userRepository.findByUsername(username);
    }
    @Override 
    public List<User> findAllUsers(){
    	return userRepository.findAll();
    }
    
    public void deleteUser(User user) {
        if (user != null) {
            Optional<User> existingUser = userRepository.findById(user.getUserId());
            if (existingUser.isPresent()) {
                User userToDelete = existingUser.get();

                userToDelete.setRoleSet(Collections.emptyList());
                userRepository.save(userToDelete);

                userRepository.delete(userToDelete);
            }
        }
    }
}
