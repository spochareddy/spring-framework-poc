package com.neom.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neom.framework.entity.UserInfo;
import com.neom.framework.exception.BusinessException;
import com.neom.framework.repository.UserRepository;
import com.neom.framework.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUser(UserInfo user) {
		userRepository.save(user);
	}

	@Override
	public UserInfo getUserByNameAndPassword(String name, String password) throws BusinessException {
		UserInfo user = userRepository.findByUserNameAndPassword(name, password);

		if (user == null) {
			throw new BusinessException("Invalid id and password");
		}
		log.info("inside getUserByNameAndPassword method ::" + user.toString());
		return user;
	}

}
