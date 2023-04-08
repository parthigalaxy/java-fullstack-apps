package com.skill.tracker.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.processor.repository.SkillTrackerApiClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SkillTrackerServiceImpl implements SkillTrackerService {
	
	@Autowired
	SkillTrackerApiClient skillTrackerApiClient;

	@Override
	public SkillTrackerResponse addNewSkill(SkillTrackerRequest skillTrackerRequest) {
		log.info("addNewSkill >> Start");

		SkillTrackerResponse skillTrackerResponse = null;
		try {
			skillTrackerResponse = skillTrackerApiClient.addNewProfile(skillTrackerRequest);			
		}catch(Exception ex) {
			log.error("Exception while add new profile", ex);
		}
		
		log.info("addNewSkill >> End >> {}", skillTrackerResponse);
		return skillTrackerResponse;
	}

}
