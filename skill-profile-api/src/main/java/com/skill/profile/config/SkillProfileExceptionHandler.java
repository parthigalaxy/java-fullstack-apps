package com.skill.profile.config;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.skill.profile.api.jaxb.types.SkillProfileResponse;
import com.skill.profile.util.SkillProfileResponseHelper;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class SkillProfileExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ SkillProfileException.class })
	protected ResponseEntity<Object> handleSkillValidationException(final SkillProfileException exception, WebRequest request) {
		String message = exception.getMessage();
		SkillProfileResponse skillProfileResponse = SkillProfileResponseHelper.createSkillProfileResponse("ERROR", message);
		log.error("handleSkillValidationException > SkillProfileException > " + message );
		return handleExceptionInternal(exception, skillProfileResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ ValidationException.class})
	protected ResponseEntity<Object> handleValidationException(final ValidationException exception, WebRequest request) {
		String message = exception.getLocalizedMessage();
		SkillProfileResponse skillProfileResponse = SkillProfileResponseHelper.createSkillProfileResponse("ERROR", message);
		log.error("handleValidationException > ValidationException > " + message );
		return handleExceptionInternal(exception, skillProfileResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String[] errorMessage = prepareErrorMessages(exception);
		SkillProfileResponse skillProfileResponse = SkillProfileResponseHelper.createSkillProfileResponse("ERROR", "Error while validation", "VALIDATION", errorMessage);
		log.error("handleMethodArgumentNotValid > MethodArgumentNotValidException > " + errorMessage );
		return handleExceptionInternal(exception, skillProfileResponse,  new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private String[] prepareErrorMessages(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getFieldErrors();
		String[] errorMessage = new String[fieldErrors.size()];
		int count = 0;
		for (FieldError fieldError : fieldErrors) {
			errorMessage[count++] = fieldError.getField() +": "+ fieldError.getCode();
		}
		return errorMessage;
	}
	
}
