package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Usercard;
import com.weisen.www.code.yjf.basic.repository.UsercardRepository;
import com.weisen.www.code.yjf.basic.service.UsercardService;
import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UsercardMapper;
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
 * Integration tests for the {@Link UsercardResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class UsercardResourceIT {

    private static final String DEFAULT_BANKNAME = "AAAAAAAAAA";
    private static final String UPDATED_BANKNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_LOGO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK = "AAAAAAAAAA";
    private static final String UPDATED_BANK = "BBBBBBBBBB";

    private static final String DEFAULT_CARDNUM = "AAAAAAAAAA";
    private static final String UPDATED_CARDNUM = "BBBBBBBBBB";

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
    private UsercardRepository usercardRepository;

    @Autowired
    private UsercardMapper usercardMapper;

    @Autowired
    private UsercardService usercardService;

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

    private MockMvc restUsercardMockMvc;

    private Usercard usercard;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UsercardResource usercardResource = new UsercardResource(usercardService);
        this.restUsercardMockMvc = MockMvcBuilders.standaloneSetup(usercardResource)
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
    public static Usercard createEntity(EntityManager em) {
        Usercard usercard = new Usercard()
            .bankname(DEFAULT_BANKNAME)
            .logo(DEFAULT_LOGO)
            .bank(DEFAULT_BANK)
            .cardnum(DEFAULT_CARDNUM)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return usercard;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usercard createUpdatedEntity(EntityManager em) {
        Usercard usercard = new Usercard()
            .bankname(UPDATED_BANKNAME)
            .logo(UPDATED_LOGO)
            .bank(UPDATED_BANK)
            .cardnum(UPDATED_CARDNUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return usercard;
    }

    @BeforeEach
    public void initTest() {
        usercard = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsercard() throws Exception {
        int databaseSizeBeforeCreate = usercardRepository.findAll().size();

        // Create the Usercard
        UsercardDTO usercardDTO = usercardMapper.toDto(usercard);
        restUsercardMockMvc.perform(post("/api/usercards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usercardDTO)))
            .andExpect(status().isCreated());

        // Validate the Usercard in the database
        List<Usercard> usercardList = usercardRepository.findAll();
        assertThat(usercardList).hasSize(databaseSizeBeforeCreate + 1);
        Usercard testUsercard = usercardList.get(usercardList.size() - 1);
        assertThat(testUsercard.getBankname()).isEqualTo(DEFAULT_BANKNAME);
        assertThat(testUsercard.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testUsercard.getBank()).isEqualTo(DEFAULT_BANK);
        assertThat(testUsercard.getCardnum()).isEqualTo(DEFAULT_CARDNUM);
        assertThat(testUsercard.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testUsercard.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testUsercard.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testUsercard.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testUsercard.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testUsercard.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testUsercard.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createUsercardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usercardRepository.findAll().size();

        // Create the Usercard with an existing ID
        usercard.setId(1L);
        UsercardDTO usercardDTO = usercardMapper.toDto(usercard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsercardMockMvc.perform(post("/api/usercards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usercardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Usercard in the database
        List<Usercard> usercardList = usercardRepository.findAll();
        assertThat(usercardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUsercards() throws Exception {
        // Initialize the database
        usercardRepository.saveAndFlush(usercard);

        // Get all the usercardList
        restUsercardMockMvc.perform(get("/api/usercards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usercard.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankname").value(hasItem(DEFAULT_BANKNAME.toString())))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(DEFAULT_LOGO.toString())))
            .andExpect(jsonPath("$.[*].bank").value(hasItem(DEFAULT_BANK.toString())))
            .andExpect(jsonPath("$.[*].cardnum").value(hasItem(DEFAULT_CARDNUM.toString())))
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
    public void getUsercard() throws Exception {
        // Initialize the database
        usercardRepository.saveAndFlush(usercard);

        // Get the usercard
        restUsercardMockMvc.perform(get("/api/usercards/{id}", usercard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(usercard.getId().intValue()))
            .andExpect(jsonPath("$.bankname").value(DEFAULT_BANKNAME.toString()))
            .andExpect(jsonPath("$.logo").value(DEFAULT_LOGO.toString()))
            .andExpect(jsonPath("$.bank").value(DEFAULT_BANK.toString()))
            .andExpect(jsonPath("$.cardnum").value(DEFAULT_CARDNUM.toString()))
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
    public void getNonExistingUsercard() throws Exception {
        // Get the usercard
        restUsercardMockMvc.perform(get("/api/usercards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsercard() throws Exception {
        // Initialize the database
        usercardRepository.saveAndFlush(usercard);

        int databaseSizeBeforeUpdate = usercardRepository.findAll().size();

        // Update the usercard
        Usercard updatedUsercard = usercardRepository.findById(usercard.getId()).get();
        // Disconnect from session so that the updates on updatedUsercard are not directly saved in db
        em.detach(updatedUsercard);
        updatedUsercard
            .bankname(UPDATED_BANKNAME)
            .logo(UPDATED_LOGO)
            .bank(UPDATED_BANK)
            .cardnum(UPDATED_CARDNUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        UsercardDTO usercardDTO = usercardMapper.toDto(updatedUsercard);

        restUsercardMockMvc.perform(put("/api/usercards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usercardDTO)))
            .andExpect(status().isOk());

        // Validate the Usercard in the database
        List<Usercard> usercardList = usercardRepository.findAll();
        assertThat(usercardList).hasSize(databaseSizeBeforeUpdate);
        Usercard testUsercard = usercardList.get(usercardList.size() - 1);
        assertThat(testUsercard.getBankname()).isEqualTo(UPDATED_BANKNAME);
        assertThat(testUsercard.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testUsercard.getBank()).isEqualTo(UPDATED_BANK);
        assertThat(testUsercard.getCardnum()).isEqualTo(UPDATED_CARDNUM);
        assertThat(testUsercard.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testUsercard.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testUsercard.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testUsercard.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testUsercard.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testUsercard.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testUsercard.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingUsercard() throws Exception {
        int databaseSizeBeforeUpdate = usercardRepository.findAll().size();

        // Create the Usercard
        UsercardDTO usercardDTO = usercardMapper.toDto(usercard);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsercardMockMvc.perform(put("/api/usercards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usercardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Usercard in the database
        List<Usercard> usercardList = usercardRepository.findAll();
        assertThat(usercardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsercard() throws Exception {
        // Initialize the database
        usercardRepository.saveAndFlush(usercard);

        int databaseSizeBeforeDelete = usercardRepository.findAll().size();

        // Delete the usercard
        restUsercardMockMvc.perform(delete("/api/usercards/{id}", usercard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Usercard> usercardList = usercardRepository.findAll();
        assertThat(usercardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Usercard.class);
        Usercard usercard1 = new Usercard();
        usercard1.setId(1L);
        Usercard usercard2 = new Usercard();
        usercard2.setId(usercard1.getId());
        assertThat(usercard1).isEqualTo(usercard2);
        usercard2.setId(2L);
        assertThat(usercard1).isNotEqualTo(usercard2);
        usercard1.setId(null);
        assertThat(usercard1).isNotEqualTo(usercard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsercardDTO.class);
        UsercardDTO usercardDTO1 = new UsercardDTO();
        usercardDTO1.setId(1L);
        UsercardDTO usercardDTO2 = new UsercardDTO();
        assertThat(usercardDTO1).isNotEqualTo(usercardDTO2);
        usercardDTO2.setId(usercardDTO1.getId());
        assertThat(usercardDTO1).isEqualTo(usercardDTO2);
        usercardDTO2.setId(2L);
        assertThat(usercardDTO1).isNotEqualTo(usercardDTO2);
        usercardDTO1.setId(null);
        assertThat(usercardDTO1).isNotEqualTo(usercardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(usercardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(usercardMapper.fromId(null)).isNull();
    }
}
