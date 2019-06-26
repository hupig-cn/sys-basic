package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Usercard}.
 */
public interface UsercardService {

    /**
     * Save a usercard.
     *
     * @param usercardDTO the entity to save.
     * @return the persisted entity.
     */
    UsercardDTO save(UsercardDTO usercardDTO);

    /**
     * Get all the usercards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UsercardDTO> findAll(Pageable pageable);


    /**
     * Get the "id" usercard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UsercardDTO> findOne(Long id);

    /**
     * Delete the "id" usercard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
