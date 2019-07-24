package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.UserassetsRepository;
import com.weisen.www.code.yjf.basic.service.UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserassetsMapper;
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
 * Integration tests for the {@Link UserassetsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class UserassetsResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_BALANCE = "AAAAAAAAAA";
    private static final String UPDATED_BALANCE = "BBBBBBBBBB";

    private static final String DEFAULT_USABLEBALANCE = "AAAAAAAAAA";
    private static final String UPDATED_USABLEBALANCE = "BBBBBBBBBB";

    private static final String DEFAULT_FROZENBALANCE = "AAAAAAAAAA";
    private static final String UPDATED_FROZENBALANCE = "BBBBBBBBBB";

    private static final String DEFAULT_COUPONSUM = "AAAAAAAAAA";
    private static final String UPDATED_COUPONSUM = "BBBBBBBBBB";

    private static final String DEFAULT_INTEGRAL = "AAAAAAAAAA";
    private static final String UPDATED_INTEGRAL = "BBBBBBBBBB";

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
    private UserassetsRepository userassetsRepository;

    @Autowired
    private UserassetsMapper userassetsMapper;

    @Autowired
    private UserassetsService userassetsService;

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

    private MockMvc restUserassetsMockMvc;

    private Userassets userassets;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserassetsResource userassetsResource = new UserassetsResource(userassetsService);
        this.restUserassetsMockMvc = MockMvcBuilders.standaloneSetup(userassetsResource)
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
    public static Userassets createEntity(EntityManager em) {
        Userassets userassets = new Userassets()
            .userid(DEFAULT_USERID)
            .balance(DEFAULT_BALANCE)
            .usablebalance(DEFAULT_USABLEBALANCE)
            .frozenbalance(DEFAULT_FROZENBALANCE)
            .couponsum(DEFAULT_COUPONSUM)
            .integral(DEFAULT_INTEGRAL)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return userassets;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userassets createUpdatedEntity(EntityManager em) {
        Userassets userassets = new Userassets()
            .userid(UPDATED_USERID)
            .balance(UPDATED_BALANCE)
            .usablebalance(UPDATED_USABLEBALANCE)
            .frozenbalance(UPDATED_FROZENBALANCE)
            .couponsum(UPDATED_COUPONSUM)
            .integral(UPDATED_INTEGRAL)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return userassets;
    }

    @BeforeEach
    public void initTest() {
        userassets = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserassets() throws Exception {
        int databaseSizeBeforeCreate = userassetsRepository.findAll().size();

        // Create the Userassets
        UserassetsDTO userassetsDTO = userassetsMapper.toDto(userassets);
        restUserassetsMockMvc.perform(post("/api/userassets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userassetsDTO)))
            .andExpect(status().isCreated());

        // Validate the Userassets in the database
        List<Userassets> userassetsList = userassetsRepository.findAll();
        assertThat(userassetsList).hasSize(databaseSizeBeforeCreate + 1);
        Userassets testUserassets = userassetsList.get(userassetsList.size() - 1);
        assertThat(testUserassets.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testUserassets.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testUserassets.getUsablebalance()).isEqualTo(DEFAULT_USABLEBALANCE);
        assertThat(testUserassets.getFrozenbalance()).isEqualTo(DEFAULT_FROZENBALANCE);
        assertThat(testUserassets.getCouponsum()).isEqualTo(DEFAULT_COUPONSUM);
        assertThat(testUserassets.getIntegral()).isEqualTo(DEFAULT_INTEGRAL);
        assertThat(testUserassets.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testUserassets.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testUserassets.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testUserassets.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testUserassets.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testUserassets.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testUserassets.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createUserassetsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userassetsRepository.findAll().size();

        // Create the Userassets with an existing ID
        userassets.setId(1L);
        UserassetsDTO userassetsDTO = userassetsMapper.toDto(userassets);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserassetsMockMvc.perform(post("/api/userassets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userassetsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userassets in the database
        List<Userassets> userassetsList = userassetsRepository.findAll();
        assertThat(userassetsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserassets() throws Exception {
        // Initialize the database
        userassetsRepository.saveAndFlush(userassets);

        // Get all the userassetsList
        restUserassetsMockMvc.perform(get("/api/userassets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userassets.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.toString())))
            .andExpect(jsonPath("$.[*].usablebalance").value(hasItem(DEFAULT_USABLEBALANCE.toString())))
            .andExpect(jsonPath("$.[*].frozenbalance").value(hasItem(DEFAULT_FROZENBALANCE.toString())))
            .andExpect(jsonPath("$.[*].couponsum").value(hasItem(DEFAULT_COUPONSUM.toString())))
            .andExpect(jsonPath("$.[*].integral").value(hasItem(DEFAULT_INTEGRAL.toString())))
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
    public void getUserassets() throws Exception {
        // Initialize the database
        userassetsRepository.saveAndFlush(userassets);

        // Get the userassets
        restUserassetsMockMvc.perform(get("/api/userassets/{id}", userassets.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userassets.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.toString()))
            .andExpect(jsonPath("$.usablebalance").value(DEFAULT_USABLEBALANCE.toString()))
            .andExpect(jsonPath("$.frozenbalance").value(DEFAULT_FROZENBALANCE.toString()))
            .andExpect(jsonPath("$.couponsum").value(DEFAULT_COUPONSUM.toString()))
            .andExpect(jsonPath("$.integral").value(DEFAULT_INTEGRAL.toString()))
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
    public void getNonExistingUserassets() throws Exception {
        // Get the userassets
        restUserassetsMockMvc.perform(get("/api/userassets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserassets() throws Exception {
        // Initialize the database
        userassetsRepository.saveAndFlush(userassets);

        int databaseSizeBeforeUpdate = userassetsRepository.findAll().size();

        // Update the userassets
        Userassets updatedUserassets = userassetsRepository.findById(userassets.getId()).get();
        // Disconnect from session so that the updates on updatedUserassets are not directly saved in db
        em.detach(updatedUserassets);
        updatedUserassets
            .userid(UPDATED_USERID)
            .balance(UPDATED_BALANCE)
            .usablebalance(UPDATED_USABLEBALANCE)
            .frozenbalance(UPDATED_FROZENBALANCE)
            .couponsum(UPDATED_COUPONSUM)
            .integral(UPDATED_INTEGRAL)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        UserassetsDTO userassetsDTO = userassetsMapper.toDto(updatedUserassets);

        restUserassetsMockMvc.perform(put("/api/userassets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userassetsDTO)))
            .andExpect(status().isOk());

        // Validate the Userassets in the database
        List<Userassets> userassetsList = userassetsRepository.findAll();
        assertThat(userassetsList).hasSize(databaseSizeBeforeUpdate);
        Userassets testUserassets = userassetsList.get(userassetsList.size() - 1);
        assertThat(testUserassets.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testUserassets.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testUserassets.getUsablebalance()).isEqualTo(UPDATED_USABLEBALANCE);
        assertThat(testUserassets.getFrozenbalance()).isEqualTo(UPDATED_FROZENBALANCE);
        assertThat(testUserassets.getCouponsum()).isEqualTo(UPDATED_COUPONSUM);
        assertThat(testUserassets.getIntegral()).isEqualTo(UPDATED_INTEGRAL);
        assertThat(testUserassets.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testUserassets.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testUserassets.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testUserassets.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testUserassets.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testUserassets.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testUserassets.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingUserassets() throws Exception {
        int databaseSizeBeforeUpdate = userassetsRepository.findAll().size();

        // Create the Userassets
        UserassetsDTO userassetsDTO = userassetsMapper.toDto(userassets);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserassetsMockMvc.perform(put("/api/userassets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userassetsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userassets in the database
        List<Userassets> userassetsList = userassetsRepository.findAll();
        assertThat(userassetsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserassets() throws Exception {
        // Initialize the database
        userassetsRepository.saveAndFlush(userassets);

        int databaseSizeBeforeDelete = userassetsRepository.findAll().size();

        // Delete the userassets
        restUserassetsMockMvc.perform(delete("/api/userassets/{id}", userassets.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Userassets> userassetsList = userassetsRepository.findAll();
        assertThat(userassetsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Userassets.class);
        Userassets userassets1 = new Userassets();
        userassets1.setId(1L);
        Userassets userassets2 = new Userassets();
        userassets2.setId(userassets1.getId());
        assertThat(userassets1).isEqualTo(userassets2);
        userassets2.setId(2L);
        assertThat(userassets1).isNotEqualTo(userassets2);
        userassets1.setId(null);
        assertThat(userassets1).isNotEqualTo(userassets2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserassetsDTO.class);
        UserassetsDTO userassetsDTO1 = new UserassetsDTO();
        userassetsDTO1.setId(1L);
        UserassetsDTO userassetsDTO2 = new UserassetsDTO();
        assertThat(userassetsDTO1).isNotEqualTo(userassetsDTO2);
        userassetsDTO2.setId(userassetsDTO1.getId());
        assertThat(userassetsDTO1).isEqualTo(userassetsDTO2);
        userassetsDTO2.setId(2L);
        assertThat(userassetsDTO1).isNotEqualTo(userassetsDTO2);
        userassetsDTO1.setId(null);
        assertThat(userassetsDTO1).isNotEqualTo(userassetsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userassetsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userassetsMapper.fromId(null)).isNull();
    }
}
