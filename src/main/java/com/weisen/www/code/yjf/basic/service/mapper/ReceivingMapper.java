package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.ReceivingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Receiving and its DTO ReceivingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReceivingMapper extends EntityMapper<ReceivingDTO, Receiving> {



    default Receiving fromId(Long id) {
        if (id == null) {
            return null;
        }
        Receiving receiving = new Receiving();
        receiving.setId(id);
        return receiving;
    }
}
