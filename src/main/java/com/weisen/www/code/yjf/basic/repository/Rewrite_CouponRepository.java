package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Rewrite_CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findAllByUserid(String userId);
}
