package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.CouponService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.CouponDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Coupon}.
 */
@RestController
@RequestMapping("/api")
public class CouponResource {

    private final Logger log = LoggerFactory.getLogger(CouponResource.class);

    private static final String ENTITY_NAME = "basicCoupon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CouponService couponService;

    public CouponResource(CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * {@code POST  /coupons} : Create a new coupon.
     *
     * @param couponDTO the couponDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new couponDTO, or with status {@code 400 (Bad Request)} if the coupon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/coupons")
    public ResponseEntity<CouponDTO> createCoupon(@RequestBody CouponDTO couponDTO) throws URISyntaxException {
        log.debug("REST request to save Coupon : {}", couponDTO);
        if (couponDTO.getId() != null) {
            throw new BadRequestAlertException("A new coupon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CouponDTO result = couponService.save(couponDTO);
        return ResponseEntity.created(new URI("/api/coupons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /coupons} : Updates an existing coupon.
     *
     * @param couponDTO the couponDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated couponDTO,
     * or with status {@code 400 (Bad Request)} if the couponDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the couponDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/coupons")
    public ResponseEntity<CouponDTO> updateCoupon(@RequestBody CouponDTO couponDTO) throws URISyntaxException {
        log.debug("REST request to update Coupon : {}", couponDTO);
        if (couponDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CouponDTO result = couponService.save(couponDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, couponDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /coupons} : get all the coupons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coupons in body.
     */
    @GetMapping("/coupons")
    public ResponseEntity<List<CouponDTO>> getAllCoupons(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Coupons");
        Page<CouponDTO> page = couponService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /coupons/:id} : get the "id" coupon.
     *
     * @param id the id of the couponDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the couponDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/coupons/{id}")
    public ResponseEntity<CouponDTO> getCoupon(@PathVariable Long id) {
        log.debug("REST request to get Coupon : {}", id);
        Optional<CouponDTO> couponDTO = couponService.findOne(id);
        return ResponseUtil.wrapOrNotFound(couponDTO);
    }

    /**
     * {@code DELETE  /coupons/:id} : delete the "id" coupon.
     *
     * @param id the id of the couponDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/coupons/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        log.debug("REST request to delete Coupon : {}", id);
        couponService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
