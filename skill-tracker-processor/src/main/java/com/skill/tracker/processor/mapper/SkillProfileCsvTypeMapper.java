package com.skill.tracker.processor.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import com.skill.profile.api.jaxb.types.Skill;
import com.skill.profile.api.jaxb.types.SkillProfileType;
import com.skill.tracker.processor.model.SkillProfileCsvModel;
import com.skill.tracker.processor.util.SkillLevalHelper;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SkillProfileCsvTypeMapper {
	
	static String TECH = "TECH";
	static String NON_TECH = "NON_TECH";
	
	@Mapping(source = "skillLevals", target = "skillLevals", qualifiedByName = "skillLevals" )
	@Mapping(source = "nonTechnicalSkillLevals", target = "nonTechnicalSkillLevals", qualifiedByName = "nonTechnicalSkillLevals" )
	@Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'" )
	SkillProfileType skillProfileCsvModelToSkillProfileType(SkillProfileCsvModel skillProfileCsvModel);

	@Named("skillLevals")
	default List<Skill> skillLevals(String value){
		return skillLevelMapper(value, TECH);
	}

	default List<Skill> skillLevelMapper(String value, String type) {
		List<Skill> skills = new ArrayList<>();
		String[] skillLevels = value.split("\\|");
		for (String skillLevel : skillLevels) {
			String[] skillIdVal = skillLevel.split("-");
			skills.add(mapSkill(SkillLevalHelper.skillLevalsMap(skillIdVal[0],type),skillIdVal[1]));
		}
		return skills;
	}
	
	Skill mapSkill(String skill,String level);

	@Named("nonTechnicalSkillLevals")
	default List<Skill> nonTechnicalSkillLevals(String value){
		return skillLevelMapper(value, NON_TECH);
	}

}
