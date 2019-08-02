package com.weisen.www.code.yjf.basic.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.domain.Userlocation;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkaccountRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlocationRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_WeChatService;
import com.weisen.www.code.yjf.basic.util.AlipayUtil;
import com.weisen.www.code.yjf.basic.util.DateUtils;

@Service
@Transactional
public class Rewrite_WeChatServiceImpl implements Rewrite_WeChatService {

	private final Rewrite_LinkaccountRepository rewrite_LinkaccountRepository;

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;
	
	private final Rewrite_UserlocationRepository rewrite_UserlocationRepository;

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

	public Rewrite_WeChatServiceImpl(Rewrite_LinkaccountRepository rewrite_LinkaccountRepository,
			Rewrite_LinkuserRepository rewrite_LinkuserRepository,
			Rewrite_UserassetsRepository rewrite_UserassetsRepository,
			Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository,
			Rewrite_UserlocationRepository rewrite_UserlocationRepository) {
		this.rewrite_LinkaccountRepository = rewrite_LinkaccountRepository;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
		this.rewrite_UserlocationRepository = rewrite_UserlocationRepository;
	}

	@Override
	public String scaningWeChat(String userid, String code) {
		// 绑定微信
		return null;
	}

	@Override
	public String queryWeChat(String userid) {
		Linkaccount mylinkaccount = rewrite_LinkaccountRepository.findFirstByUseridAndAccounttype(userid, "微信");
		if (mylinkaccount != null) {
			return "已绑定";
		}
		return "未绑定";
	}

	@Override
	public String queryWeChatUser(String code) {
//		if (userInfo == null) {
//			return "获取微信会员信息失败";
//		}
//		String alipayUserId = userInfo.getUserId();
//		Linkaccount linkaccount = rewrite_LinkaccountRepository.findFirstByAccounttypeAndToken("支付宝", alipayUserId);// 判断系统是否有这个微信
//		if (linkaccount != null) {
//			return "用户存在";
//		} else {
//			return alipayUserId;
//		}
		return null;
	}
}
