package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Withdrawal;
import com.weisen.www.code.yjf.basic.repository.WithdrawalRepository;
import com.weisen.www.code.yjf.basic.service.WithdrawalService;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.mapper.WithdrawalMapper;
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
 * Integration tests for the {@Link WithdrawalResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class WithdrawalResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_WITHDRAWALAMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_WITHDRAWALAMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_WITHDRAWALTYPE = "AAAAAAAAAA";
    private static final String UPDATED_WITHDRAWALTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_GATHERINGWAY = "AAAAAAAAAA";
    private static final String UPDATED_GATHERINGWAY = "BBBBBBBBBB";

    private static final String DEFAULT_BANKCARDID = "AAAAAAAAAA";
    private static final String UPDATED_BANKCARDID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE = "BBBBBBBBBB";

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
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private WithdrawalMapper withdrawalMapper;

    @Autowired
    private WithdrawalService withdrawalService;

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

    private MockMvc restWithdrawalMockMvc;

    private Withdrawal withdrawal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WithdrawalResource withdrawalResource = new WithdrawalResource(withdrawalService);
        this.restWithdrawalMockMvc = MockMvcBuilders.standaloneSetup(withdrawalResource)
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
    public static Withdrawal createEntity(EntityManager em) {
        Withdrawal withdrawal = new Withdrawal()
            .userid(DEFAULT_USERID)
            .withdrawalamount(DEFAULT_WITHDRAWALAMOUNT)
            .withdrawaltype(DEFAULT_WITHDRAWALTYPE)
            .gatheringway(DEFAULT_GATHERINGWAY)
            .bankcardid(DEFAULT_BANKCARDID)
            .payee(DEFAULT_PAYEE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return withdrawal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Withdrawal createUpdatedEntity(EntityManager em) {
        Withdrawal withdrawal = new Withdrawal()
            .userid(UPDATED_USERID)
            .withdrawalamount(UPDATED_WITHDRAWALAMOUNT)
            .withdrawaltype(UPDATED_WITHDRAWALTYPE)
            .gatheringway(UPDATED_GATHERINGWAY)
            .bankcardid(UPDATED_BANKCARDID)
            .payee(UPDATED_PAYEE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return withdrawal;
    }

    @BeforeEach
    public void initTest() {
        withdrawal = createEntity(em);
    }

    @Test
    @Transactional
    public void createWithdrawal() throws Exception {
        int databaseSizeBeforeCreate = withdrawalRepository.findAll().size();

        // Create the Withdrawal
        WithdrawalDTO withdrawalDTO = withdrawalMapper.toDto(withdrawal);
        restWithdrawalMockMvc.perform(post("/api/withdrawals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(withdrawalDTO)))
            .andExpect(status().isCreated());

        // Validate the Withdrawal in the database
        List<Withdrawal> withdrawalList = withdrawalRepository.findAll();
        assertThat(withdrawalList).hasSize(databaseSizeBeforeCreate + 1);
        Withdrawal testWithdrawal = withdrawalList.get(withdrawalList.size() - 1);
        assertThat(testWithdrawal.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testWithdrawal.getWithdrawalamount()).isEqualTo(DEFAULT_WITHDRAWALAMOUNT);
        assertThat(testWithdrawal.getWithdrawaltype()).isEqualTo(DEFAULT_WITHDRAWALTYPE);
        assertThat(testWithdrawal.getGatheringway()).isEqualTo(DEFAULT_GATHERINGWAY);
        assertThat(testWithdrawal.getBankcardid()).isEqualTo(DEFAULT_BANKCARDID);
        assertThat(testWithdrawal.getPayee()).isEqualTo(DEFAULT_PAYEE);
        assertThat(testWithdrawal.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testWithdrawal.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testWithdrawal.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testWithdrawal.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testWithdrawal.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testWithdrawal.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testWithdrawal.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createWithdrawalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = withdrawalRepository.findAll().size();

        // Create the Withdrawal with an existing ID
        withdrawal.setId(1L);
        WithdrawalDTO withdrawalDTO = withdrawalMapper.toDto(withdrawal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWithdrawalMockMvc.perform(post("/api/withdrawals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(withdrawalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Withdrawal in the database
        List<Withdrawal> withdrawalList = withdrawalRepository.findAll();
        assertThat(withdrawalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWithdrawals() throws Exception {
        // Initialize the database
        withdrawalRepository.saveAndFlush(withdrawal);

        // Get all the withdrawalList
        restWithdrawalMockMvc.perform(get("/api/withdrawals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(withdrawal.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].withdrawalamount").value(hasItem(DEFAULT_WITHDRAWALAMOUNT.toString())))
            .andExpect(jsonPath("$.[*].withdrawaltype").value(hasItem(DEFAULT_WITHDRAWALTYPE.toString())))
            .andExpect(jsonPath("$.[*].gatheringway").value(hasItem(DEFAULT_GATHERINGWAY.toString())))
            .andExpect(jsonPath("$.[*].bankcardid").value(hasItem(DEFAULT_BANKCARDID.toString())))
            .andExpect(jsonPath("$.[*].payee").value(hasItem(DEFAULT_PAYEE.toString())))
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
    public void getWithdrawal() throws Exception {
        // Initialize the database
        withdrawalRepository.saveAndFlush(withdrawal);

        // Get the withdrawal
        restWithdrawalMockMvc.perform(get("/api/withdrawals/{id}", withdrawal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(withdrawal.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.withdrawalamount").value(DEFAULT_WITHDRAWALAMOUNT.toString()))
            .andExpect(jsonPath("$.withdrawaltype").value(DEFAULT_WITHDRAWALTYPE.toString()))
            .andExpect(jsonPath("$.gatheringway").value(DEFAULT_GATHERINGWAY.toString()))
            .andExpect(jsonPath("$.bankcardid").value(DEFAULT_BANKCARDID.toString()))
            .andExpect(jsonPath("$.payee").value(DEFAULT_PAYEE.toString()))
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
    public void getNonExistingWithdrawal() throws Exception {
        // Get the withdrawal
        restWithdrawalMockMvc.perform(get("/api/withdrawals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWithdrawal() throws Exception {
        // Initialize the database
        withdrawalRepository.saveAndFlush(withdrawal);

        int databaseSizeBeforeUpdate = withdrawalRepository.findAll().size();

        // Update the withdrawal
        Withdrawal updatedWithdrawal = withdrawalRepository.findById(withdrawal.getId()).get();
        // Disconnect from session so that the updates on updatedWithdrawal are not directly saved in db
        em.detach(updatedWithdrawal);
        updatedWithdrawal
            .userid(UPDATED_USERID)
            .withdrawalamount(UPDATED_WITHDRAWALAMOUNT)
            .withdrawaltype(UPDATED_WITHDRAWALTYPE)
            .gatheringway(UPDATED_GATHERINGWAY)
            .bankcardid(UPDATED_BANKCARDID)
            .payee(UPDATED_PAYEE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        WithdrawalDTO withdrawalDTO = withdrawalMapper.toDto(updatedWithdrawal);

        restWithdrawalMockMvc.perform(put("/api/withdrawals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(withdrawalDTO)))
            .andExpect(status().isOk());

        // Validate the Withdrawal in the database
        List<Withdrawal> withdrawalList = withdrawalRepository.findAll();
        assertThat(withdrawalList).hasSize(databaseSizeBeforeUpdate);
        Withdrawal testWithdrawal = withdrawalList.get(withdrawalList.size() - 1);
        assertThat(testWithdrawal.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testWithdrawal.getWithdrawalamount()).isEqualTo(UPDATED_WITHDRAWALAMOUNT);
        assertThat(testWithdrawal.getWithdrawaltype()).isEqualTo(UPDATED_WITHDRAWALTYPE);
        assertThat(testWithdrawal.getGatheringway()).isEqualTo(UPDATED_GATHERINGWAY);
        assertThat(testWithdrawal.getBankcardid()).isEqualTo(UPDATED_BANKCARDID);
        assertThat(testWithdrawal.getPayee()).isEqualTo(UPDATED_PAYEE);
        assertThat(testWithdrawal.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testWithdrawal.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testWithdrawal.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testWithdrawal.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testWithdrawal.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testWithdrawal.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testWithdrawal.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingWithdrawal() throws Exception {
        int databaseSizeBeforeUpdate = withdrawalRepository.findAll().size();

        // Create the Withdrawal
        WithdrawalDTO withdrawalDTO = withdrawalMapper.toDto(withdrawal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWithdrawalMockMvc.perform(put("/api/withdrawals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(withdrawalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Withdrawal in the database
        List<Withdrawal> withdrawalList = withdrawalRepository.findAll();
        assertThat(withdrawalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWithdrawal() throws Exception {
        // Initialize the database
        withdrawalRepository.saveAndFlush(withdrawal);

        int databaseSizeBeforeDelete = withdrawalRepository.findAll().size();

        // Delete the withdrawal
        restWithdrawalMockMvc.perform(delete("/api/withdrawals/{id}", withdrawal.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Withdrawal> withdrawalList = withdrawalRepository.findAll();
        assertThat(withdrawalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Withdrawal.class);
        Withdrawal withdrawal1 = new Withdrawal();
        withdrawal1.setId(1L);
        Withdrawal withdrawal2 = new Withdrawal();
        withdrawal2.setId(withdrawal1.getId());
        assertThat(withdrawal1).isEqualTo(withdrawal2);
        withdrawal2.setId(2L);
        assertThat(withdrawal1).isNotEqualTo(withdrawal2);
        withdrawal1.setId(null);
        assertThat(withdrawal1).isNotEqualTo(withdrawal2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WithdrawalDTO.class);
        WithdrawalDTO withdrawalDTO1 = new WithdrawalDTO();
        withdrawalDTO1.setId(1L);
        WithdrawalDTO withdrawalDTO2 = new WithdrawalDTO();
        assertThat(withdrawalDTO1).isNotEqualTo(withdrawalDTO2);
        withdrawalDTO2.setId(withdrawalDTO1.getId());
        assertThat(withdrawalDTO1).isEqualTo(withdrawalDTO2);
        withdrawalDTO2.setId(2L);
        assertThat(withdrawalDTO1).isNotEqualTo(withdrawalDTO2);
        withdrawalDTO1.setId(null);
        assertThat(withdrawalDTO1).isNotEqualTo(withdrawalDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(withdrawalMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(withdrawalMapper.fromId(null)).isNull();
    }
}
