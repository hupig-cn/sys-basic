package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.UserlinkuserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Userlinkuser}.
 */
public interface UserlinkuserService {

    /**
     * Save a userlinkuser.
     *
     * @param userlinkuserDTO the entity to save.
     * @return the persisted entity.
     */
    UserlinkuserDTO save(UserlinkuserDTO userlinkuserDTO);

    /**
     * Get all the userlinkusers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserlinkuserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userlinkuser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserlinkuserDTO> findOne(Long id);

    /**
     * Delete the "id" userlinkuser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
