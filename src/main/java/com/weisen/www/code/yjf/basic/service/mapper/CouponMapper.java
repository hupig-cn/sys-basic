package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.CouponDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Coupon} and its DTO {@link CouponDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CouponMapper extends EntityMapper<CouponDTO, Coupon> {



    default Coupon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Coupon coupon = new Coupon();
        coupon.setId(id);
        return coupon;
    }
}
