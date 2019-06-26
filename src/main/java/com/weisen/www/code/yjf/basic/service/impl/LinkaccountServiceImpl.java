package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.LinkaccountService;
import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.repository.LinkaccountRepository;
import com.weisen.www.code.yjf.basic.service.dto.LinkaccountDTO;
import com.weisen.www.code.yjf.basic.service.mapper.LinkaccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Linkaccount}.
 */
@Service
@Transactional
public class LinkaccountServiceImpl implements LinkaccountService {

    private final Logger log = LoggerFactory.getLogger(LinkaccountServiceImpl.class);

    private final LinkaccountRepository linkaccountRepository;

    private final LinkaccountMapper linkaccountMapper;

    public LinkaccountServiceImpl(LinkaccountRepository linkaccountRepository, LinkaccountMapper linkaccountMapper) {
        this.linkaccountRepository = linkaccountRepository;
        this.linkaccountMapper = linkaccountMapper;
    }

    /**
     * Save a linkaccount.
     *
     * @param linkaccountDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LinkaccountDTO save(LinkaccountDTO linkaccountDTO) {
        log.debug("Request to save Linkaccount : {}", linkaccountDTO);
        Linkaccount linkaccount = linkaccountMapper.toEntity(linkaccountDTO);
        linkaccount = linkaccountRepository.save(linkaccount);
        return linkaccountMapper.toDto(linkaccount);
    }

    /**
     * Get all the linkaccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LinkaccountDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Linkaccounts");
        return linkaccountRepository.findAll(pageable)
            .map(linkaccountMapper::toDto);
    }


    /**
     * Get one linkaccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LinkaccountDTO> findOne(Long id) {
        log.debug("Request to get Linkaccount : {}", id);
        return linkaccountRepository.findById(id)
            .map(linkaccountMapper::toDto);
    }

    /**
     * Delete the linkaccount by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Linkaccount : {}", id);
        linkaccountRepository.deleteById(id);
    }
}
