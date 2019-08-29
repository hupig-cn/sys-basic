package com.weisen.www.code.yjf.basic.service.mapper;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.service.dto.StatisticsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Statistics and its DTO StatisticsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatisticsMapper extends EntityMapper<StatisticsDTO, Statistics> {



    default Statistics fromId(Long id) {
        if (id == null) {
            return null;
        }
        Statistics statistics = new Statistics();
        statistics.setId(id);
        return statistics;
    }
}
