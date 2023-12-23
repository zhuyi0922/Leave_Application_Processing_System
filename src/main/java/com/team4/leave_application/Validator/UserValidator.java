package com.team4.leave_application.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team4.leave_application.Model.User;
@Component
public class UserValidator implements Validator {
	
	@Autowired
	private StaffValidator staffValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println(target);

	    //ValidationUtils.rejectIfEmpty(errors, "userId", "error.user.userid.empty");
	    //ValidationUtils.rejectIfEmpty(errors, "employeeId", "error.user.employeeid.empty");
	    ValidationUtils.rejectIfEmpty(errors, "username", "error.user.username.empty");
	    ValidationUtils.rejectIfEmpty(errors, "password", "error.user.password.empty");
	    User user = (User) target;
        if (user.getStaff() != null) {
            // Temporarily push the errors path
            errors.pushNestedPath("staff");
            ValidationUtils.invokeValidator(this.staffValidator, user.getStaff(), errors);
            // Pop the errors path
            errors.popNestedPath();
        }
        if (user.getRoleSet() == null || user.getRoleSet().isEmpty()) {
        	errors.rejectValue("roleSet", "error.user.roleset.empty");
        }
	}

}
