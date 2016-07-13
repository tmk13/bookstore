package com.apress.bookstore.validator;

import com.apress.bookstore.dto.RegistrationUserFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.apress.bookstore.service.UserService;

@Service
public class RegisterUserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return RegistrationUserFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
		RegistrationUserFormDTO registrationUserFormDTO = (RegistrationUserFormDTO) obj;

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required",
//				"Nazwa nie może być pusta!");
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required",
//				"Hasło nie może być puste!");

//		if (errors.hasFieldErrors("name") || errors.hasFieldErrors("password"))
//			return;

		if (userService.isUserNameExist(registrationUserFormDTO))
			errors.rejectValue("name", "", "Nazwa niedostępna!");
    }


}
