package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Options;
import com.weisen.www.code.yjf.basic.repository.OptionsRepository;
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
 * Integration tests for the {@Link OptionsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class OptionsResourceIT {

    private static final String DEFAULT_STYPE = "AAAAAAAAAA";
    private static final String UPDATED_STYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SK = "AAAAAAAAAA";
    private static final String UPDATED_SK = "BBBBBBBBBB";

    private static final String DEFAULT_SV = "AAAAAAAAAA";
    private static final String UPDATED_SV = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final Long DEFAULT_CSORT = 1L;
    private static final Long UPDATED_CSORT = 2L;

    private static final String DEFAULT_INFO = "AAAAAAAAAA";
    private static final String UPDATED_INFO = "BBBBBBBBBB";

    @Autowired
    private OptionsRepository optionsRepository;

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

    private MockMvc restOptionsMockMvc;

    private Options options;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OptionsResource optionsResource = new OptionsResource(optionsRepository);
        this.restOptionsMockMvc = MockMvcBuilders.standaloneSetup(optionsResource)
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
    public static Options createEntity(EntityManager em) {
        Options options = new Options()
            .stype(DEFAULT_STYPE)
            .sk(DEFAULT_SK)
            .sv(DEFAULT_SV)
            .state(DEFAULT_STATE)
            .csort(DEFAULT_CSORT)
            .info(DEFAULT_INFO);
        return options;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Options createUpdatedEntity(EntityManager em) {
        Options options = new Options()
            .stype(UPDATED_STYPE)
            .sk(UPDATED_SK)
            .sv(UPDATED_SV)
            .state(UPDATED_STATE)
            .csort(UPDATED_CSORT)
            .info(UPDATED_INFO);
        return options;
    }

    @BeforeEach
    public void initTest() {
        options = createEntity(em);
    }

    @Test
    @Transactional
    public void createOptions() throws Exception {
        int databaseSizeBeforeCreate = optionsRepository.findAll().size();

        // Create the Options
        restOptionsMockMvc.perform(post("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(options)))
            .andExpect(status().isCreated());

        // Validate the Options in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeCreate + 1);
        Options testOptions = optionsList.get(optionsList.size() - 1);
        assertThat(testOptions.getStype()).isEqualTo(DEFAULT_STYPE);
        assertThat(testOptions.getSk()).isEqualTo(DEFAULT_SK);
        assertThat(testOptions.getSv()).isEqualTo(DEFAULT_SV);
        assertThat(testOptions.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testOptions.getCsort()).isEqualTo(DEFAULT_CSORT);
        assertThat(testOptions.getInfo()).isEqualTo(DEFAULT_INFO);
    }

    @Test
    @Transactional
    public void createOptionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = optionsRepository.findAll().size();

        // Create the Options with an existing ID
        options.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOptionsMockMvc.perform(post("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(options)))
            .andExpect(status().isBadRequest());

        // Validate the Options in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);

        // Get all the optionsList
        restOptionsMockMvc.perform(get("/api/options?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(options.getId().intValue())))
            .andExpect(jsonPath("$.[*].stype").value(hasItem(DEFAULT_STYPE.toString())))
            .andExpect(jsonPath("$.[*].sk").value(hasItem(DEFAULT_SK.toString())))
            .andExpect(jsonPath("$.[*].sv").value(hasItem(DEFAULT_SV.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].csort").value(hasItem(DEFAULT_CSORT.intValue())))
            .andExpect(jsonPath("$.[*].info").value(hasItem(DEFAULT_INFO.toString())));
    }
    
    @Test
    @Transactional
    public void getOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);

        // Get the options
        restOptionsMockMvc.perform(get("/api/options/{id}", options.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(options.getId().intValue()))
            .andExpect(jsonPath("$.stype").value(DEFAULT_STYPE.toString()))
            .andExpect(jsonPath("$.sk").value(DEFAULT_SK.toString()))
            .andExpect(jsonPath("$.sv").value(DEFAULT_SV.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.csort").value(DEFAULT_CSORT.intValue()))
            .andExpect(jsonPath("$.info").value(DEFAULT_INFO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOptions() throws Exception {
        // Get the options
        restOptionsMockMvc.perform(get("/api/options/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);

        int databaseSizeBeforeUpdate = optionsRepository.findAll().size();

        // Update the options
        Options updatedOptions = optionsRepository.findById(options.getId()).get();
        // Disconnect from session so that the updates on updatedOptions are not directly saved in db
        em.detach(updatedOptions);
        updatedOptions
            .stype(UPDATED_STYPE)
            .sk(UPDATED_SK)
            .sv(UPDATED_SV)
            .state(UPDATED_STATE)
            .csort(UPDATED_CSORT)
            .info(UPDATED_INFO);

        restOptionsMockMvc.perform(put("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOptions)))
            .andExpect(status().isOk());

        // Validate the Options in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeUpdate);
        Options testOptions = optionsList.get(optionsList.size() - 1);
        assertThat(testOptions.getStype()).isEqualTo(UPDATED_STYPE);
        assertThat(testOptions.getSk()).isEqualTo(UPDATED_SK);
        assertThat(testOptions.getSv()).isEqualTo(UPDATED_SV);
        assertThat(testOptions.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testOptions.getCsort()).isEqualTo(UPDATED_CSORT);
        assertThat(testOptions.getInfo()).isEqualTo(UPDATED_INFO);
    }

    @Test
    @Transactional
    public void updateNonExistingOptions() throws Exception {
        int databaseSizeBeforeUpdate = optionsRepository.findAll().size();

        // Create the Options

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOptionsMockMvc.perform(put("/api/options")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(options)))
            .andExpect(status().isBadRequest());

        // Validate the Options in the database
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOptions() throws Exception {
        // Initialize the database
        optionsRepository.saveAndFlush(options);

        int databaseSizeBeforeDelete = optionsRepository.findAll().size();

        // Delete the options
        restOptionsMockMvc.perform(delete("/api/options/{id}", options.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Options> optionsList = optionsRepository.findAll();
        assertThat(optionsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Options.class);
        Options options1 = new Options();
        options1.setId(1L);
        Options options2 = new Options();
        options2.setId(options1.getId());
        assertThat(options1).isEqualTo(options2);
        options2.setId(2L);
        assertThat(options1).isNotEqualTo(options2);
        options1.setId(null);
        assertThat(options1).isNotEqualTo(options2);
    }
}
