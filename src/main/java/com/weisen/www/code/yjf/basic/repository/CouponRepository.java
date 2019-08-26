package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Coupon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Coupon entity.
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
