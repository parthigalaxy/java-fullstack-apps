package com.skill.tracker.service;

import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;

public interface SkillProfileService {
	
	public SkillTrackerResponse addNewProfile(SkillTrackerRequest skillTrackerRequest);
	
	public SkillTrackerResponse updateProfile(SkillTrackerRequest skillTrackerRequest);
}
