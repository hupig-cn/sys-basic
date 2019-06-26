package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Userorder and its DTO UserorderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserorderMapper extends EntityMapper<UserorderDTO, Userorder> {



    default Userorder fromId(Long id) {
        if (id == null) {
            return null;
        }
        Userorder userorder = new Userorder();
        userorder.setId(id);
        return userorder;
    }
}
