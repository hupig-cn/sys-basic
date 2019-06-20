package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.FeedbackDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Feedback} and its DTO {@link FeedbackDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FeedbackMapper extends EntityMapper<FeedbackDTO, Feedback> {



    default Feedback fromId(Long id) {
        if (id == null) {
            return null;
        }
        Feedback feedback = new Feedback();
        feedback.setId(id);
        return feedback;
    }
}
