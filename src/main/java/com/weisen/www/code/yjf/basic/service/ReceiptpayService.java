package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Receiptpay}.
 */
public interface ReceiptpayService {

    /**
     * Save a receiptpay.
     *
     * @param receiptpayDTO the entity to save.
     * @return the persisted entity.
     */
    ReceiptpayDTO save(ReceiptpayDTO receiptpayDTO);

    /**
     * Get all the receiptpays.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReceiptpayDTO> findAll(Pageable pageable);


    /**
     * Get the "id" receiptpay.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReceiptpayDTO> findOne(Long id);

    /**
     * Delete the "id" receiptpay.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
