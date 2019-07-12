package com.weisen.www.code.yjf.basic.service.rewrite.mapper;

import com.weisen.www.code.yjf.basic.domain.Area;
import com.weisen.www.code.yjf.basic.service.mapper.EntityMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_AreaDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_AreaMapper extends EntityMapper<Rewrite_AreaDTO, Area> {



    default Area fromId(Long id) {
        if (id == null) {
            return null;
        }
        Area area = new Area();
        area.setId(id);
        return area;
    }
}
