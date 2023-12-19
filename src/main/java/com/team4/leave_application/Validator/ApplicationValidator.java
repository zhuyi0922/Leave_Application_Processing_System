package com.team4.leave_application.Validator;

import com.team4.leave_application.Model.LeaveApplication;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class ApplicationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return LeaveApplication.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
