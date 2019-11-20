package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.domain.Boss;
import com.weisen.www.code.yjf.basic.repository.BossRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Boss}.
 */
@RestController
@RequestMapping("/api")
public class BossResource {

    private final Logger log = LoggerFactory.getLogger(BossResource.class);

    private static final String ENTITY_NAME = "basicBoss";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BossRepository bossRepository;

    public BossResource(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    /**
     * {@code POST  /bosses} : Create a new boss.
     *
     * @param boss the boss to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new boss, or with status {@code 400 (Bad Request)} if the boss has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bosses")
    public ResponseEntity<Boss> createBoss(@RequestBody Boss boss) throws URISyntaxException {
        log.debug("REST request to save Boss : {}", boss);
        if (boss.getId() != null) {
            throw new BadRequestAlertException("A new boss cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Boss result = bossRepository.save(boss);
        return ResponseEntity.created(new URI("/api/bosses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bosses} : Updates an existing boss.
     *
     * @param boss the boss to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boss,
     * or with status {@code 400 (Bad Request)} if the boss is not valid,
     * or with status {@code 500 (Internal Server Error)} if the boss couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bosses")
    public ResponseEntity<Boss> updateBoss(@RequestBody Boss boss) throws URISyntaxException {
        log.debug("REST request to update Boss : {}", boss);
        if (boss.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Boss result = bossRepository.save(boss);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, boss.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bosses} : get all the bosses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bosses in body.
     */
    @GetMapping("/bosses")
    public List<Boss> getAllBosses() {
        log.debug("REST request to get all Bosses");
        return bossRepository.findAll();
    }

    /**
     * {@code GET  /bosses/:id} : get the "id" boss.
     *
     * @param id the id of the boss to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the boss, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bosses/{id}")
    public ResponseEntity<Boss> getBoss(@PathVariable Long id) {
        log.debug("REST request to get Boss : {}", id);
        Optional<Boss> boss = bossRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(boss);
    }

    /**
     * {@code DELETE  /bosses/:id} : delete the "id" boss.
     *
     * @param id the id of the boss to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bosses/{id}")
    public ResponseEntity<Void> deleteBoss(@PathVariable Long id) {
        log.debug("REST request to delete Boss : {}", id);
        bossRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
