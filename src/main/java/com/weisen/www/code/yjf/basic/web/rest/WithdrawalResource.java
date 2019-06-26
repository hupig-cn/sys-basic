package com.weisen.www.code.yjf.basic.web.rest;
import com.weisen.www.code.yjf.basic.service.WithdrawalService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.web.rest.util.HeaderUtil;
import com.weisen.www.code.yjf.basic.web.rest.util.PaginationUtil;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Withdrawal.
 */
@RestController
@RequestMapping("/api")
public class WithdrawalResource {

    private final Logger log = LoggerFactory.getLogger(WithdrawalResource.class);

    private static final String ENTITY_NAME = "basicWithdrawal";

    private final WithdrawalService withdrawalService;

    public WithdrawalResource(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    /**
     * POST  /withdrawals : Create a new withdrawal.
     *
     * @param withdrawalDTO the withdrawalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new withdrawalDTO, or with status 400 (Bad Request) if the withdrawal has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/withdrawals")
    public ResponseEntity<WithdrawalDTO> createWithdrawal(@RequestBody WithdrawalDTO withdrawalDTO) throws URISyntaxException {
        log.debug("REST request to save Withdrawal : {}", withdrawalDTO);
        if (withdrawalDTO.getId() != null) {
            throw new BadRequestAlertException("A new withdrawal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WithdrawalDTO result = withdrawalService.save(withdrawalDTO);
        return ResponseEntity.created(new URI("/api/withdrawals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /withdrawals : Updates an existing withdrawal.
     *
     * @param withdrawalDTO the withdrawalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated withdrawalDTO,
     * or with status 400 (Bad Request) if the withdrawalDTO is not valid,
     * or with status 500 (Internal Server Error) if the withdrawalDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/withdrawals")
    public ResponseEntity<WithdrawalDTO> updateWithdrawal(@RequestBody WithdrawalDTO withdrawalDTO) throws URISyntaxException {
        log.debug("REST request to update Withdrawal : {}", withdrawalDTO);
        if (withdrawalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WithdrawalDTO result = withdrawalService.save(withdrawalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, withdrawalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /withdrawals : get all the withdrawals.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of withdrawals in body
     */
    @GetMapping("/withdrawals")
    public ResponseEntity<List<WithdrawalDTO>> getAllWithdrawals(Pageable pageable) {
        log.debug("REST request to get a page of Withdrawals");
        Page<WithdrawalDTO> page = withdrawalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/withdrawals");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /withdrawals/:id : get the "id" withdrawal.
     *
     * @param id the id of the withdrawalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the withdrawalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/withdrawals/{id}")
    public ResponseEntity<WithdrawalDTO> getWithdrawal(@PathVariable Long id) {
        log.debug("REST request to get Withdrawal : {}", id);
        Optional<WithdrawalDTO> withdrawalDTO = withdrawalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(withdrawalDTO);
    }

    /**
     * DELETE  /withdrawals/:id : delete the "id" withdrawal.
     *
     * @param id the id of the withdrawalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/withdrawals/{id}")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable Long id) {
        log.debug("REST request to delete Withdrawal : {}", id);
        withdrawalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
