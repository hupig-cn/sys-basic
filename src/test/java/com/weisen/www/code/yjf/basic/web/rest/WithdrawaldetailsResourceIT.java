package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Withdrawaldetails;
import com.weisen.www.code.yjf.basic.repository.WithdrawaldetailsRepository;
import com.weisen.www.code.yjf.basic.service.WithdrawaldetailsService;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawaldetailsDTO;
import com.weisen.www.code.yjf.basic.service.mapper.WithdrawaldetailsMapper;
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
import java.util.List;

import static com.weisen.www.code.yjf.basic.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link WithdrawaldetailsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class WithdrawaldetailsResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_WITHDRAWALWAY = "AAAAAAAAAA";
    private static final String UPDATED_WITHDRAWALWAY = "BBBBBBBBBB";

    private static final String DEFAULT_WITHDRAWALID = "AAAAAAAAAA";
    private static final String UPDATED_WITHDRAWALID = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_AFTERAMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AFTERAMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIERDATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIERDATE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    @Autowired
    private WithdrawaldetailsRepository withdrawaldetailsRepository;

    @Autowired
    private WithdrawaldetailsMapper withdrawaldetailsMapper;

    @Autowired
    private WithdrawaldetailsService withdrawaldetailsService;

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

    private MockMvc restWithdrawaldetailsMockMvc;

    private Withdrawaldetails withdrawaldetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WithdrawaldetailsResource withdrawaldetailsResource = new WithdrawaldetailsResource(withdrawaldetailsService);
        this.restWithdrawaldetailsMockMvc = MockMvcBuilders.standaloneSetup(withdrawaldetailsResource)
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
    public static Withdrawaldetails createEntity(EntityManager em) {
        Withdrawaldetails withdrawaldetails = new Withdrawaldetails()
            .userid(DEFAULT_USERID)
            .withdrawalway(DEFAULT_WITHDRAWALWAY)
            .withdrawalid(DEFAULT_WITHDRAWALID)
            .type(DEFAULT_TYPE)
            .amount(DEFAULT_AMOUNT)
            .afteramount(DEFAULT_AFTERAMOUNT)
            .createdate(DEFAULT_CREATEDATE)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .state(DEFAULT_STATE);
        return withdrawaldetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Withdrawaldetails createUpdatedEntity(EntityManager em) {
        Withdrawaldetails withdrawaldetails = new Withdrawaldetails()
            .userid(UPDATED_USERID)
            .withdrawalway(UPDATED_WITHDRAWALWAY)
            .withdrawalid(UPDATED_WITHDRAWALID)
            .type(UPDATED_TYPE)
            .amount(UPDATED_AMOUNT)
            .afteramount(UPDATED_AFTERAMOUNT)
            .createdate(UPDATED_CREATEDATE)
            .modifierdate(UPDATED_MODIFIERDATE)
            .state(UPDATED_STATE);
        return withdrawaldetails;
    }

    @BeforeEach
    public void initTest() {
        withdrawaldetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createWithdrawaldetails() throws Exception {
        int databaseSizeBeforeCreate = withdrawaldetailsRepository.findAll().size();

        // Create the Withdrawaldetails
        WithdrawaldetailsDTO withdrawaldetailsDTO = withdrawaldetailsMapper.toDto(withdrawaldetails);
        restWithdrawaldetailsMockMvc.perform(post("/api/withdrawaldetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(withdrawaldetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the Withdrawaldetails in the database
        List<Withdrawaldetails> withdrawaldetailsList = withdrawaldetailsRepository.findAll();
        assertThat(withdrawaldetailsList).hasSize(databaseSizeBeforeCreate + 1);
        Withdrawaldetails testWithdrawaldetails = withdrawaldetailsList.get(withdrawaldetailsList.size() - 1);
        assertThat(testWithdrawaldetails.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testWithdrawaldetails.getWithdrawalway()).isEqualTo(DEFAULT_WITHDRAWALWAY);
        assertThat(testWithdrawaldetails.getWithdrawalid()).isEqualTo(DEFAULT_WITHDRAWALID);
        assertThat(testWithdrawaldetails.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testWithdrawaldetails.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testWithdrawaldetails.getAfteramount()).isEqualTo(DEFAULT_AFTERAMOUNT);
        assertThat(testWithdrawaldetails.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testWithdrawaldetails.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testWithdrawaldetails.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    public void createWithdrawaldetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = withdrawaldetailsRepository.findAll().size();

        // Create the Withdrawaldetails with an existing ID
        withdrawaldetails.setId(1L);
        WithdrawaldetailsDTO withdrawaldetailsDTO = withdrawaldetailsMapper.toDto(withdrawaldetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWithdrawaldetailsMockMvc.perform(post("/api/withdrawaldetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(withdrawaldetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Withdrawaldetails in the database
        List<Withdrawaldetails> withdrawaldetailsList = withdrawaldetailsRepository.findAll();
        assertThat(withdrawaldetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWithdrawaldetails() throws Exception {
        // Initialize the database
        withdrawaldetailsRepository.saveAndFlush(withdrawaldetails);

        // Get all the withdrawaldetailsList
        restWithdrawaldetailsMockMvc.perform(get("/api/withdrawaldetails?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(withdrawaldetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].withdrawalway").value(hasItem(DEFAULT_WITHDRAWALWAY.toString())))
            .andExpect(jsonPath("$.[*].withdrawalid").value(hasItem(DEFAULT_WITHDRAWALID.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].afteramount").value(hasItem(DEFAULT_AFTERAMOUNT.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].modifierdate").value(hasItem(DEFAULT_MODIFIERDATE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }
    
    @Test
    @Transactional
    public void getWithdrawaldetails() throws Exception {
        // Initialize the database
        withdrawaldetailsRepository.saveAndFlush(withdrawaldetails);

        // Get the withdrawaldetails
        restWithdrawaldetailsMockMvc.perform(get("/api/withdrawaldetails/{id}", withdrawaldetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(withdrawaldetails.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.withdrawalway").value(DEFAULT_WITHDRAWALWAY.toString()))
            .andExpect(jsonPath("$.withdrawalid").value(DEFAULT_WITHDRAWALID.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.toString()))
            .andExpect(jsonPath("$.afteramount").value(DEFAULT_AFTERAMOUNT.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.modifierdate").value(DEFAULT_MODIFIERDATE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWithdrawaldetails() throws Exception {
        // Get the withdrawaldetails
        restWithdrawaldetailsMockMvc.perform(get("/api/withdrawaldetails/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWithdrawaldetails() throws Exception {
        // Initialize the database
        withdrawaldetailsRepository.saveAndFlush(withdrawaldetails);

        int databaseSizeBeforeUpdate = withdrawaldetailsRepository.findAll().size();

        // Update the withdrawaldetails
        Withdrawaldetails updatedWithdrawaldetails = withdrawaldetailsRepository.findById(withdrawaldetails.getId()).get();
        // Disconnect from session so that the updates on updatedWithdrawaldetails are not directly saved in db
        em.detach(updatedWithdrawaldetails);
        updatedWithdrawaldetails
            .userid(UPDATED_USERID)
            .withdrawalway(UPDATED_WITHDRAWALWAY)
            .withdrawalid(UPDATED_WITHDRAWALID)
            .type(UPDATED_TYPE)
            .amount(UPDATED_AMOUNT)
            .afteramount(UPDATED_AFTERAMOUNT)
            .createdate(UPDATED_CREATEDATE)
            .modifierdate(UPDATED_MODIFIERDATE)
            .state(UPDATED_STATE);
        WithdrawaldetailsDTO withdrawaldetailsDTO = withdrawaldetailsMapper.toDto(updatedWithdrawaldetails);

        restWithdrawaldetailsMockMvc.perform(put("/api/withdrawaldetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(withdrawaldetailsDTO)))
            .andExpect(status().isOk());

        // Validate the Withdrawaldetails in the database
        List<Withdrawaldetails> withdrawaldetailsList = withdrawaldetailsRepository.findAll();
        assertThat(withdrawaldetailsList).hasSize(databaseSizeBeforeUpdate);
        Withdrawaldetails testWithdrawaldetails = withdrawaldetailsList.get(withdrawaldetailsList.size() - 1);
        assertThat(testWithdrawaldetails.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testWithdrawaldetails.getWithdrawalway()).isEqualTo(UPDATED_WITHDRAWALWAY);
        assertThat(testWithdrawaldetails.getWithdrawalid()).isEqualTo(UPDATED_WITHDRAWALID);
        assertThat(testWithdrawaldetails.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testWithdrawaldetails.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testWithdrawaldetails.getAfteramount()).isEqualTo(UPDATED_AFTERAMOUNT);
        assertThat(testWithdrawaldetails.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testWithdrawaldetails.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testWithdrawaldetails.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingWithdrawaldetails() throws Exception {
        int databaseSizeBeforeUpdate = withdrawaldetailsRepository.findAll().size();

        // Create the Withdrawaldetails
        WithdrawaldetailsDTO withdrawaldetailsDTO = withdrawaldetailsMapper.toDto(withdrawaldetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWithdrawaldetailsMockMvc.perform(put("/api/withdrawaldetails")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(withdrawaldetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Withdrawaldetails in the database
        List<Withdrawaldetails> withdrawaldetailsList = withdrawaldetailsRepository.findAll();
        assertThat(withdrawaldetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWithdrawaldetails() throws Exception {
        // Initialize the database
        withdrawaldetailsRepository.saveAndFlush(withdrawaldetails);

        int databaseSizeBeforeDelete = withdrawaldetailsRepository.findAll().size();

        // Delete the withdrawaldetails
        restWithdrawaldetailsMockMvc.perform(delete("/api/withdrawaldetails/{id}", withdrawaldetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Withdrawaldetails> withdrawaldetailsList = withdrawaldetailsRepository.findAll();
        assertThat(withdrawaldetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Withdrawaldetails.class);
        Withdrawaldetails withdrawaldetails1 = new Withdrawaldetails();
        withdrawaldetails1.setId(1L);
        Withdrawaldetails withdrawaldetails2 = new Withdrawaldetails();
        withdrawaldetails2.setId(withdrawaldetails1.getId());
        assertThat(withdrawaldetails1).isEqualTo(withdrawaldetails2);
        withdrawaldetails2.setId(2L);
        assertThat(withdrawaldetails1).isNotEqualTo(withdrawaldetails2);
        withdrawaldetails1.setId(null);
        assertThat(withdrawaldetails1).isNotEqualTo(withdrawaldetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WithdrawaldetailsDTO.class);
        WithdrawaldetailsDTO withdrawaldetailsDTO1 = new WithdrawaldetailsDTO();
        withdrawaldetailsDTO1.setId(1L);
        WithdrawaldetailsDTO withdrawaldetailsDTO2 = new WithdrawaldetailsDTO();
        assertThat(withdrawaldetailsDTO1).isNotEqualTo(withdrawaldetailsDTO2);
        withdrawaldetailsDTO2.setId(withdrawaldetailsDTO1.getId());
        assertThat(withdrawaldetailsDTO1).isEqualTo(withdrawaldetailsDTO2);
        withdrawaldetailsDTO2.setId(2L);
        assertThat(withdrawaldetailsDTO1).isNotEqualTo(withdrawaldetailsDTO2);
        withdrawaldetailsDTO1.setId(null);
        assertThat(withdrawaldetailsDTO1).isNotEqualTo(withdrawaldetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(withdrawaldetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(withdrawaldetailsMapper.fromId(null)).isNull();
    }
}
