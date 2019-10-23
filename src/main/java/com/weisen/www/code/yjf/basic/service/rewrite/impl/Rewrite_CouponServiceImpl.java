package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Coupon;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_CouponRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_CouponService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_CouponDTO;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_CouponServiceImpl implements Rewrite_CouponService {

	private final Rewrite_CouponRepository rewrite_CouponRepository;

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	public Rewrite_CouponServiceImpl(Rewrite_CouponRepository rewrite_CouponRepository,
			Rewrite_LinkuserRepository rewrite_LinkuserRepository) {
		this.rewrite_CouponRepository = rewrite_CouponRepository;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
	}

	/**
	 * 查询优惠券明细页
	 * 
	 * @author LuoJinShui
	 */
	@Override
	public Result getCoupon(String userId) {
		// 判断是否有该用户
		Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(userId);
		if (linkuser == null) {
			return Result.fail("没有该用户!请重新输入查找!");
		} else {
			// 查询用户所有的优惠券
			List<Coupon> couponList = rewrite_CouponRepository.findAllByUserid(userId);
			List<Rewrite_CouponDTO> couponDTOList = new ArrayList<>();
			for (Coupon coupon : couponList) {
				Rewrite_CouponDTO couponDTO = new Rewrite_CouponDTO();
				// 获取优惠券数值
				couponDTO.setSum(coupon.getSum());
				// 查询优惠券类型
				couponDTO.setCouponType(coupon.getCoupontype());
				// 可以线上支付
				couponDTO.setLineon(coupon.isLineon());
				// 可以线下支付
				couponDTO.setLineunder(coupon.isLineunder());
				// 可以产生积分
				couponDTO.setIntegral(coupon.isIntegral());
				// 可以产生收益
				couponDTO.setProfit(coupon.isProfit());
				// 创建者
				couponDTO.setCreator(coupon.getCreator());
				// 创建时间
				couponDTO.setCreateDate(coupon.getCreatedate());
				// 修改者
				couponDTO.setModifier(coupon.getModifier());
				// 修改时间
				couponDTO.setModifierDate(coupon.getModifierdate());
				// 修改次数
				couponDTO.setModifierNum(coupon.getModifiernum());
				// 逻辑删除
				couponDTO.setLogicDelete(coupon.isLogicdelete());
				// 备注
				couponDTO.setOther(coupon.getOther());
				couponDTOList.add(couponDTO);
			}
			return Result.suc("查询成功!", couponDTOList);
		}
	}

}
