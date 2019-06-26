package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.ProfitlogService;
import com.weisen.www.code.yjf.basic.domain.Profitlog;
import com.weisen.www.code.yjf.basic.repository.ProfitlogRepository;
import com.weisen.www.code.yjf.basic.service.dto.ProfitlogDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ProfitlogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Profitlog}.
 */
@Service
@Transactional
public class ProfitlogServiceImpl implements ProfitlogService {

    private final Logger log = LoggerFactory.getLogger(ProfitlogServiceImpl.class);

    private final ProfitlogRepository profitlogRepository;

    private final ProfitlogMapper profitlogMapper;

    public ProfitlogServiceImpl(ProfitlogRepository profitlogRepository, ProfitlogMapper profitlogMapper) {
        this.profitlogRepository = profitlogRepository;
        this.profitlogMapper = profitlogMapper;
    }

    /**
     * Save a profitlog.
     *
     * @param profitlogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProfitlogDTO save(ProfitlogDTO profitlogDTO) {
        log.debug("Request to save Profitlog : {}", profitlogDTO);
        Profitlog profitlog = profitlogMapper.toEntity(profitlogDTO);
        profitlog = profitlogRepository.save(profitlog);
        return profitlogMapper.toDto(profitlog);
    }

    /**
     * Get all the profitlogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfitlogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Profitlogs");
        return profitlogRepository.findAll(pageable)
            .map(profitlogMapper::toDto);
    }


    /**
     * Get one profitlog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProfitlogDTO> findOne(Long id) {
        log.debug("Request to get Profitlog : {}", id);
        return profitlogRepository.findById(id)
            .map(profitlogMapper::toDto);
    }

    /**
     * Delete the profitlog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Profitlog : {}", id);
        profitlogRepository.deleteById(id);
    }
}
