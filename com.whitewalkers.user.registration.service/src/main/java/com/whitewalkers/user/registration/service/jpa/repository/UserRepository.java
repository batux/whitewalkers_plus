package com.whitewalkers.user.registration.service.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.whitewalkers.user.registration.service.jpa.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	public UserEntity findByUserName(String userName);
	
}
