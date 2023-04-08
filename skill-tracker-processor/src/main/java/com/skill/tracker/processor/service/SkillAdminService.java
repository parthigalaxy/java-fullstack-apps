package com.skill.tracker.processor.service;

import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;

public interface SkillAdminService {

	public SkillTrackerResponse createDocument(SkillTrackerType skillTrackerType);
	
	public SkillTrackerResponse retrieveDocuments(String criteria, String criteriaValue, int pageStart);
	
}
