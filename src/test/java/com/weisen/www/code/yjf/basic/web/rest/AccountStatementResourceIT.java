package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.AccountStatement;
import com.weisen.www.code.yjf.basic.repository.AccountStatementRepository;
import com.weisen.www.code.yjf.basic.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static com.weisen.www.code.yjf.basic.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link AccountStatementResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class AccountStatementResourceIT {

    private static final String DEFAULT_STATEMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_STATEMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ASSET = new BigDecimal(1);
    private static final BigDecimal UPDATED_ASSET = new BigDecimal(2);

    private static final Integer DEFAULT_BTYPE = 1;
    private static final Integer UPDATED_BTYPE = 2;

    private static final Integer DEFAULT_CTYPE = 1;
    private static final Integer UPDATED_CTYPE = 2;

    private static final Integer DEFAULT_DIRECTION = 1;
    private static final Integer UPDATED_DIRECTION = 2;

    private static final Integer DEFAULT_PAYWAY = 1;
    private static final Integer UPDATED_PAYWAY = 2;

    private static final String DEFAULT_CTIME = "AAAAAAAAAA";
    private static final String UPDATED_CTIME = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_TRACKING = "AAAAAAAAAA";
    private static final String UPDATED_TRACKING = "BBBBBBBBBB";

    @Autowired
    private AccountStatementRepository accountStatementRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAccountStatementMockMvc;

    private AccountStatement accountStatement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountStatementResource accountStatementResource = new AccountStatementResource(accountStatementRepository);
        this.restAccountStatementMockMvc = MockMvcBuilders.standaloneSetup(accountStatementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountStatement createEntity(EntityManager em) {
        AccountStatement accountStatement = new AccountStatement()
            .statementId(DEFAULT_STATEMENT_ID)
            .userid(DEFAULT_USERID)
            .amount(DEFAULT_AMOUNT)
            .asset(DEFAULT_ASSET)
            .btype(DEFAULT_BTYPE)
            .ctype(DEFAULT_CTYPE)
            .direction(DEFAULT_DIRECTION)
            .payway(DEFAULT_PAYWAY)
            .ctime(DEFAULT_CTIME)
            .desc(DEFAULT_DESC)
            .tracking(DEFAULT_TRACKING);
        return accountStatement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountStatement createUpdatedEntity(EntityManager em) {
        AccountStatement accountStatement = new AccountStatement()
            .statementId(UPDATED_STATEMENT_ID)
            .userid(UPDATED_USERID)
            .amount(UPDATED_AMOUNT)
            .asset(UPDATED_ASSET)
            .btype(UPDATED_BTYPE)
            .ctype(UPDATED_CTYPE)
            .direction(UPDATED_DIRECTION)
            .payway(UPDATED_PAYWAY)
            .ctime(UPDATED_CTIME)
            .desc(UPDATED_DESC)
            .tracking(UPDATED_TRACKING);
        return accountStatement;
    }

    @BeforeEach
    public void initTest() {
        accountStatement = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountStatement() throws Exception {
        int databaseSizeBeforeCreate = accountStatementRepository.findAll().size();

        // Create the AccountStatement
        restAccountStatementMockMvc.perform(post("/api/account-statements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountStatement)))
            .andExpect(status().isCreated());

        // Validate the AccountStatement in the database
        List<AccountStatement> accountStatementList = accountStatementRepository.findAll();
        assertThat(accountStatementList).hasSize(databaseSizeBeforeCreate + 1);
        AccountStatement testAccountStatement = accountStatementList.get(accountStatementList.size() - 1);
        assertThat(testAccountStatement.getStatementId()).isEqualTo(DEFAULT_STATEMENT_ID);
        assertThat(testAccountStatement.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testAccountStatement.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testAccountStatement.getAsset()).isEqualTo(DEFAULT_ASSET);
        assertThat(testAccountStatement.getBtype()).isEqualTo(DEFAULT_BTYPE);
        assertThat(testAccountStatement.getCtype()).isEqualTo(DEFAULT_CTYPE);
        assertThat(testAccountStatement.getDirection()).isEqualTo(DEFAULT_DIRECTION);
        assertThat(testAccountStatement.getPayway()).isEqualTo(DEFAULT_PAYWAY);
        assertThat(testAccountStatement.getCtime()).isEqualTo(DEFAULT_CTIME);
        assertThat(testAccountStatement.getDesc()).isEqualTo(DEFAULT_DESC);
        assertThat(testAccountStatement.getTracking()).isEqualTo(DEFAULT_TRACKING);
    }

    @Test
    @Transactional
    public void createAccountStatementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountStatementRepository.findAll().size();

        // Create the AccountStatement with an existing ID
        accountStatement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountStatementMockMvc.perform(post("/api/account-statements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountStatement)))
            .andExpect(status().isBadRequest());

        // Validate the AccountStatement in the database
        List<AccountStatement> accountStatementList = accountStatementRepository.findAll();
        assertThat(accountStatementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountStatements() throws Exception {
        // Initialize the database
        accountStatementRepository.saveAndFlush(accountStatement);

        // Get all the accountStatementList
        restAccountStatementMockMvc.perform(get("/api/account-statements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountStatement.getId().intValue())))
            .andExpect(jsonPath("$.[*].statementId").value(hasItem(DEFAULT_STATEMENT_ID.toString())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].asset").value(hasItem(DEFAULT_ASSET.intValue())))
            .andExpect(jsonPath("$.[*].btype").value(hasItem(DEFAULT_BTYPE)))
            .andExpect(jsonPath("$.[*].ctype").value(hasItem(DEFAULT_CTYPE)))
            .andExpect(jsonPath("$.[*].direction").value(hasItem(DEFAULT_DIRECTION)))
            .andExpect(jsonPath("$.[*].payway").value(hasItem(DEFAULT_PAYWAY)))
            .andExpect(jsonPath("$.[*].ctime").value(hasItem(DEFAULT_CTIME.toString())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())))
            .andExpect(jsonPath("$.[*].tracking").value(hasItem(DEFAULT_TRACKING.toString())));
    }
    
    @Test
    @Transactional
    public void getAccountStatement() throws Exception {
        // Initialize the database
        accountStatementRepository.saveAndFlush(accountStatement);

        // Get the accountStatement
        restAccountStatementMockMvc.perform(get("/api/account-statements/{id}", accountStatement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountStatement.getId().intValue()))
            .andExpect(jsonPath("$.statementId").value(DEFAULT_STATEMENT_ID.toString()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.asset").value(DEFAULT_ASSET.intValue()))
            .andExpect(jsonPath("$.btype").value(DEFAULT_BTYPE))
            .andExpect(jsonPath("$.ctype").value(DEFAULT_CTYPE))
            .andExpect(jsonPath("$.direction").value(DEFAULT_DIRECTION))
            .andExpect(jsonPath("$.payway").value(DEFAULT_PAYWAY))
            .andExpect(jsonPath("$.ctime").value(DEFAULT_CTIME.toString()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()))
            .andExpect(jsonPath("$.tracking").value(DEFAULT_TRACKING.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountStatement() throws Exception {
        // Get the accountStatement
        restAccountStatementMockMvc.perform(get("/api/account-statements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountStatement() throws Exception {
        // Initialize the database
        accountStatementRepository.saveAndFlush(accountStatement);

        int databaseSizeBeforeUpdate = accountStatementRepository.findAll().size();

        // Update the accountStatement
        AccountStatement updatedAccountStatement = accountStatementRepository.findById(accountStatement.getId()).get();
        // Disconnect from session so that the updates on updatedAccountStatement are not directly saved in db
        em.detach(updatedAccountStatement);
        updatedAccountStatement
            .statementId(UPDATED_STATEMENT_ID)
            .userid(UPDATED_USERID)
            .amount(UPDATED_AMOUNT)
            .asset(UPDATED_ASSET)
            .btype(UPDATED_BTYPE)
            .ctype(UPDATED_CTYPE)
            .direction(UPDATED_DIRECTION)
            .payway(UPDATED_PAYWAY)
            .ctime(UPDATED_CTIME)
            .desc(UPDATED_DESC)
            .tracking(UPDATED_TRACKING);

        restAccountStatementMockMvc.perform(put("/api/account-statements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAccountStatement)))
            .andExpect(status().isOk());

        // Validate the AccountStatement in the database
        List<AccountStatement> accountStatementList = accountStatementRepository.findAll();
        assertThat(accountStatementList).hasSize(databaseSizeBeforeUpdate);
        AccountStatement testAccountStatement = accountStatementList.get(accountStatementList.size() - 1);
        assertThat(testAccountStatement.getStatementId()).isEqualTo(UPDATED_STATEMENT_ID);
        assertThat(testAccountStatement.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testAccountStatement.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAccountStatement.getAsset()).isEqualTo(UPDATED_ASSET);
        assertThat(testAccountStatement.getBtype()).isEqualTo(UPDATED_BTYPE);
        assertThat(testAccountStatement.getCtype()).isEqualTo(UPDATED_CTYPE);
        assertThat(testAccountStatement.getDirection()).isEqualTo(UPDATED_DIRECTION);
        assertThat(testAccountStatement.getPayway()).isEqualTo(UPDATED_PAYWAY);
        assertThat(testAccountStatement.getCtime()).isEqualTo(UPDATED_CTIME);
        assertThat(testAccountStatement.getDesc()).isEqualTo(UPDATED_DESC);
        assertThat(testAccountStatement.getTracking()).isEqualTo(UPDATED_TRACKING);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountStatement() throws Exception {
        int databaseSizeBeforeUpdate = accountStatementRepository.findAll().size();

        // Create the AccountStatement

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountStatementMockMvc.perform(put("/api/account-statements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountStatement)))
            .andExpect(status().isBadRequest());

        // Validate the AccountStatement in the database
        List<AccountStatement> accountStatementList = accountStatementRepository.findAll();
        assertThat(accountStatementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountStatement() throws Exception {
        // Initialize the database
        accountStatementRepository.saveAndFlush(accountStatement);

        int databaseSizeBeforeDelete = accountStatementRepository.findAll().size();

        // Delete the accountStatement
        restAccountStatementMockMvc.perform(delete("/api/account-statements/{id}", accountStatement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountStatement> accountStatementList = accountStatementRepository.findAll();
        assertThat(accountStatementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountStatement.class);
        AccountStatement accountStatement1 = new AccountStatement();
        accountStatement1.setId(1L);
        AccountStatement accountStatement2 = new AccountStatement();
        accountStatement2.setId(accountStatement1.getId());
        assertThat(accountStatement1).isEqualTo(accountStatement2);
        accountStatement2.setId(2L);
        assertThat(accountStatement1).isNotEqualTo(accountStatement2);
        accountStatement1.setId(null);
        assertThat(accountStatement1).isNotEqualTo(accountStatement2);
    }
}
