package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.ReceiptpayService;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.repository.ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ReceiptpayMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Receiptpay.
 */
@Service
@Transactional
public class ReceiptpayServiceImpl implements ReceiptpayService {

    private final Logger log = LoggerFactory.getLogger(ReceiptpayServiceImpl.class);

    private final ReceiptpayRepository receiptpayRepository;

    private final ReceiptpayMapper receiptpayMapper;

    public ReceiptpayServiceImpl(ReceiptpayRepository receiptpayRepository, ReceiptpayMapper receiptpayMapper) {
        this.receiptpayRepository = receiptpayRepository;
        this.receiptpayMapper = receiptpayMapper;
    }

    /**
     * Save a receiptpay.
     *
     * @param receiptpayDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReceiptpayDTO save(ReceiptpayDTO receiptpayDTO) {
        log.debug("Request to save Receiptpay : {}", receiptpayDTO);
        Receiptpay receiptpay = receiptpayMapper.toEntity(receiptpayDTO);
        receiptpay = receiptpayRepository.save(receiptpay);
        return receiptpayMapper.toDto(receiptpay);
    }

    /**
     * Get all the receiptpays.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReceiptpayDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Receiptpays");
        return receiptpayRepository.findAll(pageable)
            .map(receiptpayMapper::toDto);
    }


    /**
     * Get one receiptpay by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReceiptpayDTO> findOne(Long id) {
        log.debug("Request to get Receiptpay : {}", id);
        return receiptpayRepository.findById(id)
            .map(receiptpayMapper::toDto);
    }

    /**
     * Delete the receiptpay by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Receiptpay : {}", id);        receiptpayRepository.deleteById(id);
    }
}
