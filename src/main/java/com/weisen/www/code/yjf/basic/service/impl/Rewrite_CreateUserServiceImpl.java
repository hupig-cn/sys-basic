package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.service.Rewrite_CreateUserService;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_CreateUserServiceImpl implements Rewrite_CreateUserService {

	private LinkuserRepository linkuserRepository;
	
	private LinkaccountRepository linkaccountRepository;

	private UserlinkuserRepository userlinkuserRepository;

	private UserlocationRepository userlocationRepository;

	private UserassetsRepository userassetsRepository;

    public Rewrite_CreateUserServiceImpl(LinkuserRepository linkuserRepository, LinkaccountRepository linkaccountRepository,
                                             UserlinkuserRepository userlinkuserRepository, UserlocationRepository userlocationRepository,
                                             UserassetsRepository userassetsRepository) {
        this.linkuserRepository = linkuserRepository;
        this.linkaccountRepository = linkaccountRepository;
        this.userlinkuserRepository = userlinkuserRepository;
        this.userlocationRepository = userlocationRepository;
        this.userassetsRepository = userassetsRepository;
    }

    /**
	 * 该接口适用于微信或者支付宝扫描授权后创建用户
	 */
	@Override
	public Result createUserByScan(String userId, String token, String accounttype, String recommendId, String coordinate) {
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
		//3.在userlinkuser表中创建用户关系信息
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
	public Result createUserByPhone(String userId, String phone, String coordinate) {
		//1.首先在linkuser表中创建用户附加信息
		Linkuser linkuser = new Linkuser();
		linkuser.setUserid(userId);
		linkuser.setCreator(userId);
		linkuser.setPhone(phone);
		linkuser.setCreatedate("创建时间");
		linkuserRepository.save(linkuser);
        createBasicInfo(userId, coordinate);
		return Result.suc("用户创建成功");
	}

	private void createBasicInfo (String userId, String coordinate) {
        //1.在userlocation表中创建用户位置信息
        Userlocation userlocation = new Userlocation();
        userlocation.setUserid(userId);
        userlocation.setCoordinate(coordinate);
        userlocation.setCreator(userId);
        userlocation.setCreatedate("创建时间");
        userlocationRepository.save(userlocation);
        //2.在userassets表中创建用户资产信息
        Userassets userassets = new Userassets();
        userassets.setUserid(userId);
        userassets.setBalance("0");
        userassets.setIntegral("0");
        userassets.setUsablebalance("0");
        userassets.setFrozenbalance("0");
        userassets.setCreator(userId);
        userassets.setCreatedate("创建时间");
        userassetsRepository.save(userassets);
    }

}
