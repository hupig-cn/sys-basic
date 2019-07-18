package com.weisen.www.code.yjf.basic.service.rewrite.mapper;

import com.weisen.www.code.yjf.basic.domain.Receiving;
import com.weisen.www.code.yjf.basic.service.mapper.EntityMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ReceivingDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Receiving and its DTO ReceivingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_ReceivingMapper extends EntityMapper<Rewrite_ReceivingDTO, Receiving> {



    default Receiving fromId(Long id) {
        if (id == null) {
            return null;
        }
        Receiving receiving = new Receiving();
        receiving.setId(id);
        return receiving;
    }
}
