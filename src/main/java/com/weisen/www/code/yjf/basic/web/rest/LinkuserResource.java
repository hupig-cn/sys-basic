package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.LinkuserService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Linkuser}.
 */
@RestController
@RequestMapping("/api")
public class LinkuserResource {

    private final Logger log = LoggerFactory.getLogger(LinkuserResource.class);

    private static final String ENTITY_NAME = "basicLinkuser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LinkuserService linkuserService;

    public LinkuserResource(LinkuserService linkuserService) {
        this.linkuserService = linkuserService;
    }

    /**
     * {@code POST  /linkusers} : Create a new linkuser.
     *
     * @param linkuserDTO the linkuserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new linkuserDTO, or with status {@code 400 (Bad Request)} if the linkuser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/linkusers")
    public ResponseEntity<LinkuserDTO> createLinkuser(@RequestBody LinkuserDTO linkuserDTO) throws URISyntaxException {
        log.debug("REST request to save Linkuser : {}", linkuserDTO);
        if (linkuserDTO.getId() != null) {
            throw new BadRequestAlertException("A new linkuser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LinkuserDTO result = linkuserService.save(linkuserDTO);
        return ResponseEntity.created(new URI("/api/linkusers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /linkusers} : Updates an existing linkuser.
     *
     * @param linkuserDTO the linkuserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linkuserDTO,
     * or with status {@code 400 (Bad Request)} if the linkuserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the linkuserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/linkusers")
    public ResponseEntity<LinkuserDTO> updateLinkuser(@RequestBody LinkuserDTO linkuserDTO) throws URISyntaxException {
        log.debug("REST request to update Linkuser : {}", linkuserDTO);
        if (linkuserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LinkuserDTO result = linkuserService.save(linkuserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, linkuserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /linkusers} : get all the linkusers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of linkusers in body.
     */
    @GetMapping("/linkusers")
    public ResponseEntity<List<LinkuserDTO>> getAllLinkusers(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Linkusers");
        Page<LinkuserDTO> page = linkuserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /linkusers/:id} : get the "id" linkuser.
     *
     * @param id the id of the linkuserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the linkuserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/linkusers/{id}")
    public ResponseEntity<LinkuserDTO> getLinkuser(@PathVariable Long id) {
        log.debug("REST request to get Linkuser : {}", id);
        Optional<LinkuserDTO> linkuserDTO = linkuserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(linkuserDTO);
    }

    /**
     * {@code DELETE  /linkusers/:id} : delete the "id" linkuser.
     *
     * @param id the id of the linkuserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/linkusers/{id}")
    public ResponseEntity<Void> deleteLinkuser(@PathVariable Long id) {
        log.debug("REST request to delete Linkuser : {}", id);
        linkuserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
