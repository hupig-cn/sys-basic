package com.weisen.www.code.yjf.basic.service;

public interface Rewrite_AlipayService {

	String scaning(String userid, String authCode);

	String queryAlipay(String userid);

	String queryAlipayUser(String authCode);
}