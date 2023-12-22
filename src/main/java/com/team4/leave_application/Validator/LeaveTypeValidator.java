package com.team4.leave_application.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team4.leave_application.Model.LeaveType;

@Component
public class LeaveTypeValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return LeaveType.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
	LeaveType leaveType = (LeaveType) target;
    System.out.println(target);
    
    ValidationUtils.rejectIfEmpty(errors, "leaveTypeId", "error.leavetype.leavetypeid.empty");
    ValidationUtils.rejectIfEmpty(errors, "leaveTypeName", "error.leavetype.leavetypename.empty");
    ValidationUtils.rejectIfEmpty(errors, "staffTitle", "error.leavetype.stafftitle.empty");
    
    if (leaveType.getMaxLeaveDay() <= 0) {
        errors.rejectValue("maxLeaveDay", "error.leavetype.maxLeaveDay.negative");
    }
  }

}
