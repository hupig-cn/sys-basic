package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Paymethod;
import com.weisen.www.code.yjf.basic.repository.PaymethodRepository;
import com.weisen.www.code.yjf.basic.service.PaymethodService;
import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.service.mapper.PaymethodMapper;
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
 * Integration tests for the {@Link PaymethodResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class PaymethodResourceIT {

    private static final String DEFAULT_OS = "AAAAAAAAAA";
    private static final String UPDATED_OS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ONLINE = false;
    private static final Boolean UPDATED_ONLINE = true;

    private static final Boolean DEFAULT_SWITCHS = false;
    private static final Boolean UPDATED_SWITCHS = true;

    private static final String DEFAULT_PAYNAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYNAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER = "AAAAAAAAAA";
    private static final String UPDATED_ORDER = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGES = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGES = "BBBBBBBBBB";

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
    private PaymethodRepository paymethodRepository;

    @Autowired
    private PaymethodMapper paymethodMapper;

    @Autowired
    private PaymethodService paymethodService;

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

    private MockMvc restPaymethodMockMvc;

    private Paymethod paymethod;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PaymethodResource paymethodResource = new PaymethodResource(paymethodService);
        this.restPaymethodMockMvc = MockMvcBuilders.standaloneSetup(paymethodResource)
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
    public static Paymethod createEntity(EntityManager em) {
        Paymethod paymethod = new Paymethod()
            .os(DEFAULT_OS)
            .online(DEFAULT_ONLINE)
            .switchs(DEFAULT_SWITCHS)
            .payname(DEFAULT_PAYNAME)
            .order(DEFAULT_ORDER)
            .messages(DEFAULT_MESSAGES)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return paymethod;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paymethod createUpdatedEntity(EntityManager em) {
        Paymethod paymethod = new Paymethod()
            .os(UPDATED_OS)
            .online(UPDATED_ONLINE)
            .switchs(UPDATED_SWITCHS)
            .payname(UPDATED_PAYNAME)
            .order(UPDATED_ORDER)
            .messages(UPDATED_MESSAGES)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return paymethod;
    }

    @BeforeEach
    public void initTest() {
        paymethod = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymethod() throws Exception {
        int databaseSizeBeforeCreate = paymethodRepository.findAll().size();

        // Create the Paymethod
        PaymethodDTO paymethodDTO = paymethodMapper.toDto(paymethod);
        restPaymethodMockMvc.perform(post("/api/paymethods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymethodDTO)))
            .andExpect(status().isCreated());

        // Validate the Paymethod in the database
        List<Paymethod> paymethodList = paymethodRepository.findAll();
        assertThat(paymethodList).hasSize(databaseSizeBeforeCreate + 1);
        Paymethod testPaymethod = paymethodList.get(paymethodList.size() - 1);
        assertThat(testPaymethod.getOs()).isEqualTo(DEFAULT_OS);
        assertThat(testPaymethod.isOnline()).isEqualTo(DEFAULT_ONLINE);
        assertThat(testPaymethod.isSwitchs()).isEqualTo(DEFAULT_SWITCHS);
        assertThat(testPaymethod.getPayname()).isEqualTo(DEFAULT_PAYNAME);
        assertThat(testPaymethod.getOrder()).isEqualTo(DEFAULT_ORDER);
        assertThat(testPaymethod.getMessages()).isEqualTo(DEFAULT_MESSAGES);
        assertThat(testPaymethod.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPaymethod.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testPaymethod.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testPaymethod.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testPaymethod.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testPaymethod.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testPaymethod.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createPaymethodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymethodRepository.findAll().size();

        // Create the Paymethod with an existing ID
        paymethod.setId(1L);
        PaymethodDTO paymethodDTO = paymethodMapper.toDto(paymethod);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymethodMockMvc.perform(post("/api/paymethods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paymethod in the database
        List<Paymethod> paymethodList = paymethodRepository.findAll();
        assertThat(paymethodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPaymethods() throws Exception {
        // Initialize the database
        paymethodRepository.saveAndFlush(paymethod);

        // Get all the paymethodList
        restPaymethodMockMvc.perform(get("/api/paymethods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymethod.getId().intValue())))
            .andExpect(jsonPath("$.[*].os").value(hasItem(DEFAULT_OS.toString())))
            .andExpect(jsonPath("$.[*].online").value(hasItem(DEFAULT_ONLINE.booleanValue())))
            .andExpect(jsonPath("$.[*].switchs").value(hasItem(DEFAULT_SWITCHS.booleanValue())))
            .andExpect(jsonPath("$.[*].payname").value(hasItem(DEFAULT_PAYNAME.toString())))
            .andExpect(jsonPath("$.[*].order").value(hasItem(DEFAULT_ORDER.toString())))
            .andExpect(jsonPath("$.[*].messages").value(hasItem(DEFAULT_MESSAGES.toString())))
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
    public void getPaymethod() throws Exception {
        // Initialize the database
        paymethodRepository.saveAndFlush(paymethod);

        // Get the paymethod
        restPaymethodMockMvc.perform(get("/api/paymethods/{id}", paymethod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paymethod.getId().intValue()))
            .andExpect(jsonPath("$.os").value(DEFAULT_OS.toString()))
            .andExpect(jsonPath("$.online").value(DEFAULT_ONLINE.booleanValue()))
            .andExpect(jsonPath("$.switchs").value(DEFAULT_SWITCHS.booleanValue()))
            .andExpect(jsonPath("$.payname").value(DEFAULT_PAYNAME.toString()))
            .andExpect(jsonPath("$.order").value(DEFAULT_ORDER.toString()))
            .andExpect(jsonPath("$.messages").value(DEFAULT_MESSAGES.toString()))
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
    public void getNonExistingPaymethod() throws Exception {
        // Get the paymethod
        restPaymethodMockMvc.perform(get("/api/paymethods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymethod() throws Exception {
        // Initialize the database
        paymethodRepository.saveAndFlush(paymethod);

        int databaseSizeBeforeUpdate = paymethodRepository.findAll().size();

        // Update the paymethod
        Paymethod updatedPaymethod = paymethodRepository.findById(paymethod.getId()).get();
        // Disconnect from session so that the updates on updatedPaymethod are not directly saved in db
        em.detach(updatedPaymethod);
        updatedPaymethod
            .os(UPDATED_OS)
            .online(UPDATED_ONLINE)
            .switchs(UPDATED_SWITCHS)
            .payname(UPDATED_PAYNAME)
            .order(UPDATED_ORDER)
            .messages(UPDATED_MESSAGES)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        PaymethodDTO paymethodDTO = paymethodMapper.toDto(updatedPaymethod);

        restPaymethodMockMvc.perform(put("/api/paymethods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymethodDTO)))
            .andExpect(status().isOk());

        // Validate the Paymethod in the database
        List<Paymethod> paymethodList = paymethodRepository.findAll();
        assertThat(paymethodList).hasSize(databaseSizeBeforeUpdate);
        Paymethod testPaymethod = paymethodList.get(paymethodList.size() - 1);
        assertThat(testPaymethod.getOs()).isEqualTo(UPDATED_OS);
        assertThat(testPaymethod.isOnline()).isEqualTo(UPDATED_ONLINE);
        assertThat(testPaymethod.isSwitchs()).isEqualTo(UPDATED_SWITCHS);
        assertThat(testPaymethod.getPayname()).isEqualTo(UPDATED_PAYNAME);
        assertThat(testPaymethod.getOrder()).isEqualTo(UPDATED_ORDER);
        assertThat(testPaymethod.getMessages()).isEqualTo(UPDATED_MESSAGES);
        assertThat(testPaymethod.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPaymethod.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testPaymethod.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testPaymethod.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testPaymethod.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testPaymethod.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testPaymethod.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymethod() throws Exception {
        int databaseSizeBeforeUpdate = paymethodRepository.findAll().size();

        // Create the Paymethod
        PaymethodDTO paymethodDTO = paymethodMapper.toDto(paymethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymethodMockMvc.perform(put("/api/paymethods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paymethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Paymethod in the database
        List<Paymethod> paymethodList = paymethodRepository.findAll();
        assertThat(paymethodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymethod() throws Exception {
        // Initialize the database
        paymethodRepository.saveAndFlush(paymethod);

        int databaseSizeBeforeDelete = paymethodRepository.findAll().size();

        // Delete the paymethod
        restPaymethodMockMvc.perform(delete("/api/paymethods/{id}", paymethod.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Paymethod> paymethodList = paymethodRepository.findAll();
        assertThat(paymethodList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Paymethod.class);
        Paymethod paymethod1 = new Paymethod();
        paymethod1.setId(1L);
        Paymethod paymethod2 = new Paymethod();
        paymethod2.setId(paymethod1.getId());
        assertThat(paymethod1).isEqualTo(paymethod2);
        paymethod2.setId(2L);
        assertThat(paymethod1).isNotEqualTo(paymethod2);
        paymethod1.setId(null);
        assertThat(paymethod1).isNotEqualTo(paymethod2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymethodDTO.class);
        PaymethodDTO paymethodDTO1 = new PaymethodDTO();
        paymethodDTO1.setId(1L);
        PaymethodDTO paymethodDTO2 = new PaymethodDTO();
        assertThat(paymethodDTO1).isNotEqualTo(paymethodDTO2);
        paymethodDTO2.setId(paymethodDTO1.getId());
        assertThat(paymethodDTO1).isEqualTo(paymethodDTO2);
        paymethodDTO2.setId(2L);
        assertThat(paymethodDTO1).isNotEqualTo(paymethodDTO2);
        paymethodDTO1.setId(null);
        assertThat(paymethodDTO1).isNotEqualTo(paymethodDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(paymethodMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(paymethodMapper.fromId(null)).isNull();
    }
}
