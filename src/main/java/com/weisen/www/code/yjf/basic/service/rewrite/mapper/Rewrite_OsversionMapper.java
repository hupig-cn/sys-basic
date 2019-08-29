package com.weisen.www.code.yjf.basic.service.rewrite.mapper;

import com.weisen.www.code.yjf.basic.domain.Osversion;
import com.weisen.www.code.yjf.basic.service.mapper.EntityMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_OsversionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Osversion and its DTO OsversionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_OsversionMapper extends EntityMapper<Rewrite_OsversionDTO, Osversion> {



    default Osversion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Osversion osversion = new Osversion();
        osversion.setId(id);
        return osversion;
    }
}
