package com.team4.leave_application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.team4.leave_application.Service.AdminService;
import com.team4.leave_application.Model.User;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("users")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = adminService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int userId) {
        adminService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody User user) {
        User updatedUser = adminService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = adminService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
}
