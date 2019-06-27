package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.CoderecordService;
import com.weisen.www.code.yjf.basic.service.dto.CoderecordDTO;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Coderecord.
 */
@RestController
@RequestMapping("/api")
public class CoderecordResource {

    private final Logger log = LoggerFactory.getLogger(CoderecordResource.class);

    private static final String ENTITY_NAME = "basicCoderecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoderecordService coderecordService;

    public CoderecordResource(CoderecordService coderecordService) {
        this.coderecordService = coderecordService;
    }

    /**
     * {@code POST  /coderecords} : Create a new coderecord.
     *
     * @param coderecordDTO the coderecordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coderecordDTO, or with status {@code 400 (Bad Request)} if the coderecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/coderecords")
    public ResponseEntity<CoderecordDTO> createCoderecord(@RequestBody CoderecordDTO coderecordDTO) throws URISyntaxException {
        log.debug("REST request to save Coderecord : {}", coderecordDTO);
        if (coderecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new coderecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoderecordDTO result = coderecordService.save(coderecordDTO);
        return ResponseEntity.created(new URI("/api/coderecords/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /coderecords} : Updates an existing coderecord.
     *
     * @param coderecordDTO the coderecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coderecordDTO,
     * or with status {@code 400 (Bad Request)} if the coderecordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coderecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/coderecords")
    public ResponseEntity<CoderecordDTO> updateCoderecord(@RequestBody CoderecordDTO coderecordDTO) throws URISyntaxException {
        log.debug("REST request to update Coderecord : {}", coderecordDTO);
        if (coderecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoderecordDTO result = coderecordService.save(coderecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coderecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /coderecords} : get all the coderecords.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coderecords in body.
     */
    @GetMapping("/coderecords")
    public ResponseEntity<List<CoderecordDTO>> getAllCoderecords(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Coderecords");
        Page<CoderecordDTO> page = coderecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /coderecords/:id} : get the "id" coderecord.
     *
     * @param id the id of the coderecordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coderecordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/coderecords/{id}")
    public ResponseEntity<CoderecordDTO> getCoderecord(@PathVariable Long id) {
        log.debug("REST request to get Coderecord : {}", id);
        Optional<CoderecordDTO> coderecordDTO = coderecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coderecordDTO);
    }

    /**
     * {@code DELETE  /coderecords/:id} : delete the "id" coderecord.
     *
     * @param id the id of the coderecordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/coderecords/{id}")
    public ResponseEntity<Void> deleteCoderecord(@PathVariable Long id) {
        log.debug("REST request to delete Coderecord : {}", id);
        coderecordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
