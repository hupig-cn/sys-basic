package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.PercentageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Percentage} and its DTO {@link PercentageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PercentageMapper extends EntityMapper<PercentageDTO, Percentage> {



    default Percentage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Percentage percentage = new Percentage();
        percentage.setId(id);
        return percentage;
    }
}
