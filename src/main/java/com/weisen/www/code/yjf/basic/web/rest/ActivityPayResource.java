package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.domain.ActivityPay;
import com.weisen.www.code.yjf.basic.repository.ActivityPayRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.ActivityPay}.
 */
@RestController
@RequestMapping("/api")
public class ActivityPayResource {

    private final Logger log = LoggerFactory.getLogger(ActivityPayResource.class);

    private static final String ENTITY_NAME = "basicActivityPay";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActivityPayRepository activityPayRepository;

    public ActivityPayResource(ActivityPayRepository activityPayRepository) {
        this.activityPayRepository = activityPayRepository;
    }

    /**
     * {@code POST  /activity-pays} : Create a new activityPay.
     *
     * @param activityPay the activityPay to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activityPay, or with status {@code 400 (Bad Request)} if the activityPay has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activity-pays")
    public ResponseEntity<ActivityPay> createActivityPay(@RequestBody ActivityPay activityPay) throws URISyntaxException {
        log.debug("REST request to save ActivityPay : {}", activityPay);
        if (activityPay.getId() != null) {
            throw new BadRequestAlertException("A new activityPay cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActivityPay result = activityPayRepository.save(activityPay);
        return ResponseEntity.created(new URI("/api/activity-pays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activity-pays} : Updates an existing activityPay.
     *
     * @param activityPay the activityPay to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activityPay,
     * or with status {@code 400 (Bad Request)} if the activityPay is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activityPay couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activity-pays")
    public ResponseEntity<ActivityPay> updateActivityPay(@RequestBody ActivityPay activityPay) throws URISyntaxException {
        log.debug("REST request to update ActivityPay : {}", activityPay);
        if (activityPay.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActivityPay result = activityPayRepository.save(activityPay);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, activityPay.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /activity-pays} : get all the activityPays.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activityPays in body.
     */
    @GetMapping("/activity-pays")
    public List<ActivityPay> getAllActivityPays() {
        log.debug("REST request to get all ActivityPays");
        return activityPayRepository.findAll();
    }

    /**
     * {@code GET  /activity-pays/:id} : get the "id" activityPay.
     *
     * @param id the id of the activityPay to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activityPay, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activity-pays/{id}")
    public ResponseEntity<ActivityPay> getActivityPay(@PathVariable Long id) {
        log.debug("REST request to get ActivityPay : {}", id);
        Optional<ActivityPay> activityPay = activityPayRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(activityPay);
    }

    /**
     * {@code DELETE  /activity-pays/:id} : delete the "id" activityPay.
     *
     * @param id the id of the activityPay to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activity-pays/{id}")
    public ResponseEntity<Void> deleteActivityPay(@PathVariable Long id) {
        log.debug("REST request to delete ActivityPay : {}", id);
        activityPayRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
