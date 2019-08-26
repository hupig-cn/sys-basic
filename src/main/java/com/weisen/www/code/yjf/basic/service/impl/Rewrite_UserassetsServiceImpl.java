package com.weisen.www.code.yjf.basic.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Coupon;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.Rewrite_CouponRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_UserPriceDTO;
import com.weisen.www.code.yjf.basic.service.mapper.CouponMapper;
import com.weisen.www.code.yjf.basic.service.mapper.UserassetsMapper;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_UserassetsServiceImpl implements Rewrite_UserassetsService {

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final UserassetsMapper userassetsMapper;

	private final Rewrite_CouponRepository rewrite_CouponRepository;
	
	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	private final CouponMapper couponMapper;
	
	private final Logger log = LoggerFactory.getLogger(Rewrite_UserassetsServiceImpl.class);

	public Rewrite_UserassetsServiceImpl(Rewrite_UserassetsRepository rewrite_UserassetsRepository,
			UserassetsMapper userassetsMapper, Rewrite_CouponRepository rewrite_CouponRepository,
			Rewrite_LinkuserRepository rewrite_LinkuserRepository,CouponMapper couponMapper) {
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.userassetsMapper = userassetsMapper;
		this.rewrite_CouponRepository = rewrite_CouponRepository;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.couponMapper = couponMapper;
	}
	
	@Override
	public synchronized Result rechangeYue(String mobile, String yue) {
		log.debug("增加余额手机号{}, 余额{}", mobile, yue);
		Linkuser Linkuser = rewrite_LinkuserRepository.findByPhone(mobile);
		if (Linkuser == null) {
			return Result.fail("用户不存在");
		}
		Userassets userassets = rewrite_UserassetsRepository.findByUserid(Linkuser.getUserid());
		if (userassets == null) {
			return Result.fail("目标用户不存在");
		}
		BigDecimal balance = new BigDecimal(userassets.getBalance());
        BigDecimal useableBalance = new BigDecimal(userassets.getUsablebalance());
        BigDecimal rechangeYue = new BigDecimal(yue);
        if (rechangeYue.compareTo(BigDecimal.ZERO) < 0) {
			return Result.fail("操作失败，充值数目不能小于0");
		}
        userassets.setBalance(balance.add(rechangeYue).setScale(3).toString());
        userassets.setUsablebalance(useableBalance.add(rechangeYue).setScale(3).toString());
        rewrite_UserassetsRepository.saveAndFlush(userassets);
		return Result.suc("充值成功");
	}

	@Override
	public synchronized Result deductYue(String mobile, String yue) {
		log.debug("扣减余额手机号{}, 余额{}", mobile, yue);
		Linkuser Linkuser = rewrite_LinkuserRepository.findByPhone(mobile);
		if (Linkuser == null) {
			return Result.fail("用户不存在");
		}
		Userassets userassets = rewrite_UserassetsRepository.findByUserid(Linkuser.getUserid());
		if (userassets == null) {
			return Result.fail("目标用户不存在");
		}
		BigDecimal balance = new BigDecimal(userassets.getBalance());
        BigDecimal useableBalance = new BigDecimal(userassets.getUsablebalance());
        BigDecimal deductYue = new BigDecimal(yue);
        if (deductYue.compareTo(BigDecimal.ZERO) < 0) {
			return Result.fail("操作失败，扣减数目不能小于0");
		}
        if (balance.subtract(deductYue).compareTo(BigDecimal.ZERO) < 0) {
			return Result.fail("操作失败，扣减余额不能大于总余额");
		}
        if (useableBalance.subtract(deductYue).compareTo(BigDecimal.ZERO) < 0) {
			return Result.fail("操作失败，扣减余额不能大于可用金额");
		}
        userassets.setBalance(balance.add(deductYue).setScale(3).toString());
        userassets.setUsablebalance(useableBalance.add(deductYue).setScale(3).toString());
        rewrite_UserassetsRepository.saveAndFlush(userassets);
		return Result.suc("扣减成功");
	}

	// 查询用户余额
	@Override
	public Rewrite_UserPriceDTO findUserBalance(Long userId) {
		Userassets userassets = rewrite_UserassetsRepository.findByUserid(userId.toString());
		if (null == userassets) {
            return null;
        }
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
