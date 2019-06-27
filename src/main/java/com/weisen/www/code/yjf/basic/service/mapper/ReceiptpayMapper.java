package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Receiptpay} and its DTO {@link ReceiptpayDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReceiptpayMapper extends EntityMapper<ReceiptpayDTO, Receiptpay> {



    default Receiptpay fromId(Long id) {
        if (id == null) {
            return null;
        }
        Receiptpay receiptpay = new Receiptpay();
        receiptpay.setId(id);
        return receiptpay;
    }
}
