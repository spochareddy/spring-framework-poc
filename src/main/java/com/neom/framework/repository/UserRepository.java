package com.neom.framework.repository;

import org.springframework.data.repository.CrudRepository;

import com.neom.framework.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, String> {
	
	UserInfo findByUserNameAndPassword(String userName, String password);
}
