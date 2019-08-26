package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Userbankcard and its DTO UserbankcardDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserbankcardMapper extends EntityMapper<UserbankcardDTO, Userbankcard> {



    default Userbankcard fromId(Long id) {
        if (id == null) {
            return null;
        }
        Userbankcard userbankcard = new Userbankcard();
        userbankcard.setId(id);
        return userbankcard;
    }
}
