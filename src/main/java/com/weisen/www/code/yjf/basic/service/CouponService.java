package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.CouponDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Coupon}.
 */
public interface CouponService {

    /**
     * Save a coupon.
     *
     * @param couponDTO the entity to save.
     * @return the persisted entity.
     */
    CouponDTO save(CouponDTO couponDTO);

    /**
     * Get all the coupons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CouponDTO> findAll(Pageable pageable);


    /**
     * Get the "id" coupon.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CouponDTO> findOne(Long id);

    /**
     * Delete the "id" coupon.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
