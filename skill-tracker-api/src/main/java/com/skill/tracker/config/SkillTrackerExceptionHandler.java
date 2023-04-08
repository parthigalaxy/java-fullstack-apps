package com.skill.tracker.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class SkillTrackerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ SkillTrackerException.class })
	protected ResponseEntity<Object> handleSkillValidationException(final SkillTrackerException exception, WebRequest request) {
		SkillTrackerResponse skillTrackerResponse = new SkillTrackerResponse();
		skillTrackerResponse.setResponse(exception.getResponse());
		log.error("handleSkillValidationException > SkillProfileException > " + exception.getMessage() );
		return handleExceptionInternal(exception, skillTrackerResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	
}
