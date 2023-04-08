package com.skill.tracker.config;

import com.skill.tracker.api.jaxb.types.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SkillTrackerException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7125528436364503633L;
	
	public String message;
	
	public Response response;

}
