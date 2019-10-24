package com.weisen.www.code.yjf.basic.repository.rewrite;

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
	// 根据用户UserId和优惠券createDate创建时间查询优惠券的数据
	// number值为0查询当前月份数据,为1是查询上个月,为-1是查询下个月
	@Transactional
	@Query(value = "SELECT c.* FROM coupon c WHERE c.userid = ?1 AND PERIOD_DIFF(DATE_FORMAT(CURDATE(),'%Y%m'),DATE_FORMAT(?2,'%Y%m'))=?3", nativeQuery = true)
	List<Coupon> findCouponByUseridAndCreatedate(String userId, String createDate, Integer number);
}
