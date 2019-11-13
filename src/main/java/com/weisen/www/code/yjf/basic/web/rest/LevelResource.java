package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.domain.Level;
import com.weisen.www.code.yjf.basic.repository.LevelRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Level}.
 */
@RestController
@RequestMapping("/api")
public class LevelResource {

    private final Logger log = LoggerFactory.getLogger(LevelResource.class);

    private static final String ENTITY_NAME = "basicLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LevelRepository levelRepository;

    public LevelResource(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    /**
     * {@code POST  /levels} : Create a new level.
     *
     * @param level the level to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new level, or with status {@code 400 (Bad Request)} if the level has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/levels")
    public ResponseEntity<Level> createLevel(@RequestBody Level level) throws URISyntaxException {
        log.debug("REST request to save Level : {}", level);
        if (level.getId() != null) {
            throw new BadRequestAlertException("A new level cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Level result = levelRepository.save(level);
        return ResponseEntity.created(new URI("/api/levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /levels} : Updates an existing level.
     *
     * @param level the level to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated level,
     * or with status {@code 400 (Bad Request)} if the level is not valid,
     * or with status {@code 500 (Internal Server Error)} if the level couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/levels")
    public ResponseEntity<Level> updateLevel(@RequestBody Level level) throws URISyntaxException {
        log.debug("REST request to update Level : {}", level);
        if (level.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Level result = levelRepository.save(level);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, level.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /levels} : get all the levels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of levels in body.
     */
    @GetMapping("/levels")
    public List<Level> getAllLevels() {
        log.debug("REST request to get all Levels");
        return levelRepository.findAll();
    }

    /**
     * {@code GET  /levels/:id} : get the "id" level.
     *
     * @param id the id of the level to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the level, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/levels/{id}")
    public ResponseEntity<Level> getLevel(@PathVariable Long id) {
        log.debug("REST request to get Level : {}", id);
        Optional<Level> level = levelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(level);
    }

    /**
     * {@code DELETE  /levels/:id} : delete the "id" level.
     *
     * @param id the id of the level to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/levels/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        log.debug("REST request to delete Level : {}", id);
        levelRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
