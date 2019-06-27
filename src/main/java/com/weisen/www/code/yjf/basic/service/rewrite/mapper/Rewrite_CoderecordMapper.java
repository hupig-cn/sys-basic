package com.weisen.www.code.yjf.basic.service.rewrite.mapper;

import com.weisen.www.code.yjf.basic.domain.Coderecord;
import com.weisen.www.code.yjf.basic.service.dto.CoderecordDTO;
import com.weisen.www.code.yjf.basic.service.mapper.EntityMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_CoderecordDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Coderecord} and its DTO {@link CoderecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_CoderecordMapper extends EntityMapper<Rewrite_CoderecordDTO, Coderecord> {



    default Coderecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        Coderecord coderecord = new Coderecord();
        coderecord.setId(id);
        return coderecord;
    }
}
