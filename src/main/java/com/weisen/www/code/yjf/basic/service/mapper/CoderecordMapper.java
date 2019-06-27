package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.CoderecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Coderecord and its DTO CoderecordDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoderecordMapper extends EntityMapper<CoderecordDTO, Coderecord> {



    default Coderecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        Coderecord coderecord = new Coderecord();
        coderecord.setId(id);
        return coderecord;
    }
}
