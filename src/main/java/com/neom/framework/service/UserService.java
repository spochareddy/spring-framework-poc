package com.neom.framework.service;

import com.neom.framework.entity.UserInfo;
import com.neom.framework.exception.BusinessException;

public interface UserService {

	void saveUser(UserInfo user);

	UserInfo getUserByNameAndPassword(String name, String password) throws BusinessException;
}
