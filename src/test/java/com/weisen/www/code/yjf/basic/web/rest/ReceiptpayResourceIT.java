package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.repository.ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.service.ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ReceiptpayMapper;
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
import java.math.BigDecimal;
import java.util.List;

import static com.weisen.www.code.yjf.basic.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ReceiptpayResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class ReceiptpayResourceIT {

    private static final String DEFAULT_DEALTYPE = "AAAAAAAAAA";
    private static final String UPDATED_DEALTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCER = "AAAAAAAAAA";
    private static final String UPDATED_SOURCER = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFIT = "AAAAAAAAAA";
    private static final String UPDATED_BENEFIT = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BONUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_BONUS = new BigDecimal(2);

    private static final String DEFAULT_HAPPENDATE = "AAAAAAAAAA";
    private static final String UPDATED_HAPPENDATE = "BBBBBBBBBB";

    private static final String DEFAULT_FREEZEDATE = "AAAAAAAAAA";
    private static final String UPDATED_FREEZEDATE = "BBBBBBBBBB";

    private static final String DEFAULT_DEALSTATE = "AAAAAAAAAA";
    private static final String UPDATED_DEALSTATE = "BBBBBBBBBB";

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
    private ReceiptpayRepository receiptpayRepository;

    @Autowired
    private ReceiptpayMapper receiptpayMapper;

    @Autowired
    private ReceiptpayService receiptpayService;

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

    private MockMvc restReceiptpayMockMvc;

    private Receiptpay receiptpay;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReceiptpayResource receiptpayResource = new ReceiptpayResource(receiptpayService);
        this.restReceiptpayMockMvc = MockMvcBuilders.standaloneSetup(receiptpayResource)
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
    public static Receiptpay createEntity(EntityManager em) {
        Receiptpay receiptpay = new Receiptpay()
            .dealtype(DEFAULT_DEALTYPE)
            .userid(DEFAULT_USERID)
            .sourcer(DEFAULT_SOURCER)
            .benefit(DEFAULT_BENEFIT)
            .amount(DEFAULT_AMOUNT)
            .bonus(DEFAULT_BONUS)
            .happendate(DEFAULT_HAPPENDATE)
            .freezedate(DEFAULT_FREEZEDATE)
            .dealstate(DEFAULT_DEALSTATE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return receiptpay;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Receiptpay createUpdatedEntity(EntityManager em) {
        Receiptpay receiptpay = new Receiptpay()
            .dealtype(UPDATED_DEALTYPE)
            .userid(UPDATED_USERID)
            .sourcer(UPDATED_SOURCER)
            .benefit(UPDATED_BENEFIT)
            .amount(UPDATED_AMOUNT)
            .bonus(UPDATED_BONUS)
            .happendate(UPDATED_HAPPENDATE)
            .freezedate(UPDATED_FREEZEDATE)
            .dealstate(UPDATED_DEALSTATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return receiptpay;
    }

    @BeforeEach
    public void initTest() {
        receiptpay = createEntity(em);
    }

    @Test
    @Transactional
    public void createReceiptpay() throws Exception {
        int databaseSizeBeforeCreate = receiptpayRepository.findAll().size();

        // Create the Receiptpay
        ReceiptpayDTO receiptpayDTO = receiptpayMapper.toDto(receiptpay);
        restReceiptpayMockMvc.perform(post("/api/receiptpays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receiptpayDTO)))
            .andExpect(status().isCreated());

        // Validate the Receiptpay in the database
        List<Receiptpay> receiptpayList = receiptpayRepository.findAll();
        assertThat(receiptpayList).hasSize(databaseSizeBeforeCreate + 1);
        Receiptpay testReceiptpay = receiptpayList.get(receiptpayList.size() - 1);
        assertThat(testReceiptpay.getDealtype()).isEqualTo(DEFAULT_DEALTYPE);
        assertThat(testReceiptpay.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testReceiptpay.getSourcer()).isEqualTo(DEFAULT_SOURCER);
        assertThat(testReceiptpay.getBenefit()).isEqualTo(DEFAULT_BENEFIT);
        assertThat(testReceiptpay.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testReceiptpay.getBonus()).isEqualTo(DEFAULT_BONUS);
        assertThat(testReceiptpay.getHappendate()).isEqualTo(DEFAULT_HAPPENDATE);
        assertThat(testReceiptpay.getFreezedate()).isEqualTo(DEFAULT_FREEZEDATE);
        assertThat(testReceiptpay.getDealstate()).isEqualTo(DEFAULT_DEALSTATE);
        assertThat(testReceiptpay.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testReceiptpay.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testReceiptpay.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testReceiptpay.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testReceiptpay.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testReceiptpay.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testReceiptpay.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createReceiptpayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = receiptpayRepository.findAll().size();

        // Create the Receiptpay with an existing ID
        receiptpay.setId(1L);
        ReceiptpayDTO receiptpayDTO = receiptpayMapper.toDto(receiptpay);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReceiptpayMockMvc.perform(post("/api/receiptpays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receiptpayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Receiptpay in the database
        List<Receiptpay> receiptpayList = receiptpayRepository.findAll();
        assertThat(receiptpayList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReceiptpays() throws Exception {
        // Initialize the database
        receiptpayRepository.saveAndFlush(receiptpay);

        // Get all the receiptpayList
        restReceiptpayMockMvc.perform(get("/api/receiptpays?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(receiptpay.getId().intValue())))
            .andExpect(jsonPath("$.[*].dealtype").value(hasItem(DEFAULT_DEALTYPE.toString())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].sourcer").value(hasItem(DEFAULT_SOURCER.toString())))
            .andExpect(jsonPath("$.[*].benefit").value(hasItem(DEFAULT_BENEFIT.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].bonus").value(hasItem(DEFAULT_BONUS.intValue())))
            .andExpect(jsonPath("$.[*].happendate").value(hasItem(DEFAULT_HAPPENDATE.toString())))
            .andExpect(jsonPath("$.[*].freezedate").value(hasItem(DEFAULT_FREEZEDATE.toString())))
            .andExpect(jsonPath("$.[*].dealstate").value(hasItem(DEFAULT_DEALSTATE.toString())))
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
    public void getReceiptpay() throws Exception {
        // Initialize the database
        receiptpayRepository.saveAndFlush(receiptpay);

        // Get the receiptpay
        restReceiptpayMockMvc.perform(get("/api/receiptpays/{id}", receiptpay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(receiptpay.getId().intValue()))
            .andExpect(jsonPath("$.dealtype").value(DEFAULT_DEALTYPE.toString()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.sourcer").value(DEFAULT_SOURCER.toString()))
            .andExpect(jsonPath("$.benefit").value(DEFAULT_BENEFIT.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.bonus").value(DEFAULT_BONUS.intValue()))
            .andExpect(jsonPath("$.happendate").value(DEFAULT_HAPPENDATE.toString()))
            .andExpect(jsonPath("$.freezedate").value(DEFAULT_FREEZEDATE.toString()))
            .andExpect(jsonPath("$.dealstate").value(DEFAULT_DEALSTATE.toString()))
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
    public void getNonExistingReceiptpay() throws Exception {
        // Get the receiptpay
        restReceiptpayMockMvc.perform(get("/api/receiptpays/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReceiptpay() throws Exception {
        // Initialize the database
        receiptpayRepository.saveAndFlush(receiptpay);

        int databaseSizeBeforeUpdate = receiptpayRepository.findAll().size();

        // Update the receiptpay
        Receiptpay updatedReceiptpay = receiptpayRepository.findById(receiptpay.getId()).get();
        // Disconnect from session so that the updates on updatedReceiptpay are not directly saved in db
        em.detach(updatedReceiptpay);
        updatedReceiptpay
            .dealtype(UPDATED_DEALTYPE)
            .userid(UPDATED_USERID)
            .sourcer(UPDATED_SOURCER)
            .benefit(UPDATED_BENEFIT)
            .amount(UPDATED_AMOUNT)
            .bonus(UPDATED_BONUS)
            .happendate(UPDATED_HAPPENDATE)
            .freezedate(UPDATED_FREEZEDATE)
            .dealstate(UPDATED_DEALSTATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        ReceiptpayDTO receiptpayDTO = receiptpayMapper.toDto(updatedReceiptpay);

        restReceiptpayMockMvc.perform(put("/api/receiptpays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receiptpayDTO)))
            .andExpect(status().isOk());

        // Validate the Receiptpay in the database
        List<Receiptpay> receiptpayList = receiptpayRepository.findAll();
        assertThat(receiptpayList).hasSize(databaseSizeBeforeUpdate);
        Receiptpay testReceiptpay = receiptpayList.get(receiptpayList.size() - 1);
        assertThat(testReceiptpay.getDealtype()).isEqualTo(UPDATED_DEALTYPE);
        assertThat(testReceiptpay.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testReceiptpay.getSourcer()).isEqualTo(UPDATED_SOURCER);
        assertThat(testReceiptpay.getBenefit()).isEqualTo(UPDATED_BENEFIT);
        assertThat(testReceiptpay.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testReceiptpay.getBonus()).isEqualTo(UPDATED_BONUS);
        assertThat(testReceiptpay.getHappendate()).isEqualTo(UPDATED_HAPPENDATE);
        assertThat(testReceiptpay.getFreezedate()).isEqualTo(UPDATED_FREEZEDATE);
        assertThat(testReceiptpay.getDealstate()).isEqualTo(UPDATED_DEALSTATE);
        assertThat(testReceiptpay.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testReceiptpay.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testReceiptpay.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testReceiptpay.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testReceiptpay.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testReceiptpay.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testReceiptpay.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingReceiptpay() throws Exception {
        int databaseSizeBeforeUpdate = receiptpayRepository.findAll().size();

        // Create the Receiptpay
        ReceiptpayDTO receiptpayDTO = receiptpayMapper.toDto(receiptpay);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceiptpayMockMvc.perform(put("/api/receiptpays")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receiptpayDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Receiptpay in the database
        List<Receiptpay> receiptpayList = receiptpayRepository.findAll();
        assertThat(receiptpayList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReceiptpay() throws Exception {
        // Initialize the database
        receiptpayRepository.saveAndFlush(receiptpay);

        int databaseSizeBeforeDelete = receiptpayRepository.findAll().size();

        // Delete the receiptpay
        restReceiptpayMockMvc.perform(delete("/api/receiptpays/{id}", receiptpay.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Receiptpay> receiptpayList = receiptpayRepository.findAll();
        assertThat(receiptpayList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Receiptpay.class);
        Receiptpay receiptpay1 = new Receiptpay();
        receiptpay1.setId(1L);
        Receiptpay receiptpay2 = new Receiptpay();
        receiptpay2.setId(receiptpay1.getId());
        assertThat(receiptpay1).isEqualTo(receiptpay2);
        receiptpay2.setId(2L);
        assertThat(receiptpay1).isNotEqualTo(receiptpay2);
        receiptpay1.setId(null);
        assertThat(receiptpay1).isNotEqualTo(receiptpay2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceiptpayDTO.class);
        ReceiptpayDTO receiptpayDTO1 = new ReceiptpayDTO();
        receiptpayDTO1.setId(1L);
        ReceiptpayDTO receiptpayDTO2 = new ReceiptpayDTO();
        assertThat(receiptpayDTO1).isNotEqualTo(receiptpayDTO2);
        receiptpayDTO2.setId(receiptpayDTO1.getId());
        assertThat(receiptpayDTO1).isEqualTo(receiptpayDTO2);
        receiptpayDTO2.setId(2L);
        assertThat(receiptpayDTO1).isNotEqualTo(receiptpayDTO2);
        receiptpayDTO1.setId(null);
        assertThat(receiptpayDTO1).isNotEqualTo(receiptpayDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(receiptpayMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(receiptpayMapper.fromId(null)).isNull();
    }
}
