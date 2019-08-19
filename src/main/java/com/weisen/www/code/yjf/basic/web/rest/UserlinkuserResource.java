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

import com.weisen.www.code.yjf.basic.service.UserlinkuserService;
import com.weisen.www.code.yjf.basic.service.dto.UserlinkuserDTO;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Userlinkuser}.
 */
@RestController
@RequestMapping("/api")
public class UserlinkuserResource {

    private final Logger log = LoggerFactory.getLogger(UserlinkuserResource.class);

    private static final String ENTITY_NAME = "basicUserlinkuser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserlinkuserService userlinkuserService;

    public UserlinkuserResource(UserlinkuserService userlinkuserService) {
        this.userlinkuserService = userlinkuserService;
    }

    /**
     * {@code POST  /userlinkusers} : Create a new userlinkuser.
     *
     * @param userlinkuserDTO the userlinkuserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userlinkuserDTO, or with status {@code 400 (Bad Request)} if the userlinkuser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/userlinkusers")
    public ResponseEntity<UserlinkuserDTO> createUserlinkuser(@RequestBody UserlinkuserDTO userlinkuserDTO) throws URISyntaxException {
        log.debug("REST request to save Userlinkuser : {}", userlinkuserDTO);
        if (userlinkuserDTO.getId() != null) {
            throw new BadRequestAlertException("A new userlinkuser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserlinkuserDTO result = userlinkuserService.save(userlinkuserDTO);
        return ResponseEntity.created(new URI("/api/userlinkusers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /userlinkusers} : Updates an existing userlinkuser.
     *
     * @param userlinkuserDTO the userlinkuserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userlinkuserDTO,
     * or with status {@code 400 (Bad Request)} if the userlinkuserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userlinkuserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/userlinkusers")
    public ResponseEntity<UserlinkuserDTO> updateUserlinkuser(@RequestBody UserlinkuserDTO userlinkuserDTO) throws URISyntaxException {
        log.debug("REST request to update Userlinkuser : {}", userlinkuserDTO);
        if (userlinkuserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserlinkuserDTO result = userlinkuserService.save(userlinkuserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userlinkuserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /userlinkusers} : get all the userlinkusers.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userlinkusers in body.
     */
    @GetMapping("/userlinkusers")
    public ResponseEntity<List<UserlinkuserDTO>> getAllUserlinkusers(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Userlinkusers");
        Page<UserlinkuserDTO> page = userlinkuserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /userlinkusers/:id} : get the "id" userlinkuser.
     *
     * @param id the id of the userlinkuserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userlinkuserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/userlinkusers/{id}")
    public ResponseEntity<UserlinkuserDTO> getUserlinkuser(@PathVariable Long id) {
        log.debug("REST request to get Userlinkuser : {}", id);
        Optional<UserlinkuserDTO> userlinkuserDTO = userlinkuserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userlinkuserDTO);
    }

    /**
     * {@code DELETE  /userlinkusers/:id} : delete the "id" userlinkuser.
     *
     * @param id the id of the userlinkuserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/userlinkusers/{id}")
    public ResponseEntity<Void> deleteUserlinkuser(@PathVariable Long id) {
        log.debug("REST request to delete Userlinkuser : {}", id);
        userlinkuserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
