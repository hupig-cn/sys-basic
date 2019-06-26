package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.UsercardService;
import com.weisen.www.code.yjf.basic.domain.Usercard;
import com.weisen.www.code.yjf.basic.repository.UsercardRepository;
import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UsercardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Usercard}.
 */
@Service
@Transactional
public class UsercardServiceImpl implements UsercardService {

    private final Logger log = LoggerFactory.getLogger(UsercardServiceImpl.class);

    private final UsercardRepository usercardRepository;

    private final UsercardMapper usercardMapper;

    public UsercardServiceImpl(UsercardRepository usercardRepository, UsercardMapper usercardMapper) {
        this.usercardRepository = usercardRepository;
        this.usercardMapper = usercardMapper;
    }

    /**
     * Save a usercard.
     *
     * @param usercardDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UsercardDTO save(UsercardDTO usercardDTO) {
        log.debug("Request to save Usercard : {}", usercardDTO);
        Usercard usercard = usercardMapper.toEntity(usercardDTO);
        usercard = usercardRepository.save(usercard);
        return usercardMapper.toDto(usercard);
    }

    /**
     * Get all the usercards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsercardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Usercards");
        return usercardRepository.findAll(pageable)
            .map(usercardMapper::toDto);
    }


    /**
     * Get one usercard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UsercardDTO> findOne(Long id) {
        log.debug("Request to get Usercard : {}", id);
        return usercardRepository.findById(id)
            .map(usercardMapper::toDto);
    }

    /**
     * Delete the usercard by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Usercard : {}", id);
        usercardRepository.deleteById(id);
    }
}
