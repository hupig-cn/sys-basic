package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.ProfitlogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Profitlog}.
 */
public interface ProfitlogService {

    /**
     * Save a profitlog.
     *
     * @param profitlogDTO the entity to save.
     * @return the persisted entity.
     */
    ProfitlogDTO save(ProfitlogDTO profitlogDTO);

    /**
     * Get all the profitlogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProfitlogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" profitlog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfitlogDTO> findOne(Long id);

    /**
     * Delete the "id" profitlog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
