package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.UserassetsService;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.UserassetsRepository;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserassetsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Userassets}.
 */
@Service
@Transactional
public class UserassetsServiceImpl implements UserassetsService {

    private final Logger log = LoggerFactory.getLogger(UserassetsServiceImpl.class);

    private final UserassetsRepository userassetsRepository;

    private final UserassetsMapper userassetsMapper;

    public UserassetsServiceImpl(UserassetsRepository userassetsRepository, UserassetsMapper userassetsMapper) {
        this.userassetsRepository = userassetsRepository;
        this.userassetsMapper = userassetsMapper;
    }

    /**
     * Save a userassets.
     *
     * @param userassetsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserassetsDTO save(UserassetsDTO userassetsDTO) {
        log.debug("Request to save Userassets : {}", userassetsDTO);
        Userassets userassets = userassetsMapper.toEntity(userassetsDTO);
        userassets = userassetsRepository.save(userassets);
        return userassetsMapper.toDto(userassets);
    }

    /**
     * Get all the userassets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserassetsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Userassets");
        return userassetsRepository.findAll(pageable)
            .map(userassetsMapper::toDto);
    }


    /**
     * Get one userassets by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserassetsDTO> findOne(Long id) {
        log.debug("Request to get Userassets : {}", id);
        return userassetsRepository.findById(id)
            .map(userassetsMapper::toDto);
    }

    /**
     * Delete the userassets by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Userassets : {}", id);
        userassetsRepository.deleteById(id);
    }
}
