package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.WithdrawalService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
@RestController
@RequestMapping("/api")
public class WithdrawalResource {

    private final Logger log = LoggerFactory.getLogger(WithdrawalResource.class);

    private static final String ENTITY_NAME = "basicWithdrawal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WithdrawalService withdrawalService;

    public WithdrawalResource(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    /**
     * {@code POST  /withdrawals} : Create a new withdrawal.
     *
     * @param withdrawalDTO the withdrawalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new withdrawalDTO, or with status {@code 400 (Bad Request)} if the withdrawal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/withdrawals")
    public ResponseEntity<WithdrawalDTO> createWithdrawal(@RequestBody WithdrawalDTO withdrawalDTO) throws URISyntaxException {
        log.debug("REST request to save Withdrawal : {}", withdrawalDTO);
        if (withdrawalDTO.getId() != null) {
            throw new BadRequestAlertException("A new withdrawal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WithdrawalDTO result = withdrawalService.save(withdrawalDTO);
        return ResponseEntity.created(new URI("/api/withdrawals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /withdrawals} : Updates an existing withdrawal.
     *
     * @param withdrawalDTO the withdrawalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated withdrawalDTO,
     * or with status {@code 400 (Bad Request)} if the withdrawalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the withdrawalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/withdrawals")
    public ResponseEntity<WithdrawalDTO> updateWithdrawal(@RequestBody WithdrawalDTO withdrawalDTO) throws URISyntaxException {
        log.debug("REST request to update Withdrawal : {}", withdrawalDTO);
        if (withdrawalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WithdrawalDTO result = withdrawalService.save(withdrawalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, withdrawalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /withdrawals} : get all the withdrawals.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of withdrawals in body.
     */
    @GetMapping("/withdrawals")
    public ResponseEntity<List<WithdrawalDTO>> getAllWithdrawals(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Withdrawals");
        Page<WithdrawalDTO> page = withdrawalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /withdrawals/:id} : get the "id" withdrawal.
     *
     * @param id the id of the withdrawalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the withdrawalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/withdrawals/{id}")
    public ResponseEntity<WithdrawalDTO> getWithdrawal(@PathVariable Long id) {
        log.debug("REST request to get Withdrawal : {}", id);
        Optional<WithdrawalDTO> withdrawalDTO = withdrawalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(withdrawalDTO);
    }

    /**
     * {@code DELETE  /withdrawals/:id} : delete the "id" withdrawal.
     *
     * @param id the id of the withdrawalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/withdrawals/{id}")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable Long id) {
        log.debug("REST request to delete Withdrawal : {}", id);
        withdrawalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
