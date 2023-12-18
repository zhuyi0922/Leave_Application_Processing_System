package com.team4.leave_application.Model;

import java.util.List;
import lombok.*;

@Getter
@Setter
public class UserSession {
	private User user;
	private Staff staff;
	private List<Staff> subordinates;
}
