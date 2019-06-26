package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.UserlinkuserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Userlinkuser} and its DTO {@link UserlinkuserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserlinkuserMapper extends EntityMapper<UserlinkuserDTO, Userlinkuser> {



    default Userlinkuser fromId(Long id) {
        if (id == null) {
            return null;
        }
        Userlinkuser userlinkuser = new Userlinkuser();
        userlinkuser.setId(id);
        return userlinkuser;
    }
}
