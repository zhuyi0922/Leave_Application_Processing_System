package com.team4.leave_application.Service;

import com.team4.leave_application.Repository.AdministratorsRepository;
import com.team4.leave_application.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoService {
    @Autowired
    private StaffRepository staffRepo;
    private AdministratorsRepository adminRepo;
    public boolean validateUser(String username, String password, String role){
        if (role == "admin"){
            var admin = adminRepo.findByUsername(username);
            return admin.getPassword().equals(password);
        }
        else{
            var staff = staffRepo.findByUsername(username);
            return staff.getPassword().equals(password);
        }
    }

    public boolean UserIsExit(String username, String role){
        return role.equals("admin") ? adminRepo.existsByUsername(username) : staffRepo.existsByUsername(username);
    }


}
