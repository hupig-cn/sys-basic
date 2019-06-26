package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Userassets} and its DTO {@link UserassetsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserassetsMapper extends EntityMapper<UserassetsDTO, Userassets> {



    default Userassets fromId(Long id) {
        if (id == null) {
            return null;
        }
        Userassets userassets = new Userassets();
        userassets.setId(id);
        return userassets;
    }
}
