package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.MessagetemplateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Messagetemplate}.
 */
public interface MessagetemplateService {

    /**
     * Save a messagetemplate.
     *
     * @param messagetemplateDTO the entity to save.
     * @return the persisted entity.
     */
    MessagetemplateDTO save(MessagetemplateDTO messagetemplateDTO);

    /**
     * Get all the messagetemplates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MessagetemplateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" messagetemplate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MessagetemplateDTO> findOne(Long id);

    /**
     * Delete the "id" messagetemplate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
