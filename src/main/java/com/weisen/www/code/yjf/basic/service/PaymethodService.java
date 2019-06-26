package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Paymethod}.
 */
public interface PaymethodService {

    /**
     * Save a paymethod.
     *
     * @param paymethodDTO the entity to save.
     * @return the persisted entity.
     */
    PaymethodDTO save(PaymethodDTO paymethodDTO);

    /**
     * Get all the paymethods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PaymethodDTO> findAll(Pageable pageable);


    /**
     * Get the "id" paymethod.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymethodDTO> findOne(Long id);

    /**
     * Delete the "id" paymethod.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
