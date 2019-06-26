package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.LinkaccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Linkaccount} and its DTO {@link LinkaccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LinkaccountMapper extends EntityMapper<LinkaccountDTO, Linkaccount> {



    default Linkaccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        Linkaccount linkaccount = new Linkaccount();
        linkaccount.setId(id);
        return linkaccount;
    }
}
