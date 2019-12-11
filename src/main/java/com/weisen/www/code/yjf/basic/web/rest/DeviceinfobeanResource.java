package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.domain.Deviceinfobean;
import com.weisen.www.code.yjf.basic.repository.DeviceinfobeanRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Deviceinfobean}.
 */
@RestController
@RequestMapping("/api")
public class DeviceinfobeanResource {

    private final Logger log = LoggerFactory.getLogger(DeviceinfobeanResource.class);

    private static final String ENTITY_NAME = "basicDeviceinfobean";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeviceinfobeanRepository deviceinfobeanRepository;

    public DeviceinfobeanResource(DeviceinfobeanRepository deviceinfobeanRepository) {
        this.deviceinfobeanRepository = deviceinfobeanRepository;
    }

    /**
     * {@code POST  /deviceinfobeans} : Create a new deviceinfobean.
     *
     * @param deviceinfobean the deviceinfobean to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deviceinfobean, or with status {@code 400 (Bad Request)} if the deviceinfobean has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deviceinfobeans")
    public ResponseEntity<Deviceinfobean> createDeviceinfobean(@RequestBody Deviceinfobean deviceinfobean) throws URISyntaxException {
        log.debug("REST request to save Deviceinfobean : {}", deviceinfobean);
        if (deviceinfobean.getId() != null) {
            throw new BadRequestAlertException("A new deviceinfobean cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Deviceinfobean result = deviceinfobeanRepository.save(deviceinfobean);
        return ResponseEntity.created(new URI("/api/deviceinfobeans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deviceinfobeans} : Updates an existing deviceinfobean.
     *
     * @param deviceinfobean the deviceinfobean to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviceinfobean,
     * or with status {@code 400 (Bad Request)} if the deviceinfobean is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deviceinfobean couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deviceinfobeans")
    public ResponseEntity<Deviceinfobean> updateDeviceinfobean(@RequestBody Deviceinfobean deviceinfobean) throws URISyntaxException {
        log.debug("REST request to update Deviceinfobean : {}", deviceinfobean);
        if (deviceinfobean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Deviceinfobean result = deviceinfobeanRepository.save(deviceinfobean);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deviceinfobean.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deviceinfobeans} : get all the deviceinfobeans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deviceinfobeans in body.
     */
    @GetMapping("/deviceinfobeans")
    public List<Deviceinfobean> getAllDeviceinfobeans() {
        log.debug("REST request to get all Deviceinfobeans");
        return deviceinfobeanRepository.findAll();
    }

    /**
     * {@code GET  /deviceinfobeans/:id} : get the "id" deviceinfobean.
     *
     * @param id the id of the deviceinfobean to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deviceinfobean, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deviceinfobeans/{id}")
    public ResponseEntity<Deviceinfobean> getDeviceinfobean(@PathVariable Long id) {
        log.debug("REST request to get Deviceinfobean : {}", id);
        Optional<Deviceinfobean> deviceinfobean = deviceinfobeanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(deviceinfobean);
    }

    /**
     * {@code DELETE  /deviceinfobeans/:id} : delete the "id" deviceinfobean.
     *
     * @param id the id of the deviceinfobean to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deviceinfobeans/{id}")
    public ResponseEntity<Void> deleteDeviceinfobean(@PathVariable Long id) {
        log.debug("REST request to delete Deviceinfobean : {}", id);
        deviceinfobeanRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
