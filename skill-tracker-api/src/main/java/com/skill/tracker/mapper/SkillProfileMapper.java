package com.skill.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.skill.profile.api.jaxb.types.SkillProfileRequest;
import com.skill.profile.api.jaxb.types.SkillProfileResponse;
import com.skill.tracker.api.jaxb.types.SkillTrackerRequest;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillProfileMapper {
	
	@Mapping(source =  "skillTracker", target = "skillProfile")
	SkillProfileRequest skillTrackerRequestToSkillProfileRequest(SkillTrackerRequest skillTrackerRequest);
	
	@Mapping(source =  "skillProfiles", target = "skillTrackers")
	SkillTrackerResponse skillProfileResponseToSkillTrackerResponse(SkillProfileResponse skillProfileResponse);

}
