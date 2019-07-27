package com.weisen.www.code.yjf.basic.service.impl;

import java.util.List;

import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_UserPriceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Coupon;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.Rewrite_CouponRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.mapper.CouponMapper;
import com.weisen.www.code.yjf.basic.service.mapper.UserassetsMapper;

@Service
@Transactional
public class Rewrite_UserassetsServiceImpl implements Rewrite_UserassetsService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_UserassetsServiceImpl.class);

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final UserassetsMapper userassetsMapper;

	private final Rewrite_CouponRepository rewrite_CouponRepository;

	private final CouponMapper couponMapper;

	public Rewrite_UserassetsServiceImpl(Rewrite_UserassetsRepository rewrite_UserassetsRepository,
			UserassetsMapper userassetsMapper, Rewrite_CouponRepository rewrite_CouponRepository,
			CouponMapper couponMapper) {
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.userassetsMapper = userassetsMapper;
		this.rewrite_CouponRepository = rewrite_CouponRepository;
		this.couponMapper = couponMapper;
	}

	// 查询用户余额
	@Override
	public Rewrite_UserPriceDTO findUserBalance(Long userId) {
		Userassets userassets = rewrite_UserassetsRepository.findByUserid(userId.toString());
//		if (null == userassets) {
//			return new Rewrite_PriceDTO("0");
//		}
        Rewrite_UserPriceDTO rewrite_UserPriceDTO = new Rewrite_UserPriceDTO();
        rewrite_UserPriceDTO.setBalance(userassets.getBalance());
        rewrite_UserPriceDTO.setAvailableBalance(userassets.getUsablebalance());
        rewrite_UserPriceDTO.setFrozenBalance(userassets.getFrozenbalance());
		return rewrite_UserPriceDTO;
	}

	// 查询用户的余额 积分 优惠券
	@Override
	public Rewrite_PriceDTO findUserInfo(Long userId) {
		Userassets userassets = rewrite_UserassetsRepository.findByUserid(userId.toString());
		Rewrite_PriceDTO rewrite_PriceDTO = null;
		rewrite_PriceDTO.setBalance(userassets.getBalance());
		rewrite_PriceDTO.setIntegral(userassets.getIntegral());
		List<Coupon> coupon = rewrite_CouponRepository.findAllByUserid(userId.toString());
		rewrite_PriceDTO.setCoupon(couponMapper.toDto(coupon));
		return rewrite_PriceDTO;
	}

	@Override
	public UserassetsDTO findUserAssets(String userid) {
		return userassetsMapper.toDto(rewrite_UserassetsRepository.findByUserid(userid));
	}
}
