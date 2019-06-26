package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Userassets}.
 */
public interface UserassetsService {

    /**
     * Save a userassets.
     *
     * @param userassetsDTO the entity to save.
     * @return the persisted entity.
     */
    UserassetsDTO save(UserassetsDTO userassetsDTO);

    /**
     * Get all the userassets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserassetsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userassets.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserassetsDTO> findOne(Long id);

    /**
     * Delete the "id" userassets.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
