package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.ProfitlogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profitlog} and its DTO {@link ProfitlogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfitlogMapper extends EntityMapper<ProfitlogDTO, Profitlog> {



    default Profitlog fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profitlog profitlog = new Profitlog();
        profitlog.setId(id);
        return profitlog;
    }
}
