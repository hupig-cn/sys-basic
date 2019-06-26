package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Withdrawal} and its DTO {@link WithdrawalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WithdrawalMapper extends EntityMapper<WithdrawalDTO, Withdrawal> {



    default Withdrawal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(id);
        return withdrawal;
    }
}
