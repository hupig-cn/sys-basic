package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.domain.AccountStatement;
import com.weisen.www.code.yjf.basic.repository.AccountStatementRepository;
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
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.AccountStatement}.
 */
@RestController
@RequestMapping("/api")
public class AccountStatementResource {

    private final Logger log = LoggerFactory.getLogger(AccountStatementResource.class);

    private static final String ENTITY_NAME = "basicAccountStatement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountStatementRepository accountStatementRepository;

    public AccountStatementResource(AccountStatementRepository accountStatementRepository) {
        this.accountStatementRepository = accountStatementRepository;
    }

    /**
     * {@code POST  /account-statements} : Create a new accountStatement.
     *
     * @param accountStatement the accountStatement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountStatement, or with status {@code 400 (Bad Request)} if the accountStatement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-statements")
    public ResponseEntity<AccountStatement> createAccountStatement(@RequestBody AccountStatement accountStatement) throws URISyntaxException {
        log.debug("REST request to save AccountStatement : {}", accountStatement);
        if (accountStatement.getId() != null) {
            throw new BadRequestAlertException("A new accountStatement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountStatement result = accountStatementRepository.save(accountStatement);
        return ResponseEntity.created(new URI("/api/account-statements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-statements} : Updates an existing accountStatement.
     *
     * @param accountStatement the accountStatement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountStatement,
     * or with status {@code 400 (Bad Request)} if the accountStatement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountStatement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-statements")
    public ResponseEntity<AccountStatement> updateAccountStatement(@RequestBody AccountStatement accountStatement) throws URISyntaxException {
        log.debug("REST request to update AccountStatement : {}", accountStatement);
        if (accountStatement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountStatement result = accountStatementRepository.save(accountStatement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountStatement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-statements} : get all the accountStatements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountStatements in body.
     */
    @GetMapping("/account-statements")
    public List<AccountStatement> getAllAccountStatements() {
        log.debug("REST request to get all AccountStatements");
        return accountStatementRepository.findAll();
    }

    /**
     * {@code GET  /account-statements/:id} : get the "id" accountStatement.
     *
     * @param id the id of the accountStatement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountStatement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-statements/{id}")
    public ResponseEntity<AccountStatement> getAccountStatement(@PathVariable Long id) {
        log.debug("REST request to get AccountStatement : {}", id);
        Optional<AccountStatement> accountStatement = accountStatementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(accountStatement);
    }

    /**
     * {@code DELETE  /account-statements/:id} : delete the "id" accountStatement.
     *
     * @param id the id of the accountStatement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-statements/{id}")
    public ResponseEntity<Void> deleteAccountStatement(@PathVariable Long id) {
        log.debug("REST request to delete AccountStatement : {}", id);
        accountStatementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
