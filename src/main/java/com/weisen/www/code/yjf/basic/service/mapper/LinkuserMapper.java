package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Linkuser} and its DTO {@link LinkuserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LinkuserMapper extends EntityMapper<LinkuserDTO, Linkuser> {



    default Linkuser fromId(Long id) {
        if (id == null) {
            return null;
        }
        Linkuser linkuser = new Linkuser();
        linkuser.setId(id);
        return linkuser;
    }
}
