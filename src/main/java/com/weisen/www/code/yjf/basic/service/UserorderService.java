package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Userorder}.
 */
public interface UserorderService {

    /**
     * Save a userorder.
     *
     * @param userorderDTO the entity to save.
     * @return the persisted entity.
     */
    UserorderDTO save(UserorderDTO userorderDTO);

    /**
     * Get all the userorders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserorderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userorder.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserorderDTO> findOne(Long id);

    /**
     * Delete the "id" userorder.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
