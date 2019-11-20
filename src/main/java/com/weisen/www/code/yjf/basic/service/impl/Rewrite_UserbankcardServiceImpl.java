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
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

}
