package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_000_CreateUserService {

	Result createUserByScan(String userId, String token, String accounttype);
	
	Result createUserByPhone(String userId, String phone);
}
