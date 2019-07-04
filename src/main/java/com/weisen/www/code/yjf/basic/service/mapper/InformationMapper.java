package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.InformationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Information and its DTO InformationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InformationMapper extends EntityMapper<InformationDTO, Information> {



    default Information fromId(Long id) {
        if (id == null) {
            return null;
        }
        Information information = new Information();
        information.setId(id);
        return information;
    }
}
