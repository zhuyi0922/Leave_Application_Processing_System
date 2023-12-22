package com.team4.leave_application.Service;

import java.util.List;

import com.team4.leave_application.Model.Role;

public interface RoleService {

	List<Role> findAllRoles();
	
	Role findRole(String roleId);

	Role createRole(Role role);
	
	Role editRole(Role role);
	
	void deleteRole(Role role);
	
	List<String> findAllRolesNames();

	List<Role> findRoleByName(String name);

}
