package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawaldetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Withdrawaldetails} and its DTO {@link WithdrawaldetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WithdrawaldetailsMapper extends EntityMapper<WithdrawaldetailsDTO, Withdrawaldetails> {



    default Withdrawaldetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        Withdrawaldetails withdrawaldetails = new Withdrawaldetails();
        withdrawaldetails.setId(id);
        return withdrawaldetails;
    }
}
