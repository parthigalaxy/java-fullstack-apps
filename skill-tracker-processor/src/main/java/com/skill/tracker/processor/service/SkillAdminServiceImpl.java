package com.skill.tracker.processor.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.skill.tracker.api.jaxb.types.Pageable;
import com.skill.tracker.api.jaxb.types.Response;
import com.skill.tracker.api.jaxb.types.SkillTrackerResponse;
import com.skill.tracker.api.jaxb.types.SkillTrackerType;
import com.skill.tracker.processor.config.SkillTrackerProcessorException;
import com.skill.tracker.processor.mapper.SkillTrackerAdminMapper;
import com.skill.tracker.processor.model.SkillAdmin;
import com.skill.tracker.processor.repository.SkillAdminRepository;
import com.skill.tracker.processor.util.JsonUtil;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SkillAdminServiceImpl implements SkillAdminService {

	@Autowired
	SkillAdminRepository skillAdminRepository;

	@Autowired
	SkillTrackerAdminMapper skillTrackerAdminMapper;

	@Autowired
	ElasticsearchClient elasticsearchClient;
	
	public SkillTrackerResponse createDocument(SkillTrackerType skillTrackerType) {

		SkillAdmin skillAdmin = skillTrackerAdminMapper.skillTrackerTypeToSkillAdmin(skillTrackerType);

		SkillAdmin skillAdminRes = skillAdminRepository.save(skillAdmin);

		log.info("skillAdminRes >> " + JsonUtil.convertToString(skillAdminRes));

		List<SkillTrackerType> skillTrackerTypes = skillTrackerAdminMapper
				.skillAdminToSkillTrackerTypes(Arrays.asList(skillAdminRes));

		Response response = skillTrackerAdminMapper.createResponse("SUCCESS", "Skill updated successfully", null);

		return skillTrackerAdminMapper.skillTrackerTypeToSkillTrackerResponse(skillTrackerTypes, response, null);
	}

	@Override
	public SkillTrackerResponse retrieveDocuments(String criteria, String criteriaValue, int pageStart) {

		log.info("retrieveDocuments >> {} >> {} ", criteria, criteriaValue);

		List<SkillTrackerType> skillTrackerTypes = null;
		Page<SkillAdmin> skillAdminPage = null;

		switch (criteria) {

		case "NAME":
			skillAdminPage = skillAdminRepository.findByNameLike(criteriaValue, PageRequest.of(pageStart, 5));
			break;
		case "ASSOCIATEID":
			skillAdminPage = skillAdminRepository.findByAssociateIdLike(criteriaValue, PageRequest.of(pageStart, 5));
			break;
		case "SKILL":
			skillAdminPage = skillAdminRepository.findBySkillLevalsSkill(criteriaValue,
					PageRequest.of(pageStart, 5));
			break;
		case "ALL":
			skillAdminPage = skillAdminRepository.findAll(PageRequest.of(pageStart, 5));
			break;
		default:
			throw new SkillTrackerProcessorException("Search supports By NAME, ASSOCIATEID or SKILL");
		}

		skillTrackerTypes = skillTrackerAdminMapper.skillAdminToSkillTrackerTypes(skillAdminPage.getContent());
		
		Pageable pageable = new Pageable();
		pageable.setNumber(skillAdminPage.getNumber());
		pageable.setNumberOfElements(skillAdminPage.getNumberOfElements()); 
		pageable.setSize(skillAdminPage.getSize());
		pageable.setTotalElements(skillAdminPage.getTotalElements()); 
		pageable.setTotalPages(skillAdminPage.getTotalPages());

		Response response = skillTrackerAdminMapper.createResponse("SUCCESS", "Skill details retrieved successfully",
				null);

		return skillTrackerAdminMapper.skillTrackerTypeToSkillTrackerResponse(skillTrackerTypes, response, pageable);
	}
	
}
