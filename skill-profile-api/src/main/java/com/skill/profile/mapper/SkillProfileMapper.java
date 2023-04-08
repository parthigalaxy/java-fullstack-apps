package com.skill.profile.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.skill.profile.api.jaxb.types.SkillProfileType;
import com.skill.profile.model.SkillProfile;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface SkillProfileMapper {

	@Mapping(target = "id",conditionExpression = "java( null != id && !\"\".equals(id) )", defaultExpression = "java(UUID.randomUUID().toString())")
	SkillProfile skillProfileTypeToSkillProfile(SkillProfileType skillProfileType);
	
	SkillProfileType skillProfileToSkillProfileType(SkillProfile skillProfile);
	
}
