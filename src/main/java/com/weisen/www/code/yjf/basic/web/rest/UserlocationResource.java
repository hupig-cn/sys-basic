package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.UserlocationService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.UserlocationDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Userlocation}.
 */
@RestController
@RequestMapping("/api")
public class UserlocationResource {

    private final Logger log = LoggerFactory.getLogger(UserlocationResource.class);

    private static final String ENTITY_NAME = "basicUserlocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserlocationService userlocationService;

    public UserlocationResource(UserlocationService userlocationService) {
        this.userlocationService = userlocationService;
    }

    /**
     * {@code POST  /userlocations} : Create a new userlocation.
     *
     * @param userlocationDTO the userlocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userlocationDTO, or with status {@code 400 (Bad Request)} if the userlocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/userlocations")
    public ResponseEntity<UserlocationDTO> createUserlocation(@RequestBody UserlocationDTO userlocationDTO) throws URISyntaxException {
        log.debug("REST request to save Userlocation : {}", userlocationDTO);
        if (userlocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new userlocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserlocationDTO result = userlocationService.save(userlocationDTO);
        return ResponseEntity.created(new URI("/api/userlocations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /userlocations} : Updates an existing userlocation.
     *
     * @param userlocationDTO the userlocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userlocationDTO,
     * or with status {@code 400 (Bad Request)} if the userlocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userlocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/userlocations")
    public ResponseEntity<UserlocationDTO> updateUserlocation(@RequestBody UserlocationDTO userlocationDTO) throws URISyntaxException {
        log.debug("REST request to update Userlocation : {}", userlocationDTO);
        if (userlocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserlocationDTO result = userlocationService.save(userlocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userlocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /userlocations} : get all the userlocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userlocations in body.
     */
    @GetMapping("/userlocations")
    public ResponseEntity<List<UserlocationDTO>> getAllUserlocations(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Userlocations");
        Page<UserlocationDTO> page = userlocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /userlocations/:id} : get the "id" userlocation.
     *
     * @param id the id of the userlocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userlocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/userlocations/{id}")
    public ResponseEntity<UserlocationDTO> getUserlocation(@PathVariable Long id) {
        log.debug("REST request to get Userlocation : {}", id);
        Optional<UserlocationDTO> userlocationDTO = userlocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userlocationDTO);
    }

    /**
     * {@code DELETE  /userlocations/:id} : delete the "id" userlocation.
     *
     * @param id the id of the userlocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/userlocations/{id}")
    public ResponseEntity<Void> deleteUserlocation(@PathVariable Long id) {
        log.debug("REST request to delete Userlocation : {}", id);
        userlocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
