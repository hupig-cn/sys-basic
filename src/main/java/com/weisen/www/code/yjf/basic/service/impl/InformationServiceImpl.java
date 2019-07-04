package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.InformationService;
import com.weisen.www.code.yjf.basic.domain.Information;
import com.weisen.www.code.yjf.basic.repository.InformationRepository;
import com.weisen.www.code.yjf.basic.service.dto.InformationDTO;
import com.weisen.www.code.yjf.basic.service.mapper.InformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Information}.
 */
@Service
@Transactional
public class InformationServiceImpl implements InformationService {

    private final Logger log = LoggerFactory.getLogger(InformationServiceImpl.class);

    private final InformationRepository informationRepository;

    private final InformationMapper informationMapper;

    public InformationServiceImpl(InformationRepository informationRepository, InformationMapper informationMapper) {
        this.informationRepository = informationRepository;
        this.informationMapper = informationMapper;
    }

    /**
     * Save a information.
     *
     * @param informationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InformationDTO save(InformationDTO informationDTO) {
        log.debug("Request to save Information : {}", informationDTO);
        Information information = informationMapper.toEntity(informationDTO);
        information = informationRepository.save(information);
        return informationMapper.toDto(information);
    }

    /**
     * Get all the information.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InformationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Information");
        return informationRepository.findAll(pageable)
            .map(informationMapper::toDto);
    }


    /**
     * Get one information by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InformationDTO> findOne(Long id) {
        log.debug("Request to get Information : {}", id);
        return informationRepository.findById(id)
            .map(informationMapper::toDto);
    }

    /**
     * Delete the information by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Information : {}", id);
        informationRepository.deleteById(id);
    }
}
