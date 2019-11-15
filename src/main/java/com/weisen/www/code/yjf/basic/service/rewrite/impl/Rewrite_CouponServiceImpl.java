package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_CouponService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_CouponDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.util.TimeUtil;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_CouponServiceImpl implements Rewrite_CouponService {

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

	private final Rewrite_UserRepository rewrite_UserRepository;

	public Rewrite_CouponServiceImpl(Rewrite_UserassetsRepository rewrite_UserassetsRepository,
			Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository, Rewrite_UserRepository rewrite_UserRepository) {
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
		this.rewrite_UserRepository = rewrite_UserRepository;
	}

	/**
	 * 查询用户优惠券
	 * 
	 * @author LuoJinShui
	 */
	@Override
	public Result getCoupon(String userId) {
		// 判断是否有该用户
		User jhiUser = rewrite_UserRepository.findJhiUserById(Long.parseLong(userId));
		if (jhiUser == null) {
			return Result.fail("没有该用户!请重新输入查找!");
		} else {
			Userassets userassets = rewrite_UserassetsRepository.findByUserid(userId);
			Rewrite_UserassetsDTO userassetsDTO = new Rewrite_UserassetsDTO();
			userassetsDTO.setCoupon(userassets.getCouponsum());
			return Result.suc("查询成功!", userassetsDTO.getCoupon());
		}
	}

	/**
	 * 查询优惠券明细支出收入
	 * 
	 * @author LuoJinShui
	 */
	@Override
	public Result getUserCoupon(String userId, Integer pageNum, Integer pageSize) {
		// 判断是否有该用户
		// 获取被推荐人Login库资料
		User jhiUser = rewrite_UserRepository.findJhiUserById(Long.parseLong(userId));
		if (jhiUser == null) {
			return Result.fail("没有该用户!请重新输入查找!");
		} else {
			List<Rewrite_CouponDTO> rewrite_CouponDTOs = new ArrayList<Rewrite_CouponDTO>();
			// 查询用户优惠券支出收入数据
			List<Receiptpay> receiptpayExpenditureList = rewrite_ReceiptpayRepository.findByCoupon(userId,
					pageNum * pageSize, pageSize);
			for (Receiptpay receiptpayExpenditure : receiptpayExpenditureList) {
				Rewrite_CouponDTO rewrite_CouponDTO = new Rewrite_CouponDTO();
				rewrite_CouponDTO.setAmount(receiptpayExpenditure.getAmount());
				String dataStr = receiptpayExpenditure.getCreatedate();
				// 转换时间格式显示今天、昨天,如果是当前年份不显示年份,不是当前年份就显示出来
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
				Date createDate = null;
				try {
					createDate = sdf.parse(dataStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				rewrite_CouponDTO.setCreateDate(TimeUtil.getTime(createDate));
				if (receiptpayExpenditure.getDealtype().equals("7")) {
					rewrite_CouponDTO.setStatus(0);
					rewrite_CouponDTO.setExplain("兑换商品");
				}
				if (receiptpayExpenditure.getDealtype().equals("8")) {
					rewrite_CouponDTO.setStatus(1);
					rewrite_CouponDTO.setExplain("消费收益");
				}
				rewrite_CouponDTOs.add(rewrite_CouponDTO);
			}
			return Result.suc("查询成功!", rewrite_CouponDTOs, rewrite_CouponDTOs.size());
		}
	}

}
