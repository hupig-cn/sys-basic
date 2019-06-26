package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paymethod} and its DTO {@link PaymethodDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymethodMapper extends EntityMapper<PaymethodDTO, Paymethod> {



    default Paymethod fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paymethod paymethod = new Paymethod();
        paymethod.setId(id);
        return paymethod;
    }
}
