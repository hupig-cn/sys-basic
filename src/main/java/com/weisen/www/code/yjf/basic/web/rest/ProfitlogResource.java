package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.ProfitlogService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.ProfitlogDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Profitlog}.
 */
@RestController
@RequestMapping("/api")
public class ProfitlogResource {

    private final Logger log = LoggerFactory.getLogger(ProfitlogResource.class);

    private static final String ENTITY_NAME = "basicProfitlog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfitlogService profitlogService;

    public ProfitlogResource(ProfitlogService profitlogService) {
        this.profitlogService = profitlogService;
    }

    /**
     * {@code POST  /profitlogs} : Create a new profitlog.
     *
     * @param profitlogDTO the profitlogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profitlogDTO, or with status {@code 400 (Bad Request)} if the profitlog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profitlogs")
    public ResponseEntity<ProfitlogDTO> createProfitlog(@RequestBody ProfitlogDTO profitlogDTO) throws URISyntaxException {
        log.debug("REST request to save Profitlog : {}", profitlogDTO);
        if (profitlogDTO.getId() != null) {
            throw new BadRequestAlertException("A new profitlog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfitlogDTO result = profitlogService.save(profitlogDTO);
        return ResponseEntity.created(new URI("/api/profitlogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /profitlogs} : Updates an existing profitlog.
     *
     * @param profitlogDTO the profitlogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profitlogDTO,
     * or with status {@code 400 (Bad Request)} if the profitlogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profitlogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/profitlogs")
    public ResponseEntity<ProfitlogDTO> updateProfitlog(@RequestBody ProfitlogDTO profitlogDTO) throws URISyntaxException {
        log.debug("REST request to update Profitlog : {}", profitlogDTO);
        if (profitlogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfitlogDTO result = profitlogService.save(profitlogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profitlogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /profitlogs} : get all the profitlogs.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profitlogs in body.
     */
    @GetMapping("/profitlogs")
    public ResponseEntity<List<ProfitlogDTO>> getAllProfitlogs(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Profitlogs");
        Page<ProfitlogDTO> page = profitlogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /profitlogs/:id} : get the "id" profitlog.
     *
     * @param id the id of the profitlogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profitlogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profitlogs/{id}")
    public ResponseEntity<ProfitlogDTO> getProfitlog(@PathVariable Long id) {
        log.debug("REST request to get Profitlog : {}", id);
        Optional<ProfitlogDTO> profitlogDTO = profitlogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profitlogDTO);
    }

    /**
     * {@code DELETE  /profitlogs/:id} : delete the "id" profitlog.
     *
     * @param id the id of the profitlogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profitlogs/{id}")
    public ResponseEntity<Void> deleteProfitlog(@PathVariable Long id) {
        log.debug("REST request to delete Profitlog : {}", id);
        profitlogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
