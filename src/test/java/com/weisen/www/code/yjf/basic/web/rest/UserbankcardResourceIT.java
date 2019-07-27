package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Userbankcard;
import com.weisen.www.code.yjf.basic.repository.UserbankcardRepository;
import com.weisen.www.code.yjf.basic.service.UserbankcardService;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserbankcardMapper;
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
 * Integration tests for the {@Link UserbankcardResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class UserbankcardResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_REALNAME = "AAAAAAAAAA";
    private static final String UPDATED_REALNAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANKCARD = "AAAAAAAAAA";
    private static final String UPDATED_BANKCARD = "BBBBBBBBBB";

    private static final String DEFAULT_BANKTYPE = "AAAAAAAAAA";
    private static final String UPDATED_BANKTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CARDTYPE = "AAAAAAAAAA";
    private static final String UPDATED_CARDTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BANKICON = "AAAAAAAAAA";
    private static final String UPDATED_BANKICON = "BBBBBBBBBB";

    private static final String DEFAULT_BANKPHONE = "AAAAAAAAAA";
    private static final String UPDATED_BANKPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDATE = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIERDATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIERDATE = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIERNUM = 1L;
    private static final Long UPDATED_MODIFIERNUM = 2L;

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LOGICDELETE = false;
    private static final Boolean UPDATED_LOGICDELETE = true;

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    @Autowired
    private UserbankcardRepository userbankcardRepository;

    @Autowired
    private UserbankcardMapper userbankcardMapper;

    @Autowired
    private UserbankcardService userbankcardService;

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

    private MockMvc restUserbankcardMockMvc;

    private Userbankcard userbankcard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserbankcardResource userbankcardResource = new UserbankcardResource(userbankcardService);
        this.restUserbankcardMockMvc = MockMvcBuilders.standaloneSetup(userbankcardResource)
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
    public static Userbankcard createEntity(EntityManager em) {
        Userbankcard userbankcard = new Userbankcard()
            .userid(DEFAULT_USERID)
            .realname(DEFAULT_REALNAME)
            .bankcard(DEFAULT_BANKCARD)
            .banktype(DEFAULT_BANKTYPE)
            .cardtype(DEFAULT_CARDTYPE)
            .bankicon(DEFAULT_BANKICON)
            .bankphone(DEFAULT_BANKPHONE)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .state(DEFAULT_STATE)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return userbankcard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userbankcard createUpdatedEntity(EntityManager em) {
        Userbankcard userbankcard = new Userbankcard()
            .userid(UPDATED_USERID)
            .realname(UPDATED_REALNAME)
            .bankcard(UPDATED_BANKCARD)
            .banktype(UPDATED_BANKTYPE)
            .cardtype(UPDATED_CARDTYPE)
            .bankicon(UPDATED_BANKICON)
            .bankphone(UPDATED_BANKPHONE)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .state(UPDATED_STATE)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return userbankcard;
    }

    @BeforeEach
    public void initTest() {
        userbankcard = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserbankcard() throws Exception {
        int databaseSizeBeforeCreate = userbankcardRepository.findAll().size();

        // Create the Userbankcard
        UserbankcardDTO userbankcardDTO = userbankcardMapper.toDto(userbankcard);
        restUserbankcardMockMvc.perform(post("/api/userbankcards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userbankcardDTO)))
            .andExpect(status().isCreated());

        // Validate the Userbankcard in the database
        List<Userbankcard> userbankcardList = userbankcardRepository.findAll();
        assertThat(userbankcardList).hasSize(databaseSizeBeforeCreate + 1);
        Userbankcard testUserbankcard = userbankcardList.get(userbankcardList.size() - 1);
        assertThat(testUserbankcard.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testUserbankcard.getRealname()).isEqualTo(DEFAULT_REALNAME);
        assertThat(testUserbankcard.getBankcard()).isEqualTo(DEFAULT_BANKCARD);
        assertThat(testUserbankcard.getBanktype()).isEqualTo(DEFAULT_BANKTYPE);
        assertThat(testUserbankcard.getCardtype()).isEqualTo(DEFAULT_CARDTYPE);
        assertThat(testUserbankcard.getBankicon()).isEqualTo(DEFAULT_BANKICON);
        assertThat(testUserbankcard.getBankphone()).isEqualTo(DEFAULT_BANKPHONE);
        assertThat(testUserbankcard.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testUserbankcard.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testUserbankcard.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testUserbankcard.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testUserbankcard.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testUserbankcard.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testUserbankcard.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createUserbankcardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userbankcardRepository.findAll().size();

        // Create the Userbankcard with an existing ID
        userbankcard.setId(1L);
        UserbankcardDTO userbankcardDTO = userbankcardMapper.toDto(userbankcard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserbankcardMockMvc.perform(post("/api/userbankcards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userbankcardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userbankcard in the database
        List<Userbankcard> userbankcardList = userbankcardRepository.findAll();
        assertThat(userbankcardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserbankcards() throws Exception {
        // Initialize the database
        userbankcardRepository.saveAndFlush(userbankcard);

        // Get all the userbankcardList
        restUserbankcardMockMvc.perform(get("/api/userbankcards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userbankcard.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].realname").value(hasItem(DEFAULT_REALNAME.toString())))
            .andExpect(jsonPath("$.[*].bankcard").value(hasItem(DEFAULT_BANKCARD.toString())))
            .andExpect(jsonPath("$.[*].banktype").value(hasItem(DEFAULT_BANKTYPE.toString())))
            .andExpect(jsonPath("$.[*].cardtype").value(hasItem(DEFAULT_CARDTYPE.toString())))
            .andExpect(jsonPath("$.[*].bankicon").value(hasItem(DEFAULT_BANKICON.toString())))
            .andExpect(jsonPath("$.[*].bankphone").value(hasItem(DEFAULT_BANKPHONE.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierdate").value(hasItem(DEFAULT_MODIFIERDATE.toString())))
            .andExpect(jsonPath("$.[*].modifiernum").value(hasItem(DEFAULT_MODIFIERNUM.intValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].logicdelete").value(hasItem(DEFAULT_LOGICDELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())));
    }
    
    @Test
    @Transactional
    public void getUserbankcard() throws Exception {
        // Initialize the database
        userbankcardRepository.saveAndFlush(userbankcard);

        // Get the userbankcard
        restUserbankcardMockMvc.perform(get("/api/userbankcards/{id}", userbankcard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userbankcard.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.realname").value(DEFAULT_REALNAME.toString()))
            .andExpect(jsonPath("$.bankcard").value(DEFAULT_BANKCARD.toString()))
            .andExpect(jsonPath("$.banktype").value(DEFAULT_BANKTYPE.toString()))
            .andExpect(jsonPath("$.cardtype").value(DEFAULT_CARDTYPE.toString()))
            .andExpect(jsonPath("$.bankicon").value(DEFAULT_BANKICON.toString()))
            .andExpect(jsonPath("$.bankphone").value(DEFAULT_BANKPHONE.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierdate").value(DEFAULT_MODIFIERDATE.toString()))
            .andExpect(jsonPath("$.modifiernum").value(DEFAULT_MODIFIERNUM.intValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.logicdelete").value(DEFAULT_LOGICDELETE.booleanValue()))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserbankcard() throws Exception {
        // Get the userbankcard
        restUserbankcardMockMvc.perform(get("/api/userbankcards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserbankcard() throws Exception {
        // Initialize the database
        userbankcardRepository.saveAndFlush(userbankcard);

        int databaseSizeBeforeUpdate = userbankcardRepository.findAll().size();

        // Update the userbankcard
        Userbankcard updatedUserbankcard = userbankcardRepository.findById(userbankcard.getId()).get();
        // Disconnect from session so that the updates on updatedUserbankcard are not directly saved in db
        em.detach(updatedUserbankcard);
        updatedUserbankcard
            .userid(UPDATED_USERID)
            .realname(UPDATED_REALNAME)
            .bankcard(UPDATED_BANKCARD)
            .banktype(UPDATED_BANKTYPE)
            .cardtype(UPDATED_CARDTYPE)
            .bankicon(UPDATED_BANKICON)
            .bankphone(UPDATED_BANKPHONE)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .state(UPDATED_STATE)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        UserbankcardDTO userbankcardDTO = userbankcardMapper.toDto(updatedUserbankcard);

        restUserbankcardMockMvc.perform(put("/api/userbankcards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userbankcardDTO)))
            .andExpect(status().isOk());

        // Validate the Userbankcard in the database
        List<Userbankcard> userbankcardList = userbankcardRepository.findAll();
        assertThat(userbankcardList).hasSize(databaseSizeBeforeUpdate);
        Userbankcard testUserbankcard = userbankcardList.get(userbankcardList.size() - 1);
        assertThat(testUserbankcard.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testUserbankcard.getRealname()).isEqualTo(UPDATED_REALNAME);
        assertThat(testUserbankcard.getBankcard()).isEqualTo(UPDATED_BANKCARD);
        assertThat(testUserbankcard.getBanktype()).isEqualTo(UPDATED_BANKTYPE);
        assertThat(testUserbankcard.getCardtype()).isEqualTo(UPDATED_CARDTYPE);
        assertThat(testUserbankcard.getBankicon()).isEqualTo(UPDATED_BANKICON);
        assertThat(testUserbankcard.getBankphone()).isEqualTo(UPDATED_BANKPHONE);
        assertThat(testUserbankcard.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testUserbankcard.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testUserbankcard.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testUserbankcard.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testUserbankcard.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testUserbankcard.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testUserbankcard.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingUserbankcard() throws Exception {
        int databaseSizeBeforeUpdate = userbankcardRepository.findAll().size();

        // Create the Userbankcard
        UserbankcardDTO userbankcardDTO = userbankcardMapper.toDto(userbankcard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserbankcardMockMvc.perform(put("/api/userbankcards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userbankcardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userbankcard in the database
        List<Userbankcard> userbankcardList = userbankcardRepository.findAll();
        assertThat(userbankcardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserbankcard() throws Exception {
        // Initialize the database
        userbankcardRepository.saveAndFlush(userbankcard);

        int databaseSizeBeforeDelete = userbankcardRepository.findAll().size();

        // Delete the userbankcard
        restUserbankcardMockMvc.perform(delete("/api/userbankcards/{id}", userbankcard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Userbankcard> userbankcardList = userbankcardRepository.findAll();
        assertThat(userbankcardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Userbankcard.class);
        Userbankcard userbankcard1 = new Userbankcard();
        userbankcard1.setId(1L);
        Userbankcard userbankcard2 = new Userbankcard();
        userbankcard2.setId(userbankcard1.getId());
        assertThat(userbankcard1).isEqualTo(userbankcard2);
        userbankcard2.setId(2L);
        assertThat(userbankcard1).isNotEqualTo(userbankcard2);
        userbankcard1.setId(null);
        assertThat(userbankcard1).isNotEqualTo(userbankcard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserbankcardDTO.class);
        UserbankcardDTO userbankcardDTO1 = new UserbankcardDTO();
        userbankcardDTO1.setId(1L);
        UserbankcardDTO userbankcardDTO2 = new UserbankcardDTO();
        assertThat(userbankcardDTO1).isNotEqualTo(userbankcardDTO2);
        userbankcardDTO2.setId(userbankcardDTO1.getId());
        assertThat(userbankcardDTO1).isEqualTo(userbankcardDTO2);
        userbankcardDTO2.setId(2L);
        assertThat(userbankcardDTO1).isNotEqualTo(userbankcardDTO2);
        userbankcardDTO1.setId(null);
        assertThat(userbankcardDTO1).isNotEqualTo(userbankcardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userbankcardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userbankcardMapper.fromId(null)).isNull();
    }
}
