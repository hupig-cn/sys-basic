package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.IssueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Issue} and its DTO {@link IssueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IssueMapper extends EntityMapper<IssueDTO, Issue> {



    default Issue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Issue issue = new Issue();
        issue.setId(id);
        return issue;
    }
}
