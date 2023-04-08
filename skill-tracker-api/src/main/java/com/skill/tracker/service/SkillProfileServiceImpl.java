package com.skill.tracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.skill.profile.api.jaxb.types.SkillProfileRequest;
import com.skill.profile.api.jaxb.types.SkillProfileResponse;
import com.skill.tracker.api.jaxb.types.Response;
import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.mapper.SkillProfileMapper;
import com.skill.tracker.repository.SkillProfileApiClient;
import com.skill.tracker.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SkillProfileServiceImpl implements SkillProfileService{

	@Autowired
	SkillProfileMapper skillProfileMapper;
	
	@Autowired
	SkillProfileApiClient skillProfileApiClient;
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@Override
	public SkillTrackerResponse addNewProfile(SkillTrackerRequest skillTrackerRequest) {
		log.info("addNewProfile >>");
		
		SkillProfileRequest skillProfileRequest = skillProfileMapper.skillTrackerRequestToSkillProfileRequest(skillTrackerRequest);
		
		SkillProfileResponse skillProfileResponse = skillProfileApiClient.addProfile(skillProfileRequest);
		
		log.info("SkillProfileResponse >> {}", JsonUtil.convertToString(skillProfileResponse));
		
		SkillTrackerResponse skillTrackerResponse = skillProfileMapper.skillProfileResponseToSkillTrackerResponse(skillProfileResponse);
		
		if(isSuccessResponse(Optional.of(skillTrackerResponse))) {
			kafkaTemplate.send("Skll_Profile_Admin", skillTrackerResponse.getSkillTrackers().get(0));
		}
		
		return skillTrackerResponse;
	}

	private boolean isSuccessResponse(Optional<SkillTrackerResponse> skillTrackerResponseO) {
		
		Optional<String> statusO = skillTrackerResponseO
				.map(SkillTrackerResponse::getResponse)
				.map(Response::getStatus);

		return statusO.filter(status -> "SUCCESS".equals(status)).isPresent();
	}

	@Override
	public SkillTrackerResponse updateProfile(SkillTrackerRequest skillTrackerRequest) {
		log.info("updateProfile >>");
		
		SkillProfileRequest skillProfileRequest = skillProfileMapper.skillTrackerRequestToSkillProfileRequest(skillTrackerRequest);
		
		SkillProfileResponse skillProfileResponse = skillProfileApiClient.updateProfile(skillProfileRequest);
		
		log.info("SkillProfileResponse >> {}", JsonUtil.convertToString(skillProfileResponse));
		
		SkillTrackerResponse skillTrackerResponse  = skillProfileMapper.skillProfileResponseToSkillTrackerResponse(skillProfileResponse);
		
		if(isSuccessResponse(Optional.of(skillTrackerResponse))) {
			kafkaTemplate.send("Skll_Profile_Update", skillTrackerResponse.getSkillTrackers().get(0));
		}
		
		return skillTrackerResponse;
	}
	
}
