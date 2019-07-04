package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.CoderecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Coderecord.
 */
public interface CoderecordService {

    /**
     * Save a coderecord.
     *
     * @param coderecordDTO the entity to save
     * @return the persisted entity
     */
    CoderecordDTO save(CoderecordDTO coderecordDTO);

    /**
     * Get all the coderecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CoderecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" coderecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CoderecordDTO> findOne(Long id);

    /**
     * Delete the "id" coderecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
