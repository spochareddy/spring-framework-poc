package com.neom.framework.service;

import java.util.Map;

import com.neom.framework.entity.UserInfo;

public interface JwtTokenGeneratorInterface {

	Map<String, String> generateToken(UserInfo user);
}
