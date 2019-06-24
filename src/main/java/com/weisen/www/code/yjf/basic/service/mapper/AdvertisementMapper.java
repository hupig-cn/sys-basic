package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.AdvertisementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Advertisement} and its DTO {@link AdvertisementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdvertisementMapper extends EntityMapper<AdvertisementDTO, Advertisement> {



    default Advertisement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Advertisement advertisement = new Advertisement();
        advertisement.setId(id);
        return advertisement;
    }
}
