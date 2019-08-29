package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.StatisticsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Statistics.
 */
public interface StatisticsService {

    /**
     * Save a statistics.
     *
     * @param statisticsDTO the entity to save
     * @return the persisted entity
     */
    StatisticsDTO save(StatisticsDTO statisticsDTO);

    /**
     * Get all the statistics.
     *
     * @return the list of entities
     */
    List<StatisticsDTO> findAll();


    /**
     * Get the "id" statistics.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StatisticsDTO> findOne(Long id);

    /**
     * Delete the "id" statistics.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
