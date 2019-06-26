package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.LinkaccountService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.LinkaccountDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Linkaccount}.
 */
@RestController
@RequestMapping("/api")
public class LinkaccountResource {

    private final Logger log = LoggerFactory.getLogger(LinkaccountResource.class);

    private static final String ENTITY_NAME = "basicLinkaccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LinkaccountService linkaccountService;

    public LinkaccountResource(LinkaccountService linkaccountService) {
        this.linkaccountService = linkaccountService;
    }

    /**
     * {@code POST  /linkaccounts} : Create a new linkaccount.
     *
     * @param linkaccountDTO the linkaccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new linkaccountDTO, or with status {@code 400 (Bad Request)} if the linkaccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/linkaccounts")
    public ResponseEntity<LinkaccountDTO> createLinkaccount(@RequestBody LinkaccountDTO linkaccountDTO) throws URISyntaxException {
        log.debug("REST request to save Linkaccount : {}", linkaccountDTO);
        if (linkaccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new linkaccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LinkaccountDTO result = linkaccountService.save(linkaccountDTO);
        return ResponseEntity.created(new URI("/api/linkaccounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /linkaccounts} : Updates an existing linkaccount.
     *
     * @param linkaccountDTO the linkaccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linkaccountDTO,
     * or with status {@code 400 (Bad Request)} if the linkaccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the linkaccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/linkaccounts")
    public ResponseEntity<LinkaccountDTO> updateLinkaccount(@RequestBody LinkaccountDTO linkaccountDTO) throws URISyntaxException {
        log.debug("REST request to update Linkaccount : {}", linkaccountDTO);
        if (linkaccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LinkaccountDTO result = linkaccountService.save(linkaccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, linkaccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /linkaccounts} : get all the linkaccounts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of linkaccounts in body.
     */
    @GetMapping("/linkaccounts")
    public ResponseEntity<List<LinkaccountDTO>> getAllLinkaccounts(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Linkaccounts");
        Page<LinkaccountDTO> page = linkaccountService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /linkaccounts/:id} : get the "id" linkaccount.
     *
     * @param id the id of the linkaccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the linkaccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/linkaccounts/{id}")
    public ResponseEntity<LinkaccountDTO> getLinkaccount(@PathVariable Long id) {
        log.debug("REST request to get Linkaccount : {}", id);
        Optional<LinkaccountDTO> linkaccountDTO = linkaccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(linkaccountDTO);
    }

    /**
     * {@code DELETE  /linkaccounts/:id} : delete the "id" linkaccount.
     *
     * @param id the id of the linkaccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/linkaccounts/{id}")
    public ResponseEntity<Void> deleteLinkaccount(@PathVariable Long id) {
        log.debug("REST request to delete Linkaccount : {}", id);
        linkaccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
