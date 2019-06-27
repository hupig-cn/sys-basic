package com.weisen.www.code.yjf.basic.service.rewrite.mapper;

import com.weisen.www.code.yjf.basic.domain.Information;
import com.weisen.www.code.yjf.basic.service.dto.InformationDTO;
import com.weisen.www.code.yjf.basic.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_InformationMapper extends EntityMapper<InformationDTO, Information> {



    default Information fromId(Long id) {
        if (id == null) {
            return null;
        }
        Information information = new Information();
        information.setId(id);
        return information;
    }
}
