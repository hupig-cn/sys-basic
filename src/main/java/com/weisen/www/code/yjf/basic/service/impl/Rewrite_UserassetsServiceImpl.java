package com.weisen.www.code.yjf.basic.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Coupon;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_CouponRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_UserPriceDTO;
import com.weisen.www.code.yjf.basic.service.mapper.CouponMapper;
import com.weisen.www.code.yjf.basic.service.mapper.UserassetsMapper;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;

@Service
@Transactional
public class Rewrite_UserassetsServiceImpl implements Rewrite_UserassetsService {

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final UserassetsMapper userassetsMapper;

	private final Rewrite_CouponRepository rewrite_CouponRepository;

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;
	
	private final CouponMapper couponMapper;

	private final ReceiptpayRepository receiptpayRepository;

	private final Logger log = LoggerFactory.getLogger(Rewrite_UserassetsServiceImpl.class);

	public Rewrite_UserassetsServiceImpl(Rewrite_UserassetsRepository rewrite_UserassetsRepository,
			UserassetsMapper userassetsMapper, Rewrite_CouponRepository rewrite_CouponRepository,
			Rewrite_LinkuserRepository rewrite_LinkuserRepository, CouponMapper couponMapper,
			ReceiptpayRepository receiptpayRepository) {
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.userassetsMapper = userassetsMapper;
		this.rewrite_CouponRepository = rewrite_CouponRepository;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.couponMapper = couponMapper;
		this.receiptpayRepository = receiptpayRepository;
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
		// 收入流水（后台充值）
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(rechangeYue);
        receiptpay.setDealtype(ReceiptpayConstant.RECHARGE_BALANCE); //余额充值【后台】
        receiptpay.setDealstate(ReceiptpayConstant.INCOME);
//        receiptpay.setPayway(null);
        receiptpay.setUserid(Linkuser.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
//        receiptpay.setOther(null);
        receiptpay.setExplain("后台充值余额"+rechangeYue+"元");
        receiptpayRepository.save(receiptpay);
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
		userassets.setBalance(balance.subtract(deductYue).setScale(3).toString());
		userassets.setUsablebalance(useableBalance.subtract(deductYue).setScale(3).toString());
		rewrite_UserassetsRepository.saveAndFlush(userassets);
		// 支出流水（后台扣减）
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(deductYue);
        receiptpay.setDealtype(ReceiptpayConstant.REDUCT_BALANCE); //余额扣减【后台】
        receiptpay.setDealstate(ReceiptpayConstant.PAY);
//        receiptpay.setPayway(null);
        receiptpay.setUserid(Linkuser.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
//        receiptpay.setOther(null);
        receiptpay.setExplain("后台扣减余额"+deductYue+"元");
        receiptpayRepository.save(receiptpay);
		return Result.suc("扣减成功");
	}
	
	@Override
	public synchronized Result rechangeIntegral(String mobile, String integral) {
		log.debug("增加积分手机号{}, 积分{}", mobile, integral);
		Linkuser Linkuser = rewrite_LinkuserRepository.findByPhone(mobile);
		if (Linkuser == null) {
			return Result.fail("用户不存在");
		}
		Userassets userassets = rewrite_UserassetsRepository.findByUserid(Linkuser.getUserid());
		if (userassets == null) {
			return Result.fail("目标用户不存在");
		}
		BigDecimal userIntegral = new BigDecimal(userassets.getIntegral());
		BigDecimal rechangeIntegral = new BigDecimal(integral);
		if (rechangeIntegral.compareTo(BigDecimal.ZERO) < 0) {
			return Result.fail("操作失败，充值数目不能小于0");
		}
		userassets.setIntegral(userIntegral.add(rechangeIntegral).setScale(3).toString());
		rewrite_UserassetsRepository.saveAndFlush(userassets);
		// 收入流水（后台充值）
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(rechangeIntegral);
        receiptpay.setDealtype(ReceiptpayConstant.RECHARGE_INTEGRAL); //积分充值【后台】
        receiptpay.setDealstate(ReceiptpayConstant.INCOME);
//        receiptpay.setPayway(null);
        receiptpay.setUserid(Linkuser.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
//        receiptpay.setOther(null);
        receiptpay.setExplain("后台充值了"+rechangeIntegral+"积分");
        receiptpayRepository.save(receiptpay);
		return Result.suc("充值成功");
	}

	@Override
	public synchronized Result deductIntegral(String mobile, String integral) {
		log.debug("扣减积分手机号{}, 积分{}", mobile, integral);
		Linkuser Linkuser = rewrite_LinkuserRepository.findByPhone(mobile);
		if (Linkuser == null) {
			return Result.fail("用户不存在");
		}
		Userassets userassets = rewrite_UserassetsRepository.findByUserid(Linkuser.getUserid());
		if (userassets == null) {
			return Result.fail("目标用户不存在");
		}
		BigDecimal userIntegral = new BigDecimal(userassets.getIntegral());
		BigDecimal deductIntegral = new BigDecimal(integral);
		if (deductIntegral.compareTo(BigDecimal.ZERO) < 0) {
			return Result.fail("操作失败，扣减数目不能小于0");
		}
		if (userIntegral.subtract(deductIntegral).compareTo(BigDecimal.ZERO) < 0) {
			return Result.fail("操作失败，扣减余额不能大于总余额");
		}
		userassets.setIntegral(userIntegral.subtract(deductIntegral).setScale(3).toString());
		rewrite_UserassetsRepository.saveAndFlush(userassets);
		// 支出流水（后台扣减）
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setAmount(deductIntegral);
        receiptpay.setDealtype(ReceiptpayConstant.REDUCT_INTEGRAL); //余额扣减【后台】
        receiptpay.setDealstate(ReceiptpayConstant.PAY);
//        receiptpay.setPayway(null);
        receiptpay.setUserid(Linkuser.getUserid());
        receiptpay.setCreatedate(TimeUtil.getDate());
        receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
        receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
        receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
        receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
        receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
//        receiptpay.setOther(null);
        receiptpay.setExplain("后台扣减了"+deductIntegral+"积分");
        receiptpayRepository.save(receiptpay);
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
		Rewrite_PriceDTO rewrite_PriceDTO = new Rewrite_PriceDTO();
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
