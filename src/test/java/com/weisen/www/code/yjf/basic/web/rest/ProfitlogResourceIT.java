package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Profitlog;
import com.weisen.www.code.yjf.basic.repository.ProfitlogRepository;
import com.weisen.www.code.yjf.basic.service.ProfitlogService;
import com.weisen.www.code.yjf.basic.service.dto.ProfitlogDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ProfitlogMapper;
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
 * Integration tests for the {@Link ProfitlogResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class ProfitlogResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_CONSUMER = "AAAAAAAAAA";
    private static final String UPDATED_CONSUMER = "BBBBBBBBBB";

    private static final String DEFAULT_APPEARAMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_APPEARAMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_APPEARDATE = "AAAAAAAAAA";
    private static final String UPDATED_APPEARDATE = "BBBBBBBBBB";

    private static final String DEFAULT_FROZENDATE = "AAAAAAAAAA";
    private static final String UPDATED_FROZENDATE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFITTYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROFITTYPE = "BBBBBBBBBB";

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
    private ProfitlogRepository profitlogRepository;

    @Autowired
    private ProfitlogMapper profitlogMapper;

    @Autowired
    private ProfitlogService profitlogService;

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

    private MockMvc restProfitlogMockMvc;

    private Profitlog profitlog;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfitlogResource profitlogResource = new ProfitlogResource(profitlogService);
        this.restProfitlogMockMvc = MockMvcBuilders.standaloneSetup(profitlogResource)
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
    public static Profitlog createEntity(EntityManager em) {
        Profitlog profitlog = new Profitlog()
            .userid(DEFAULT_USERID)
            .consumer(DEFAULT_CONSUMER)
            .appearamount(DEFAULT_APPEARAMOUNT)
            .appeardate(DEFAULT_APPEARDATE)
            .frozendate(DEFAULT_FROZENDATE)
            .profittype(DEFAULT_PROFITTYPE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return profitlog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Profitlog createUpdatedEntity(EntityManager em) {
        Profitlog profitlog = new Profitlog()
            .userid(UPDATED_USERID)
            .consumer(UPDATED_CONSUMER)
            .appearamount(UPDATED_APPEARAMOUNT)
            .appeardate(UPDATED_APPEARDATE)
            .frozendate(UPDATED_FROZENDATE)
            .profittype(UPDATED_PROFITTYPE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return profitlog;
    }

    @BeforeEach
    public void initTest() {
        profitlog = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfitlog() throws Exception {
        int databaseSizeBeforeCreate = profitlogRepository.findAll().size();

        // Create the Profitlog
        ProfitlogDTO profitlogDTO = profitlogMapper.toDto(profitlog);
        restProfitlogMockMvc.perform(post("/api/profitlogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profitlogDTO)))
            .andExpect(status().isCreated());

        // Validate the Profitlog in the database
        List<Profitlog> profitlogList = profitlogRepository.findAll();
        assertThat(profitlogList).hasSize(databaseSizeBeforeCreate + 1);
        Profitlog testProfitlog = profitlogList.get(profitlogList.size() - 1);
        assertThat(testProfitlog.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testProfitlog.getConsumer()).isEqualTo(DEFAULT_CONSUMER);
        assertThat(testProfitlog.getAppearamount()).isEqualTo(DEFAULT_APPEARAMOUNT);
        assertThat(testProfitlog.getAppeardate()).isEqualTo(DEFAULT_APPEARDATE);
        assertThat(testProfitlog.getFrozendate()).isEqualTo(DEFAULT_FROZENDATE);
        assertThat(testProfitlog.getProfittype()).isEqualTo(DEFAULT_PROFITTYPE);
        assertThat(testProfitlog.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testProfitlog.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testProfitlog.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testProfitlog.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testProfitlog.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testProfitlog.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testProfitlog.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createProfitlogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profitlogRepository.findAll().size();

        // Create the Profitlog with an existing ID
        profitlog.setId(1L);
        ProfitlogDTO profitlogDTO = profitlogMapper.toDto(profitlog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfitlogMockMvc.perform(post("/api/profitlogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profitlogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profitlog in the database
        List<Profitlog> profitlogList = profitlogRepository.findAll();
        assertThat(profitlogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProfitlogs() throws Exception {
        // Initialize the database
        profitlogRepository.saveAndFlush(profitlog);

        // Get all the profitlogList
        restProfitlogMockMvc.perform(get("/api/profitlogs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profitlog.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].consumer").value(hasItem(DEFAULT_CONSUMER.toString())))
            .andExpect(jsonPath("$.[*].appearamount").value(hasItem(DEFAULT_APPEARAMOUNT.toString())))
            .andExpect(jsonPath("$.[*].appeardate").value(hasItem(DEFAULT_APPEARDATE.toString())))
            .andExpect(jsonPath("$.[*].frozendate").value(hasItem(DEFAULT_FROZENDATE.toString())))
            .andExpect(jsonPath("$.[*].profittype").value(hasItem(DEFAULT_PROFITTYPE.toString())))
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
    public void getProfitlog() throws Exception {
        // Initialize the database
        profitlogRepository.saveAndFlush(profitlog);

        // Get the profitlog
        restProfitlogMockMvc.perform(get("/api/profitlogs/{id}", profitlog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profitlog.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.consumer").value(DEFAULT_CONSUMER.toString()))
            .andExpect(jsonPath("$.appearamount").value(DEFAULT_APPEARAMOUNT.toString()))
            .andExpect(jsonPath("$.appeardate").value(DEFAULT_APPEARDATE.toString()))
            .andExpect(jsonPath("$.frozendate").value(DEFAULT_FROZENDATE.toString()))
            .andExpect(jsonPath("$.profittype").value(DEFAULT_PROFITTYPE.toString()))
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
    public void getNonExistingProfitlog() throws Exception {
        // Get the profitlog
        restProfitlogMockMvc.perform(get("/api/profitlogs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfitlog() throws Exception {
        // Initialize the database
        profitlogRepository.saveAndFlush(profitlog);

        int databaseSizeBeforeUpdate = profitlogRepository.findAll().size();

        // Update the profitlog
        Profitlog updatedProfitlog = profitlogRepository.findById(profitlog.getId()).get();
        // Disconnect from session so that the updates on updatedProfitlog are not directly saved in db
        em.detach(updatedProfitlog);
        updatedProfitlog
            .userid(UPDATED_USERID)
            .consumer(UPDATED_CONSUMER)
            .appearamount(UPDATED_APPEARAMOUNT)
            .appeardate(UPDATED_APPEARDATE)
            .frozendate(UPDATED_FROZENDATE)
            .profittype(UPDATED_PROFITTYPE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        ProfitlogDTO profitlogDTO = profitlogMapper.toDto(updatedProfitlog);

        restProfitlogMockMvc.perform(put("/api/profitlogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profitlogDTO)))
            .andExpect(status().isOk());

        // Validate the Profitlog in the database
        List<Profitlog> profitlogList = profitlogRepository.findAll();
        assertThat(profitlogList).hasSize(databaseSizeBeforeUpdate);
        Profitlog testProfitlog = profitlogList.get(profitlogList.size() - 1);
        assertThat(testProfitlog.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testProfitlog.getConsumer()).isEqualTo(UPDATED_CONSUMER);
        assertThat(testProfitlog.getAppearamount()).isEqualTo(UPDATED_APPEARAMOUNT);
        assertThat(testProfitlog.getAppeardate()).isEqualTo(UPDATED_APPEARDATE);
        assertThat(testProfitlog.getFrozendate()).isEqualTo(UPDATED_FROZENDATE);
        assertThat(testProfitlog.getProfittype()).isEqualTo(UPDATED_PROFITTYPE);
        assertThat(testProfitlog.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testProfitlog.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testProfitlog.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testProfitlog.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testProfitlog.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testProfitlog.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testProfitlog.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingProfitlog() throws Exception {
        int databaseSizeBeforeUpdate = profitlogRepository.findAll().size();

        // Create the Profitlog
        ProfitlogDTO profitlogDTO = profitlogMapper.toDto(profitlog);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfitlogMockMvc.perform(put("/api/profitlogs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profitlogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Profitlog in the database
        List<Profitlog> profitlogList = profitlogRepository.findAll();
        assertThat(profitlogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfitlog() throws Exception {
        // Initialize the database
        profitlogRepository.saveAndFlush(profitlog);

        int databaseSizeBeforeDelete = profitlogRepository.findAll().size();

        // Delete the profitlog
        restProfitlogMockMvc.perform(delete("/api/profitlogs/{id}", profitlog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Profitlog> profitlogList = profitlogRepository.findAll();
        assertThat(profitlogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Profitlog.class);
        Profitlog profitlog1 = new Profitlog();
        profitlog1.setId(1L);
        Profitlog profitlog2 = new Profitlog();
        profitlog2.setId(profitlog1.getId());
        assertThat(profitlog1).isEqualTo(profitlog2);
        profitlog2.setId(2L);
        assertThat(profitlog1).isNotEqualTo(profitlog2);
        profitlog1.setId(null);
        assertThat(profitlog1).isNotEqualTo(profitlog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfitlogDTO.class);
        ProfitlogDTO profitlogDTO1 = new ProfitlogDTO();
        profitlogDTO1.setId(1L);
        ProfitlogDTO profitlogDTO2 = new ProfitlogDTO();
        assertThat(profitlogDTO1).isNotEqualTo(profitlogDTO2);
        profitlogDTO2.setId(profitlogDTO1.getId());
        assertThat(profitlogDTO1).isEqualTo(profitlogDTO2);
        profitlogDTO2.setId(2L);
        assertThat(profitlogDTO1).isNotEqualTo(profitlogDTO2);
        profitlogDTO1.setId(null);
        assertThat(profitlogDTO1).isNotEqualTo(profitlogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(profitlogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(profitlogMapper.fromId(null)).isNull();
    }
}
