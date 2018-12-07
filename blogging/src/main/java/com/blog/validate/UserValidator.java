package com.blog.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.blog.dto.request.RegisterUserRequest;

/**
 * This is a helper class to validate login access of a user.
 * Specific business logic of putting in checks around user/password validation can go here.
 *
 */
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUserRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterUserRequest user = (RegisterUserRequest) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

//        if (!user.getConfirmPassword().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }
    }
}
