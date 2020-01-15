package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_AlipayService {

	String scaning(String userid, String authCode);

	String queryAlipay(String userid);
	
	// 重写查询支付宝绑定状态
	Result queryAlipay2(String userid);

	String queryAlipayUser(String authCode);
}
