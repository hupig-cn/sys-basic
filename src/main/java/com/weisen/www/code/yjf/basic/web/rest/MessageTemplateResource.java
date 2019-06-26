package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.service.MessageTemplateService;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.yjf.basic.service.dto.MessageTemplateDTO;

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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.MessageTemplate}.
 */
@RestController
@RequestMapping("/api")
public class MessageTemplateResource {

    private final Logger log = LoggerFactory.getLogger(MessageTemplateResource.class);

    private static final String ENTITY_NAME = "basicMessageTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessageTemplateService messageTemplateService;

    public MessageTemplateResource(MessageTemplateService messageTemplateService) {
        this.messageTemplateService = messageTemplateService;
    }

    /**
     * {@code POST  /message-templates} : Create a new messageTemplate.
     *
     * @param messageTemplateDTO the messageTemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageTemplateDTO, or with status {@code 400 (Bad Request)} if the messageTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/message-templates")
    public ResponseEntity<MessageTemplateDTO> createMessageTemplate(@RequestBody MessageTemplateDTO messageTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save MessageTemplate : {}", messageTemplateDTO);
        if (messageTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new messageTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MessageTemplateDTO result = messageTemplateService.save(messageTemplateDTO);
        return ResponseEntity.created(new URI("/api/message-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /message-templates} : Updates an existing messageTemplate.
     *
     * @param messageTemplateDTO the messageTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the messageTemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messageTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/message-templates")
    public ResponseEntity<MessageTemplateDTO> updateMessageTemplate(@RequestBody MessageTemplateDTO messageTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update MessageTemplate : {}", messageTemplateDTO);
        if (messageTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MessageTemplateDTO result = messageTemplateService.save(messageTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, messageTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /message-templates} : get all the messageTemplates.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageTemplates in body.
     */
    @GetMapping("/message-templates")
    public ResponseEntity<List<MessageTemplateDTO>> getAllMessageTemplates(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of MessageTemplates");
        Page<MessageTemplateDTO> page = messageTemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /message-templates/:id} : get the "id" messageTemplate.
     *
     * @param id the id of the messageTemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageTemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/message-templates/{id}")
    public ResponseEntity<MessageTemplateDTO> getMessageTemplate(@PathVariable Long id) {
        log.debug("REST request to get MessageTemplate : {}", id);
        Optional<MessageTemplateDTO> messageTemplateDTO = messageTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(messageTemplateDTO);
    }

    /**
     * {@code DELETE  /message-templates/:id} : delete the "id" messageTemplate.
     *
     * @param id the id of the messageTemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/message-templates/{id}")
    public ResponseEntity<Void> deleteMessageTemplate(@PathVariable Long id) {
        log.debug("REST request to delete MessageTemplate : {}", id);
        messageTemplateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
