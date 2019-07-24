package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.ReceivingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Receiving}.
 */
public interface ReceivingService {

    /**
     * Save a receiving.
     *
     * @param receivingDTO the entity to save.
     * @return the persisted entity.
     */
    ReceivingDTO save(ReceivingDTO receivingDTO);

    /**
     * Get all the receivings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReceivingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" receiving.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReceivingDTO> findOne(Long id);

    /**
     * Delete the "id" receiving.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
