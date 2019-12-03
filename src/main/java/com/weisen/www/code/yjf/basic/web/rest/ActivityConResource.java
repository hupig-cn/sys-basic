package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.domain.ActivityCon;
import com.weisen.www.code.yjf.basic.repository.ActivityConRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.ActivityCon}.
 */
@RestController
@RequestMapping("/api")
public class ActivityConResource {

    private final Logger log = LoggerFactory.getLogger(ActivityConResource.class);

    private static final String ENTITY_NAME = "basicActivityCon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActivityConRepository activityConRepository;

    public ActivityConResource(ActivityConRepository activityConRepository) {
        this.activityConRepository = activityConRepository;
    }

    /**
     * {@code POST  /activity-cons} : Create a new activityCon.
     *
     * @param activityCon the activityCon to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activityCon, or with status {@code 400 (Bad Request)} if the activityCon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activity-cons")
    public ResponseEntity<ActivityCon> createActivityCon(@RequestBody ActivityCon activityCon) throws URISyntaxException {
        log.debug("REST request to save ActivityCon : {}", activityCon);
        if (activityCon.getId() != null) {
            throw new BadRequestAlertException("A new activityCon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActivityCon result = activityConRepository.save(activityCon);
        return ResponseEntity.created(new URI("/api/activity-cons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activity-cons} : Updates an existing activityCon.
     *
     * @param activityCon the activityCon to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activityCon,
     * or with status {@code 400 (Bad Request)} if the activityCon is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activityCon couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activity-cons")
    public ResponseEntity<ActivityCon> updateActivityCon(@RequestBody ActivityCon activityCon) throws URISyntaxException {
        log.debug("REST request to update ActivityCon : {}", activityCon);
        if (activityCon.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActivityCon result = activityConRepository.save(activityCon);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activityCon.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /activity-cons} : get all the activityCons.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activityCons in body.
     */
    @GetMapping("/activity-cons")
    public List<ActivityCon> getAllActivityCons() {
        log.debug("REST request to get all ActivityCons");
        return activityConRepository.findAll();
    }

    /**
     * {@code GET  /activity-cons/:id} : get the "id" activityCon.
     *
     * @param id the id of the activityCon to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activityCon, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activity-cons/{id}")
    public ResponseEntity<ActivityCon> getActivityCon(@PathVariable Long id) {
        log.debug("REST request to get ActivityCon : {}", id);
        Optional<ActivityCon> activityCon = activityConRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(activityCon);
    }

    /**
     * {@code DELETE  /activity-cons/:id} : delete the "id" activityCon.
     *
     * @param id the id of the activityCon to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activity-cons/{id}")
    public ResponseEntity<Void> deleteActivityCon(@PathVariable Long id) {
        log.debug("REST request to delete ActivityCon : {}", id);
        activityConRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
