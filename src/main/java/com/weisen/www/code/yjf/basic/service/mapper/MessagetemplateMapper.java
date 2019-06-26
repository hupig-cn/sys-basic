package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.MessagetemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Messagetemplate} and its DTO {@link MessagetemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessagetemplateMapper extends EntityMapper<MessagetemplateDTO, Messagetemplate> {



    default Messagetemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        Messagetemplate messagetemplate = new Messagetemplate();
        messagetemplate.setId(id);
        return messagetemplate;
    }
}
