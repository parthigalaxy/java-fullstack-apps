package com.skill.tracker.server.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skill.tracker.server.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByUserNameAndPassword(String userName, String password);

}