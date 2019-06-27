package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Userlocation;
import com.weisen.www.code.yjf.basic.repository.UserlocationRepository;
import com.weisen.www.code.yjf.basic.service.UserlocationService;
import com.weisen.www.code.yjf.basic.service.dto.UserlocationDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserlocationMapper;
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
 * Integration tests for the {@Link UserlocationResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class UserlocationResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_COORDINATE = "AAAAAAAAAA";
    private static final String UPDATED_COORDINATE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

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
    private UserlocationRepository userlocationRepository;

    @Autowired
    private UserlocationMapper userlocationMapper;

    @Autowired
    private UserlocationService userlocationService;

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

    private MockMvc restUserlocationMockMvc;

    private Userlocation userlocation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserlocationResource userlocationResource = new UserlocationResource(userlocationService);
        this.restUserlocationMockMvc = MockMvcBuilders.standaloneSetup(userlocationResource)
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
    public static Userlocation createEntity(EntityManager em) {
        Userlocation userlocation = new Userlocation()
            .userid(DEFAULT_USERID)
            .coordinate(DEFAULT_COORDINATE)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .county(DEFAULT_COUNTY)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return userlocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userlocation createUpdatedEntity(EntityManager em) {
        Userlocation userlocation = new Userlocation()
            .userid(UPDATED_USERID)
            .coordinate(UPDATED_COORDINATE)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .county(UPDATED_COUNTY)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return userlocation;
    }

    @BeforeEach
    public void initTest() {
        userlocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserlocation() throws Exception {
        int databaseSizeBeforeCreate = userlocationRepository.findAll().size();

        // Create the Userlocation
        UserlocationDTO userlocationDTO = userlocationMapper.toDto(userlocation);
        restUserlocationMockMvc.perform(post("/api/userlocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userlocationDTO)))
            .andExpect(status().isCreated());

        // Validate the Userlocation in the database
        List<Userlocation> userlocationList = userlocationRepository.findAll();
        assertThat(userlocationList).hasSize(databaseSizeBeforeCreate + 1);
        Userlocation testUserlocation = userlocationList.get(userlocationList.size() - 1);
        assertThat(testUserlocation.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testUserlocation.getCoordinate()).isEqualTo(DEFAULT_COORDINATE);
        assertThat(testUserlocation.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testUserlocation.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testUserlocation.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testUserlocation.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testUserlocation.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testUserlocation.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testUserlocation.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testUserlocation.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testUserlocation.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testUserlocation.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createUserlocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userlocationRepository.findAll().size();

        // Create the Userlocation with an existing ID
        userlocation.setId(1L);
        UserlocationDTO userlocationDTO = userlocationMapper.toDto(userlocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserlocationMockMvc.perform(post("/api/userlocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userlocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userlocation in the database
        List<Userlocation> userlocationList = userlocationRepository.findAll();
        assertThat(userlocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserlocations() throws Exception {
        // Initialize the database
        userlocationRepository.saveAndFlush(userlocation);

        // Get all the userlocationList
        restUserlocationMockMvc.perform(get("/api/userlocations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userlocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].coordinate").value(hasItem(DEFAULT_COORDINATE.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY.toString())))
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
    public void getUserlocation() throws Exception {
        // Initialize the database
        userlocationRepository.saveAndFlush(userlocation);

        // Get the userlocation
        restUserlocationMockMvc.perform(get("/api/userlocations/{id}", userlocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userlocation.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.coordinate").value(DEFAULT_COORDINATE.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY.toString()))
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
    public void getNonExistingUserlocation() throws Exception {
        // Get the userlocation
        restUserlocationMockMvc.perform(get("/api/userlocations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserlocation() throws Exception {
        // Initialize the database
        userlocationRepository.saveAndFlush(userlocation);

        int databaseSizeBeforeUpdate = userlocationRepository.findAll().size();

        // Update the userlocation
        Userlocation updatedUserlocation = userlocationRepository.findById(userlocation.getId()).get();
        // Disconnect from session so that the updates on updatedUserlocation are not directly saved in db
        em.detach(updatedUserlocation);
        updatedUserlocation
            .userid(UPDATED_USERID)
            .coordinate(UPDATED_COORDINATE)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .county(UPDATED_COUNTY)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        UserlocationDTO userlocationDTO = userlocationMapper.toDto(updatedUserlocation);

        restUserlocationMockMvc.perform(put("/api/userlocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userlocationDTO)))
            .andExpect(status().isOk());

        // Validate the Userlocation in the database
        List<Userlocation> userlocationList = userlocationRepository.findAll();
        assertThat(userlocationList).hasSize(databaseSizeBeforeUpdate);
        Userlocation testUserlocation = userlocationList.get(userlocationList.size() - 1);
        assertThat(testUserlocation.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testUserlocation.getCoordinate()).isEqualTo(UPDATED_COORDINATE);
        assertThat(testUserlocation.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testUserlocation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserlocation.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testUserlocation.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testUserlocation.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testUserlocation.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testUserlocation.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testUserlocation.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testUserlocation.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testUserlocation.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingUserlocation() throws Exception {
        int databaseSizeBeforeUpdate = userlocationRepository.findAll().size();

        // Create the Userlocation
        UserlocationDTO userlocationDTO = userlocationMapper.toDto(userlocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserlocationMockMvc.perform(put("/api/userlocations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userlocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userlocation in the database
        List<Userlocation> userlocationList = userlocationRepository.findAll();
        assertThat(userlocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserlocation() throws Exception {
        // Initialize the database
        userlocationRepository.saveAndFlush(userlocation);

        int databaseSizeBeforeDelete = userlocationRepository.findAll().size();

        // Delete the userlocation
        restUserlocationMockMvc.perform(delete("/api/userlocations/{id}", userlocation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Userlocation> userlocationList = userlocationRepository.findAll();
        assertThat(userlocationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Userlocation.class);
        Userlocation userlocation1 = new Userlocation();
        userlocation1.setId(1L);
        Userlocation userlocation2 = new Userlocation();
        userlocation2.setId(userlocation1.getId());
        assertThat(userlocation1).isEqualTo(userlocation2);
        userlocation2.setId(2L);
        assertThat(userlocation1).isNotEqualTo(userlocation2);
        userlocation1.setId(null);
        assertThat(userlocation1).isNotEqualTo(userlocation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserlocationDTO.class);
        UserlocationDTO userlocationDTO1 = new UserlocationDTO();
        userlocationDTO1.setId(1L);
        UserlocationDTO userlocationDTO2 = new UserlocationDTO();
        assertThat(userlocationDTO1).isNotEqualTo(userlocationDTO2);
        userlocationDTO2.setId(userlocationDTO1.getId());
        assertThat(userlocationDTO1).isEqualTo(userlocationDTO2);
        userlocationDTO2.setId(2L);
        assertThat(userlocationDTO1).isNotEqualTo(userlocationDTO2);
        userlocationDTO1.setId(null);
        assertThat(userlocationDTO1).isNotEqualTo(userlocationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userlocationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userlocationMapper.fromId(null)).isNull();
    }
}
