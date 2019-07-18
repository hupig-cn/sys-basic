package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.ReceivingService;
import com.weisen.www.code.yjf.basic.domain.Receiving;
import com.weisen.www.code.yjf.basic.repository.ReceivingRepository;
import com.weisen.www.code.yjf.basic.service.dto.ReceivingDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ReceivingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Receiving.
 */
@Service
@Transactional
public class ReceivingServiceImpl implements ReceivingService {

    private final Logger log = LoggerFactory.getLogger(ReceivingServiceImpl.class);

    private final ReceivingRepository receivingRepository;

    private final ReceivingMapper receivingMapper;

    public ReceivingServiceImpl(ReceivingRepository receivingRepository, ReceivingMapper receivingMapper) {
        this.receivingRepository = receivingRepository;
        this.receivingMapper = receivingMapper;
    }

    /**
     * Save a receiving.
     *
     * @param receivingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReceivingDTO save(ReceivingDTO receivingDTO) {
        log.debug("Request to save Receiving : {}", receivingDTO);
        Receiving receiving = receivingMapper.toEntity(receivingDTO);
        receiving = receivingRepository.save(receiving);
        return receivingMapper.toDto(receiving);
    }

    /**
     * Get all the receivings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReceivingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Receivings");
        return receivingRepository.findAll(pageable)
            .map(receivingMapper::toDto);
    }


    /**
     * Get one receiving by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReceivingDTO> findOne(Long id) {
        log.debug("Request to get Receiving : {}", id);
        return receivingRepository.findById(id)
            .map(receivingMapper::toDto);
    }

    /**
     * Delete the receiving by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Receiving : {}", id);
        receivingRepository.deleteById(id);
    }
}
