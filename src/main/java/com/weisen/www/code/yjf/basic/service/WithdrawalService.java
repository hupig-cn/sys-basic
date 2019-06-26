package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
public interface WithdrawalService {

    /**
     * Save a withdrawal.
     *
     * @param withdrawalDTO the entity to save.
     * @return the persisted entity.
     */
    WithdrawalDTO save(WithdrawalDTO withdrawalDTO);

    /**
     * Get all the withdrawals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WithdrawalDTO> findAll(Pageable pageable);


    /**
     * Get the "id" withdrawal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WithdrawalDTO> findOne(Long id);

    /**
     * Delete the "id" withdrawal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
