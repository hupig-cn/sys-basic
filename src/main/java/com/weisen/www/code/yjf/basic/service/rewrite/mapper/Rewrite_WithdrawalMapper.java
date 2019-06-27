package com.weisen.www.code.yjf.basic.service.rewrite.mapper;

import com.weisen.www.code.yjf.basic.domain.Withdrawal;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.mapper.EntityMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_WithdrawalDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Withdrawal} and its DTO {@link WithdrawalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_WithdrawalMapper extends EntityMapper<Rewrite_WithdrawalDTO, Withdrawal> {



    default Withdrawal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(id);
        return withdrawal;
    }
}
