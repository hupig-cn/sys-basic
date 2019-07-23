package com.weisen.www.code.yjf.basic.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Coupon;
import com.weisen.www.code.yjf.basic.domain.Information;
import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.domain.Userlocation;
import com.weisen.www.code.yjf.basic.repository.CouponRepository;
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

	private CouponRepository couponRepository;

	public Rewrite_CreateUserServiceImpl(LinkuserRepository linkuserRepository,
			LinkaccountRepository linkaccountRepository, UserlinkuserRepository userlinkuserRepository,
			UserlocationRepository userlocationRepository, UserassetsRepository userassetsRepository,
			InformationRepository informationRepository, CouponRepository couponRepository) {
		this.linkuserRepository = linkuserRepository;
		this.linkaccountRepository = linkaccountRepository;
		this.userlinkuserRepository = userlinkuserRepository;
		this.userlocationRepository = userlocationRepository;
		this.userassetsRepository = userassetsRepository;
		this.informationRepository = informationRepository;
		this.couponRepository = couponRepository;
	}

	/**
	 * 该接口适用于微信或者支付宝扫描授权后创建用户
	 */
	@Override
	public Result createUserByScan(String userId, String token, String accounttype, String recommendId,
			String coordinate) {
		// 1.首先在linkuser表中创建用户附加信息
		Linkuser linkuser = new Linkuser();
		linkuser.setUserid(userId);
		linkuser.setCreator(userId);
		linkuser.setCreatedate("创建时间");
		linkuserRepository.save(linkuser);
		// 2.在Linkaccount表中添加用户绑定账号信息
		Linkaccount linkaccount = new Linkaccount();
		linkaccount.setUserid(userId);
		linkaccount.setAccounttype(accounttype);
		linkaccount.setToken(token);
		linkaccount.setCreator(userId);
		linkaccount.setCreatedate("创建时间");
		linkaccountRepository.save(linkaccount);
		// 3.在userlinkuser表中创建用户关系信息
		Userlinkuser userlinkuser = new Userlinkuser();
		userlinkuser.setUserid(userId);
		userlinkuser.setRecommendid(recommendId);
		userlinkuserRepository.save(userlinkuser);
		createBasicInfo(userId, coordinate);
		return Result.suc("用户创建成功");
	}

	/**
	 * 该接口适用于app手机注册后创建用户
	 */
	@Override
	public Result createUserByPhone(String userId, String phone) {
		createBasicInfo(userId, phone);
		return Result.suc("用户创建成功");
	}

	/**
	 * 该接口适用于扫码推荐注册后创建用户
	 */
	@Override
	public Result createUserByScanning(String userId, String phone, String referrer) {
		String thisDate = DateUtils.getDateForNow();
		Userlinkuser userlinkuser = new Userlinkuser();
		userlinkuser.setUserid(userId);
		userlinkuser.setRecommendid(referrer);
		userlinkuser.setPartner(false);
		userlinkuser.setProvince(false);
		userlinkuser.setCity(false);
		userlinkuser.setCounty(false);
		userlinkuser.setCreator(userId);
		userlinkuser.setCreatedate(thisDate);
		userlinkuser.setModifier(userId);
		userlinkuser.setModifierdate(thisDate);
		userlinkuserRepository.save(userlinkuser);// 推荐人
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
		createBasicInfo(userId, phone);
		return Result.suc("用户创建成功");
	}

	private void createBasicInfo(String userId, String phone) {
		Linkuser linkuser = new Linkuser();
		linkuser.setUserid(userId);
		linkuser.setPhone(phone);
		linkuserRepository.save(linkuser);// 用户附加信息
		Userlocation userlocation = new Userlocation();
		userlocation.setUserid(userId);
		userlocationRepository.save(userlocation);// 用户位置信息
		Userassets userassets = new Userassets();
		userassets.setUserid(userId);
		userassets.setBalance("0");
		userassets.setUsablebalance("0");
		userassets.setFrozenbalance("0");
		userassets.setIntegral("0");
		userassetsRepository.save(userassets);// 用户资产
		Coupon Coupon = new Coupon();
		Coupon.setUserid(userId);
		Coupon.setSum("0");
		Coupon.setCoupontype("普通");
		Coupon.setLineon(true);
		Coupon.setLineunder(true);
		Coupon.setIntegral(true);
		Coupon.setProfit(true);
		couponRepository.save(Coupon);// 优惠卷
	}
	@Override
	public String createUserByScanningMerchant(String userid, String merchantid, String token, String accounttype) {
		//创建用户推荐信息
		String thisDate = DateUtils.getDateForNow();
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
		userlinkuserRepository.save(userlinkuser);// 推荐人
		//创建用户资产
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
		//创建账号绑定
		Linkaccount linkaccount = new Linkaccount();
		linkaccount.setUserid(userid);
		linkaccount.setAccounttype(accounttype);
		linkaccount.setToken(token);
		linkaccount.setCreator(userid);
		linkaccount.setCreatedate(thisDate);
		linkaccount.setModifier(userid);
		linkaccount.setModifierdate(thisDate);
		linkaccount.setOther(accounttype);
		linkaccountRepository.save(linkaccount);
		return "创建成功";
	}
}
