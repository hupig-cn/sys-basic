package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.UserlocationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Userlocation}.
 */
public interface UserlocationService {

    /**
     * Save a userlocation.
     *
     * @param userlocationDTO the entity to save.
     * @return the persisted entity.
     */
    UserlocationDTO save(UserlocationDTO userlocationDTO);

    /**
     * Get all the userlocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserlocationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userlocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserlocationDTO> findOne(Long id);

    /**
     * Delete the "id" userlocation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
