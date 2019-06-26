package com.weisen.www.code.yjf.basic.web.rest;
import com.weisen.www.code.yjf.basic.service.UserorderService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.web.rest.util.HeaderUtil;
import com.weisen.www.code.yjf.basic.web.rest.util.PaginationUtil;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Userorder.
 */
@RestController
@RequestMapping("/api")
public class UserorderResource {

    private final Logger log = LoggerFactory.getLogger(UserorderResource.class);

    private static final String ENTITY_NAME = "basicUserorder";

    private final UserorderService userorderService;

    public UserorderResource(UserorderService userorderService) {
        this.userorderService = userorderService;
    }

    /**
     * POST  /userorders : Create a new userorder.
     *
     * @param userorderDTO the userorderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userorderDTO, or with status 400 (Bad Request) if the userorder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/userorders")
    public ResponseEntity<UserorderDTO> createUserorder(@RequestBody UserorderDTO userorderDTO) throws URISyntaxException {
        log.debug("REST request to save Userorder : {}", userorderDTO);
        if (userorderDTO.getId() != null) {
            throw new BadRequestAlertException("A new userorder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserorderDTO result = userorderService.save(userorderDTO);
        return ResponseEntity.created(new URI("/api/userorders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /userorders : Updates an existing userorder.
     *
     * @param userorderDTO the userorderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userorderDTO,
     * or with status 400 (Bad Request) if the userorderDTO is not valid,
     * or with status 500 (Internal Server Error) if the userorderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/userorders")
    public ResponseEntity<UserorderDTO> updateUserorder(@RequestBody UserorderDTO userorderDTO) throws URISyntaxException {
        log.debug("REST request to update Userorder : {}", userorderDTO);
        if (userorderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserorderDTO result = userorderService.save(userorderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userorderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /userorders : get all the userorders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userorders in body
     */
    @GetMapping("/userorders")
    public ResponseEntity<List<UserorderDTO>> getAllUserorders(Pageable pageable) {
        log.debug("REST request to get a page of Userorders");
        Page<UserorderDTO> page = userorderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/userorders");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /userorders/:id : get the "id" userorder.
     *
     * @param id the id of the userorderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userorderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/userorders/{id}")
    public ResponseEntity<UserorderDTO> getUserorder(@PathVariable Long id) {
        log.debug("REST request to get Userorder : {}", id);
        Optional<UserorderDTO> userorderDTO = userorderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userorderDTO);
    }

    /**
     * DELETE  /userorders/:id : delete the "id" userorder.
     *
     * @param id the id of the userorderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/userorders/{id}")
    public ResponseEntity<Void> deleteUserorder(@PathVariable Long id) {
        log.debug("REST request to delete Userorder : {}", id);
        userorderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
