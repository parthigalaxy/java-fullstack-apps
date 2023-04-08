package com.skill.tracker.processor.batch;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;
import com.skill.tracker.processor.mapper.SkillTrackerCsvTypeMapper;
import com.skill.tracker.processor.service.SkillTrackerService;
import com.skill.tracker.processor.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SkillTrackerWriter implements ItemWriter<SkillTrackerType>{

	@Autowired
	SkillTrackerService skillTrackerService;
	
	@Autowired
	SkillTrackerCsvTypeMapper skillTrackerCsvTypeMapper;
	
	@Override
	public void write(Chunk<? extends SkillTrackerType> chunk) throws Exception {

		log.info("SkillProfileWriter >> Start");
				
		List<? extends SkillTrackerType> skillTrackerTypes = chunk.getItems();
		
		log.info("SkillProfileWriter >> {}",JsonUtil.convertToString(skillTrackerTypes));
		
		for (SkillTrackerType skillTrackerType : skillTrackerTypes) {
			SkillTrackerResponse skillTrackerResponse = skillTrackerService.addNewSkill(skillTrackerCsvTypeMapper.skillTrackerTypeToSkillTrackerRequest(skillTrackerType));
			
			log.info("SkillTrackerResponse >> {} ",JsonUtil.convertToString(skillTrackerResponse));
		}
		
		log.info("SkillProfileWriter >> End");
		
	}

}
