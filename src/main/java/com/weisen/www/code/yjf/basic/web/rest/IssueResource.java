package com.weisen.www.code.yjf.basic.web.rest;
import com.weisen.www.code.yjf.basic.service.IssueService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.web.rest.util.HeaderUtil;
import com.weisen.www.code.yjf.basic.web.rest.util.PaginationUtil;
import com.weisen.www.code.yjf.basic.service.dto.IssueDTO;
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
 * REST controller for managing Issue.
 */
@RestController
@RequestMapping("/api")
public class IssueResource {

    private final Logger log = LoggerFactory.getLogger(IssueResource.class);

    private static final String ENTITY_NAME = "basicIssue";

    private final IssueService issueService;

    public IssueResource(IssueService issueService) {
        this.issueService = issueService;
    }

    /**
     * POST  /issues : Create a new issue.
     *
     * @param issueDTO the issueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new issueDTO, or with status 400 (Bad Request) if the issue has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/issues")
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueDTO issueDTO) throws URISyntaxException {
        log.debug("REST request to save Issue : {}", issueDTO);
        if (issueDTO.getId() != null) {
            throw new BadRequestAlertException("A new issue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IssueDTO result = issueService.save(issueDTO);
        return ResponseEntity.created(new URI("/api/issues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /issues : Updates an existing issue.
     *
     * @param issueDTO the issueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated issueDTO,
     * or with status 400 (Bad Request) if the issueDTO is not valid,
     * or with status 500 (Internal Server Error) if the issueDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/issues")
    public ResponseEntity<IssueDTO> updateIssue(@RequestBody IssueDTO issueDTO) throws URISyntaxException {
        log.debug("REST request to update Issue : {}", issueDTO);
        if (issueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IssueDTO result = issueService.save(issueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, issueDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /issues : get all the issues.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of issues in body
     */
    @GetMapping("/issues")
    public ResponseEntity<List<IssueDTO>> getAllIssues(Pageable pageable) {
        log.debug("REST request to get a page of Issues");
        Page<IssueDTO> page = issueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/issues");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /issues/:id : get the "id" issue.
     *
     * @param id the id of the issueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the issueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/issues/{id}")
    public ResponseEntity<IssueDTO> getIssue(@PathVariable Long id) {
        log.debug("REST request to get Issue : {}", id);
        Optional<IssueDTO> issueDTO = issueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(issueDTO);
    }

    /**
     * DELETE  /issues/:id : delete the "id" issue.
     *
     * @param id the id of the issueDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/issues/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        log.debug("REST request to delete Issue : {}", id);
        issueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
