package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.ActivityCon;
import com.weisen.www.code.yjf.basic.repository.ActivityConRepository;
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
 * Integration tests for the {@Link ActivityConResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class ActivityConResourceIT {

    private static final String DEFAULT_ACTIVITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTEREST_RATE = new BigDecimal(2);

    private static final String DEFAULT_CREATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_TIME = "BBBBBBBBBB";

    @Autowired
    private ActivityConRepository activityConRepository;

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

    private MockMvc restActivityConMockMvc;

    private ActivityCon activityCon;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActivityConResource activityConResource = new ActivityConResource(activityConRepository);
        this.restActivityConMockMvc = MockMvcBuilders.standaloneSetup(activityConResource)
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
    public static ActivityCon createEntity(EntityManager em) {
        ActivityCon activityCon = new ActivityCon()
            .activityName(DEFAULT_ACTIVITY_NAME)
            .interestRate(DEFAULT_INTEREST_RATE)
            .createTime(DEFAULT_CREATE_TIME);
        return activityCon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActivityCon createUpdatedEntity(EntityManager em) {
        ActivityCon activityCon = new ActivityCon()
            .activityName(UPDATED_ACTIVITY_NAME)
            .interestRate(UPDATED_INTEREST_RATE)
            .createTime(UPDATED_CREATE_TIME);
        return activityCon;
    }

    @BeforeEach
    public void initTest() {
        activityCon = createEntity(em);
    }

    @Test
    @Transactional
    public void createActivityCon() throws Exception {
        int databaseSizeBeforeCreate = activityConRepository.findAll().size();

        // Create the ActivityCon
        restActivityConMockMvc.perform(post("/api/activity-cons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityCon)))
            .andExpect(status().isCreated());

        // Validate the ActivityCon in the database
        List<ActivityCon> activityConList = activityConRepository.findAll();
        assertThat(activityConList).hasSize(databaseSizeBeforeCreate + 1);
        ActivityCon testActivityCon = activityConList.get(activityConList.size() - 1);
        assertThat(testActivityCon.getActivityName()).isEqualTo(DEFAULT_ACTIVITY_NAME);
        assertThat(testActivityCon.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testActivityCon.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    public void createActivityConWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activityConRepository.findAll().size();

        // Create the ActivityCon with an existing ID
        activityCon.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivityConMockMvc.perform(post("/api/activity-cons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityCon)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityCon in the database
        List<ActivityCon> activityConList = activityConRepository.findAll();
        assertThat(activityConList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllActivityCons() throws Exception {
        // Initialize the database
        activityConRepository.saveAndFlush(activityCon);

        // Get all the activityConList
        restActivityConMockMvc.perform(get("/api/activity-cons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activityCon.getId().intValue())))
            .andExpect(jsonPath("$.[*].activityName").value(hasItem(DEFAULT_ACTIVITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].interestRate").value(hasItem(DEFAULT_INTEREST_RATE.intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getActivityCon() throws Exception {
        // Initialize the database
        activityConRepository.saveAndFlush(activityCon);

        // Get the activityCon
        restActivityConMockMvc.perform(get("/api/activity-cons/{id}", activityCon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(activityCon.getId().intValue()))
            .andExpect(jsonPath("$.activityName").value(DEFAULT_ACTIVITY_NAME.toString()))
            .andExpect(jsonPath("$.interestRate").value(DEFAULT_INTEREST_RATE.intValue()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActivityCon() throws Exception {
        // Get the activityCon
        restActivityConMockMvc.perform(get("/api/activity-cons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActivityCon() throws Exception {
        // Initialize the database
        activityConRepository.saveAndFlush(activityCon);

        int databaseSizeBeforeUpdate = activityConRepository.findAll().size();

        // Update the activityCon
        ActivityCon updatedActivityCon = activityConRepository.findById(activityCon.getId()).get();
        // Disconnect from session so that the updates on updatedActivityCon are not directly saved in db
        em.detach(updatedActivityCon);
        updatedActivityCon
            .activityName(UPDATED_ACTIVITY_NAME)
            .interestRate(UPDATED_INTEREST_RATE)
            .createTime(UPDATED_CREATE_TIME);

        restActivityConMockMvc.perform(put("/api/activity-cons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedActivityCon)))
            .andExpect(status().isOk());

        // Validate the ActivityCon in the database
        List<ActivityCon> activityConList = activityConRepository.findAll();
        assertThat(activityConList).hasSize(databaseSizeBeforeUpdate);
        ActivityCon testActivityCon = activityConList.get(activityConList.size() - 1);
        assertThat(testActivityCon.getActivityName()).isEqualTo(UPDATED_ACTIVITY_NAME);
        assertThat(testActivityCon.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testActivityCon.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingActivityCon() throws Exception {
        int databaseSizeBeforeUpdate = activityConRepository.findAll().size();

        // Create the ActivityCon

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActivityConMockMvc.perform(put("/api/activity-cons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activityCon)))
            .andExpect(status().isBadRequest());

        // Validate the ActivityCon in the database
        List<ActivityCon> activityConList = activityConRepository.findAll();
        assertThat(activityConList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActivityCon() throws Exception {
        // Initialize the database
        activityConRepository.saveAndFlush(activityCon);

        int databaseSizeBeforeDelete = activityConRepository.findAll().size();

        // Delete the activityCon
        restActivityConMockMvc.perform(delete("/api/activity-cons/{id}", activityCon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ActivityCon> activityConList = activityConRepository.findAll();
        assertThat(activityConList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivityCon.class);
        ActivityCon activityCon1 = new ActivityCon();
        activityCon1.setId(1L);
        ActivityCon activityCon2 = new ActivityCon();
        activityCon2.setId(activityCon1.getId());
        assertThat(activityCon1).isEqualTo(activityCon2);
        activityCon2.setId(2L);
        assertThat(activityCon1).isNotEqualTo(activityCon2);
        activityCon1.setId(null);
        assertThat(activityCon1).isNotEqualTo(activityCon2);
    }
}
