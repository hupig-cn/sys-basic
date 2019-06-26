package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.UserorderService;
import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.repository.UserorderRepository;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserorderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Userorder.
 */
@Service
@Transactional
public class UserorderServiceImpl implements UserorderService {

    private final Logger log = LoggerFactory.getLogger(UserorderServiceImpl.class);

    private final UserorderRepository userorderRepository;

    private final UserorderMapper userorderMapper;

    public UserorderServiceImpl(UserorderRepository userorderRepository, UserorderMapper userorderMapper) {
        this.userorderRepository = userorderRepository;
        this.userorderMapper = userorderMapper;
    }

    /**
     * Save a userorder.
     *
     * @param userorderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserorderDTO save(UserorderDTO userorderDTO) {
        log.debug("Request to save Userorder : {}", userorderDTO);
        Userorder userorder = userorderMapper.toEntity(userorderDTO);
        userorder = userorderRepository.save(userorder);
        return userorderMapper.toDto(userorder);
    }

    /**
     * Get all the userorders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserorderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Userorders");
        return userorderRepository.findAll(pageable)
            .map(userorderMapper::toDto);
    }


    /**
     * Get one userorder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserorderDTO> findOne(Long id) {
        log.debug("Request to get Userorder : {}", id);
        return userorderRepository.findById(id)
            .map(userorderMapper::toDto);
    }

    /**
     * Delete the userorder by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Userorder : {}", id);        userorderRepository.deleteById(id);
    }
}
