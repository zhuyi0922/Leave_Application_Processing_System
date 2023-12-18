package com.team4.leave_application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.leave_application.Service.LeaveApplicationService;

@Controller
@RequestMapping(value="/manager")
public class ManagerLeaveController {
	
	@Autowired
	private LeaveApplicationService laService;
	
	@InitBinder
}
