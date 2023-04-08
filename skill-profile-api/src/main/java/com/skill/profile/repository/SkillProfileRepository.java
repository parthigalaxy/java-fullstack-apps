package com.skill.profile.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.skill.profile.model.SkillProfile;

@Repository
public interface SkillProfileRepository extends CassandraRepository<SkillProfile, String> {

	List<SkillProfile> findByAssociateId(String associateId);
	
}
