package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.WithdrawaldetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Withdrawaldetails}.
 */
public interface WithdrawaldetailsService {

    /**
     * Save a withdrawaldetails.
     *
     * @param withdrawaldetailsDTO the entity to save.
     * @return the persisted entity.
     */
    WithdrawaldetailsDTO save(WithdrawaldetailsDTO withdrawaldetailsDTO);

    /**
     * Get all the withdrawaldetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WithdrawaldetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" withdrawaldetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WithdrawaldetailsDTO> findOne(Long id);

    /**
     * Delete the "id" withdrawaldetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
