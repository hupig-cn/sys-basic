package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.ActivityPay;
import com.weisen.www.code.yjf.basic.repository.ActivityPayRepository;
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
 * Integration tests for the {@Link ActivityPayResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class ActivityPayResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final BigDecimal DEFAULT_INCOME_AMO = new BigDecimal(1);
    private static final BigDecimal UPDATED_INCOME_AMO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TRANSFORMATION_AMO = new BigDecimal(1);
    private static final BigDecimal UPDATED_TRANSFORMATION_AMO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTEREST_RATE = new BigDecimal(2);

    private static final String DEFAULT_CREATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_TIME = "BBBBBBBBBB";

    @Autowired
    private ActivityPayRepository activityPayRepository;

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

    private MockMvc restActivityPayMockMvc;

    private ActivityPay activityPay;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActivityPayResource activityPayResource = new ActivityPayResource(activityPayRepository);
        this.restActivityPayMockMvc = MockMvcBuilders.standaloneSetup(activityPayResource)
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
    public static ActivityPay createEntity(EntityManager em) {
        ActivityPay activityPay = new ActivityPay()
            .userId(DEFAULT_USER_ID)
            .type(DEFAULT_TYPE)
            .incomeAmo(DEFAULT_INCOME_AMO)
            .transformationAmo(DEFAULT_TRANSFORMATION_AMO)
            .interestRate(DEFAULT_INTEREST_RATE)
            .createTime(DEFAULT_CREATE_TIME);
        return activityPay;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActivityPay createUpdatedEntity(EntityManager em) {
        ActivityPay activityPay = new ActivityPay()
            .userId(UPDATED_USER_ID)
            .type(UPDATED_TYPE)
            .incomeAmo(UPDATED_INCOME_AMO)
            .transformationAmo(UPDATED_TRANSFORMATION_AMO)
            .interestRate(UPDATED_INTEREST_RATE)
            .createTime(UPDATED_CREATE_TIME);
        return activityPay;
    }

    @BeforeEach
    public void initTest() {
        activityPay = createEntity(em);
    }

    @Test
    @Transactional
    public void createActivityPay() throws Exception {
        int databaseSizeBeforeCreate = activityPayRepository.findAll().size();

        // Create the ActivityPay
        restActivityPayMockMvc.perform(post("/api/activity-pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityPay)))
            .andExpect(status().isCreated());

        // Validate the ActivityPay in the database
        List<ActivityPay> activityPayList = activityPayRepository.findAll();
        assertThat(activityPayList).hasSize(databaseSizeBeforeCreate + 1);
        ActivityPay testActivityPay = activityPayList.get(activityPayList.size() - 1);
        assertThat(testActivityPay.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testActivityPay.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testActivityPay.getIncomeAmo()).isEqualTo(DEFAULT_INCOME_AMO);
        assertThat(testActivityPay.getTransformationAmo()).isEqualTo(DEFAULT_TRANSFORMATION_AMO);
        assertThat(testActivityPay.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testActivityPay.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    public void createActivityPayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activityPayRepository.findAll().size();

        // Create the ActivityPay with an existing ID
        activityPay.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityPayMockMvc.perform(post("/api/activity-pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityPay)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityPay in the database
        List<ActivityPay> activityPayList = activityPayRepository.findAll();
        assertThat(activityPayList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllActivityPays() throws Exception {
        // Initialize the database
        activityPayRepository.saveAndFlush(activityPay);

        // Get all the activityPayList
        restActivityPayMockMvc.perform(get("/api/activity-pays?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activityPay.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].incomeAmo").value(hasItem(DEFAULT_INCOME_AMO.intValue())))
            .andExpect(jsonPath("$.[*].transformationAmo").value(hasItem(DEFAULT_TRANSFORMATION_AMO.intValue())))
            .andExpect(jsonPath("$.[*].interestRate").value(hasItem(DEFAULT_INTEREST_RATE.intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getActivityPay() throws Exception {
        // Initialize the database
        activityPayRepository.saveAndFlush(activityPay);

        // Get the activityPay
        restActivityPayMockMvc.perform(get("/api/activity-pays/{id}", activityPay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(activityPay.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.incomeAmo").value(DEFAULT_INCOME_AMO.intValue()))
            .andExpect(jsonPath("$.transformationAmo").value(DEFAULT_TRANSFORMATION_AMO.intValue()))
            .andExpect(jsonPath("$.interestRate").value(DEFAULT_INTEREST_RATE.intValue()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActivityPay() throws Exception {
        // Get the activityPay
        restActivityPayMockMvc.perform(get("/api/activity-pays/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActivityPay() throws Exception {
        // Initialize the database
        activityPayRepository.saveAndFlush(activityPay);

        int databaseSizeBeforeUpdate = activityPayRepository.findAll().size();

        // Update the activityPay
        ActivityPay updatedActivityPay = activityPayRepository.findById(activityPay.getId()).get();
        // Disconnect from session so that the updates on updatedActivityPay are not directly saved in db
        em.detach(updatedActivityPay);
        updatedActivityPay
            .userId(UPDATED_USER_ID)
            .type(UPDATED_TYPE)
            .incomeAmo(UPDATED_INCOME_AMO)
            .transformationAmo(UPDATED_TRANSFORMATION_AMO)
            .interestRate(UPDATED_INTEREST_RATE)
            .createTime(UPDATED_CREATE_TIME);

        restActivityPayMockMvc.perform(put("/api/activity-pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedActivityPay)))
            .andExpect(status().isOk());

        // Validate the ActivityPay in the database
        List<ActivityPay> activityPayList = activityPayRepository.findAll();
        assertThat(activityPayList).hasSize(databaseSizeBeforeUpdate);
        ActivityPay testActivityPay = activityPayList.get(activityPayList.size() - 1);
        assertThat(testActivityPay.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testActivityPay.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testActivityPay.getIncomeAmo()).isEqualTo(UPDATED_INCOME_AMO);
        assertThat(testActivityPay.getTransformationAmo()).isEqualTo(UPDATED_TRANSFORMATION_AMO);
        assertThat(testActivityPay.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testActivityPay.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingActivityPay() throws Exception {
        int databaseSizeBeforeUpdate = activityPayRepository.findAll().size();

        // Create the ActivityPay

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActivityPayMockMvc.perform(put("/api/activity-pays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityPay)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityPay in the database
        List<ActivityPay> activityPayList = activityPayRepository.findAll();
        assertThat(activityPayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActivityPay() throws Exception {
        // Initialize the database
        activityPayRepository.saveAndFlush(activityPay);

        int databaseSizeBeforeDelete = activityPayRepository.findAll().size();

        // Delete the activityPay
        restActivityPayMockMvc.perform(delete("/api/activity-pays/{id}", activityPay.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ActivityPay> activityPayList = activityPayRepository.findAll();
        assertThat(activityPayList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityPay.class);
        ActivityPay activityPay1 = new ActivityPay();
        activityPay1.setId(1L);
        ActivityPay activityPay2 = new ActivityPay();
        activityPay2.setId(activityPay1.getId());
        assertThat(activityPay1).isEqualTo(activityPay2);
        activityPay2.setId(2L);
        assertThat(activityPay1).isNotEqualTo(activityPay2);
        activityPay1.setId(null);
        assertThat(activityPay1).isNotEqualTo(activityPay2);
    }
}
