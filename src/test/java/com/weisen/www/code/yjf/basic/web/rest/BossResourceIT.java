package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Boss;
import com.weisen.www.code.yjf.basic.repository.BossRepository;
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
 * Integration tests for the {@Link BossResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class BossResourceIT {

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_TURNOVER = "AAAAAAAAAA";
    private static final String UPDATED_TURNOVER = "BBBBBBBBBB";

    private static final Long DEFAULT_USERID = 1L;
    private static final Long UPDATED_USERID = 2L;

    @Autowired
    private BossRepository bossRepository;

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

    private MockMvc restBossMockMvc;

    private Boss boss;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BossResource bossResource = new BossResource(bossRepository);
        this.restBossMockMvc = MockMvcBuilders.standaloneSetup(bossResource)
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
    public static Boss createEntity(EntityManager em) {
        Boss boss = new Boss()
            .region(DEFAULT_REGION)
            .turnover(DEFAULT_TURNOVER)
            .userid(DEFAULT_USERID);
        return boss;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Boss createUpdatedEntity(EntityManager em) {
        Boss boss = new Boss()
            .region(UPDATED_REGION)
            .turnover(UPDATED_TURNOVER)
            .userid(UPDATED_USERID);
        return boss;
    }

    @BeforeEach
    public void initTest() {
        boss = createEntity(em);
    }

    @Test
    @Transactional
    public void createBoss() throws Exception {
        int databaseSizeBeforeCreate = bossRepository.findAll().size();

        // Create the Boss
        restBossMockMvc.perform(post("/api/bosses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boss)))
            .andExpect(status().isCreated());

        // Validate the Boss in the database
        List<Boss> bossList = bossRepository.findAll();
        assertThat(bossList).hasSize(databaseSizeBeforeCreate + 1);
        Boss testBoss = bossList.get(bossList.size() - 1);
        assertThat(testBoss.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testBoss.getTurnover()).isEqualTo(DEFAULT_TURNOVER);
        assertThat(testBoss.getUserid()).isEqualTo(DEFAULT_USERID);
    }

    @Test
    @Transactional
    public void createBossWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bossRepository.findAll().size();

        // Create the Boss with an existing ID
        boss.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBossMockMvc.perform(post("/api/bosses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boss)))
            .andExpect(status().isBadRequest());

        // Validate the Boss in the database
        List<Boss> bossList = bossRepository.findAll();
        assertThat(bossList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBosses() throws Exception {
        // Initialize the database
        bossRepository.saveAndFlush(boss);

        // Get all the bossList
        restBossMockMvc.perform(get("/api/bosses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boss.getId().intValue())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION.toString())))
            .andExpect(jsonPath("$.[*].turnover").value(hasItem(DEFAULT_TURNOVER.toString())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.intValue())));
    }
    
    @Test
    @Transactional
    public void getBoss() throws Exception {
        // Initialize the database
        bossRepository.saveAndFlush(boss);

        // Get the boss
        restBossMockMvc.perform(get("/api/bosses/{id}", boss.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(boss.getId().intValue()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION.toString()))
            .andExpect(jsonPath("$.turnover").value(DEFAULT_TURNOVER.toString()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBoss() throws Exception {
        // Get the boss
        restBossMockMvc.perform(get("/api/bosses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBoss() throws Exception {
        // Initialize the database
        bossRepository.saveAndFlush(boss);

        int databaseSizeBeforeUpdate = bossRepository.findAll().size();

        // Update the boss
        Boss updatedBoss = bossRepository.findById(boss.getId()).get();
        // Disconnect from session so that the updates on updatedBoss are not directly saved in db
        em.detach(updatedBoss);
        updatedBoss
            .region(UPDATED_REGION)
            .turnover(UPDATED_TURNOVER)
            .userid(UPDATED_USERID);

        restBossMockMvc.perform(put("/api/bosses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBoss)))
            .andExpect(status().isOk());

        // Validate the Boss in the database
        List<Boss> bossList = bossRepository.findAll();
        assertThat(bossList).hasSize(databaseSizeBeforeUpdate);
        Boss testBoss = bossList.get(bossList.size() - 1);
        assertThat(testBoss.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testBoss.getTurnover()).isEqualTo(UPDATED_TURNOVER);
        assertThat(testBoss.getUserid()).isEqualTo(UPDATED_USERID);
    }

    @Test
    @Transactional
    public void updateNonExistingBoss() throws Exception {
        int databaseSizeBeforeUpdate = bossRepository.findAll().size();

        // Create the Boss

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBossMockMvc.perform(put("/api/bosses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(boss)))
            .andExpect(status().isBadRequest());

        // Validate the Boss in the database
        List<Boss> bossList = bossRepository.findAll();
        assertThat(bossList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBoss() throws Exception {
        // Initialize the database
        bossRepository.saveAndFlush(boss);

        int databaseSizeBeforeDelete = bossRepository.findAll().size();

        // Delete the boss
        restBossMockMvc.perform(delete("/api/bosses/{id}", boss.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Boss> bossList = bossRepository.findAll();
        assertThat(bossList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Boss.class);
        Boss boss1 = new Boss();
        boss1.setId(1L);
        Boss boss2 = new Boss();
        boss2.setId(boss1.getId());
        assertThat(boss1).isEqualTo(boss2);
        boss2.setId(2L);
        assertThat(boss1).isNotEqualTo(boss2);
        boss1.setId(null);
        assertThat(boss1).isNotEqualTo(boss2);
    }
}
