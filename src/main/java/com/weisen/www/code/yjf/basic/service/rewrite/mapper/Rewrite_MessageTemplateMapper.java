package com.weisen.www.code.yjf.basic.service.rewrite.mapper;

import com.weisen.www.code.yjf.basic.domain.Messagetemplate;
import com.weisen.www.code.yjf.basic.service.mapper.EntityMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_MessageTemplateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_MessageTemplateMapper extends EntityMapper<Rewrite_MessageTemplateDTO, Messagetemplate> {



    default Messagetemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        Messagetemplate messageTemplate = new Messagetemplate();
        messageTemplate.setId(id);
        return messageTemplate;
    }
}
