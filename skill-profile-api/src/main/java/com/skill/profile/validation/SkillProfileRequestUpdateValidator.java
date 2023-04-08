package com.skill.profile.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.skill.profile.api.jaxb.types.SkillProfileRequest;

@Component
public class SkillProfileRequestUpdateValidator implements Validator {
	

	@Override
	public boolean supports(Class<?> clazz) {
		return SkillProfileRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "skillProfile", "skillProfile.empty");

		if (!errors.hasErrors()) {
			
			ValidationUtils.rejectIfEmpty(errors, "skillProfile.id", "SkillProfile Id Should not be Empty");

		}

	}

}
