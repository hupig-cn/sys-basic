package com.weisen.www.code.yjf.basic.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Information;
import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.domain.Userlocation;
import com.weisen.www.code.yjf.basic.repository.InformationRepository;
import com.weisen.www.code.yjf.basic.repository.LinkaccountRepository;
import com.weisen.www.code.yjf.basic.repository.LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.UserlocationRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_CreateUserService;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_CreateUserServiceImpl implements Rewrite_CreateUserService {

	private LinkuserRepository linkuserRepository;

	private InformationRepository informationRepository;

	private LinkaccountRepository linkaccountRepository;

	private UserlinkuserRepository userlinkuserRepository;

	private UserlocationRepository userlocationRepository;

	private UserassetsRepository userassetsRepository;

	public Rewrite_CreateUserServiceImpl(LinkuserRepository linkuserRepository,
			LinkaccountRepository linkaccountRepository, UserlinkuserRepository userlinkuserRepository,
			UserlocationRepository userlocationRepository, UserassetsRepository userassetsRepository,
			InformationRepository informationRepository) {
		this.linkuserRepository = linkuserRepository;
		this.linkaccountRepository = linkaccountRepository;
		this.userlinkuserRepository = userlinkuserRepository;
		this.userlocationRepository = userlocationRepository;
		this.userassetsRepository = userassetsRepository;
		this.informationRepository = informationRepository;
	}

	/**
	 * 该接口适用于app手机注册后创建用户
	 */
	@Override
	public Result createUserByPhone(String userId, String phone) {
		createBasicInfo(userId, phone, "", DateUtils.getDateForNow());
		return Result.suc("用户创建成功");
	}

	/**
	 * 该接口适用于扫码推荐注册后创建用户
	 */
	@Override
	public Result createUserByScanning(String userId, String phone, String referrer) {
		String thisDate = DateUtils.getDateForNow();
		createBasicInfo(userId, phone, referrer, thisDate);
		Information information = new Information();
		information.setType("推荐消息");
		information.setSenduserid("1");
		information.setReaduserid(referrer);
		information.setSenddate(thisDate);
		information.setTitle("推荐成功");
		information.setContent("推荐用户：" + phone + "成功，推荐时间：" + thisDate);
		information.setState("未读");
		information.setWeight("正常");
		informationRepository.save(information);// 发推荐消息
		return Result.suc("用户创建成功");
	}

	/**
	 * 该接口适用于扫描商家付款码后创建用户
	 */
	@Override
	public String createUserByScanningMerchant(String userid, String token, String accounttype) {
		String thisDate = DateUtils.getDateForNow();
		Linkaccount linkaccount = new Linkaccount();
		linkaccount.setUserid(userid);
		linkaccount.setAccounttype(accounttype);
		linkaccount.setToken(token);
		linkaccount.setCreator(userid);
		linkaccount.setCreatedate(thisDate);
		linkaccount.setModifier(userid);
		linkaccount.setModifierdate(thisDate);
		linkaccount.setOther(accounttype);
		linkaccountRepository.save(linkaccount);// 创建账号绑定
		createBasicInfo(userid, "", "", thisDate);
		return "创建成功";
	}

	private void createBasicInfo(String userid, String phone, String merchantid, String thisDate) {
		Linkuser linkuser = new Linkuser();
		linkuser.setUserid(userid);
		linkuser.setPhone(phone);
		linkuser.setCreator(userid);
		linkuser.setCreatedate(thisDate);
		linkuser.setModifier(userid);
		linkuser.setModifierdate(thisDate);
		linkuserRepository.save(linkuser);// 用户附加信息
		Userlocation userlocation = new Userlocation();
		userlocation.setUserid(userid);
		userlocation.setCreator(userid);
		userlocation.setCreatedate(thisDate);
		userlocation.setModifier(userid);
		userlocation.setModifierdate(thisDate);
		userlocationRepository.save(userlocation);// 用户位置信息
		Userlinkuser userlinkuser = new Userlinkuser();
		userlinkuser.setUserid(userid);
		userlinkuser.setRecommendid(merchantid);
		userlinkuser.setPartner(false);
		userlinkuser.setProvince(false);
		userlinkuser.setCity(false);
		userlinkuser.setCounty(false);
		userlinkuser.setCreator(userid);
		userlinkuser.setCreatedate(thisDate);
		userlinkuser.setModifier(userid);
		userlinkuser.setModifierdate(thisDate);
		userlinkuserRepository.save(userlinkuser);// 用户关系表
		Userassets userassets = new Userassets();
		userassets.setUserid(userid);
		userassets.setBalance("0");
		userassets.setUsablebalance("0");
		userassets.setFrozenbalance("0");
		userassets.setIntegral("0");
		userassets.setCreator(userid);
		userassets.setCreatedate(thisDate);
		userassets.setModifier(userid);
		userassets.setModifierdate(thisDate);
		userassetsRepository.save(userassets);// 用户资产
	}
}
