package com.team4.leave_application.Service;

import com.team4.leave_application.Model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User authenticate(String username, String password);
}
