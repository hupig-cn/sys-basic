package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.UserbankcardService;
import com.weisen.www.code.yjf.basic.domain.Userbankcard;
import com.weisen.www.code.yjf.basic.repository.UserbankcardRepository;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserbankcardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Userbankcard}.
 */
@Service
@Transactional
public class UserbankcardServiceImpl implements UserbankcardService {

    private final Logger log = LoggerFactory.getLogger(UserbankcardServiceImpl.class);

    private final UserbankcardRepository userbankcardRepository;

    private final UserbankcardMapper userbankcardMapper;

    public UserbankcardServiceImpl(UserbankcardRepository userbankcardRepository, UserbankcardMapper userbankcardMapper) {
        this.userbankcardRepository = userbankcardRepository;
        this.userbankcardMapper = userbankcardMapper;
    }

    /**
     * Save a userbankcard.
     *
     * @param userbankcardDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserbankcardDTO save(UserbankcardDTO userbankcardDTO) {
        log.debug("Request to save Userbankcard : {}", userbankcardDTO);
        Userbankcard userbankcard = userbankcardMapper.toEntity(userbankcardDTO);
        userbankcard = userbankcardRepository.save(userbankcard);
        return userbankcardMapper.toDto(userbankcard);
    }

    /**
     * Get all the userbankcards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserbankcardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Userbankcards");
        return userbankcardRepository.findAll(pageable)
            .map(userbankcardMapper::toDto);
    }


    /**
     * Get one userbankcard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserbankcardDTO> findOne(Long id) {
        log.debug("Request to get Userbankcard : {}", id);
        return userbankcardRepository.findById(id)
            .map(userbankcardMapper::toDto);
    }

    /**
     * Delete the userbankcard by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Userbankcard : {}", id);
        userbankcardRepository.deleteById(id);
    }
}
