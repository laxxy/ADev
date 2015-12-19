package com.audev.common.Entity.Annotation;

import com.audev.common.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by cosxt on 19.12.2015.
 */
public class EmailConstraintValidator implements ConstraintValidator<EmailValidation, String> {

    @Autowired
    private UserService userService;

    public void initialize(EmailValidation emailValidation) {

    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.getUserByEmail(s) == null;
    }
}
