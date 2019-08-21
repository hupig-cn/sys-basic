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

import com.weisen.www.code.yjf.basic.service.UserbankcardService;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Userbankcard}.
 */
@RestController
@RequestMapping("/api")
public class UserbankcardResource {

    private final Logger log = LoggerFactory.getLogger(UserbankcardResource.class);

    private static final String ENTITY_NAME = "basicUserbankcard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserbankcardService userbankcardService;

    public UserbankcardResource(UserbankcardService userbankcardService) {
        this.userbankcardService = userbankcardService;
    }

    /**
     * {@code POST  /userbankcards} : Create a new userbankcard.
     *
     * @param userbankcardDTO the userbankcardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userbankcardDTO, or with status {@code 400 (Bad Request)} if the userbankcard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/userbankcards")
    public ResponseEntity<UserbankcardDTO> createUserbankcard(@RequestBody UserbankcardDTO userbankcardDTO) throws URISyntaxException {
        log.debug("REST request to save Userbankcard : {}", userbankcardDTO);
        if (userbankcardDTO.getId() != null) {
            throw new BadRequestAlertException("A new userbankcard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserbankcardDTO result = userbankcardService.save(userbankcardDTO);
        return ResponseEntity.created(new URI("/api/userbankcards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /userbankcards} : Updates an existing userbankcard.
     *
     * @param userbankcardDTO the userbankcardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userbankcardDTO,
     * or with status {@code 400 (Bad Request)} if the userbankcardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userbankcardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/userbankcards")
    public ResponseEntity<UserbankcardDTO> updateUserbankcard(@RequestBody UserbankcardDTO userbankcardDTO) throws URISyntaxException {
        log.debug("REST request to update Userbankcard : {}", userbankcardDTO);
        if (userbankcardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserbankcardDTO result = userbankcardService.save(userbankcardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userbankcardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /userbankcards} : get all the userbankcards.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userbankcards in body.
     */
    @GetMapping("/userbankcards")
    public ResponseEntity<List<UserbankcardDTO>> getAllUserbankcards(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Userbankcards");
        Page<UserbankcardDTO> page = userbankcardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /userbankcards/:id} : get the "id" userbankcard.
     *
     * @param id the id of the userbankcardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userbankcardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/userbankcards/{id}")
    public ResponseEntity<UserbankcardDTO> getUserbankcard(@PathVariable Long id) {
        log.debug("REST request to get Userbankcard : {}", id);
        Optional<UserbankcardDTO> userbankcardDTO = userbankcardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userbankcardDTO);
    }

    /**
     * {@code DELETE  /userbankcards/:id} : delete the "id" userbankcard.
     *
     * @param id the id of the userbankcardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/userbankcards/{id}")
    public ResponseEntity<Void> deleteUserbankcard(@PathVariable Long id) {
        log.debug("REST request to delete Userbankcard : {}", id);
        userbankcardService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
