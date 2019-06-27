package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Percentage;
import com.weisen.www.code.yjf.basic.repository.PercentageRepository;
import com.weisen.www.code.yjf.basic.service.PercentageService;
import com.weisen.www.code.yjf.basic.service.dto.PercentageDTO;
import com.weisen.www.code.yjf.basic.service.mapper.PercentageMapper;
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
 * Integration tests for the {@Link PercentageResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class PercentageResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIERDATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIERDATE = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIERNUM = 1L;
    private static final Long UPDATED_MODIFIERNUM = 2L;

    private static final Boolean DEFAULT_LOGICDELETE = false;
    private static final Boolean UPDATED_LOGICDELETE = true;

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    @Autowired
    private PercentageRepository percentageRepository;

    @Autowired
    private PercentageMapper percentageMapper;

    @Autowired
    private PercentageService percentageService;

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

    private MockMvc restPercentageMockMvc;

    private Percentage percentage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PercentageResource percentageResource = new PercentageResource(percentageService);
        this.restPercentageMockMvc = MockMvcBuilders.standaloneSetup(percentageResource)
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
    public static Percentage createEntity(EntityManager em) {
        Percentage percentage = new Percentage()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .value(DEFAULT_VALUE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return percentage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Percentage createUpdatedEntity(EntityManager em) {
        Percentage percentage = new Percentage()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return percentage;
    }

    @BeforeEach
    public void initTest() {
        percentage = createEntity(em);
    }

    @Test
    @Transactional
    public void createPercentage() throws Exception {
        int databaseSizeBeforeCreate = percentageRepository.findAll().size();

        // Create the Percentage
        PercentageDTO percentageDTO = percentageMapper.toDto(percentage);
        restPercentageMockMvc.perform(post("/api/percentages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(percentageDTO)))
            .andExpect(status().isCreated());

        // Validate the Percentage in the database
        List<Percentage> percentageList = percentageRepository.findAll();
        assertThat(percentageList).hasSize(databaseSizeBeforeCreate + 1);
        Percentage testPercentage = percentageList.get(percentageList.size() - 1);
        assertThat(testPercentage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPercentage.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPercentage.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testPercentage.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPercentage.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testPercentage.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testPercentage.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testPercentage.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testPercentage.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testPercentage.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createPercentageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = percentageRepository.findAll().size();

        // Create the Percentage with an existing ID
        percentage.setId(1L);
        PercentageDTO percentageDTO = percentageMapper.toDto(percentage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPercentageMockMvc.perform(post("/api/percentages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(percentageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Percentage in the database
        List<Percentage> percentageList = percentageRepository.findAll();
        assertThat(percentageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPercentages() throws Exception {
        // Initialize the database
        percentageRepository.saveAndFlush(percentage);

        // Get all the percentageList
        restPercentageMockMvc.perform(get("/api/percentages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(percentage.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierdate").value(hasItem(DEFAULT_MODIFIERDATE.toString())))
            .andExpect(jsonPath("$.[*].modifiernum").value(hasItem(DEFAULT_MODIFIERNUM.intValue())))
            .andExpect(jsonPath("$.[*].logicdelete").value(hasItem(DEFAULT_LOGICDELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())));
    }
    
    @Test
    @Transactional
    public void getPercentage() throws Exception {
        // Initialize the database
        percentageRepository.saveAndFlush(percentage);

        // Get the percentage
        restPercentageMockMvc.perform(get("/api/percentages/{id}", percentage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(percentage.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierdate").value(DEFAULT_MODIFIERDATE.toString()))
            .andExpect(jsonPath("$.modifiernum").value(DEFAULT_MODIFIERNUM.intValue()))
            .andExpect(jsonPath("$.logicdelete").value(DEFAULT_LOGICDELETE.booleanValue()))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPercentage() throws Exception {
        // Get the percentage
        restPercentageMockMvc.perform(get("/api/percentages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePercentage() throws Exception {
        // Initialize the database
        percentageRepository.saveAndFlush(percentage);

        int databaseSizeBeforeUpdate = percentageRepository.findAll().size();

        // Update the percentage
        Percentage updatedPercentage = percentageRepository.findById(percentage.getId()).get();
        // Disconnect from session so that the updates on updatedPercentage are not directly saved in db
        em.detach(updatedPercentage);
        updatedPercentage
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .value(UPDATED_VALUE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        PercentageDTO percentageDTO = percentageMapper.toDto(updatedPercentage);

        restPercentageMockMvc.perform(put("/api/percentages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(percentageDTO)))
            .andExpect(status().isOk());

        // Validate the Percentage in the database
        List<Percentage> percentageList = percentageRepository.findAll();
        assertThat(percentageList).hasSize(databaseSizeBeforeUpdate);
        Percentage testPercentage = percentageList.get(percentageList.size() - 1);
        assertThat(testPercentage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPercentage.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPercentage.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testPercentage.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPercentage.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testPercentage.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testPercentage.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testPercentage.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testPercentage.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testPercentage.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingPercentage() throws Exception {
        int databaseSizeBeforeUpdate = percentageRepository.findAll().size();

        // Create the Percentage
        PercentageDTO percentageDTO = percentageMapper.toDto(percentage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPercentageMockMvc.perform(put("/api/percentages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(percentageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Percentage in the database
        List<Percentage> percentageList = percentageRepository.findAll();
        assertThat(percentageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePercentage() throws Exception {
        // Initialize the database
        percentageRepository.saveAndFlush(percentage);

        int databaseSizeBeforeDelete = percentageRepository.findAll().size();

        // Delete the percentage
        restPercentageMockMvc.perform(delete("/api/percentages/{id}", percentage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Percentage> percentageList = percentageRepository.findAll();
        assertThat(percentageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Percentage.class);
        Percentage percentage1 = new Percentage();
        percentage1.setId(1L);
        Percentage percentage2 = new Percentage();
        percentage2.setId(percentage1.getId());
        assertThat(percentage1).isEqualTo(percentage2);
        percentage2.setId(2L);
        assertThat(percentage1).isNotEqualTo(percentage2);
        percentage1.setId(null);
        assertThat(percentage1).isNotEqualTo(percentage2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PercentageDTO.class);
        PercentageDTO percentageDTO1 = new PercentageDTO();
        percentageDTO1.setId(1L);
        PercentageDTO percentageDTO2 = new PercentageDTO();
        assertThat(percentageDTO1).isNotEqualTo(percentageDTO2);
        percentageDTO2.setId(percentageDTO1.getId());
        assertThat(percentageDTO1).isEqualTo(percentageDTO2);
        percentageDTO2.setId(2L);
        assertThat(percentageDTO1).isNotEqualTo(percentageDTO2);
        percentageDTO1.setId(null);
        assertThat(percentageDTO1).isNotEqualTo(percentageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(percentageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(percentageMapper.fromId(null)).isNull();
    }
}
