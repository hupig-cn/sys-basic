package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.repository.UserorderRepository;
import com.weisen.www.code.yjf.basic.service.UserorderService;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserorderMapper;
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
 * Integration tests for the {@Link UserorderResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class UserorderResourceIT {

    private static final String DEFAULT_ORDERCODE = "AAAAAAAAAA";
    private static final String UPDATED_ORDERCODE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_ORDERSTATUS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUM = new BigDecimal(2);

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYWAY = "AAAAAAAAAA";
    private static final String UPDATED_PAYWAY = "BBBBBBBBBB";

    private static final String DEFAULT_PAYRESULT = "AAAAAAAAAA";
    private static final String UPDATED_PAYRESULT = "BBBBBBBBBB";

    private static final String DEFAULT_PAYTIME = "AAAAAAAAAA";
    private static final String UPDATED_PAYTIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONCESSION = 1;
    private static final Integer UPDATED_CONCESSION = 2;

    private static final Integer DEFAULT_REBATE = 1;
    private static final Integer UPDATED_REBATE = 2;

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
    private UserorderRepository userorderRepository;

    @Autowired
    private UserorderMapper userorderMapper;

    @Autowired
    private UserorderService userorderService;

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

    private MockMvc restUserorderMockMvc;

    private Userorder userorder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserorderResource userorderResource = new UserorderResource(userorderService);
        this.restUserorderMockMvc = MockMvcBuilders.standaloneSetup(userorderResource)
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
    public static Userorder createEntity(EntityManager em) {
        Userorder userorder = new Userorder()
            .ordercode(DEFAULT_ORDERCODE)
            .orderstatus(DEFAULT_ORDERSTATUS)
            .sum(DEFAULT_SUM)
            .userid(DEFAULT_USERID)
            .payee(DEFAULT_PAYEE)
            .payway(DEFAULT_PAYWAY)
            .payresult(DEFAULT_PAYRESULT)
            .paytime(DEFAULT_PAYTIME)
            .concession(DEFAULT_CONCESSION)
            .rebate(DEFAULT_REBATE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return userorder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userorder createUpdatedEntity(EntityManager em) {
        Userorder userorder = new Userorder()
            .ordercode(UPDATED_ORDERCODE)
            .orderstatus(UPDATED_ORDERSTATUS)
            .sum(UPDATED_SUM)
            .userid(UPDATED_USERID)
            .payee(UPDATED_PAYEE)
            .payway(UPDATED_PAYWAY)
            .payresult(UPDATED_PAYRESULT)
            .paytime(UPDATED_PAYTIME)
            .concession(UPDATED_CONCESSION)
            .rebate(UPDATED_REBATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return userorder;
    }

    @BeforeEach
    public void initTest() {
        userorder = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserorder() throws Exception {
        int databaseSizeBeforeCreate = userorderRepository.findAll().size();

        // Create the Userorder
        UserorderDTO userorderDTO = userorderMapper.toDto(userorder);
        restUserorderMockMvc.perform(post("/api/userorders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userorderDTO)))
            .andExpect(status().isCreated());

        // Validate the Userorder in the database
        List<Userorder> userorderList = userorderRepository.findAll();
        assertThat(userorderList).hasSize(databaseSizeBeforeCreate + 1);
        Userorder testUserorder = userorderList.get(userorderList.size() - 1);
        assertThat(testUserorder.getOrdercode()).isEqualTo(DEFAULT_ORDERCODE);
        assertThat(testUserorder.getOrderstatus()).isEqualTo(DEFAULT_ORDERSTATUS);
        assertThat(testUserorder.getSum()).isEqualTo(DEFAULT_SUM);
        assertThat(testUserorder.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testUserorder.getPayee()).isEqualTo(DEFAULT_PAYEE);
        assertThat(testUserorder.getPayway()).isEqualTo(DEFAULT_PAYWAY);
        assertThat(testUserorder.getPayresult()).isEqualTo(DEFAULT_PAYRESULT);
        assertThat(testUserorder.getPaytime()).isEqualTo(DEFAULT_PAYTIME);
        assertThat(testUserorder.getConcession()).isEqualTo(DEFAULT_CONCESSION);
        assertThat(testUserorder.getRebate()).isEqualTo(DEFAULT_REBATE);
        assertThat(testUserorder.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testUserorder.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testUserorder.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testUserorder.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testUserorder.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testUserorder.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testUserorder.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createUserorderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userorderRepository.findAll().size();

        // Create the Userorder with an existing ID
        userorder.setId(1L);
        UserorderDTO userorderDTO = userorderMapper.toDto(userorder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserorderMockMvc.perform(post("/api/userorders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userorderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userorder in the database
        List<Userorder> userorderList = userorderRepository.findAll();
        assertThat(userorderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserorders() throws Exception {
        // Initialize the database
        userorderRepository.saveAndFlush(userorder);

        // Get all the userorderList
        restUserorderMockMvc.perform(get("/api/userorders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userorder.getId().intValue())))
            .andExpect(jsonPath("$.[*].ordercode").value(hasItem(DEFAULT_ORDERCODE.toString())))
            .andExpect(jsonPath("$.[*].orderstatus").value(hasItem(DEFAULT_ORDERSTATUS.toString())))
            .andExpect(jsonPath("$.[*].sum").value(hasItem(DEFAULT_SUM.intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].payee").value(hasItem(DEFAULT_PAYEE.toString())))
            .andExpect(jsonPath("$.[*].payway").value(hasItem(DEFAULT_PAYWAY.toString())))
            .andExpect(jsonPath("$.[*].payresult").value(hasItem(DEFAULT_PAYRESULT.toString())))
            .andExpect(jsonPath("$.[*].paytime").value(hasItem(DEFAULT_PAYTIME.toString())))
            .andExpect(jsonPath("$.[*].concession").value(hasItem(DEFAULT_CONCESSION)))
            .andExpect(jsonPath("$.[*].rebate").value(hasItem(DEFAULT_REBATE)))
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
    public void getUserorder() throws Exception {
        // Initialize the database
        userorderRepository.saveAndFlush(userorder);

        // Get the userorder
        restUserorderMockMvc.perform(get("/api/userorders/{id}", userorder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userorder.getId().intValue()))
            .andExpect(jsonPath("$.ordercode").value(DEFAULT_ORDERCODE.toString()))
            .andExpect(jsonPath("$.orderstatus").value(DEFAULT_ORDERSTATUS.toString()))
            .andExpect(jsonPath("$.sum").value(DEFAULT_SUM.intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.payee").value(DEFAULT_PAYEE.toString()))
            .andExpect(jsonPath("$.payway").value(DEFAULT_PAYWAY.toString()))
            .andExpect(jsonPath("$.payresult").value(DEFAULT_PAYRESULT.toString()))
            .andExpect(jsonPath("$.paytime").value(DEFAULT_PAYTIME.toString()))
            .andExpect(jsonPath("$.concession").value(DEFAULT_CONCESSION))
            .andExpect(jsonPath("$.rebate").value(DEFAULT_REBATE))
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
    public void getNonExistingUserorder() throws Exception {
        // Get the userorder
        restUserorderMockMvc.perform(get("/api/userorders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserorder() throws Exception {
        // Initialize the database
        userorderRepository.saveAndFlush(userorder);

        int databaseSizeBeforeUpdate = userorderRepository.findAll().size();

        // Update the userorder
        Userorder updatedUserorder = userorderRepository.findById(userorder.getId()).get();
        // Disconnect from session so that the updates on updatedUserorder are not directly saved in db
        em.detach(updatedUserorder);
        updatedUserorder
            .ordercode(UPDATED_ORDERCODE)
            .orderstatus(UPDATED_ORDERSTATUS)
            .sum(UPDATED_SUM)
            .userid(UPDATED_USERID)
            .payee(UPDATED_PAYEE)
            .payway(UPDATED_PAYWAY)
            .payresult(UPDATED_PAYRESULT)
            .paytime(UPDATED_PAYTIME)
            .concession(UPDATED_CONCESSION)
            .rebate(UPDATED_REBATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        UserorderDTO userorderDTO = userorderMapper.toDto(updatedUserorder);

        restUserorderMockMvc.perform(put("/api/userorders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userorderDTO)))
            .andExpect(status().isOk());

        // Validate the Userorder in the database
        List<Userorder> userorderList = userorderRepository.findAll();
        assertThat(userorderList).hasSize(databaseSizeBeforeUpdate);
        Userorder testUserorder = userorderList.get(userorderList.size() - 1);
        assertThat(testUserorder.getOrdercode()).isEqualTo(UPDATED_ORDERCODE);
        assertThat(testUserorder.getOrderstatus()).isEqualTo(UPDATED_ORDERSTATUS);
        assertThat(testUserorder.getSum()).isEqualTo(UPDATED_SUM);
        assertThat(testUserorder.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testUserorder.getPayee()).isEqualTo(UPDATED_PAYEE);
        assertThat(testUserorder.getPayway()).isEqualTo(UPDATED_PAYWAY);
        assertThat(testUserorder.getPayresult()).isEqualTo(UPDATED_PAYRESULT);
        assertThat(testUserorder.getPaytime()).isEqualTo(UPDATED_PAYTIME);
        assertThat(testUserorder.getConcession()).isEqualTo(UPDATED_CONCESSION);
        assertThat(testUserorder.getRebate()).isEqualTo(UPDATED_REBATE);
        assertThat(testUserorder.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testUserorder.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testUserorder.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testUserorder.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testUserorder.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testUserorder.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testUserorder.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingUserorder() throws Exception {
        int databaseSizeBeforeUpdate = userorderRepository.findAll().size();

        // Create the Userorder
        UserorderDTO userorderDTO = userorderMapper.toDto(userorder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserorderMockMvc.perform(put("/api/userorders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userorderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Userorder in the database
        List<Userorder> userorderList = userorderRepository.findAll();
        assertThat(userorderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserorder() throws Exception {
        // Initialize the database
        userorderRepository.saveAndFlush(userorder);

        int databaseSizeBeforeDelete = userorderRepository.findAll().size();

        // Delete the userorder
        restUserorderMockMvc.perform(delete("/api/userorders/{id}", userorder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Userorder> userorderList = userorderRepository.findAll();
        assertThat(userorderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Userorder.class);
        Userorder userorder1 = new Userorder();
        userorder1.setId(1L);
        Userorder userorder2 = new Userorder();
        userorder2.setId(userorder1.getId());
        assertThat(userorder1).isEqualTo(userorder2);
        userorder2.setId(2L);
        assertThat(userorder1).isNotEqualTo(userorder2);
        userorder1.setId(null);
        assertThat(userorder1).isNotEqualTo(userorder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserorderDTO.class);
        UserorderDTO userorderDTO1 = new UserorderDTO();
        userorderDTO1.setId(1L);
        UserorderDTO userorderDTO2 = new UserorderDTO();
        assertThat(userorderDTO1).isNotEqualTo(userorderDTO2);
        userorderDTO2.setId(userorderDTO1.getId());
        assertThat(userorderDTO1).isEqualTo(userorderDTO2);
        userorderDTO2.setId(2L);
        assertThat(userorderDTO1).isNotEqualTo(userorderDTO2);
        userorderDTO1.setId(null);
        assertThat(userorderDTO1).isNotEqualTo(userorderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userorderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userorderMapper.fromId(null)).isNull();
    }
}
