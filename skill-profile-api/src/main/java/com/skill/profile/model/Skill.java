package com.skill.profile.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@UserDefinedType(value = "skill")
public class Skill {

	@Column
	public String skill;
	@Column
	public Integer level;

}
