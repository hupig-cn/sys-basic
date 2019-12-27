package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Userbankcard;
import com.weisen.www.code.yjf.basic.domain.Usercard;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserbankcardRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UsercardRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserbankcardService;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_BackCardInfo;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_BankCardDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserCardDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class Rewrite_UserbankcardServiceImpl implements Rewrite_UserbankcardService {

	private final Rewrite_UserbankcardRepository rewrite_UserbankcardRepository;

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	private final Rewrite_UsercardRepository rewrite_UsercardRepository;

	public Rewrite_UserbankcardServiceImpl(Rewrite_UserbankcardRepository rewrite_UserbankcardRepository,
			Rewrite_LinkuserRepository rewrite_LinkuserRepository,
			Rewrite_UsercardRepository rewrite_UsercardRepository) {
		this.rewrite_UserbankcardRepository = rewrite_UserbankcardRepository;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.rewrite_UsercardRepository = rewrite_UsercardRepository;
	}

	// 查询用户银行卡列表
	@Override
	public Result findAllUserBankCard(Long userId) {
		List<Userbankcard> list = rewrite_UserbankcardRepository.findAllByUserid(userId.toString());
		List<Rewrite_BankCardDTO> listbank = new ArrayList<>();

		list.forEach(x -> {
			Rewrite_BankCardDTO rewrite_BankCardDTO = new Rewrite_BankCardDTO();

			rewrite_BankCardDTO.setId(x.getId());
			rewrite_BankCardDTO.setBankname(x.getBanktype());
			rewrite_BankCardDTO
					.setBanknum(x.getBankcard().substring(x.getBankcard().length() - 4, x.getBankcard().length()));
			rewrite_BankCardDTO.setBankuser(x.getRealname());
			rewrite_BankCardDTO.setLogo(x.getBankicon());
			listbank.add(rewrite_BankCardDTO);
		});

		Linkuser Linkuser = rewrite_LinkuserRepository.findByUserid(userId.toString());
		Rewrite_BackCardInfo rewrite_BackCardInfo = new Rewrite_BackCardInfo(listbank,
				Linkuser.getAlipay() != null ? Linkuser.getAlipay() : null,
				Linkuser.getWechat() != null ? Linkuser.getWechat() : null,
				Linkuser.getAlipayname() != null ? Linkuser.getAlipayname() : null,
				Linkuser.getWechatname() != null ? Linkuser.getWechatname() : null);
		return Result.suc("成功", rewrite_BackCardInfo);
	}

	/**
	 * 重写查询用户银行卡列表
	 */
	@Override
	public Result getAllUserBankCard(String userId) {
		List<Userbankcard> list = rewrite_UserbankcardRepository.findAllByUserid(userId.toString());
		List<Rewrite_BankCardDTO> listbank = new ArrayList<>();

		list.forEach(x -> {
			Rewrite_BankCardDTO rewrite_BankCardDTO = new Rewrite_BankCardDTO();

			rewrite_BankCardDTO.setId(x.getId());
			rewrite_BankCardDTO.setBankname(x.getBanktype());

			rewrite_BankCardDTO
					.setBanknum(x.getBankcard().substring(x.getBankcard().length() - 4, x.getBankcard().length()));
			rewrite_BankCardDTO.setBankuser(x.getRealname());
			rewrite_BankCardDTO.setLogo(x.getBankicon());
			Usercard usercard = rewrite_UsercardRepository.findByLogo(x.getBankicon());
			rewrite_BankCardDTO.setPicId(usercard.getOther());
			rewrite_BankCardDTO.setBankBackground(usercard.getBankBackground());
			listbank.add(rewrite_BankCardDTO);
		});

		Linkuser Linkuser = rewrite_LinkuserRepository.findByUserid(userId.toString());
		Rewrite_BackCardInfo rewrite_BackCardInfo = new Rewrite_BackCardInfo(listbank,
				Linkuser.getAlipay() != null ? Linkuser.getAlipay() : "",
				Linkuser.getWechat() != null ? Linkuser.getWechat() : "",
				Linkuser.getAlipayname() != null ? Linkuser.getAlipayname() : "",
				Linkuser.getWechatname() != null ? Linkuser.getWechatname() : "");
		return Result.suc("成功", rewrite_BackCardInfo);
	}

	/**
	 * 查询银行卡列表
	 */
	@Override
	public Result findAllBankCard(String userId) {
		List<Usercard> usercardsList = rewrite_UsercardRepository.findAll();
		List<Rewrite_BankCardDTO> bankCardDTOs = new ArrayList<Rewrite_BankCardDTO>();
		for (Usercard usercard : usercardsList) {
			Rewrite_BankCardDTO bankCardDTO = new Rewrite_BankCardDTO();
			bankCardDTO.setBankname(usercard.getBankname());
			bankCardDTO.setLogo(usercard.getLogo());
			bankCardDTO.setPicId(usercard.getOther());
			bankCardDTOs.add(bankCardDTO);
		}
		return Result.suc("查询成功!", bankCardDTOs);
	}

	// 用户添加银行卡
	@Override
	public Result createBankCard(UserbankcardDTO userbankcardDTO) {
		Userbankcard userbankcard = new Userbankcard();
		userbankcard.setBankcard(userbankcardDTO.getBankcard());
		userbankcard.setBankicon(userbankcardDTO.getBankicon());
		userbankcard.setBankphone(userbankcardDTO.getBankphone());
		userbankcard.setBanktype(userbankcardDTO.getBanktype());
		userbankcard.setRealname(userbankcardDTO.getRealname());
		userbankcard.setCreatedate(TimeUtil.getDate());
		userbankcard.setUserid(userbankcardDTO.getUserid());
		userbankcard.setBankcity(userbankcardDTO.getBankcity());
		userbankcard.setBanksubbranch(userbankcardDTO.getBanksubbranch());
		rewrite_UserbankcardRepository.save(userbankcard);

		return Result.suc("success");
	}

	// 用户删除银行卡
	@Override
	public Result deleteBackCard(Long bankcardId) {
		rewrite_UserbankcardRepository.deleteById(bankcardId);
		return Result.suc("success");
	}

	// 用户删除多个银行卡
	@Override
	public Result deleteBackCards(List<String> bankcardId) {
		for (String long1 : bankcardId) {
			rewrite_UserbankcardRepository.deleteById(Long.parseLong(long1));
		}
		return Result.suc("删除成功");
	}

	// 重写用户添加银行卡LuoJinShui
	@Override
	public Result createBankCard2(Rewrite_UserCardDTO rewrite_UserCardDTO) {
		// 根据用户传过来的id查找相对应的银行卡信息
		Usercard usercard = rewrite_UsercardRepository.findById2(rewrite_UserCardDTO.getId());
		Userbankcard userBankCard = rewrite_UserbankcardRepository.findByBankcard(rewrite_UserCardDTO.getBankcard());
		Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(rewrite_UserCardDTO.getUserid());
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(rewrite_UserCardDTO.getBankphone()); // registrant_phone ==== 电话号码字段
		boolean isMatch = m.matches();
		if (usercard == null) {
			return Result.fail("没有该银行卡类型哦!请重新输入!");
		}
		if (rewrite_UserCardDTO.getBankcard().trim().length() < 15
				|| rewrite_UserCardDTO.getBankcard().trim().length() > 19) {
			return Result.fail("银行卡号格式不正确!请重新输入!");
		}
		if (rewrite_UserCardDTO.getRealname().trim() == null || rewrite_UserCardDTO.getRealname().trim().equals("")) {
			return Result.fail("持卡人姓名不能为空哦!请输入!");
		}
		if (rewrite_UserCardDTO.getBankphone().trim() == null || rewrite_UserCardDTO.getBankphone().trim().equals("")) {
			return Result.fail("银行预留手机号不能为空哦!请输入!");
		}
		if (!isMatch) {
			return Result.fail("银行预留手机号格式不正确!请重新输入!");
		}
		if (userBankCard != null) {
			if (userBankCard.getRealname().equals(rewrite_UserCardDTO.getRealname())) {
				return Result.fail("当前添加的银行卡已绑定!请进入银行卡列表查看!");
			} else {
				return Result.fail("该银行卡已被绑定!请重新输入!");
			}
		}
		if (linkuser == null) {
			return Result.fail("当前用户不存在!请重新输入!");
		}
		Userbankcard userbankcard = new Userbankcard();
		userbankcard.setBankcard(rewrite_UserCardDTO.getBankcard().trim());
		userbankcard.setRealname(rewrite_UserCardDTO.getRealname().trim());
		userbankcard.setBankphone(rewrite_UserCardDTO.getBankphone().trim());
		// 把用户传过来的银行卡的LoGo保存
		userbankcard.setBankicon(usercard.getLogo());
		// 把用户传过来的银行卡的名字保存
		userbankcard.setBanktype(usercard.getBankname());
		userbankcard.setCreatedate(TimeUtil.getDate());
		userbankcard.setUserid(rewrite_UserCardDTO.getUserid().trim());
		rewrite_UserbankcardRepository.save(userbankcard);
		return Result.suc("添加成功!");
	}
}
