package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.UserlinkuserService;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.dto.UserlinkuserDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserlinkuserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Userlinkuser}.
 */
@Service
@Transactional
public class UserlinkuserServiceImpl implements UserlinkuserService {

    private final Logger log = LoggerFactory.getLogger(UserlinkuserServiceImpl.class);

    private final UserlinkuserRepository userlinkuserRepository;

    private final UserlinkuserMapper userlinkuserMapper;

    public UserlinkuserServiceImpl(UserlinkuserRepository userlinkuserRepository, UserlinkuserMapper userlinkuserMapper) {
        this.userlinkuserRepository = userlinkuserRepository;
        this.userlinkuserMapper = userlinkuserMapper;
    }

    /**
     * Save a userlinkuser.
     *
     * @param userlinkuserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserlinkuserDTO save(UserlinkuserDTO userlinkuserDTO) {
        log.debug("Request to save Userlinkuser : {}", userlinkuserDTO);
        Userlinkuser userlinkuser = userlinkuserMapper.toEntity(userlinkuserDTO);
        userlinkuser = userlinkuserRepository.save(userlinkuser);
        return userlinkuserMapper.toDto(userlinkuser);
    }

    /**
     * Get all the userlinkusers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserlinkuserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Userlinkusers");
        return userlinkuserRepository.findAll(pageable)
            .map(userlinkuserMapper::toDto);
    }


    /**
     * Get one userlinkuser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserlinkuserDTO> findOne(Long id) {
        log.debug("Request to get Userlinkuser : {}", id);
        return userlinkuserRepository.findById(id)
            .map(userlinkuserMapper::toDto);
    }

    /**
     * Delete the userlinkuser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Userlinkuser : {}", id);
        userlinkuserRepository.deleteById(id);
    }
}
