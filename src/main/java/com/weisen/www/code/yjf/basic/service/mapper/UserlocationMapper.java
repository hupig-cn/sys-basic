package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.UserlocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Userlocation} and its DTO {@link UserlocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserlocationMapper extends EntityMapper<UserlocationDTO, Userlocation> {



    default Userlocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Userlocation userlocation = new Userlocation();
        userlocation.setId(id);
        return userlocation;
    }
}
