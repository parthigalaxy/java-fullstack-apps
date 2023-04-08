package com.skill.tracker.processor.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SkillTrackerProcessorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3486923923464716300L;

	public String message;
	
}
