package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.domain.ActivitySer;
import com.weisen.www.code.yjf.basic.repository.ActivitySerRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.ActivitySer}.
 */
@RestController
@RequestMapping("/api")
public class ActivitySerResource {

    private final Logger log = LoggerFactory.getLogger(ActivitySerResource.class);

    private static final String ENTITY_NAME = "basicActivitySer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActivitySerRepository activitySerRepository;

    public ActivitySerResource(ActivitySerRepository activitySerRepository) {
        this.activitySerRepository = activitySerRepository;
    }

    /**
     * {@code POST  /activity-sers} : Create a new activitySer.
     *
     * @param activitySer the activitySer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activitySer, or with status {@code 400 (Bad Request)} if the activitySer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activity-sers")
    public ResponseEntity<ActivitySer> createActivitySer(@RequestBody ActivitySer activitySer) throws URISyntaxException {
        log.debug("REST request to save ActivitySer : {}", activitySer);
        if (activitySer.getId() != null) {
            throw new BadRequestAlertException("A new activitySer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActivitySer result = activitySerRepository.save(activitySer);
        return ResponseEntity.created(new URI("/api/activity-sers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activity-sers} : Updates an existing activitySer.
     *
     * @param activitySer the activitySer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activitySer,
     * or with status {@code 400 (Bad Request)} if the activitySer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activitySer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activity-sers")
    public ResponseEntity<ActivitySer> updateActivitySer(@RequestBody ActivitySer activitySer) throws URISyntaxException {
        log.debug("REST request to update ActivitySer : {}", activitySer);
        if (activitySer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActivitySer result = activitySerRepository.save(activitySer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activitySer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /activity-sers} : get all the activitySers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activitySers in body.
     */
    @GetMapping("/activity-sers")
    public List<ActivitySer> getAllActivitySers() {
        log.debug("REST request to get all ActivitySers");
        return activitySerRepository.findAll();
    }

    /**
     * {@code GET  /activity-sers/:id} : get the "id" activitySer.
     *
     * @param id the id of the activitySer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activitySer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activity-sers/{id}")
    public ResponseEntity<ActivitySer> getActivitySer(@PathVariable Long id) {
        log.debug("REST request to get ActivitySer : {}", id);
        Optional<ActivitySer> activitySer = activitySerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(activitySer);
    }

    /**
     * {@code DELETE  /activity-sers/:id} : delete the "id" activitySer.
     *
     * @param id the id of the activitySer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activity-sers/{id}")
    public ResponseEntity<Void> deleteActivitySer(@PathVariable Long id) {
        log.debug("REST request to delete ActivitySer : {}", id);
        activitySerRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
