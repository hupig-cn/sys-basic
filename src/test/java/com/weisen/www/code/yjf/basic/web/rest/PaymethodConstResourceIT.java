package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.PaymethodConst;
import com.weisen.www.code.yjf.basic.repository.PaymethodConstRepository;
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
 * Integration tests for the {@Link PaymethodConstResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class PaymethodConstResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATE = 1;
    private static final Integer UPDATED_STATE = 2;

    @Autowired
    private PaymethodConstRepository paymethodConstRepository;

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

    private MockMvc restPaymethodConstMockMvc;

    private PaymethodConst paymethodConst;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymethodConstResource paymethodConstResource = new PaymethodConstResource(paymethodConstRepository);
        this.restPaymethodConstMockMvc = MockMvcBuilders.standaloneSetup(paymethodConstResource)
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
    public static PaymethodConst createEntity(EntityManager em) {
        PaymethodConst paymethodConst = new PaymethodConst()
            .title(DEFAULT_TITLE)
            .state(DEFAULT_STATE);
        return paymethodConst;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymethodConst createUpdatedEntity(EntityManager em) {
        PaymethodConst paymethodConst = new PaymethodConst()
            .title(UPDATED_TITLE)
            .state(UPDATED_STATE);
        return paymethodConst;
    }

    @BeforeEach
    public void initTest() {
        paymethodConst = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymethodConst() throws Exception {
        int databaseSizeBeforeCreate = paymethodConstRepository.findAll().size();

        // Create the PaymethodConst
        restPaymethodConstMockMvc.perform(post("/api/paymethod-consts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymethodConst)))
            .andExpect(status().isCreated());

        // Validate the PaymethodConst in the database
        List<PaymethodConst> paymethodConstList = paymethodConstRepository.findAll();
        assertThat(paymethodConstList).hasSize(databaseSizeBeforeCreate + 1);
        PaymethodConst testPaymethodConst = paymethodConstList.get(paymethodConstList.size() - 1);
        assertThat(testPaymethodConst.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testPaymethodConst.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    public void createPaymethodConstWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymethodConstRepository.findAll().size();

        // Create the PaymethodConst with an existing ID
        paymethodConst.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymethodConstMockMvc.perform(post("/api/paymethod-consts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymethodConst)))
            .andExpect(status().isBadRequest());

        // Validate the PaymethodConst in the database
        List<PaymethodConst> paymethodConstList = paymethodConstRepository.findAll();
        assertThat(paymethodConstList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPaymethodConsts() throws Exception {
        // Initialize the database
        paymethodConstRepository.saveAndFlush(paymethodConst);

        // Get all the paymethodConstList
        restPaymethodConstMockMvc.perform(get("/api/paymethod-consts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymethodConst.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)));
    }
    
    @Test
    @Transactional
    public void getPaymethodConst() throws Exception {
        // Initialize the database
        paymethodConstRepository.saveAndFlush(paymethodConst);

        // Get the paymethodConst
        restPaymethodConstMockMvc.perform(get("/api/paymethod-consts/{id}", paymethodConst.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paymethodConst.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE));
    }

    @Test
    @Transactional
    public void getNonExistingPaymethodConst() throws Exception {
        // Get the paymethodConst
        restPaymethodConstMockMvc.perform(get("/api/paymethod-consts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymethodConst() throws Exception {
        // Initialize the database
        paymethodConstRepository.saveAndFlush(paymethodConst);

        int databaseSizeBeforeUpdate = paymethodConstRepository.findAll().size();

        // Update the paymethodConst
        PaymethodConst updatedPaymethodConst = paymethodConstRepository.findById(paymethodConst.getId()).get();
        // Disconnect from session so that the updates on updatedPaymethodConst are not directly saved in db
        em.detach(updatedPaymethodConst);
        updatedPaymethodConst
            .title(UPDATED_TITLE)
            .state(UPDATED_STATE);

        restPaymethodConstMockMvc.perform(put("/api/paymethod-consts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPaymethodConst)))
            .andExpect(status().isOk());

        // Validate the PaymethodConst in the database
        List<PaymethodConst> paymethodConstList = paymethodConstRepository.findAll();
        assertThat(paymethodConstList).hasSize(databaseSizeBeforeUpdate);
        PaymethodConst testPaymethodConst = paymethodConstList.get(paymethodConstList.size() - 1);
        assertThat(testPaymethodConst.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPaymethodConst.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymethodConst() throws Exception {
        int databaseSizeBeforeUpdate = paymethodConstRepository.findAll().size();

        // Create the PaymethodConst

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymethodConstMockMvc.perform(put("/api/paymethod-consts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymethodConst)))
            .andExpect(status().isBadRequest());

        // Validate the PaymethodConst in the database
        List<PaymethodConst> paymethodConstList = paymethodConstRepository.findAll();
        assertThat(paymethodConstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymethodConst() throws Exception {
        // Initialize the database
        paymethodConstRepository.saveAndFlush(paymethodConst);

        int databaseSizeBeforeDelete = paymethodConstRepository.findAll().size();

        // Delete the paymethodConst
        restPaymethodConstMockMvc.perform(delete("/api/paymethod-consts/{id}", paymethodConst.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PaymethodConst> paymethodConstList = paymethodConstRepository.findAll();
        assertThat(paymethodConstList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymethodConst.class);
        PaymethodConst paymethodConst1 = new PaymethodConst();
        paymethodConst1.setId(1L);
        PaymethodConst paymethodConst2 = new PaymethodConst();
        paymethodConst2.setId(paymethodConst1.getId());
        assertThat(paymethodConst1).isEqualTo(paymethodConst2);
        paymethodConst2.setId(2L);
        assertThat(paymethodConst1).isNotEqualTo(paymethodConst2);
        paymethodConst1.setId(null);
        assertThat(paymethodConst1).isNotEqualTo(paymethodConst2);
    }
}
