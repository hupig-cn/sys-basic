package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.WithdrawaldetailsService;
import com.weisen.www.code.yjf.basic.domain.Withdrawaldetails;
import com.weisen.www.code.yjf.basic.repository.WithdrawaldetailsRepository;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawaldetailsDTO;
import com.weisen.www.code.yjf.basic.service.mapper.WithdrawaldetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Withdrawaldetails}.
 */
@Service
@Transactional
public class WithdrawaldetailsServiceImpl implements WithdrawaldetailsService {

    private final Logger log = LoggerFactory.getLogger(WithdrawaldetailsServiceImpl.class);

    private final WithdrawaldetailsRepository withdrawaldetailsRepository;

    private final WithdrawaldetailsMapper withdrawaldetailsMapper;

    public WithdrawaldetailsServiceImpl(WithdrawaldetailsRepository withdrawaldetailsRepository, WithdrawaldetailsMapper withdrawaldetailsMapper) {
        this.withdrawaldetailsRepository = withdrawaldetailsRepository;
        this.withdrawaldetailsMapper = withdrawaldetailsMapper;
    }

    /**
     * Save a withdrawaldetails.
     *
     * @param withdrawaldetailsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WithdrawaldetailsDTO save(WithdrawaldetailsDTO withdrawaldetailsDTO) {
        log.debug("Request to save Withdrawaldetails : {}", withdrawaldetailsDTO);
        Withdrawaldetails withdrawaldetails = withdrawaldetailsMapper.toEntity(withdrawaldetailsDTO);
        withdrawaldetails = withdrawaldetailsRepository.save(withdrawaldetails);
        return withdrawaldetailsMapper.toDto(withdrawaldetails);
    }

    /**
     * Get all the withdrawaldetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WithdrawaldetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Withdrawaldetails");
        return withdrawaldetailsRepository.findAll(pageable)
            .map(withdrawaldetailsMapper::toDto);
    }


    /**
     * Get one withdrawaldetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WithdrawaldetailsDTO> findOne(Long id) {
        log.debug("Request to get Withdrawaldetails : {}", id);
        return withdrawaldetailsRepository.findById(id)
            .map(withdrawaldetailsMapper::toDto);
    }

    /**
     * Delete the withdrawaldetails by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Withdrawaldetails : {}", id);
        withdrawaldetailsRepository.deleteById(id);
    }
}
