package com.weisen.www.code.yjf.basic.service.rewrite.mapper;

import com.weisen.www.code.yjf.basic.domain.Statistics;
import com.weisen.www.code.yjf.basic.service.mapper.EntityMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_StatisticsDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Statistics and its DTO StatisticsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Rewrite_StatisticsMapper extends EntityMapper<Rewrite_StatisticsDTO, Statistics> {



    default Statistics fromId(Long id) {
        if (id == null) {
            return null;
        }
        Statistics statistics = new Statistics();
        statistics.setId(id);
        return statistics;
    }
}
