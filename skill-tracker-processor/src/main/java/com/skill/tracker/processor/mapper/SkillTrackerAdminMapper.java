package com.skill.tracker.processor.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.skill.tracker.api.jaxb.types.Messages;
import com.skill.tracker.api.jaxb.types.Pageable;
import com.skill.tracker.api.jaxb.types.Response;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;
import com.skill.tracker.processor.model.SkillAdmin;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillTrackerAdminMapper {
	
	SkillAdmin skillTrackerTypeToSkillAdmin(SkillTrackerType skillTrackerType);
	
	List<SkillTrackerType> skillAdminToSkillTrackerTypes(List<SkillAdmin> skillAdmin);
	
	SkillTrackerResponse skillTrackerTypeToSkillTrackerResponse(List<SkillTrackerType> skillTrackers,Response response, Pageable pageable);
	
	Messages createMessages(String type,String message);
	
	Response createResponse(String status, String satusText, List<Messages> messages);

}
