package com.skill.tracker.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SKILL_USER")
public class User {

	@Id
	private Integer userId;

	@Column
	private String userName;

	@Column
	private String password;

	@Column
	private String role;

}
