package com.skill.tracker.processor.batch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skill.tracker.api.jaxb.types.SkillTrackerType;
import com.skill.tracker.processor.mapper.SkillTrackerCsvTypeMapper;
import com.skill.tracker.processor.model.SkillProfileCsvModel;
import com.skill.tracker.processor.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SkillTrackerProcessor implements ItemProcessor<SkillProfileCsvModel,SkillTrackerType> {

	@Autowired
	SkillTrackerCsvTypeMapper skillTrackerCsvTypeMapper;
	
	@Override
	public SkillTrackerType process(SkillProfileCsvModel item) throws Exception {
		log.info("SkillProfileProcessor >> process", JsonUtil.convertToString(item) );

		if(StringUtils.isBlank(item.getUpdateTime())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			item.setUpdateTime(sdf.format(Calendar.getInstance().getTime()));
		}
		
		return skillTrackerCsvTypeMapper.skillProfileCsvModelToSkillTrackerType(item);
	}

}
