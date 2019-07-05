package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Coderecord;
import com.weisen.www.code.yjf.basic.repository.CoderecordRepository;
import com.weisen.www.code.yjf.basic.service.CoderecordService;
import com.weisen.www.code.yjf.basic.service.dto.CoderecordDTO;
import com.weisen.www.code.yjf.basic.service.mapper.CoderecordMapper;
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
 * Integration tests for the {@Link CoderecordResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class CoderecordResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM = 1;
    private static final Integer UPDATED_NUM = 2;

    @Autowired
    private CoderecordRepository coderecordRepository;

    @Autowired
    private CoderecordMapper coderecordMapper;

    @Autowired
    private CoderecordService coderecordService;

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

    private MockMvc restCoderecordMockMvc;

    private Coderecord coderecord;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoderecordResource coderecordResource = new CoderecordResource(coderecordService);
        this.restCoderecordMockMvc = MockMvcBuilders.standaloneSetup(coderecordResource)
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
    public static Coderecord createEntity(EntityManager em) {
        Coderecord coderecord = new Coderecord()
            .code(DEFAULT_CODE)
            .type(DEFAULT_TYPE)
            .phone(DEFAULT_PHONE)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE)
            .num(DEFAULT_NUM);
        return coderecord;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Coderecord createUpdatedEntity(EntityManager em) {
        Coderecord coderecord = new Coderecord()
            .code(UPDATED_CODE)
            .type(UPDATED_TYPE)
            .phone(UPDATED_PHONE)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .num(UPDATED_NUM);
        return coderecord;
    }

    @BeforeEach
    public void initTest() {
        coderecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoderecord() throws Exception {
        int databaseSizeBeforeCreate = coderecordRepository.findAll().size();

        // Create the Coderecord
        CoderecordDTO coderecordDTO = coderecordMapper.toDto(coderecord);
        restCoderecordMockMvc.perform(post("/api/coderecords")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coderecordDTO)))
            .andExpect(status().isCreated());

        // Validate the Coderecord in the database
        List<Coderecord> coderecordList = coderecordRepository.findAll();
        assertThat(coderecordList).hasSize(databaseSizeBeforeCreate + 1);
        Coderecord testCoderecord = coderecordList.get(coderecordList.size() - 1);
        assertThat(testCoderecord.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCoderecord.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCoderecord.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testCoderecord.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testCoderecord.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testCoderecord.getNum()).isEqualTo(DEFAULT_NUM);
    }

    @Test
    @Transactional
    public void createCoderecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coderecordRepository.findAll().size();

        // Create the Coderecord with an existing ID
        coderecord.setId(1L);
        CoderecordDTO coderecordDTO = coderecordMapper.toDto(coderecord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoderecordMockMvc.perform(post("/api/coderecords")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coderecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Coderecord in the database
        List<Coderecord> coderecordList = coderecordRepository.findAll();
        assertThat(coderecordList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCoderecords() throws Exception {
        // Initialize the database
        coderecordRepository.saveAndFlush(coderecord);

        // Get all the coderecordList
        restCoderecordMockMvc.perform(get("/api/coderecords?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coderecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)));
    }
    
    @Test
    @Transactional
    public void getCoderecord() throws Exception {
        // Initialize the database
        coderecordRepository.saveAndFlush(coderecord);

        // Get the coderecord
        restCoderecordMockMvc.perform(get("/api/coderecords/{id}", coderecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coderecord.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM));
    }

    @Test
    @Transactional
    public void getNonExistingCoderecord() throws Exception {
        // Get the coderecord
        restCoderecordMockMvc.perform(get("/api/coderecords/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoderecord() throws Exception {
        // Initialize the database
        coderecordRepository.saveAndFlush(coderecord);

        int databaseSizeBeforeUpdate = coderecordRepository.findAll().size();

        // Update the coderecord
        Coderecord updatedCoderecord = coderecordRepository.findById(coderecord.getId()).get();
        // Disconnect from session so that the updates on updatedCoderecord are not directly saved in db
        em.detach(updatedCoderecord);
        updatedCoderecord
            .code(UPDATED_CODE)
            .type(UPDATED_TYPE)
            .phone(UPDATED_PHONE)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .num(UPDATED_NUM);
        CoderecordDTO coderecordDTO = coderecordMapper.toDto(updatedCoderecord);

        restCoderecordMockMvc.perform(put("/api/coderecords")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coderecordDTO)))
            .andExpect(status().isOk());

        // Validate the Coderecord in the database
        List<Coderecord> coderecordList = coderecordRepository.findAll();
        assertThat(coderecordList).hasSize(databaseSizeBeforeUpdate);
        Coderecord testCoderecord = coderecordList.get(coderecordList.size() - 1);
        assertThat(testCoderecord.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCoderecord.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCoderecord.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testCoderecord.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testCoderecord.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testCoderecord.getNum()).isEqualTo(UPDATED_NUM);
    }

    @Test
    @Transactional
    public void updateNonExistingCoderecord() throws Exception {
        int databaseSizeBeforeUpdate = coderecordRepository.findAll().size();

        // Create the Coderecord
        CoderecordDTO coderecordDTO = coderecordMapper.toDto(coderecord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoderecordMockMvc.perform(put("/api/coderecords")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coderecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Coderecord in the database
        List<Coderecord> coderecordList = coderecordRepository.findAll();
        assertThat(coderecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoderecord() throws Exception {
        // Initialize the database
        coderecordRepository.saveAndFlush(coderecord);

        int databaseSizeBeforeDelete = coderecordRepository.findAll().size();

        // Delete the coderecord
        restCoderecordMockMvc.perform(delete("/api/coderecords/{id}", coderecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Coderecord> coderecordList = coderecordRepository.findAll();
        assertThat(coderecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coderecord.class);
        Coderecord coderecord1 = new Coderecord();
        coderecord1.setId(1L);
        Coderecord coderecord2 = new Coderecord();
        coderecord2.setId(coderecord1.getId());
        assertThat(coderecord1).isEqualTo(coderecord2);
        coderecord2.setId(2L);
        assertThat(coderecord1).isNotEqualTo(coderecord2);
        coderecord1.setId(null);
        assertThat(coderecord1).isNotEqualTo(coderecord2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoderecordDTO.class);
        CoderecordDTO coderecordDTO1 = new CoderecordDTO();
        coderecordDTO1.setId(1L);
        CoderecordDTO coderecordDTO2 = new CoderecordDTO();
        assertThat(coderecordDTO1).isNotEqualTo(coderecordDTO2);
        coderecordDTO2.setId(coderecordDTO1.getId());
        assertThat(coderecordDTO1).isEqualTo(coderecordDTO2);
        coderecordDTO2.setId(2L);
        assertThat(coderecordDTO1).isNotEqualTo(coderecordDTO2);
        coderecordDTO1.setId(null);
        assertThat(coderecordDTO1).isNotEqualTo(coderecordDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(coderecordMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(coderecordMapper.fromId(null)).isNull();
    }
}
