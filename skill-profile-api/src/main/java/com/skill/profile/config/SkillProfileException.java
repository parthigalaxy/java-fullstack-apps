package com.skill.profile.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SkillProfileException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7481020899599828719L;

	public String message;
	
}
