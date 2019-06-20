package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Linkuser}.
 */
public interface LinkuserService {

    /**
     * Save a linkuser.
     *
     * @param linkuserDTO the entity to save.
     * @return the persisted entity.
     */
    LinkuserDTO save(LinkuserDTO linkuserDTO);

    /**
     * Get all the linkusers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LinkuserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" linkuser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LinkuserDTO> findOne(Long id);

    /**
     * Delete the "id" linkuser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
