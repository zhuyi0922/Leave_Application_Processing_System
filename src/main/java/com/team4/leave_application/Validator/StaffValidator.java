package com.team4.leave_application.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team4.leave_application.Model.Staff;
@Component
public class StaffValidator implements Validator {
	  @Override
	  public boolean supports(Class<?> clazz) {
	    return Staff.class.isAssignableFrom(clazz);
	  }
	  
	  @Override
	  public void validate(Object target, Errors errors) {
	    System.out.println(target);
	    
	    //ValidationUtils.rejectIfEmpty(errors, "userId", "error.user.userid.empty");
	    //ValidationUtils.rejectIfEmpty(errors, "employeeId", "error.user.employeeid.empty");
	    ValidationUtils.rejectIfEmpty(errors, "id", "error.staff.id.empty");
	    ValidationUtils.rejectIfEmpty(errors, "firstName", "error.staff.firstName.empty");
	    ValidationUtils.rejectIfEmpty(errors, "lastName", "error.staff.lastName.empty");
	    ValidationUtils.rejectIfEmpty(errors, "email", "error.staff.email.empty");
	    ValidationUtils.rejectIfEmpty(errors, "gender", "error.staff.gender.empty");
	    ValidationUtils.rejectIfEmpty(errors, "title", "error.staff.title.empty");
	  }
}
