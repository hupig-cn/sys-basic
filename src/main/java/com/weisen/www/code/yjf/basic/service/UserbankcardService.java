package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Userbankcard}.
 */
public interface UserbankcardService {

    /**
     * Save a userbankcard.
     *
     * @param userbankcardDTO the entity to save.
     * @return the persisted entity.
     */
    UserbankcardDTO save(UserbankcardDTO userbankcardDTO);

    /**
     * Get all the userbankcards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserbankcardDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userbankcard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserbankcardDTO> findOne(Long id);

    /**
     * Delete the "id" userbankcard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
