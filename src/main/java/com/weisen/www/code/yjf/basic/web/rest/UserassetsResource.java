package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.UserassetsService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Userassets}.
 */
@RestController
@RequestMapping("/api")
public class UserassetsResource {

    private final Logger log = LoggerFactory.getLogger(UserassetsResource.class);

    private static final String ENTITY_NAME = "basicUserassets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserassetsService userassetsService;

    public UserassetsResource(UserassetsService userassetsService) {
        this.userassetsService = userassetsService;
    }

    /**
     * {@code POST  /userassets} : Create a new userassets.
     *
     * @param userassetsDTO the userassetsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userassetsDTO, or with status {@code 400 (Bad Request)} if the userassets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/userassets")
    public ResponseEntity<UserassetsDTO> createUserassets(@RequestBody UserassetsDTO userassetsDTO) throws URISyntaxException {
        log.debug("REST request to save Userassets : {}", userassetsDTO);
        if (userassetsDTO.getId() != null) {
            throw new BadRequestAlertException("A new userassets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserassetsDTO result = userassetsService.save(userassetsDTO);
        return ResponseEntity.created(new URI("/api/userassets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /userassets} : Updates an existing userassets.
     *
     * @param userassetsDTO the userassetsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userassetsDTO,
     * or with status {@code 400 (Bad Request)} if the userassetsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userassetsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/userassets")
    public ResponseEntity<UserassetsDTO> updateUserassets(@RequestBody UserassetsDTO userassetsDTO) throws URISyntaxException {
        log.debug("REST request to update Userassets : {}", userassetsDTO);
        if (userassetsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserassetsDTO result = userassetsService.save(userassetsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userassetsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /userassets} : get all the userassets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userassets in body.
     */
    @GetMapping("/userassets")
    public ResponseEntity<List<UserassetsDTO>> getAllUserassets(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Userassets");
        Page<UserassetsDTO> page = userassetsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /userassets/:id} : get the "id" userassets.
     *
     * @param id the id of the userassetsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userassetsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/userassets/{id}")
    public ResponseEntity<UserassetsDTO> getUserassets(@PathVariable Long id) {
        log.debug("REST request to get Userassets : {}", id);
        Optional<UserassetsDTO> userassetsDTO = userassetsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userassetsDTO);
    }

    /**
     * {@code DELETE  /userassets/:id} : delete the "id" userassets.
     *
     * @param id the id of the userassetsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/userassets/{id}")
    public ResponseEntity<Void> deleteUserassets(@PathVariable Long id) {
        log.debug("REST request to delete Userassets : {}", id);
        userassetsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
