package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.UserlinkuserService;
import com.weisen.www.code.yjf.basic.service.dto.UserlinkuserDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserlinkuserMapper;
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
 * Integration tests for the {@Link UserlinkuserResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class UserlinkuserResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMMENDID = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMENDID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PARTNER = false;
    private static final Boolean UPDATED_PARTNER = true;

    private static final Boolean DEFAULT_PROVINCE = false;
    private static final Boolean UPDATED_PROVINCE = true;

    private static final Boolean DEFAULT_CITY = false;
    private static final Boolean UPDATED_CITY = true;

    private static final Boolean DEFAULT_COUNTY = false;
    private static final Boolean UPDATED_COUNTY = true;

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
    private UserlinkuserRepository userlinkuserRepository;

    @Autowired
    private UserlinkuserMapper userlinkuserMapper;

    @Autowired
    private UserlinkuserService userlinkuserService;

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

    private MockMvc restUserlinkuserMockMvc;

    private Userlinkuser userlinkuser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserlinkuserResource userlinkuserResource = new UserlinkuserResource(userlinkuserService);
        this.restUserlinkuserMockMvc = MockMvcBuilders.standaloneSetup(userlinkuserResource)
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
    public static Userlinkuser createEntity(EntityManager em) {
        Userlinkuser userlinkuser = new Userlinkuser()
            .userid(DEFAULT_USERID)
            .recommendid(DEFAULT_RECOMMENDID)
            .partner(DEFAULT_PARTNER)
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
        return userlinkuser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userlinkuser createUpdatedEntity(EntityManager em) {
        Userlinkuser userlinkuser = new Userlinkuser()
            .userid(UPDATED_USERID)
            .recommendid(UPDATED_RECOMMENDID)
            .partner(UPDATED_PARTNER)
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
        return userlinkuser;
    }

    @BeforeEach
    public void initTest() {
        userlinkuser = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserlinkuser() throws Exception {
        int databaseSizeBeforeCreate = userlinkuserRepository.findAll().size();

        // Create the Userlinkuser
        UserlinkuserDTO userlinkuserDTO = userlinkuserMapper.toDto(userlinkuser);
        restUserlinkuserMockMvc.perform(post("/api/userlinkusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userlinkuserDTO)))
            .andExpect(status().isCreated());

        // Validate the Userlinkuser in the database
        List<Userlinkuser> userlinkuserList = userlinkuserRepository.findAll();
        assertThat(userlinkuserList).hasSize(databaseSizeBeforeCreate + 1);
        Userlinkuser testUserlinkuser = userlinkuserList.get(userlinkuserList.size() - 1);
        assertThat(testUserlinkuser.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testUserlinkuser.getRecommendid()).isEqualTo(DEFAULT_RECOMMENDID);
        assertThat(testUserlinkuser.isPartner()).isEqualTo(DEFAULT_PARTNER);
        assertThat(testUserlinkuser.isProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testUserlinkuser.isCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testUserlinkuser.isCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testUserlinkuser.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testUserlinkuser.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testUserlinkuser.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testUserlinkuser.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testUserlinkuser.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testUserlinkuser.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testUserlinkuser.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createUserlinkuserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userlinkuserRepository.findAll().size();

        // Create the Userlinkuser with an existing ID
        userlinkuser.setId(1L);
        UserlinkuserDTO userlinkuserDTO = userlinkuserMapper.toDto(userlinkuser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserlinkuserMockMvc.perform(post("/api/userlinkusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userlinkuserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userlinkuser in the database
        List<Userlinkuser> userlinkuserList = userlinkuserRepository.findAll();
        assertThat(userlinkuserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserlinkusers() throws Exception {
        // Initialize the database
        userlinkuserRepository.saveAndFlush(userlinkuser);

        // Get all the userlinkuserList
        restUserlinkuserMockMvc.perform(get("/api/userlinkusers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userlinkuser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].recommendid").value(hasItem(DEFAULT_RECOMMENDID.toString())))
            .andExpect(jsonPath("$.[*].partner").value(hasItem(DEFAULT_PARTNER.booleanValue())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.booleanValue())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.booleanValue())))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY.booleanValue())))
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
    public void getUserlinkuser() throws Exception {
        // Initialize the database
        userlinkuserRepository.saveAndFlush(userlinkuser);

        // Get the userlinkuser
        restUserlinkuserMockMvc.perform(get("/api/userlinkusers/{id}", userlinkuser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userlinkuser.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.recommendid").value(DEFAULT_RECOMMENDID.toString()))
            .andExpect(jsonPath("$.partner").value(DEFAULT_PARTNER.booleanValue()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.booleanValue()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.booleanValue()))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY.booleanValue()))
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
    public void getNonExistingUserlinkuser() throws Exception {
        // Get the userlinkuser
        restUserlinkuserMockMvc.perform(get("/api/userlinkusers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserlinkuser() throws Exception {
        // Initialize the database
        userlinkuserRepository.saveAndFlush(userlinkuser);

        int databaseSizeBeforeUpdate = userlinkuserRepository.findAll().size();

        // Update the userlinkuser
        Userlinkuser updatedUserlinkuser = userlinkuserRepository.findById(userlinkuser.getId()).get();
        // Disconnect from session so that the updates on updatedUserlinkuser are not directly saved in db
        em.detach(updatedUserlinkuser);
        updatedUserlinkuser
            .userid(UPDATED_USERID)
            .recommendid(UPDATED_RECOMMENDID)
            .partner(UPDATED_PARTNER)
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
        UserlinkuserDTO userlinkuserDTO = userlinkuserMapper.toDto(updatedUserlinkuser);

        restUserlinkuserMockMvc.perform(put("/api/userlinkusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userlinkuserDTO)))
            .andExpect(status().isOk());

        // Validate the Userlinkuser in the database
        List<Userlinkuser> userlinkuserList = userlinkuserRepository.findAll();
        assertThat(userlinkuserList).hasSize(databaseSizeBeforeUpdate);
        Userlinkuser testUserlinkuser = userlinkuserList.get(userlinkuserList.size() - 1);
        assertThat(testUserlinkuser.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testUserlinkuser.getRecommendid()).isEqualTo(UPDATED_RECOMMENDID);
        assertThat(testUserlinkuser.isPartner()).isEqualTo(UPDATED_PARTNER);
        assertThat(testUserlinkuser.isProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testUserlinkuser.isCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUserlinkuser.isCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testUserlinkuser.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testUserlinkuser.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testUserlinkuser.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testUserlinkuser.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testUserlinkuser.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testUserlinkuser.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testUserlinkuser.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingUserlinkuser() throws Exception {
        int databaseSizeBeforeUpdate = userlinkuserRepository.findAll().size();

        // Create the Userlinkuser
        UserlinkuserDTO userlinkuserDTO = userlinkuserMapper.toDto(userlinkuser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserlinkuserMockMvc.perform(put("/api/userlinkusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userlinkuserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userlinkuser in the database
        List<Userlinkuser> userlinkuserList = userlinkuserRepository.findAll();
        assertThat(userlinkuserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserlinkuser() throws Exception {
        // Initialize the database
        userlinkuserRepository.saveAndFlush(userlinkuser);

        int databaseSizeBeforeDelete = userlinkuserRepository.findAll().size();

        // Delete the userlinkuser
        restUserlinkuserMockMvc.perform(delete("/api/userlinkusers/{id}", userlinkuser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Userlinkuser> userlinkuserList = userlinkuserRepository.findAll();
        assertThat(userlinkuserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Userlinkuser.class);
        Userlinkuser userlinkuser1 = new Userlinkuser();
        userlinkuser1.setId(1L);
        Userlinkuser userlinkuser2 = new Userlinkuser();
        userlinkuser2.setId(userlinkuser1.getId());
        assertThat(userlinkuser1).isEqualTo(userlinkuser2);
        userlinkuser2.setId(2L);
        assertThat(userlinkuser1).isNotEqualTo(userlinkuser2);
        userlinkuser1.setId(null);
        assertThat(userlinkuser1).isNotEqualTo(userlinkuser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserlinkuserDTO.class);
        UserlinkuserDTO userlinkuserDTO1 = new UserlinkuserDTO();
        userlinkuserDTO1.setId(1L);
        UserlinkuserDTO userlinkuserDTO2 = new UserlinkuserDTO();
        assertThat(userlinkuserDTO1).isNotEqualTo(userlinkuserDTO2);
        userlinkuserDTO2.setId(userlinkuserDTO1.getId());
        assertThat(userlinkuserDTO1).isEqualTo(userlinkuserDTO2);
        userlinkuserDTO2.setId(2L);
        assertThat(userlinkuserDTO1).isNotEqualTo(userlinkuserDTO2);
        userlinkuserDTO1.setId(null);
        assertThat(userlinkuserDTO1).isNotEqualTo(userlinkuserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userlinkuserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userlinkuserMapper.fromId(null)).isNull();
    }
}
