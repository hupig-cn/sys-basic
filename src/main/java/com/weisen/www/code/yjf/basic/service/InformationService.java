package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.InformationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Information.
 */
public interface InformationService {

    /**
     * Save a information.
     *
     * @param informationDTO the entity to save
     * @return the persisted entity
     */
    InformationDTO save(InformationDTO informationDTO);

    /**
     * Get all the information.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<InformationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" information.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InformationDTO> findOne(Long id);

    /**
     * Delete the "id" information.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
