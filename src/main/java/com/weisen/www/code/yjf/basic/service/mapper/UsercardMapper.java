package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Usercard} and its DTO {@link UsercardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsercardMapper extends EntityMapper<UsercardDTO, Usercard> {



    default Usercard fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usercard usercard = new Usercard();
        usercard.setId(id);
        return usercard;
    }
}
