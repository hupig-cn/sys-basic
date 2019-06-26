package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.WithdrawalService;
import com.weisen.www.code.yjf.basic.domain.Withdrawal;
import com.weisen.www.code.yjf.basic.repository.WithdrawalRepository;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.mapper.WithdrawalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Withdrawal.
 */
@Service
@Transactional
public class WithdrawalServiceImpl implements WithdrawalService {

    private final Logger log = LoggerFactory.getLogger(WithdrawalServiceImpl.class);

    private final WithdrawalRepository withdrawalRepository;

    private final WithdrawalMapper withdrawalMapper;

    public WithdrawalServiceImpl(WithdrawalRepository withdrawalRepository, WithdrawalMapper withdrawalMapper) {
        this.withdrawalRepository = withdrawalRepository;
        this.withdrawalMapper = withdrawalMapper;
    }

    /**
     * Save a withdrawal.
     *
     * @param withdrawalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WithdrawalDTO save(WithdrawalDTO withdrawalDTO) {
        log.debug("Request to save Withdrawal : {}", withdrawalDTO);
        Withdrawal withdrawal = withdrawalMapper.toEntity(withdrawalDTO);
        withdrawal = withdrawalRepository.save(withdrawal);
        return withdrawalMapper.toDto(withdrawal);
    }

    /**
     * Get all the withdrawals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WithdrawalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Withdrawals");
        return withdrawalRepository.findAll(pageable)
            .map(withdrawalMapper::toDto);
    }


    /**
     * Get one withdrawal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WithdrawalDTO> findOne(Long id) {
        log.debug("Request to get Withdrawal : {}", id);
        return withdrawalRepository.findById(id)
            .map(withdrawalMapper::toDto);
    }

    /**
     * Delete the withdrawal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Withdrawal : {}", id);        withdrawalRepository.deleteById(id);
    }
}
