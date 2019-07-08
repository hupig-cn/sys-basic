package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_CreateUserService {

	Result createUserByScan(String userId, String token, String accounttype, String recommendId, String coordinate);
	
	Result createUserByPhone(String userId, String phone);
	
	Result createUserByScanning(String userId, String phone, String referrer);
	
}
