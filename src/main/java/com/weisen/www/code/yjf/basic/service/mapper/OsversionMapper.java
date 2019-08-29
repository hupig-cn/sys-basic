package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.OsversionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Osversion and its DTO OsversionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OsversionMapper extends EntityMapper<OsversionDTO, Osversion> {



    default Osversion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Osversion osversion = new Osversion();
        osversion.setId(id);
        return osversion;
    }
}
