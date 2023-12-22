package com.team4.leave_application.Service;

import com.team4.leave_application.Model.Role;
import com.team4.leave_application.Repository.RoleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceimpl implements RoleService{
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAllRoles(){
		return roleRepository.findAll();
	}
	
	public Role findRole(String roleId) {
		return roleRepository.findById(roleId).orElse(null);
	}
	
	public Role createRole(Role role) {
		return roleRepository.saveAndFlush(role);
	}
	
	public Role editRole(Role role) {
		return roleRepository.saveAndFlush(role);
	}
	
	public void deleteRole(Role role) {
		roleRepository.delete(role);
	}
	
	public List<String> findAllRolesNames() {
		return roleRepository.findAllRolesNames();
	}
	
	public List<Role> findRoleByName(String name) {
		return roleRepository.findRoleByName(name);
	}	
}
