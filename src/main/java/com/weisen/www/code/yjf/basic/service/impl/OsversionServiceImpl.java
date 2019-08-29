package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.OsversionService;
import com.weisen.www.code.yjf.basic.domain.Osversion;
import com.weisen.www.code.yjf.basic.repository.OsversionRepository;
import com.weisen.www.code.yjf.basic.service.dto.OsversionDTO;
import com.weisen.www.code.yjf.basic.service.mapper.OsversionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Osversion.
 */
@Service
@Transactional
public class OsversionServiceImpl implements OsversionService {

    private final Logger log = LoggerFactory.getLogger(OsversionServiceImpl.class);

    private final OsversionRepository osversionRepository;

    private final OsversionMapper osversionMapper;

    public OsversionServiceImpl(OsversionRepository osversionRepository, OsversionMapper osversionMapper) {
        this.osversionRepository = osversionRepository;
        this.osversionMapper = osversionMapper;
    }

    /**
     * Save a osversion.
     *
     * @param osversionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OsversionDTO save(OsversionDTO osversionDTO) {
        log.debug("Request to save Osversion : {}", osversionDTO);
        Osversion osversion = osversionMapper.toEntity(osversionDTO);
        osversion = osversionRepository.save(osversion);
        return osversionMapper.toDto(osversion);
    }

    /**
     * Get all the osversions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OsversionDTO> findAll() {
        log.debug("Request to get all Osversions");
        return osversionRepository.findAll().stream()
            .map(osversionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one osversion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OsversionDTO> findOne(Long id) {
        log.debug("Request to get Osversion : {}", id);
        return osversionRepository.findById(id)
            .map(osversionMapper::toDto);
    }

    /**
     * Delete the osversion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Osversion : {}", id);
        osversionRepository.deleteById(id);
    }
}
