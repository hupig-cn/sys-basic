package com.weisen.www.code.yjf.basic.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.repository.LinkaccountRepository;
import com.weisen.www.code.yjf.basic.repository.LinkuserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_000_CreateUserService;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_000_CreateUserServiceImpl implements Rewrite_000_CreateUserService {

	private LinkuserRepository linkuserRepository;
	
	private LinkaccountRepository linkaccountRepository;
	
	public Rewrite_000_CreateUserServiceImpl(LinkuserRepository linkuserRepository,
			LinkaccountRepository linkaccountRepository) {
		this.linkuserRepository = linkuserRepository;
		this.linkaccountRepository = linkaccountRepository;
	}

	/**
	 * 该接口适用于微信或者支付宝扫描授权后创建用户
	 */
	@Override
	public Result createUserByScan(String userId, String token, String accounttype) {
		//1.首先在linkuser表中创建用户附加信息
		Linkuser linkuser = new Linkuser();
		linkuser.setUserid(userId);
		linkuser.setCreator(userId);
		linkuser.setCreatedate("创建时间");
		linkuserRepository.save(linkuser);
		//2.在Linkaccount表中添加用户绑定账号信息
		Linkaccount linkaccount = new Linkaccount();
		linkaccount.setUserid(userId);
		linkaccount.setAccounttype(accounttype);
		linkaccount.setToken(token);
		linkaccount.setCreator(userId);
		linkaccount.setCreatedate("创建时间");
		linkaccountRepository.save(linkaccount);
		//3.在userlocation表中创建用户位置信息
		//4.在userlinkuser表中创建用户关系信息
		//5.在userassets表中创建用户资产信息
		return Result.suc("用户创建成功");
	}

	/**
	 * 该接口适用于app手机注册后创建用户
	 */
	@Override
	public Result createUserByPhone(String userId, String phone) {
		//1.首先在linkuser表中创建用户附加信息
		Linkuser linkuser = new Linkuser();
		linkuser.setUserid(userId);
		linkuser.setCreator(userId);
		linkuser.setPhone(phone);
		linkuser.setCreatedate("创建时间");
		linkuserRepository.save(linkuser);
		//2.在userlocation表中创建用户位置信息
		//3.在userlinkuser表中创建用户关系信息
		//4.在userassets表中创建用户资产信息
		return Result.suc("用户创建成功");
	}
	
}
