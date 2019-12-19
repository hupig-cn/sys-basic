package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_CreateUserService {

	Result createUserByPhone(String userId, String phone);

	Result createUserByScanning(String userId, String phone, String referrer);

	String createUserByScanningMerchant(String userid, String token, String accounttype);

	String createUserByShareLink(String userid, String token, String accounttype, String articleid);

}
