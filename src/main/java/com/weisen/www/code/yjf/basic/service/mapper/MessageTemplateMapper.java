package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.MessageTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageTemplate} and its DTO {@link MessageTemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageTemplateMapper extends EntityMapper<MessageTemplateDTO, MessageTemplate> {



    default MessageTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setId(id);
        return messageTemplate;
    }
}
