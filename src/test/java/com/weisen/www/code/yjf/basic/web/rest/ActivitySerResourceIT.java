package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.ActivitySer;
import com.weisen.www.code.yjf.basic.repository.ActivitySerRepository;
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
 * Integration tests for the {@Link ActivitySerResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class ActivitySerResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ACTIVITY_AMO = new BigDecimal(1);
    private static final BigDecimal UPDATED_ACTIVITY_AMO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AVAILABLE_AMO = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVAILABLE_AMO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CASH_WITHDRAWAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_CASH_WITHDRAWAL = new BigDecimal(2);

    private static final String DEFAULT_CREATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_TIME = "BBBBBBBBBB";

    @Autowired
    private ActivitySerRepository activitySerRepository;

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

    private MockMvc restActivitySerMockMvc;

    private ActivitySer activitySer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActivitySerResource activitySerResource = new ActivitySerResource(activitySerRepository);
        this.restActivitySerMockMvc = MockMvcBuilders.standaloneSetup(activitySerResource)
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
    public static ActivitySer createEntity(EntityManager em) {
        ActivitySer activitySer = new ActivitySer()
            .userId(DEFAULT_USER_ID)
            .activityAmo(DEFAULT_ACTIVITY_AMO)
            .availableAmo(DEFAULT_AVAILABLE_AMO)
            .cashWithdrawal(DEFAULT_CASH_WITHDRAWAL)
            .createTime(DEFAULT_CREATE_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return activitySer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActivitySer createUpdatedEntity(EntityManager em) {
        ActivitySer activitySer = new ActivitySer()
            .userId(UPDATED_USER_ID)
            .activityAmo(UPDATED_ACTIVITY_AMO)
            .availableAmo(UPDATED_AVAILABLE_AMO)
            .cashWithdrawal(UPDATED_CASH_WITHDRAWAL)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);
        return activitySer;
    }

    @BeforeEach
    public void initTest() {
        activitySer = createEntity(em);
    }

    @Test
    @Transactional
    public void createActivitySer() throws Exception {
        int databaseSizeBeforeCreate = activitySerRepository.findAll().size();

        // Create the ActivitySer
        restActivitySerMockMvc.perform(post("/api/activity-sers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activitySer)))
            .andExpect(status().isCreated());

        // Validate the ActivitySer in the database
        List<ActivitySer> activitySerList = activitySerRepository.findAll();
        assertThat(activitySerList).hasSize(databaseSizeBeforeCreate + 1);
        ActivitySer testActivitySer = activitySerList.get(activitySerList.size() - 1);
        assertThat(testActivitySer.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testActivitySer.getActivityAmo()).isEqualTo(DEFAULT_ACTIVITY_AMO);
        assertThat(testActivitySer.getAvailableAmo()).isEqualTo(DEFAULT_AVAILABLE_AMO);
        assertThat(testActivitySer.getCashWithdrawal()).isEqualTo(DEFAULT_CASH_WITHDRAWAL);
        assertThat(testActivitySer.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testActivitySer.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void createActivitySerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = activitySerRepository.findAll().size();

        // Create the ActivitySer with an existing ID
        activitySer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActivitySerMockMvc.perform(post("/api/activity-sers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activitySer)))
            .andExpect(status().isBadRequest());

        // Validate the ActivitySer in the database
        List<ActivitySer> activitySerList = activitySerRepository.findAll();
        assertThat(activitySerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllActivitySers() throws Exception {
        // Initialize the database
        activitySerRepository.saveAndFlush(activitySer);

        // Get all the activitySerList
        restActivitySerMockMvc.perform(get("/api/activity-sers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(activitySer.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].activityAmo").value(hasItem(DEFAULT_ACTIVITY_AMO.intValue())))
            .andExpect(jsonPath("$.[*].availableAmo").value(hasItem(DEFAULT_AVAILABLE_AMO.intValue())))
            .andExpect(jsonPath("$.[*].cashWithdrawal").value(hasItem(DEFAULT_CASH_WITHDRAWAL.intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getActivitySer() throws Exception {
        // Initialize the database
        activitySerRepository.saveAndFlush(activitySer);

        // Get the activitySer
        restActivitySerMockMvc.perform(get("/api/activity-sers/{id}", activitySer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(activitySer.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.activityAmo").value(DEFAULT_ACTIVITY_AMO.intValue()))
            .andExpect(jsonPath("$.availableAmo").value(DEFAULT_AVAILABLE_AMO.intValue()))
            .andExpect(jsonPath("$.cashWithdrawal").value(DEFAULT_CASH_WITHDRAWAL.intValue()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActivitySer() throws Exception {
        // Get the activitySer
        restActivitySerMockMvc.perform(get("/api/activity-sers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActivitySer() throws Exception {
        // Initialize the database
        activitySerRepository.saveAndFlush(activitySer);

        int databaseSizeBeforeUpdate = activitySerRepository.findAll().size();

        // Update the activitySer
        ActivitySer updatedActivitySer = activitySerRepository.findById(activitySer.getId()).get();
        // Disconnect from session so that the updates on updatedActivitySer are not directly saved in db
        em.detach(updatedActivitySer);
        updatedActivitySer
            .userId(UPDATED_USER_ID)
            .activityAmo(UPDATED_ACTIVITY_AMO)
            .availableAmo(UPDATED_AVAILABLE_AMO)
            .cashWithdrawal(UPDATED_CASH_WITHDRAWAL)
            .createTime(UPDATED_CREATE_TIME)
            .updateTime(UPDATED_UPDATE_TIME);

        restActivitySerMockMvc.perform(put("/api/activity-sers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedActivitySer)))
            .andExpect(status().isOk());

        // Validate the ActivitySer in the database
        List<ActivitySer> activitySerList = activitySerRepository.findAll();
        assertThat(activitySerList).hasSize(databaseSizeBeforeUpdate);
        ActivitySer testActivitySer = activitySerList.get(activitySerList.size() - 1);
        assertThat(testActivitySer.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testActivitySer.getActivityAmo()).isEqualTo(UPDATED_ACTIVITY_AMO);
        assertThat(testActivitySer.getAvailableAmo()).isEqualTo(UPDATED_AVAILABLE_AMO);
        assertThat(testActivitySer.getCashWithdrawal()).isEqualTo(UPDATED_CASH_WITHDRAWAL);
        assertThat(testActivitySer.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testActivitySer.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingActivitySer() throws Exception {
        int databaseSizeBeforeUpdate = activitySerRepository.findAll().size();

        // Create the ActivitySer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActivitySerMockMvc.perform(put("/api/activity-sers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(activitySer)))
            .andExpect(status().isBadRequest());

        // Validate the ActivitySer in the database
        List<ActivitySer> activitySerList = activitySerRepository.findAll();
        assertThat(activitySerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActivitySer() throws Exception {
        // Initialize the database
        activitySerRepository.saveAndFlush(activitySer);

        int databaseSizeBeforeDelete = activitySerRepository.findAll().size();

        // Delete the activitySer
        restActivitySerMockMvc.perform(delete("/api/activity-sers/{id}", activitySer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ActivitySer> activitySerList = activitySerRepository.findAll();
        assertThat(activitySerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActivitySer.class);
        ActivitySer activitySer1 = new ActivitySer();
        activitySer1.setId(1L);
        ActivitySer activitySer2 = new ActivitySer();
        activitySer2.setId(activitySer1.getId());
        assertThat(activitySer1).isEqualTo(activitySer2);
        activitySer2.setId(2L);
        assertThat(activitySer1).isNotEqualTo(activitySer2);
        activitySer1.setId(null);
        assertThat(activitySer1).isNotEqualTo(activitySer2);
    }
}
