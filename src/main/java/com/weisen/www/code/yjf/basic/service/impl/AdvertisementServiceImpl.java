package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.AdvertisementService;
import com.weisen.www.code.yjf.basic.domain.Advertisement;
import com.weisen.www.code.yjf.basic.repository.AdvertisementRepository;
import com.weisen.www.code.yjf.basic.service.dto.AdvertisementDTO;
import com.weisen.www.code.yjf.basic.service.mapper.AdvertisementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Advertisement}.
 */
@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {

    private final Logger log = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

    private final AdvertisementRepository advertisementRepository;

    private final AdvertisementMapper advertisementMapper;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, AdvertisementMapper advertisementMapper) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementMapper = advertisementMapper;
    }

    /**
     * Save a advertisement.
     *
     * @param advertisementDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdvertisementDTO save(AdvertisementDTO advertisementDTO) {
        log.debug("Request to save Advertisement : {}", advertisementDTO);
        Advertisement advertisement = advertisementMapper.toEntity(advertisementDTO);
        advertisement = advertisementRepository.save(advertisement);
        return advertisementMapper.toDto(advertisement);
    }

    /**
     * Get all the advertisements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdvertisementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Advertisements");
        return advertisementRepository.findAll(pageable)
            .map(advertisementMapper::toDto);
    }


    /**
     * Get one advertisement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdvertisementDTO> findOne(Long id) {
        log.debug("Request to get Advertisement : {}", id);
        return advertisementRepository.findById(id)
            .map(advertisementMapper::toDto);
    }

    /**
     * Delete the advertisement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Advertisement : {}", id);
        advertisementRepository.deleteById(id);
    }
}
