package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_WeChatService {

	String scaningWeChat(String userid, String code);

	String queryWeChat(String userid);

	String queryWeChatUser(String code);

	// 重写查询用户绑定微信
	Result queryWeChat2(String userid);
}
