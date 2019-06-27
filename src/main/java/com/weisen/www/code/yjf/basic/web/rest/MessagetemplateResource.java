package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.MessagetemplateService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.MessagetemplateDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Messagetemplate}.
 */
@RestController
@RequestMapping("/api")
public class MessagetemplateResource {

    private final Logger log = LoggerFactory.getLogger(MessagetemplateResource.class);

    private static final String ENTITY_NAME = "basicMessagetemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessagetemplateService messagetemplateService;

    public MessagetemplateResource(MessagetemplateService messagetemplateService) {
        this.messagetemplateService = messagetemplateService;
    }

    /**
     * {@code POST  /messagetemplates} : Create a new messagetemplate.
     *
     * @param messagetemplateDTO the messagetemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messagetemplateDTO, or with status {@code 400 (Bad Request)} if the messagetemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/messagetemplates")
    public ResponseEntity<MessagetemplateDTO> createMessagetemplate(@RequestBody MessagetemplateDTO messagetemplateDTO) throws URISyntaxException {
        log.debug("REST request to save Messagetemplate : {}", messagetemplateDTO);
        if (messagetemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new messagetemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MessagetemplateDTO result = messagetemplateService.save(messagetemplateDTO);
        return ResponseEntity.created(new URI("/api/messagetemplates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /messagetemplates} : Updates an existing messagetemplate.
     *
     * @param messagetemplateDTO the messagetemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messagetemplateDTO,
     * or with status {@code 400 (Bad Request)} if the messagetemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messagetemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/messagetemplates")
    public ResponseEntity<MessagetemplateDTO> updateMessagetemplate(@RequestBody MessagetemplateDTO messagetemplateDTO) throws URISyntaxException {
        log.debug("REST request to update Messagetemplate : {}", messagetemplateDTO);
        if (messagetemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MessagetemplateDTO result = messagetemplateService.save(messagetemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messagetemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /messagetemplates} : get all the messagetemplates.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messagetemplates in body.
     */
    @GetMapping("/messagetemplates")
    public ResponseEntity<List<MessagetemplateDTO>> getAllMessagetemplates(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Messagetemplates");
        Page<MessagetemplateDTO> page = messagetemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /messagetemplates/:id} : get the "id" messagetemplate.
     *
     * @param id the id of the messagetemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messagetemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/messagetemplates/{id}")
    public ResponseEntity<MessagetemplateDTO> getMessagetemplate(@PathVariable Long id) {
        log.debug("REST request to get Messagetemplate : {}", id);
        Optional<MessagetemplateDTO> messagetemplateDTO = messagetemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(messagetemplateDTO);
    }

    /**
     * {@code DELETE  /messagetemplates/:id} : delete the "id" messagetemplate.
     *
     * @param id the id of the messagetemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/messagetemplates/{id}")
    public ResponseEntity<Void> deleteMessagetemplate(@PathVariable Long id) {
        log.debug("REST request to delete Messagetemplate : {}", id);
        messagetemplateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
