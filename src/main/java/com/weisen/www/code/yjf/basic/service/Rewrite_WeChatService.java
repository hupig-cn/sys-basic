package com.weisen.www.code.yjf.basic.service;

public interface Rewrite_WeChatService {

	String scaningWeChat(String userid, String code);

	String queryWeChat(String userid);

	String queryWeChatUser(String code);
}
