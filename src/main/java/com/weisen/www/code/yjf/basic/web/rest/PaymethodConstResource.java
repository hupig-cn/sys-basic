package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.domain.PaymethodConst;
import com.weisen.www.code.yjf.basic.repository.PaymethodConstRepository;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.PaymethodConst}.
 */
@RestController
@RequestMapping("/api")
public class PaymethodConstResource {

    private final Logger log = LoggerFactory.getLogger(PaymethodConstResource.class);

    private static final String ENTITY_NAME = "basicPaymethodConst";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymethodConstRepository paymethodConstRepository;

    public PaymethodConstResource(PaymethodConstRepository paymethodConstRepository) {
        this.paymethodConstRepository = paymethodConstRepository;
    }

    /**
     * {@code POST  /paymethod-consts} : Create a new paymethodConst.
     *
     * @param paymethodConst the paymethodConst to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymethodConst, or with status {@code 400 (Bad Request)} if the paymethodConst has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paymethod-consts")
    public ResponseEntity<PaymethodConst> createPaymethodConst(@RequestBody PaymethodConst paymethodConst) throws URISyntaxException {
        log.debug("REST request to save PaymethodConst : {}", paymethodConst);
        if (paymethodConst.getId() != null) {
            throw new BadRequestAlertException("A new paymethodConst cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymethodConst result = paymethodConstRepository.save(paymethodConst);
        return ResponseEntity.created(new URI("/api/paymethod-consts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paymethod-consts} : Updates an existing paymethodConst.
     *
     * @param paymethodConst the paymethodConst to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymethodConst,
     * or with status {@code 400 (Bad Request)} if the paymethodConst is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymethodConst couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paymethod-consts")
    public ResponseEntity<PaymethodConst> updatePaymethodConst(@RequestBody PaymethodConst paymethodConst) throws URISyntaxException {
        log.debug("REST request to update PaymethodConst : {}", paymethodConst);
        if (paymethodConst.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymethodConst result = paymethodConstRepository.save(paymethodConst);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymethodConst.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paymethod-consts} : get all the paymethodConsts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymethodConsts in body.
     */
    @GetMapping("/paymethod-consts")
    public List<PaymethodConst> getAllPaymethodConsts() {
        log.debug("REST request to get all PaymethodConsts");
        return paymethodConstRepository.findAll();
    }

    /**
     * {@code GET  /paymethod-consts/:id} : get the "id" paymethodConst.
     *
     * @param id the id of the paymethodConst to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymethodConst, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paymethod-consts/{id}")
    public ResponseEntity<PaymethodConst> getPaymethodConst(@PathVariable Long id) {
        log.debug("REST request to get PaymethodConst : {}", id);
        Optional<PaymethodConst> paymethodConst = paymethodConstRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paymethodConst);
    }

    /**
     * {@code DELETE  /paymethod-consts/:id} : delete the "id" paymethodConst.
     *
     * @param id the id of the paymethodConst to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paymethod-consts/{id}")
    public ResponseEntity<Void> deletePaymethodConst(@PathVariable Long id) {
        log.debug("REST request to delete PaymethodConst : {}", id);
        paymethodConstRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
