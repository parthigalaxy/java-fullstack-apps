package com.skill.tracker.processor.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.skill.tracker.processor.model.SkillAdmin;

@Repository
public interface SkillAdminRepository extends ElasticsearchRepository<SkillAdmin, String> {
	
	Optional<SkillAdmin> findById(String id);
	
	Page<SkillAdmin> findByNameLike(String name, Pageable pageable);
	
	Page<SkillAdmin> findByAssociateIdLike(String associateId, Pageable pageable);

	@Query("{\r\n"
			+ "	\"bool\": {\r\n"
			+ "		\"must\": [\r\n"
			+ "        { \"term\" : { \"skillLevals.skill\" : {\"value\": \"?0\"} } },\r\n"
			+ "        { \"range\" : { \"skillLevals.level\" : { \"gte\" : 10 } } }\r\n"
			+ "      ]\r\n"
			+ "	}\r\n"
			+ "}")
	Page<SkillAdmin> findBySkillLevalsSkill(String skill, Pageable pageable);

}
