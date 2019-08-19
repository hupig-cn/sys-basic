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

import com.weisen.www.code.yjf.basic.service.WithdrawaldetailsService;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawaldetailsDTO;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Withdrawaldetails}.
 */
@RestController
@RequestMapping("/api")
public class WithdrawaldetailsResource {

    private final Logger log = LoggerFactory.getLogger(WithdrawaldetailsResource.class);

    private static final String ENTITY_NAME = "basicWithdrawaldetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WithdrawaldetailsService withdrawaldetailsService;

    public WithdrawaldetailsResource(WithdrawaldetailsService withdrawaldetailsService) {
        this.withdrawaldetailsService = withdrawaldetailsService;
    }

    /**
     * {@code POST  /withdrawaldetails} : Create a new withdrawaldetails.
     *
     * @param withdrawaldetailsDTO the withdrawaldetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new withdrawaldetailsDTO, or with status {@code 400 (Bad Request)} if the withdrawaldetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/withdrawaldetails")
    public ResponseEntity<WithdrawaldetailsDTO> createWithdrawaldetails(@RequestBody WithdrawaldetailsDTO withdrawaldetailsDTO) throws URISyntaxException {
        log.debug("REST request to save Withdrawaldetails : {}", withdrawaldetailsDTO);
        if (withdrawaldetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new withdrawaldetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WithdrawaldetailsDTO result = withdrawaldetailsService.save(withdrawaldetailsDTO);
        return ResponseEntity.created(new URI("/api/withdrawaldetails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /withdrawaldetails} : Updates an existing withdrawaldetails.
     *
     * @param withdrawaldetailsDTO the withdrawaldetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated withdrawaldetailsDTO,
     * or with status {@code 400 (Bad Request)} if the withdrawaldetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the withdrawaldetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/withdrawaldetails")
    public ResponseEntity<WithdrawaldetailsDTO> updateWithdrawaldetails(@RequestBody WithdrawaldetailsDTO withdrawaldetailsDTO) throws URISyntaxException {
        log.debug("REST request to update Withdrawaldetails : {}", withdrawaldetailsDTO);
        if (withdrawaldetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WithdrawaldetailsDTO result = withdrawaldetailsService.save(withdrawaldetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, withdrawaldetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /withdrawaldetails} : get all the withdrawaldetails.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of withdrawaldetails in body.
     */
    @GetMapping("/withdrawaldetails")
    public ResponseEntity<List<WithdrawaldetailsDTO>> getAllWithdrawaldetails(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Withdrawaldetails");
        Page<WithdrawaldetailsDTO> page = withdrawaldetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /withdrawaldetails/:id} : get the "id" withdrawaldetails.
     *
     * @param id the id of the withdrawaldetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the withdrawaldetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/withdrawaldetails/{id}")
    public ResponseEntity<WithdrawaldetailsDTO> getWithdrawaldetails(@PathVariable Long id) {
        log.debug("REST request to get Withdrawaldetails : {}", id);
        Optional<WithdrawaldetailsDTO> withdrawaldetailsDTO = withdrawaldetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(withdrawaldetailsDTO);
    }

    /**
     * {@code DELETE  /withdrawaldetails/:id} : delete the "id" withdrawaldetails.
     *
     * @param id the id of the withdrawaldetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/withdrawaldetails/{id}")
    public ResponseEntity<Void> deleteWithdrawaldetails(@PathVariable Long id) {
        log.debug("REST request to delete Withdrawaldetails : {}", id);
        withdrawaldetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
