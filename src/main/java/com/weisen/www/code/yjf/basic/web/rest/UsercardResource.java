package com.weisen.www.code.yjf.basic.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.weisen.www.code.yjf.basic.service.UsercardService;
import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Usercard}.
 */
@RestController
@RequestMapping("/api")
public class UsercardResource {

    private final Logger log = LoggerFactory.getLogger(UsercardResource.class);

    private static final String ENTITY_NAME = "basicUsercard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsercardService usercardService;

    public UsercardResource(UsercardService usercardService) {
        this.usercardService = usercardService;
    }

    /**
     * {@code POST  /usercards} : Create a new usercard.
     *
     * @param usercardDTO the usercardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usercardDTO, or with status {@code 400 (Bad Request)} if the usercard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/usercards")
    public ResponseEntity<UsercardDTO> createUsercard(@RequestBody UsercardDTO usercardDTO) throws URISyntaxException {
        log.debug("REST request to save Usercard : {}", usercardDTO);
        if (usercardDTO.getId() != null) {
            throw new BadRequestAlertException("A new usercard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsercardDTO result = usercardService.save(usercardDTO);
        return ResponseEntity.created(new URI("/api/usercards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /usercards} : Updates an existing usercard.
     *
     * @param usercardDTO the usercardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usercardDTO,
     * or with status {@code 400 (Bad Request)} if the usercardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usercardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/usercards")
    public ResponseEntity<UsercardDTO> updateUsercard(@RequestBody UsercardDTO usercardDTO) throws URISyntaxException {
        log.debug("REST request to update Usercard : {}", usercardDTO);
        if (usercardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsercardDTO result = usercardService.save(usercardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, usercardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /usercards} : get all the usercards.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usercards in body.
     */
    @GetMapping("/usercards")
    public ResponseEntity<List<UsercardDTO>> getAllUsercards(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Usercards");
        Page<UsercardDTO> page = usercardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /usercards/:id} : get the "id" usercard.
     *
     * @param id the id of the usercardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usercardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/usercards/{id}")
    public ResponseEntity<UsercardDTO> getUsercard(@PathVariable Long id) {
        log.debug("REST request to get Usercard : {}", id);
        Optional<UsercardDTO> usercardDTO = usercardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(usercardDTO);
    }

    /**
     * {@code DELETE  /usercards/:id} : delete the "id" usercard.
     *
     * @param id the id of the usercardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/usercards/{id}")
    public ResponseEntity<Void> deleteUsercard(@PathVariable Long id) {
        log.debug("REST request to delete Usercard : {}", id);
        usercardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
