package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.PercentageService;
import com.weisen.www.code.yjf.basic.domain.Percentage;
import com.weisen.www.code.yjf.basic.repository.PercentageRepository;
import com.weisen.www.code.yjf.basic.service.dto.PercentageDTO;
import com.weisen.www.code.yjf.basic.service.mapper.PercentageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Percentage}.
 */
@Service
@Transactional
public class PercentageServiceImpl implements PercentageService {

    private final Logger log = LoggerFactory.getLogger(PercentageServiceImpl.class);

    private final PercentageRepository percentageRepository;

    private final PercentageMapper percentageMapper;

    public PercentageServiceImpl(PercentageRepository percentageRepository, PercentageMapper percentageMapper) {
        this.percentageRepository = percentageRepository;
        this.percentageMapper = percentageMapper;
    }

    /**
     * Save a percentage.
     *
     * @param percentageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PercentageDTO save(PercentageDTO percentageDTO) {
        log.debug("Request to save Percentage : {}", percentageDTO);
        Percentage percentage = percentageMapper.toEntity(percentageDTO);
        percentage = percentageRepository.save(percentage);
        return percentageMapper.toDto(percentage);
    }

    /**
     * Get all the percentages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PercentageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Percentages");
        return percentageRepository.findAll(pageable)
            .map(percentageMapper::toDto);
    }


    /**
     * Get one percentage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PercentageDTO> findOne(Long id) {
        log.debug("Request to get Percentage : {}", id);
        return percentageRepository.findById(id)
            .map(percentageMapper::toDto);
    }

    /**
     * Delete the percentage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Percentage : {}", id);
        percentageRepository.deleteById(id);
    }
}
