package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.LinkaccountDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Linkaccount}.
 */
public interface LinkaccountService {

    /**
     * Save a linkaccount.
     *
     * @param linkaccountDTO the entity to save.
     * @return the persisted entity.
     */
    LinkaccountDTO save(LinkaccountDTO linkaccountDTO);

    /**
     * Get all the linkaccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LinkaccountDTO> findAll(Pageable pageable);


    /**
     * Get the "id" linkaccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LinkaccountDTO> findOne(Long id);

    /**
     * Delete the "id" linkaccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
