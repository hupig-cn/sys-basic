package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.UserbankcardService;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

//import com.weisen.www.code.yjf.basic.web.rest.util.HeaderUtil;
//import com.weisen.www.code.yjf.basic.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Userbankcard.
 */
@RestController
@RequestMapping("/api")
public class UserbankcardResource {

    private final Logger log = LoggerFactory.getLogger(UserbankcardResource.class);

    private static final String ENTITY_NAME = "basicUserbankcard";

    private final UserbankcardService userbankcardService;

    public UserbankcardResource(UserbankcardService userbankcardService) {
        this.userbankcardService = userbankcardService;
    }

    /**
     * POST  /userbankcards : Create a new userbankcard.
     *
     * @param userbankcardDTO the userbankcardDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userbankcardDTO, or with status 400 (Bad Request) if the userbankcard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/userbankcards")
    public ResponseEntity<UserbankcardDTO> createUserbankcard(@Valid @RequestBody UserbankcardDTO userbankcardDTO) throws URISyntaxException {
        log.debug("REST request to save Userbankcard : {}", userbankcardDTO);
        if (userbankcardDTO.getId() != null) {
            throw new BadRequestAlertException("A new userbankcard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserbankcardDTO result = userbankcardService.save(userbankcardDTO);
        return ResponseEntity.created(new URI("/api/userbankcards/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /userbankcards : Updates an existing userbankcard.
     *
     * @param userbankcardDTO the userbankcardDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userbankcardDTO,
     * or with status 400 (Bad Request) if the userbankcardDTO is not valid,
     * or with status 500 (Internal Server Error) if the userbankcardDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/userbankcards")
    public ResponseEntity<UserbankcardDTO> updateUserbankcard(@Valid @RequestBody UserbankcardDTO userbankcardDTO) throws URISyntaxException {
        log.debug("REST request to update Userbankcard : {}", userbankcardDTO);
        if (userbankcardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserbankcardDTO result = userbankcardService.save(userbankcardDTO);
        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userbankcardDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /userbankcards : get all the userbankcards.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userbankcards in body
     */
    @GetMapping("/userbankcards")
    public ResponseEntity<List<UserbankcardDTO>> getAllUserbankcards(Pageable pageable) {
        log.debug("REST request to get a page of Userbankcards");
        Page<UserbankcardDTO> page = userbankcardService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/userbankcards");
        return ResponseEntity.ok()
//            .headers(headers)
            .body(page.getContent());
    }

    /**
     * GET  /userbankcards/:id : get the "id" userbankcard.
     *
     * @param id the id of the userbankcardDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userbankcardDTO, or with status 404 (Not Found)
     */
    @GetMapping("/userbankcards/{id}")
    public ResponseEntity<UserbankcardDTO> getUserbankcard(@PathVariable Long id) {
        log.debug("REST request to get Userbankcard : {}", id);
        Optional<UserbankcardDTO> userbankcardDTO = userbankcardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userbankcardDTO);
    }

    /**
     * DELETE  /userbankcards/:id : delete the "id" userbankcard.
     *
     * @param id the id of the userbankcardDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/userbankcards/{id}")
    public ResponseEntity<Void> deleteUserbankcard(@PathVariable Long id) {
        log.debug("REST request to delete Userbankcard : {}", id);
        userbankcardService.delete(id);
        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
            .build();
    }
}
