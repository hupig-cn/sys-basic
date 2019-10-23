package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface Rewrite_CouponRepository extends JpaRepository<Coupon, Long> {

	List<Coupon> findAllByUserid(String userId);

	// 查询当前月份数据
	// select c.* from coupon c where
	// date_format(createdate,'%Y-%m')=date_format(now(),'%Y-%m')

	// 查询下个月的数据
	// SELECT c.* FROM coupon c WHERE
	// PERIOD_DIFF(DATE_FORMAT(CURDATE(),'%Y%m'),DATE_FORMAT(createdate,'%Y%m'))=-1

	// 查询上个月的数据
	// SELECT c.* FROM coupon c WHERE PERIOD_DIFF( date_format( now() , '%Y%m' ) ,
	// date_format( createdate, '%Y%m' ) ) =1
	// 按月份查找优惠券明细
	@Transactional
	@Query(value = "")
	List<Coupon> findByCreatedate(String createdate);
}
