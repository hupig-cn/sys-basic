package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.AdvertisementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Advertisement}.
 */
public interface AdvertisementService {

    /**
     * Save a advertisement.
     *
     * @param advertisementDTO the entity to save.
     * @return the persisted entity.
     */
    AdvertisementDTO save(AdvertisementDTO advertisementDTO);

    /**
     * Get all the advertisements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdvertisementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" advertisement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdvertisementDTO> findOne(Long id);

    /**
     * Delete the "id" advertisement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
