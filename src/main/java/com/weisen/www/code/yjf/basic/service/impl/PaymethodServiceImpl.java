package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.PaymethodService;
import com.weisen.www.code.yjf.basic.domain.Paymethod;
import com.weisen.www.code.yjf.basic.repository.PaymethodRepository;
import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.service.mapper.PaymethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Paymethod}.
 */
@Service
@Transactional
public class PaymethodServiceImpl implements PaymethodService {

    private final Logger log = LoggerFactory.getLogger(PaymethodServiceImpl.class);

    private final PaymethodRepository paymethodRepository;

    private final PaymethodMapper paymethodMapper;

    public PaymethodServiceImpl(PaymethodRepository paymethodRepository, PaymethodMapper paymethodMapper) {
        this.paymethodRepository = paymethodRepository;
        this.paymethodMapper = paymethodMapper;
    }

    /**
     * Save a paymethod.
     *
     * @param paymethodDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PaymethodDTO save(PaymethodDTO paymethodDTO) {
        log.debug("Request to save Paymethod : {}", paymethodDTO);
        Paymethod paymethod = paymethodMapper.toEntity(paymethodDTO);
        paymethod = paymethodRepository.save(paymethod);
        return paymethodMapper.toDto(paymethod);
    }

    /**
     * Get all the paymethods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaymethodDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paymethods");
        return paymethodRepository.findAll(pageable)
            .map(paymethodMapper::toDto);
    }


    /**
     * Get one paymethod by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaymethodDTO> findOne(Long id) {
        log.debug("Request to get Paymethod : {}", id);
        return paymethodRepository.findById(id)
            .map(paymethodMapper::toDto);
    }

    /**
     * Delete the paymethod by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Paymethod : {}", id);
        paymethodRepository.deleteById(id);
    }
}
