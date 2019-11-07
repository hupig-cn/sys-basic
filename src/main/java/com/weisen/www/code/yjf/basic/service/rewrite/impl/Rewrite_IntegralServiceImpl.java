package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IntegralService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_IntegralDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_UserassetsDTO;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_IntegralServiceImpl implements Rewrite_IntegralService {

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

	private final Rewrite_UserRepository rewrite_UserRepository;

	public Rewrite_IntegralServiceImpl(Rewrite_UserassetsRepository rewrite_UserassetsRepository,
			Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository, Rewrite_UserRepository rewrite_UserRepository) {
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
		this.rewrite_UserRepository = rewrite_UserRepository;
	}

	/**
	 * 查询用户总积分
	 */
	@Override
	public Result getIntegral(String userId) {
		// 判断是否有该用户
		// 获取被推荐人Login库资料
		User jhiUser = rewrite_UserRepository.findJhiUserById(Long.parseLong(userId));
		if (jhiUser == null) {
			return Result.fail("没有该用户!请重新输入查找!");
		} else {
			// 查询用户总积分
			Userassets userassets = rewrite_UserassetsRepository.findByUserid(userId);
			Rewrite_UserassetsDTO userassetsDTO = new Rewrite_UserassetsDTO();
			userassetsDTO.setIntegral(userassets.getIntegral());
			return Result.suc("查询成功!", userassetsDTO.getIntegral());
		}
	}

	/**
	 * 分页查询用户积分支出收入按最新时间排序
	 */
	@Override
	public Result getExpenditure(String userId, Integer pageNum, Integer pageSize) {
		// 判断是否有该用户
		// 获取被推荐人Login库资料
		User jhiUser = rewrite_UserRepository.findJhiUserById(Long.parseLong(userId));
		if (jhiUser == null) {
			return Result.fail("没有该用户!请重新输入查找!");
		} else {
			List<Rewrite_IntegralDTO> rewrite_IntegralDTOs = new ArrayList<Rewrite_IntegralDTO>();
			// 查询用户积分支出收入数据
			List<Receiptpay> receiptpayExpenditureList = rewrite_ReceiptpayRepository.findByUserid(userId,
					pageNum * pageSize, pageSize);
			for (Receiptpay receiptpayExpenditure : receiptpayExpenditureList) {
				Rewrite_IntegralDTO rewrite_IntegralExpenditureDTO = new Rewrite_IntegralDTO();
				rewrite_IntegralExpenditureDTO.setAmount(receiptpayExpenditure.getAmount());
				rewrite_IntegralExpenditureDTO.setCreateDate(receiptpayExpenditure.getCreatedate());
				if (receiptpayExpenditure.getDealtype().equals("5")) {
					rewrite_IntegralExpenditureDTO.setStatus(0);
					rewrite_IntegralExpenditureDTO.setExplain("兑换商品");
				}
				if (receiptpayExpenditure.getDealtype().equals("6")) {
					rewrite_IntegralExpenditureDTO.setStatus(1);
					rewrite_IntegralExpenditureDTO.setExplain("消费收益");
				}
				rewrite_IntegralDTOs.add(rewrite_IntegralExpenditureDTO);
			}
			return Result.suc("查询成功!", rewrite_IntegralDTOs, rewrite_IntegralDTOs.size());
		}
	}
}
