package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.PercentageService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.PercentageDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Percentage}.
 */
@RestController
@RequestMapping("/api")
public class PercentageResource {

    private final Logger log = LoggerFactory.getLogger(PercentageResource.class);

    private static final String ENTITY_NAME = "basicPercentage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PercentageService percentageService;

    public PercentageResource(PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    /**
     * {@code POST  /percentages} : Create a new percentage.
     *
     * @param percentageDTO the percentageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new percentageDTO, or with status {@code 400 (Bad Request)} if the percentage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/percentages")
    public ResponseEntity<PercentageDTO> createPercentage(@RequestBody PercentageDTO percentageDTO) throws URISyntaxException {
        log.debug("REST request to save Percentage : {}", percentageDTO);
        if (percentageDTO.getId() != null) {
            throw new BadRequestAlertException("A new percentage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PercentageDTO result = percentageService.save(percentageDTO);
        return ResponseEntity.created(new URI("/api/percentages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /percentages} : Updates an existing percentage.
     *
     * @param percentageDTO the percentageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated percentageDTO,
     * or with status {@code 400 (Bad Request)} if the percentageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the percentageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/percentages")
    public ResponseEntity<PercentageDTO> updatePercentage(@RequestBody PercentageDTO percentageDTO) throws URISyntaxException {
        log.debug("REST request to update Percentage : {}", percentageDTO);
        if (percentageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PercentageDTO result = percentageService.save(percentageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, percentageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /percentages} : get all the percentages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of percentages in body.
     */
    @GetMapping("/percentages")
    public ResponseEntity<List<PercentageDTO>> getAllPercentages(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Percentages");
        Page<PercentageDTO> page = percentageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /percentages/:id} : get the "id" percentage.
     *
     * @param id the id of the percentageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the percentageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/percentages/{id}")
    public ResponseEntity<PercentageDTO> getPercentage(@PathVariable Long id) {
        log.debug("REST request to get Percentage : {}", id);
        Optional<PercentageDTO> percentageDTO = percentageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(percentageDTO);
    }

    /**
     * {@code DELETE  /percentages/:id} : delete the "id" percentage.
     *
     * @param id the id of the percentageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/percentages/{id}")
    public ResponseEntity<Void> deletePercentage(@PathVariable Long id) {
        log.debug("REST request to delete Percentage : {}", id);
        percentageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
