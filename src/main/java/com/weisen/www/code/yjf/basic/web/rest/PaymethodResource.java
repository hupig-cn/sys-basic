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

import com.weisen.www.code.yjf.basic.service.PaymethodService;
import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Paymethod}.
 */
@RestController
@RequestMapping("/api")
public class PaymethodResource {

    private final Logger log = LoggerFactory.getLogger(PaymethodResource.class);

    private static final String ENTITY_NAME = "basicPaymethod";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymethodService paymethodService;

    public PaymethodResource(PaymethodService paymethodService) {
        this.paymethodService = paymethodService;
    }

    /**
     * {@code POST  /paymethods} : Create a new paymethod.
     *
     * @param paymethodDTO the paymethodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymethodDTO, or with status {@code 400 (Bad Request)} if the paymethod has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/paymethods")
    public ResponseEntity<PaymethodDTO> createPaymethod(@RequestBody PaymethodDTO paymethodDTO) throws URISyntaxException {
        log.debug("REST request to save Paymethod : {}", paymethodDTO);
        if (paymethodDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymethodDTO result = paymethodService.save(paymethodDTO);
        return ResponseEntity.created(new URI("/api/paymethods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /paymethods} : Updates an existing paymethod.
     *
     * @param paymethodDTO the paymethodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymethodDTO,
     * or with status {@code 400 (Bad Request)} if the paymethodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymethodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/paymethods")
    public ResponseEntity<PaymethodDTO> updatePaymethod(@RequestBody PaymethodDTO paymethodDTO) throws URISyntaxException {
        log.debug("REST request to update Paymethod : {}", paymethodDTO);
        if (paymethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymethodDTO result = paymethodService.save(paymethodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymethodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /paymethods} : get all the paymethods.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymethods in body.
     */
    @GetMapping("/paymethods")
    public ResponseEntity<List<PaymethodDTO>> getAllPaymethods(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Paymethods");
        Page<PaymethodDTO> page = paymethodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /paymethods/:id} : get the "id" paymethod.
     *
     * @param id the id of the paymethodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymethodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/paymethods/{id}")
    public ResponseEntity<PaymethodDTO> getPaymethod(@PathVariable Long id) {
        log.debug("REST request to get Paymethod : {}", id);
        Optional<PaymethodDTO> paymethodDTO = paymethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymethodDTO);
    }

    /**
     * {@code DELETE  /paymethods/:id} : delete the "id" paymethod.
     *
     * @param id the id of the paymethodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/paymethods/{id}")
    public ResponseEntity<Void> deletePaymethod(@PathVariable Long id) {
        log.debug("REST request to delete Paymethod : {}", id);
        paymethodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
