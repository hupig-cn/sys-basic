package com.weisen.www.code.yjf.basic.web.rest;
import com.weisen.www.code.yjf.basic.service.StatisticsService;
import com.weisen.www.code.yjf.basic.service.dto.StatisticsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Statistics.
 */
@RestController
@RequestMapping("/api")
public class StatisticsResource {

    private final Logger log = LoggerFactory.getLogger(StatisticsResource.class);

    private final StatisticsService statisticsService;

    public StatisticsResource(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * POST  /statistics : Create a new statistics.
     *
     * @param statisticsDTO the statisticsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statisticsDTO, or with status 400 (Bad Request) if the statistics has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/statistics")
    public ResponseEntity<StatisticsDTO> createStatistics(@RequestBody StatisticsDTO statisticsDTO) throws URISyntaxException {
        log.debug("REST request to save Statistics : {}", statisticsDTO);
//        if (statisticsDTO.getId() != null) {
//            throw new BadRequestAlertException("A new statistics cannot already have an ID", ENTITY_NAME, "idexists");
//        }
        StatisticsDTO result = statisticsService.save(statisticsDTO);
        return ResponseEntity.created(new URI("/api/statistics/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /statistics : Updates an existing statistics.
     *
     * @param statisticsDTO the statisticsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statisticsDTO,
     * or with status 400 (Bad Request) if the statisticsDTO is not valid,
     * or with status 500 (Internal Server Error) if the statisticsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/statistics")
    public ResponseEntity<StatisticsDTO> updateStatistics(@RequestBody StatisticsDTO statisticsDTO) throws URISyntaxException {
        log.debug("REST request to update Statistics : {}", statisticsDTO);
//        if (statisticsDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
        StatisticsDTO result = statisticsService.save(statisticsDTO);
        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statisticsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /statistics : get all the statistics.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of statistics in body
     */
    @GetMapping("/statistics")
    public List<StatisticsDTO> getAllStatistics() {
        log.debug("REST request to get all Statistics");
        return statisticsService.findAll();
    }

    /**
     * GET  /statistics/:id : get the "id" statistics.
     *
     * @param id the id of the statisticsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statisticsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/statistics/{id}")
    public ResponseEntity<StatisticsDTO> getStatistics(@PathVariable Long id) {
        log.debug("REST request to get Statistics : {}", id);
        Optional<StatisticsDTO> statisticsDTO = statisticsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statisticsDTO);
    }

    /**
     * DELETE  /statistics/:id : delete the "id" statistics.
     *
     * @param id the id of the statisticsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/statistics/{id}")
    public ResponseEntity<Void> deleteStatistics(@PathVariable Long id) {
        log.debug("REST request to delete Statistics : {}", id);
        statisticsService.delete(id);
        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
            .build();
    }
}
