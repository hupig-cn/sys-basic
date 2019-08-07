package com.weisen.www.code.yjf.basic.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
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
import com.weisen.www.code.yjf.basic.util.HttpRequest;

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
		String str =HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", 
        		"appid=wx5450b0124166c23d&"
        		+ "secret=8cd059133c5be21c0f570551361fbf9c&"
        		+ "code=" + code
        		+ "&grant_type=authorization_code");
        String openid = JSON.parseObject(str).getString("openid");
		if (null == openid) {
			return "获取微信会员信息失败";
		}
		// 判断我是否已经绑定微信
		Linkaccount mylinkaccount = rewrite_LinkaccountRepository.findFirstByUseridAndAccounttype(userid, "微信");
		if (mylinkaccount != null) {
			return "你已经绑定过微信了";
		}
		Linkaccount linkaccount = rewrite_LinkaccountRepository.findFirstByAccounttypeAndToken("微信", openid);// 判断系统是否有这个微信
		String recommendr = "1";
		String recommenddate = DateUtils.getDateForNow();
		String id = "0";
		if (linkaccount != null) {
			Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(linkaccount.getUserid());
			if (null != linkuser && null != linkuser.getPhone() && linkuser.getPhone().length() == 11) {
				if (linkaccount.getUserid().equals(userid)) {
					return "已绑定，请勿重复操作";
				} else {
					return "此微信已被他人绑定";
				}
			} else {
				Userassets userassetsI = rewrite_UserassetsRepository.findByUserid(userid);
				Userassets userassetsII = rewrite_UserassetsRepository.findByUserid(linkaccount.getUserid());
				if (null == userassetsI) {
					userassetsI = new Userassets();
					userassetsI.setUserid(userid);
					userassetsI.setBalance("0");
					userassetsI.setUsablebalance("0");
					userassetsI.setFrozenbalance("0");
					userassetsI.setIntegral("0");
					rewrite_UserassetsRepository.save(userassetsI);// 空的用户资产,先建立一个用户资产
				}
				if (null != userassetsII) {
					userassetsI.setIntegral(String.valueOf((Double.parseDouble(userassetsI.getIntegral())
							+ Double.parseDouble(userassetsII.getIntegral()))));
					id = userassetsII.getUserid();
					rewrite_UserassetsRepository.deleteById(userassetsII.getId());// 用来合并资产
				}
				Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(linkaccount.getUserid());
				if (null != userlinkuser) {
					recommendr = null == userlinkuser.getRecommendid()
							|| "".equals(userlinkuser.getRecommendid())
							?"1":userlinkuser.getRecommendid();
					recommenddate = userlinkuser.getModifierdate();
					rewrite_UserlinkuserRepository.delete(userlinkuser);// 拿出推荐人
				}
				Userlocation userlocation = rewrite_UserlocationRepository.findByUserid(linkuser.getUserid());
				if (null != userlocation) {
					rewrite_UserlocationRepository.delete(userlocation);//删除用户位置信息
				}
				if (null != linkuser) {
					rewrite_LinkuserRepository.delete(linkuser);//删除用户附加信息
				}
			}
			rewrite_LinkaccountRepository.delete(linkaccount);// 用完删除这个用户的微信账户
		}
		if (recommendr != null && !recommendr.equals("")) {
			Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(userid);
			if (userlinkuser != null && userlinkuser.getRecommendid() != null && !"".equals(userlinkuser.getRecommendid()) && !"1".equals(userlinkuser.getRecommendid())) {// app有推荐人
				if (userlinkuser.getOther() != null && !"".equals(userlinkuser.getOther())) {
					if (getToLong(userlinkuser.getModifierdate()) > getToLong(recommenddate)) {
						userlinkuser.setRecommendid(recommendr);
						userlinkuser.setModifierdate(recommenddate);
						userlinkuser.setOther("微信");
					}
				}
			} else if (userlinkuser != null && 
					(userlinkuser.getRecommendid() == null 
					|| "".equals(userlinkuser.getRecommendid()) 
					|| "1".equals(userlinkuser.getRecommendid()))
					&& getToLong(userlinkuser.getModifierdate()) > getToLong(recommenddate)) {// app没有推荐人，但是有数据
					userlinkuser.setRecommendid(recommendr);
					userlinkuser.setModifierdate(recommenddate);
					userlinkuser.setOther("微信");
			} else if (userlinkuser == null && getToLong(userlinkuser.getModifierdate()) > getToLong(recommenddate)) {// 没有推荐人，并且没有数据
				userlinkuser = new Userlinkuser();
				userlinkuser.setUserid(userid);
				userlinkuser.setRecommendid(recommendr);
				userlinkuser.setPartner(false);
				userlinkuser.setProvince(false);
				userlinkuser.setCity(false);
				userlinkuser.setCounty(false);
				userlinkuser.setCreator(userid);
				userlinkuser.setCreatedate(recommenddate);
				userlinkuser.setModifier(userid);
				userlinkuser.setModifierdate(recommenddate);
				userlinkuser.setOther("微信");
			}
			rewrite_UserlinkuserRepository.save(userlinkuser);
		}
		mylinkaccount = new Linkaccount();
		mylinkaccount.setAccounttype("微信");
		mylinkaccount.setUserid(userid);
		mylinkaccount.setToken(openid);
		mylinkaccount.setCreator(userid);
		mylinkaccount.setCreatedate(DateUtils.getDateForNow());
		mylinkaccount.setModifier(userid);
		mylinkaccount.setModifierdate(DateUtils.getDateForNow());
		rewrite_LinkaccountRepository.save(mylinkaccount);
		return id;
	}
	
	private long getToLong(String DateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(DateTime).getTime();
		} catch (Exception e) {
			return 0;
		}
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
		String str =HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", 
        		"appid=wx5450b0124166c23d&"
        		+ "secret=8cd059133c5be21c0f570551361fbf9c&"
        		+ "code=" + code
        		+ "&grant_type=authorization_code");
        String openid = JSON.parseObject(str).getString("openid");
		if (null == openid) {
			return "获取微信会员信息失败";
		}
		Linkaccount linkaccount = rewrite_LinkaccountRepository.findFirstByAccounttypeAndToken("微信", openid);// 判断系统是否有这个微信
		if (linkaccount != null) {
			return "用户" + linkaccount.getUserid();
		} else {
			return openid;
		}
	}
}
