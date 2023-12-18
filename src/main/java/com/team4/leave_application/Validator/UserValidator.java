package com.team4.leave_application.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team4.leave_application.Model.User;
@Component
public class UserValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    System.out.println(target);
    
    //ValidationUtils.rejectIfEmpty(errors, "userId", "error.user.userid.empty");
    //ValidationUtils.rejectIfEmpty(errors, "employeeId", "error.user.employeeid.empty");
    ValidationUtils.rejectIfEmpty(errors, "name", "error.user.name.empty");
    ValidationUtils.rejectIfEmpty(errors, "password", "error.user.password.empty");
  }

}
