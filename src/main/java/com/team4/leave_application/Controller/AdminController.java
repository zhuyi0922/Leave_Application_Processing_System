package com.team4.leave_application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.team4.leave_application.Service.*;
import com.team4.leave_application.Controller.Exception.*;
import com.team4.leave_application.Model.*;
import com.team4.leave_application.Validator.*;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class AdminController {
	@Autowired
	private LeaveTypeService leaveTypeService;
	@Autowired
	private UserService userService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private StaffValidator staffValidator;
	@Autowired
	private LeaveTypeValidator leaveTypeValidator;
	@Autowired
	private RoleValidator roleValidator;
	
	@GetMapping("users")
	public String adminPage() {
		return "admin-list";
	}
	
	/*
	 *--------------------------
	 * LeaveType CRUD Operations
	 * -------------------------
	 */
	
	@InitBinder("leaveType")
	private void initLeaveTypeBinder(WebDataBinder binder) {
	    binder.addValidators(leaveTypeValidator);
	  }
	
	@GetMapping("leavetype/list")
	public String leaveListPage(Model model) {
		List<LeaveType> leaveTypeList = leaveTypeService.findAllLeaveTypes();
		model.addAttribute("leaveTypeList", leaveTypeList);
		return "leavetype-list";
	}
	
	@GetMapping("leavetype/create")
	public String newLeaveTypePage(Model model) {
		model.addAttribute("leaveType", new LeaveType());
		model.addAttribute("ltidlist", leaveTypeService.findAllLeaveTypeIds());
		
		return "leavetype-new";
	}
	
	@PostMapping("leavetype/create")
	public String createNewLeaveType(@ModelAttribute @Valid LeaveType leaveType, BindingResult result, Model model){
		if (result.hasErrors()) {
			model.addAttribute("ltidlist", leaveTypeService.findAllLeaveTypeIds());
			return "leavetype-new";
		}
		
		leaveTypeService.createLeaveType(leaveType);
		
		String message ="New Leave Type" + leaveType.getLeaveTypeId() + "was successfully created";
		System.out.println(message);
		
		return "redirect:/admin/leavetype/list";
	}
	
	@GetMapping("leavetype/edit/{Id}")
	public String editLeaveTypePage(@PathVariable int Id, Model model) {
		 LeaveType leaveType = leaveTypeService.findLeaveTypeById(Id);
		 model.addAttribute("leaveType", leaveType);
		 model.addAttribute("ltidlist", leaveTypeService.findAllLeaveTypeIds());
		    
		 return "leavetype-edit";
	}

	@PostMapping("leavetype/edit/{Id}")
	public String editLeaveType(@ModelAttribute @Valid LeaveType leaveType, BindingResult result,  @PathVariable int Id) {
	    if (result.hasErrors()) {
	        return "leavetype-edit";
	      }
	    
	    leaveTypeService.editLeaveType(leaveType);
	    String message = "Leave Type was successfully updated.";
	    System.out.println(message);
	    
	    return "redirect:/admin/leavetype/list";
	}
	
	@GetMapping("leavetype/delete/{Id}")
	public String deleteLeaveType(@PathVariable int Id) throws LeaveTypeNotFound{
		LeaveType leaveType = leaveTypeService.findLeaveTypeById(Id);
		leaveTypeService.deleteLeaveType(leaveType);
		
	    String message = "The LeaveType " + leaveType.getLeaveTypeId() + " was successfully deleted.";
	    System.out.println(message);
	    
	    return "redirect:/admin/leavetype/list";
	}
	
	/*
	 *---------------------
	 * Role CRUD Operations
	 * --------------------
	 */
	@InitBinder("role")
	private void initRoleBinder(WebDataBinder binder) {
		binder.addValidators(roleValidator);
	}
	
	@GetMapping("role/list")
	public String roleListPage(Model model) {
	    List<Role> roleList = roleService.findAllRoles();
	    model.addAttribute("roleList", roleList);
	    
	    return "role-list";
	}
	
	@GetMapping("role/create")
	public String newRolePage(Model model) {
	  model.addAttribute("role", new Role());
		
		return "role-new";
	}
	
	@PostMapping("role/create")
	public String createNewRole(@ModelAttribute @Valid Role role, BindingResult result) {
		if (result.hasErrors()) {
			return "role-new";
		}

		String message = "New role " + role.getRoleId() + " was successfully created.";
		System.out.println(message);
		roleService.createRole(role);
		
		return "redirect:/admin/role/list";
	}
	
	@GetMapping("role/edit/{id}")
	public String editRolePage(@PathVariable String id, Model model) {
		Role role = roleService.findRole(id);
		model.addAttribute("role", role);
			
		return "role-edit";
	}
	
	@PostMapping("role/edit/{id}")
	public String editRole(@ModelAttribute @Valid Role role, BindingResult result, 
			@PathVariable String id) throws RoleNotFound {
		if (result.hasErrors()) {
			return "role-edit";
		}

		String message = "Role was successfully updated.";
		System.out.println(message);
		roleService.editRole(role);
		
		return "redirect:/admin/role/list";
	}

	@GetMapping("role/delete/{id}")
	public String deleteRole(@PathVariable String id)
			throws RoleNotFound {
		Role role = roleService.findRole(id);
		roleService.deleteRole(role);
		
		String message = "The role " + role.getRoleId() + " was successfully deleted.";
		System.out.println(message);
		
		return "redirect:/admin/role/list";
	}
	/*
	 *---------------------
	 * User CRUD Operations
	 * --------------------
	 */
	@InitBinder("user")
	private void initUserBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	@InitBinder("staff")
	private void initStaffBinder(WebDataBinder binder) {
		binder.addValidators(staffValidator);
	}
	
	@GetMapping("users/list")
	public String userListPage(Model model) {
		List<User> userList = userService.findAllUsers();
		model.addAttribute("userList", userList);
		return "user-list";
	}
	
	@GetMapping("users/create")
	public String newUserPage(Model model) {
	    model.addAttribute("user", new User());
	    List<Staff> staff = staffService.findAllStaff();
	    model.addAttribute("staff", staff);
	    List<Role> roles = roleService.findAllRoles();
	    model.addAttribute("roles", roles);
	    
	    return "user-new";
	}
	
	@PostMapping("users/create")
	public String createNewUser(@ModelAttribute @Valid User user, BindingResult userResult,
								Model model) {
		 if (userResult.hasErrors()) {
		        model.addAttribute("user", user); 
		        model.addAttribute("userResult", userResult);
		        model.addAttribute("roles", roleService.findAllRoles());
		        return "user-new";
		    }
		
		Staff newStaff = staffService.createStaff(user.getStaff());
		user.setStaff(newStaff);
		
		List<Role> newRoleSet = new ArrayList<Role>();
	    user.getRoleSet().forEach(role -> {
	      Role completeRole = roleService.findRole(role.getRoleId());
	      newRoleSet.add(completeRole);
	    });
	    user.setRoleSet(newRoleSet);

	    userService.createUser(user);
	    
	    return "redirect:/admin/users/list";
	}
	
	@GetMapping("users/edit/{userId}")
	public String editUserPage(@PathVariable int userId, Model model) {
		User user = userService.findUser(userId);
		model.addAttribute("user", user);	
	    List<Role> roles = roleService.findAllRoles();
	    model.addAttribute("roles", roles);
	    var currentRoles = user.getRoleSet();
	    model.addAttribute("currentRoles", currentRoles);
		return "user-edit";
	}
	
	@PostMapping("users/edit/{userId}")
	public String editUser(@ModelAttribute @Valid User user, BindingResult result, 
			@PathVariable int userId, Model model) throws RoleNotFound {
		if (result.hasErrors()) {
			var roles = roleService.findAllRoles();
			model.addAttribute("roles",roles);
			var currentRoles = user.getRoleSet() != null ? user.getRoleSet() : new ArrayList();
		    model.addAttribute("currentRoles", currentRoles);
			return "user-edit";
		}
		
		String message = "User was successfully updated.";
		System.out.println(message);
		userService.editUser(user);
		
		return "redirect:/admin/users/list";
	}
	
	@GetMapping("users/delete/{userid}")
	public String deleteUser(@PathVariable int userid) {
		User user = userService.findUser(userid);
		
		userService.deleteUser(user);
		staffService.deleteStaff(user.getStaff());
		
	    String message = "The user " + user.getUserId() + " was successfully deleted.";
	    System.out.println(message);
		
		return "redirect:/admin/users/list";
	}
}
