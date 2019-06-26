package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.UserlocationService;
import com.weisen.www.code.yjf.basic.domain.Userlocation;
import com.weisen.www.code.yjf.basic.repository.UserlocationRepository;
import com.weisen.www.code.yjf.basic.service.dto.UserlocationDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserlocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Userlocation}.
 */
@Service
@Transactional
public class UserlocationServiceImpl implements UserlocationService {

    private final Logger log = LoggerFactory.getLogger(UserlocationServiceImpl.class);

    private final UserlocationRepository userlocationRepository;

    private final UserlocationMapper userlocationMapper;

    public UserlocationServiceImpl(UserlocationRepository userlocationRepository, UserlocationMapper userlocationMapper) {
        this.userlocationRepository = userlocationRepository;
        this.userlocationMapper = userlocationMapper;
    }

    /**
     * Save a userlocation.
     *
     * @param userlocationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserlocationDTO save(UserlocationDTO userlocationDTO) {
        log.debug("Request to save Userlocation : {}", userlocationDTO);
        Userlocation userlocation = userlocationMapper.toEntity(userlocationDTO);
        userlocation = userlocationRepository.save(userlocation);
        return userlocationMapper.toDto(userlocation);
    }

    /**
     * Get all the userlocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserlocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Userlocations");
        return userlocationRepository.findAll(pageable)
            .map(userlocationMapper::toDto);
    }


    /**
     * Get one userlocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserlocationDTO> findOne(Long id) {
        log.debug("Request to get Userlocation : {}", id);
        return userlocationRepository.findById(id)
            .map(userlocationMapper::toDto);
    }

    /**
     * Delete the userlocation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Userlocation : {}", id);
        userlocationRepository.deleteById(id);
    }
}
