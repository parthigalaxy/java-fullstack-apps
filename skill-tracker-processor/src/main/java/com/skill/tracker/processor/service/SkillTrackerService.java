package com.skill.tracker.processor.service;

import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;

public interface SkillTrackerService {

	public SkillTrackerResponse addNewSkill(SkillTrackerRequest skillTrackerRequest);
	
}
