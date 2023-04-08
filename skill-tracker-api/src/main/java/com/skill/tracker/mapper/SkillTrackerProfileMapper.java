package com.skill.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.skill.tracker.api.jaxb.types.Response;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillTrackerProfileMapper {
	
	SkillTrackerType skillTrackerToSkillTracker(SkillTrackerType skillTrackerType);
	
	Response profileResponseToResponse(com.skill.profile.api.jaxb.types.Response skillProfileResponse);

}
