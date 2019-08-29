package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.OsversionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Osversion.
 */
public interface OsversionService {

    /**
     * Save a osversion.
     *
     * @param osversionDTO the entity to save
     * @return the persisted entity
     */
    OsversionDTO save(OsversionDTO osversionDTO);

    /**
     * Get all the osversions.
     *
     * @return the list of entities
     */
    List<OsversionDTO> findAll();


    /**
     * Get the "id" osversion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OsversionDTO> findOne(Long id);

    /**
     * Delete the "id" osversion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
