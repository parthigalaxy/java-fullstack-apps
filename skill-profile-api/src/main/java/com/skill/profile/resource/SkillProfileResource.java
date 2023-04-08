package com.skill.profile.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.profile.api.jaxb.types.SkillProfileRequest;
import com.skill.profile.api.jaxb.types.SkillProfileResponse;
import com.skill.profile.config.SkillProfileException;
import com.skill.profile.service.SkillProfileService;
import com.skill.profile.validation.SkillProfileRequestUpdateValidator;
import com.skill.profile.validation.SkillProfileRequestValidator;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/profile")
@Slf4j
public class SkillProfileResource extends BaseResource {

	@Autowired
	SkillProfileService skillProfileService;

	@Autowired
	SkillProfileRequestUpdateValidator validator;

	@PostMapping("add")
	public ResponseEntity<SkillProfileResponse> addProfile(
			@Validated @RequestBody SkillProfileRequest skillProfileRequest) {
		log.info("addProfile - start");

		SkillProfileResponse skillProfileResponse = skillProfileService.addNewProfile(skillProfileRequest);

		return resolveResponse(skillProfileResponse, HttpStatus.OK);
	}

	@PostMapping("update")
	public ResponseEntity<SkillProfileResponse> updateProfile(
			@Validated @RequestBody SkillProfileRequest skillProfileUpdateRequest, Errors errors) {
		log.info("updateProfile - start");
		validator.validate(skillProfileUpdateRequest, errors);
		if (errors.hasErrors()) {
			var errorMessage = "";
			for (ObjectError objectError : errors.getAllErrors()) {
				var fieldName = ((FieldError) objectError).getField();
				errorMessage += fieldName + " : " + objectError.getCode() + "  ";
			}
			throw new SkillProfileException("Error While updating profile details : " + errorMessage);
		}

		SkillProfileResponse skillProfileResponse = skillProfileService.updateProfile(skillProfileUpdateRequest);

		return resolveResponse(skillProfileResponse, HttpStatus.OK);
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new SkillProfileRequestValidator());
	}

}
