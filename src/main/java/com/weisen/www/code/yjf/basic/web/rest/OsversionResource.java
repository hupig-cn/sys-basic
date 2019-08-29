package com.weisen.www.code.yjf.basic.web.rest;
import com.weisen.www.code.yjf.basic.service.OsversionService;
import com.weisen.www.code.yjf.basic.service.dto.OsversionDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Osversion.
 */
@RestController
@RequestMapping("/api")
public class OsversionResource {

    private final Logger log = LoggerFactory.getLogger(OsversionResource.class);

    private static final String ENTITY_NAME = "basicOsversion";

    private final OsversionService osversionService;

    public OsversionResource(OsversionService osversionService) {
        this.osversionService = osversionService;
    }

    /**
     * POST  /osversions : Create a new osversion.
     *
     * @param osversionDTO the osversionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new osversionDTO, or with status 400 (Bad Request) if the osversion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/osversions")
    public ResponseEntity<OsversionDTO> createOsversion(@Valid @RequestBody OsversionDTO osversionDTO) throws URISyntaxException {
        log.debug("REST request to save Osversion : {}", osversionDTO);
//        if (osversionDTO.getId() != null) {
//            throw new BadRequestAlertException("A new osversion cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        OsversionDTO result = osversionService.save(osversionDTO);
        return ResponseEntity.created(new URI("/api/osversions/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /osversions : Updates an existing osversion.
     *
     * @param osversionDTO the osversionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated osversionDTO,
     * or with status 400 (Bad Request) if the osversionDTO is not valid,
     * or with status 500 (Internal Server Error) if the osversionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/osversions")
    public ResponseEntity<OsversionDTO> updateOsversion(@Valid @RequestBody OsversionDTO osversionDTO) throws URISyntaxException {
        log.debug("REST request to update Osversion : {}", osversionDTO);
//        if (osversionDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
        OsversionDTO result = osversionService.save(osversionDTO);
        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, osversionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /osversions : get all the osversions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of osversions in body
     */
    @GetMapping("/osversions")
    public List<OsversionDTO> getAllOsversions() {
        log.debug("REST request to get all Osversions");
        return osversionService.findAll();
    }

    /**
     * GET  /osversions/:id : get the "id" osversion.
     *
     * @param id the id of the osversionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the osversionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/osversions/{id}")
    public ResponseEntity<OsversionDTO> getOsversion(@PathVariable Long id) {
        log.debug("REST request to get Osversion : {}", id);
        Optional<OsversionDTO> osversionDTO = osversionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(osversionDTO);
    }

    /**
     * DELETE  /osversions/:id : delete the "id" osversion.
     *
     * @param id the id of the osversionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/osversions/{id}")
    public ResponseEntity<Void> deleteOsversion(@PathVariable Long id) {
        log.debug("REST request to delete Osversion : {}", id);
        osversionService.delete(id);
        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
            .build();
    }
}
