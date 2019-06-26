package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.LinkuserService;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.repository.LinkuserRepository;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;
import com.weisen.www.code.yjf.basic.service.mapper.LinkuserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Linkuser}.
 */
@Service
@Transactional
public class LinkuserServiceImpl implements LinkuserService {

    private final Logger log = LoggerFactory.getLogger(LinkuserServiceImpl.class);

    private final LinkuserRepository linkuserRepository;

    private final LinkuserMapper linkuserMapper;

    public LinkuserServiceImpl(LinkuserRepository linkuserRepository, LinkuserMapper linkuserMapper) {
        this.linkuserRepository = linkuserRepository;
        this.linkuserMapper = linkuserMapper;
    }

    /**
     * Save a linkuser.
     *
     * @param linkuserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LinkuserDTO save(LinkuserDTO linkuserDTO) {
        log.debug("Request to save Linkuser : {}", linkuserDTO);
        Linkuser linkuser = linkuserMapper.toEntity(linkuserDTO);
        linkuser = linkuserRepository.save(linkuser);
        return linkuserMapper.toDto(linkuser);
    }

    /**
     * Get all the linkusers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LinkuserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Linkusers");
        return linkuserRepository.findAll(pageable)
            .map(linkuserMapper::toDto);
    }


    /**
     * Get one linkuser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LinkuserDTO> findOne(Long id) {
        log.debug("Request to get Linkuser : {}", id);
        return linkuserRepository.findById(id)
            .map(linkuserMapper::toDto);
    }

    /**
     * Delete the linkuser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Linkuser : {}", id);
        linkuserRepository.deleteById(id);
    }
}
