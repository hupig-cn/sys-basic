package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.MessagetemplateService;
import com.weisen.www.code.yjf.basic.domain.Messagetemplate;
import com.weisen.www.code.yjf.basic.repository.MessagetemplateRepository;
import com.weisen.www.code.yjf.basic.service.dto.MessagetemplateDTO;
import com.weisen.www.code.yjf.basic.service.mapper.MessagetemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Messagetemplate}.
 */
@Service
@Transactional
public class MessagetemplateServiceImpl implements MessagetemplateService {

    private final Logger log = LoggerFactory.getLogger(MessagetemplateServiceImpl.class);

    private final MessagetemplateRepository messagetemplateRepository;

    private final MessagetemplateMapper messagetemplateMapper;

    public MessagetemplateServiceImpl(MessagetemplateRepository messagetemplateRepository, MessagetemplateMapper messagetemplateMapper) {
        this.messagetemplateRepository = messagetemplateRepository;
        this.messagetemplateMapper = messagetemplateMapper;
    }

    /**
     * Save a messagetemplate.
     *
     * @param messagetemplateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MessagetemplateDTO save(MessagetemplateDTO messagetemplateDTO) {
        log.debug("Request to save Messagetemplate : {}", messagetemplateDTO);
        Messagetemplate messagetemplate = messagetemplateMapper.toEntity(messagetemplateDTO);
        messagetemplate = messagetemplateRepository.save(messagetemplate);
        return messagetemplateMapper.toDto(messagetemplate);
    }

    /**
     * Get all the messagetemplates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MessagetemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Messagetemplates");
        return messagetemplateRepository.findAll(pageable)
            .map(messagetemplateMapper::toDto);
    }


    /**
     * Get one messagetemplate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MessagetemplateDTO> findOne(Long id) {
        log.debug("Request to get Messagetemplate : {}", id);
        return messagetemplateRepository.findById(id)
            .map(messagetemplateMapper::toDto);
    }

    /**
     * Delete the messagetemplate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Messagetemplate : {}", id);
        messagetemplateRepository.deleteById(id);
    }
}
