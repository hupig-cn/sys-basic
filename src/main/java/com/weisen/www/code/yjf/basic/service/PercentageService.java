package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.PercentageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Percentage}.
 */
public interface PercentageService {

    /**
     * Save a percentage.
     *
     * @param percentageDTO the entity to save.
     * @return the persisted entity.
     */
    PercentageDTO save(PercentageDTO percentageDTO);

    /**
     * Get all the percentages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PercentageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" percentage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PercentageDTO> findOne(Long id);

    /**
     * Delete the "id" percentage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
