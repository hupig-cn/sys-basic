package com.weisen.www.code.yjf.basic.service;

import java.util.List;

import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserCardDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_UserbankcardService {

	// 查询用户银行卡列表
	Result findAllUserBankCard(Long userId);

	// 重写查询用户银行卡列表
	Result getAllUserBankCard(String userId);

	// 用户添加银行卡
	Result createBankCard(UserbankcardDTO userbankcardDTO);

	// 用户删除银行卡
	Result deleteBackCard(Long bankcardId);

	// 查询银行卡列表
	Result findAllBankCard(String userId);

	Result deleteBackCards(List<String> bankcardId);

	// 重写用户添加银行卡
	Result createBankCard2(Rewrite_UserCardDTO rewrite_UserCardDTO);
}
