package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.ReceiptpayService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Receiptpay}.
 */
@RestController
@RequestMapping("/api")
public class ReceiptpayResource {

    private final Logger log = LoggerFactory.getLogger(ReceiptpayResource.class);

    private static final String ENTITY_NAME = "basicReceiptpay";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceiptpayService receiptpayService;

    public ReceiptpayResource(ReceiptpayService receiptpayService) {
        this.receiptpayService = receiptpayService;
    }

    /**
     * {@code POST  /receiptpays} : Create a new receiptpay.
     *
     * @param receiptpayDTO the receiptpayDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receiptpayDTO, or with status {@code 400 (Bad Request)} if the receiptpay has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receiptpays")
    public ResponseEntity<ReceiptpayDTO> createReceiptpay(@RequestBody ReceiptpayDTO receiptpayDTO) throws URISyntaxException {
        log.debug("REST request to save Receiptpay : {}", receiptpayDTO);
        if (receiptpayDTO.getId() != null) {
            throw new BadRequestAlertException("A new receiptpay cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceiptpayDTO result = receiptpayService.save(receiptpayDTO);
        return ResponseEntity.created(new URI("/api/receiptpays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /receiptpays} : Updates an existing receiptpay.
     *
     * @param receiptpayDTO the receiptpayDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receiptpayDTO,
     * or with status {@code 400 (Bad Request)} if the receiptpayDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the receiptpayDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/receiptpays")
    public ResponseEntity<ReceiptpayDTO> updateReceiptpay(@RequestBody ReceiptpayDTO receiptpayDTO) throws URISyntaxException {
        log.debug("REST request to update Receiptpay : {}", receiptpayDTO);
        if (receiptpayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReceiptpayDTO result = receiptpayService.save(receiptpayDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, receiptpayDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /receiptpays} : get all the receiptpays.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receiptpays in body.
     */
    @GetMapping("/receiptpays")
    public ResponseEntity<List<ReceiptpayDTO>> getAllReceiptpays(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Receiptpays");
        Page<ReceiptpayDTO> page = receiptpayService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /receiptpays/:id} : get the "id" receiptpay.
     *
     * @param id the id of the receiptpayDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receiptpayDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receiptpays/{id}")
    public ResponseEntity<ReceiptpayDTO> getReceiptpay(@PathVariable Long id) {
        log.debug("REST request to get Receiptpay : {}", id);
        Optional<ReceiptpayDTO> receiptpayDTO = receiptpayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(receiptpayDTO);
    }

    /**
     * {@code DELETE  /receiptpays/:id} : delete the "id" receiptpay.
     *
     * @param id the id of the receiptpayDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/receiptpays/{id}")
    public ResponseEntity<Void> deleteReceiptpay(@PathVariable Long id) {
        log.debug("REST request to delete Receiptpay : {}", id);
        receiptpayService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
