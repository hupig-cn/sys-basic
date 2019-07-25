package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.ReceivingService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.ReceivingDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Receiving}.
 */
@RestController
@RequestMapping("/api")
public class ReceivingResource {

    private final Logger log = LoggerFactory.getLogger(ReceivingResource.class);

    private static final String ENTITY_NAME = "basicReceiving";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceivingService receivingService;

    public ReceivingResource(ReceivingService receivingService) {
        this.receivingService = receivingService;
    }

    /**
     * {@code POST  /receivings} : Create a new receiving.
     *
     * @param receivingDTO the receivingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receivingDTO, or with status {@code 400 (Bad Request)} if the receiving has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receivings")
    public ResponseEntity<ReceivingDTO> createReceiving(@RequestBody ReceivingDTO receivingDTO) throws URISyntaxException {
        log.debug("REST request to save Receiving : {}", receivingDTO);
        if (receivingDTO.getId() != null) {
            throw new BadRequestAlertException("A new receiving cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceivingDTO result = receivingService.save(receivingDTO);
        return ResponseEntity.created(new URI("/api/receivings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /receivings} : Updates an existing receiving.
     *
     * @param receivingDTO the receivingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receivingDTO,
     * or with status {@code 400 (Bad Request)} if the receivingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the receivingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/receivings")
    public ResponseEntity<ReceivingDTO> updateReceiving(@RequestBody ReceivingDTO receivingDTO) throws URISyntaxException {
        log.debug("REST request to update Receiving : {}", receivingDTO);
        if (receivingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReceivingDTO result = receivingService.save(receivingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, receivingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /receivings} : get all the receivings.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receivings in body.
     */
    @GetMapping("/receivings")
    public ResponseEntity<List<ReceivingDTO>> getAllReceivings(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Receivings");
        Page<ReceivingDTO> page = receivingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /receivings/:id} : get the "id" receiving.
     *
     * @param id the id of the receivingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receivingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receivings/{id}")
    public ResponseEntity<ReceivingDTO> getReceiving(@PathVariable Long id) {
        log.debug("REST request to get Receiving : {}", id);
        Optional<ReceivingDTO> receivingDTO = receivingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(receivingDTO);
    }

    /**
     * {@code DELETE  /receivings/:id} : delete the "id" receiving.
     *
     * @param id the id of the receivingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/receivings/{id}")
    public ResponseEntity<Void> deleteReceiving(@PathVariable Long id) {
        log.debug("REST request to delete Receiving : {}", id);
        receivingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
