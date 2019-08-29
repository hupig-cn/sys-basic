package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Statistics;
import com.weisen.www.code.yjf.basic.repository.StatisticsRepository;
import com.weisen.www.code.yjf.basic.service.StatisticsService;
import com.weisen.www.code.yjf.basic.service.dto.StatisticsDTO;
import com.weisen.www.code.yjf.basic.service.mapper.StatisticsMapper;
import com.weisen.www.code.yjf.basic.web.rest.errors.ExceptionTranslator;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
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

//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;

/**
 * Test class for the StatisticsResource REST controller.
 *
 * @see StatisticsResource
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class StatisticsResourceIntTest {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_USEABLEBALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_USEABLEBALANCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_FREEZEBALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FREEZEBALANCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INTEGRAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTEGRAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_COUPON = new BigDecimal(1);
    private static final BigDecimal UPDATED_COUPON = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EXPENDINTEGRAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXPENDINTEGRAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INCOMEINTEGRAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_INCOMEINTEGRAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EXPENDCOUPON = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXPENDCOUPON = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INCOMECOUPON = new BigDecimal(1);
    private static final BigDecimal UPDATED_INCOMECOUPON = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PROCEEDS = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROCEEDS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EARNINGS = new BigDecimal(1);
    private static final BigDecimal UPDATED_EARNINGS = new BigDecimal(2);

    private static final String DEFAULT_CREATEDATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDATE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_EXPENSE = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXPENSE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WITHDRAW = new BigDecimal(1);
    private static final BigDecimal UPDATED_WITHDRAW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WITHDRAWSUCCESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_WITHDRAWSUCCESS = new BigDecimal(2);

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    private StatisticsService statisticsService;

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

    private MockMvc restStatisticsMockMvc;

    private Statistics statistics;

//    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatisticsResource statisticsResource = new StatisticsResource(statisticsService);
        this.restStatisticsMockMvc = MockMvcBuilders.standaloneSetup(statisticsResource)
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
    public static Statistics createEntity(EntityManager em) {
        Statistics statistics = new Statistics()
            .userid(DEFAULT_USERID)
            .balance(DEFAULT_BALANCE)
            .useablebalance(DEFAULT_USEABLEBALANCE)
            .freezebalance(DEFAULT_FREEZEBALANCE)
            .integral(DEFAULT_INTEGRAL)
            .coupon(DEFAULT_COUPON)
            .expendintegral(DEFAULT_EXPENDINTEGRAL)
            .incomeintegral(DEFAULT_INCOMEINTEGRAL)
            .expendcoupon(DEFAULT_EXPENDCOUPON)
            .incomecoupon(DEFAULT_INCOMECOUPON)
            .proceeds(DEFAULT_PROCEEDS)
            .earnings(DEFAULT_EARNINGS)
            .createdate(DEFAULT_CREATEDATE)
            .expense(DEFAULT_EXPENSE)
            .withdraw(DEFAULT_WITHDRAW)
            .withdrawsuccess(DEFAULT_WITHDRAWSUCCESS);
        return statistics;
    }

//    @Before
    public void initTest() {
        statistics = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatistics() throws Exception {
        int databaseSizeBeforeCreate = statisticsRepository.findAll().size();

        // Create the Statistics
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistics);
        restStatisticsMockMvc.perform(post("/api/statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isCreated());

        // Validate the Statistics in the database
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeCreate + 1);
        Statistics testStatistics = statisticsList.get(statisticsList.size() - 1);
        assertThat(testStatistics.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testStatistics.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testStatistics.getUseablebalance()).isEqualTo(DEFAULT_USEABLEBALANCE);
        assertThat(testStatistics.getFreezebalance()).isEqualTo(DEFAULT_FREEZEBALANCE);
        assertThat(testStatistics.getIntegral()).isEqualTo(DEFAULT_INTEGRAL);
        assertThat(testStatistics.getCoupon()).isEqualTo(DEFAULT_COUPON);
        assertThat(testStatistics.getExpendintegral()).isEqualTo(DEFAULT_EXPENDINTEGRAL);
        assertThat(testStatistics.getIncomeintegral()).isEqualTo(DEFAULT_INCOMEINTEGRAL);
        assertThat(testStatistics.getExpendcoupon()).isEqualTo(DEFAULT_EXPENDCOUPON);
        assertThat(testStatistics.getIncomecoupon()).isEqualTo(DEFAULT_INCOMECOUPON);
        assertThat(testStatistics.getProceeds()).isEqualTo(DEFAULT_PROCEEDS);
        assertThat(testStatistics.getEarnings()).isEqualTo(DEFAULT_EARNINGS);
        assertThat(testStatistics.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testStatistics.getExpense()).isEqualTo(DEFAULT_EXPENSE);
        assertThat(testStatistics.getWithdraw()).isEqualTo(DEFAULT_WITHDRAW);
        assertThat(testStatistics.getWithdrawsuccess()).isEqualTo(DEFAULT_WITHDRAWSUCCESS);
    }

    @Test
    @Transactional
    public void createStatisticsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statisticsRepository.findAll().size();

        // Create the Statistics with an existing ID
        statistics.setId(1L);
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistics);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatisticsMockMvc.perform(post("/api/statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Statistics in the database
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStatistics() throws Exception {
        // Initialize the database
        statisticsRepository.saveAndFlush(statistics);

        // Get all the statisticsList
        restStatisticsMockMvc.perform(get("/api/statistics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statistics.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].useablebalance").value(hasItem(DEFAULT_USEABLEBALANCE.intValue())))
            .andExpect(jsonPath("$.[*].freezebalance").value(hasItem(DEFAULT_FREEZEBALANCE.intValue())))
            .andExpect(jsonPath("$.[*].integral").value(hasItem(DEFAULT_INTEGRAL.intValue())))
            .andExpect(jsonPath("$.[*].coupon").value(hasItem(DEFAULT_COUPON.intValue())))
            .andExpect(jsonPath("$.[*].expendintegral").value(hasItem(DEFAULT_EXPENDINTEGRAL.intValue())))
            .andExpect(jsonPath("$.[*].incomeintegral").value(hasItem(DEFAULT_INCOMEINTEGRAL.intValue())))
            .andExpect(jsonPath("$.[*].expendcoupon").value(hasItem(DEFAULT_EXPENDCOUPON.intValue())))
            .andExpect(jsonPath("$.[*].incomecoupon").value(hasItem(DEFAULT_INCOMECOUPON.intValue())))
            .andExpect(jsonPath("$.[*].proceeds").value(hasItem(DEFAULT_PROCEEDS.intValue())))
            .andExpect(jsonPath("$.[*].earnings").value(hasItem(DEFAULT_EARNINGS.intValue())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].expense").value(hasItem(DEFAULT_EXPENSE.intValue())))
            .andExpect(jsonPath("$.[*].withdraw").value(hasItem(DEFAULT_WITHDRAW.intValue())))
            .andExpect(jsonPath("$.[*].withdrawsuccess").value(hasItem(DEFAULT_WITHDRAWSUCCESS.intValue())));
    }
    
    @Test
    @Transactional
    public void getStatistics() throws Exception {
        // Initialize the database
        statisticsRepository.saveAndFlush(statistics);

        // Get the statistics
        restStatisticsMockMvc.perform(get("/api/statistics/{id}", statistics.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statistics.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.useablebalance").value(DEFAULT_USEABLEBALANCE.intValue()))
            .andExpect(jsonPath("$.freezebalance").value(DEFAULT_FREEZEBALANCE.intValue()))
            .andExpect(jsonPath("$.integral").value(DEFAULT_INTEGRAL.intValue()))
            .andExpect(jsonPath("$.coupon").value(DEFAULT_COUPON.intValue()))
            .andExpect(jsonPath("$.expendintegral").value(DEFAULT_EXPENDINTEGRAL.intValue()))
            .andExpect(jsonPath("$.incomeintegral").value(DEFAULT_INCOMEINTEGRAL.intValue()))
            .andExpect(jsonPath("$.expendcoupon").value(DEFAULT_EXPENDCOUPON.intValue()))
            .andExpect(jsonPath("$.incomecoupon").value(DEFAULT_INCOMECOUPON.intValue()))
            .andExpect(jsonPath("$.proceeds").value(DEFAULT_PROCEEDS.intValue()))
            .andExpect(jsonPath("$.earnings").value(DEFAULT_EARNINGS.intValue()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.expense").value(DEFAULT_EXPENSE.intValue()))
            .andExpect(jsonPath("$.withdraw").value(DEFAULT_WITHDRAW.intValue()))
            .andExpect(jsonPath("$.withdrawsuccess").value(DEFAULT_WITHDRAWSUCCESS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingStatistics() throws Exception {
        // Get the statistics
        restStatisticsMockMvc.perform(get("/api/statistics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatistics() throws Exception {
        // Initialize the database
        statisticsRepository.saveAndFlush(statistics);

        int databaseSizeBeforeUpdate = statisticsRepository.findAll().size();

        // Update the statistics
        Statistics updatedStatistics = statisticsRepository.findById(statistics.getId()).get();
        // Disconnect from session so that the updates on updatedStatistics are not directly saved in db
        em.detach(updatedStatistics);
        updatedStatistics
            .userid(UPDATED_USERID)
            .balance(UPDATED_BALANCE)
            .useablebalance(UPDATED_USEABLEBALANCE)
            .freezebalance(UPDATED_FREEZEBALANCE)
            .integral(UPDATED_INTEGRAL)
            .coupon(UPDATED_COUPON)
            .expendintegral(UPDATED_EXPENDINTEGRAL)
            .incomeintegral(UPDATED_INCOMEINTEGRAL)
            .expendcoupon(UPDATED_EXPENDCOUPON)
            .incomecoupon(UPDATED_INCOMECOUPON)
            .proceeds(UPDATED_PROCEEDS)
            .earnings(UPDATED_EARNINGS)
            .createdate(UPDATED_CREATEDATE)
            .expense(UPDATED_EXPENSE)
            .withdraw(UPDATED_WITHDRAW)
            .withdrawsuccess(UPDATED_WITHDRAWSUCCESS);
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(updatedStatistics);

        restStatisticsMockMvc.perform(put("/api/statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isOk());

        // Validate the Statistics in the database
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeUpdate);
        Statistics testStatistics = statisticsList.get(statisticsList.size() - 1);
        assertThat(testStatistics.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testStatistics.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testStatistics.getUseablebalance()).isEqualTo(UPDATED_USEABLEBALANCE);
        assertThat(testStatistics.getFreezebalance()).isEqualTo(UPDATED_FREEZEBALANCE);
        assertThat(testStatistics.getIntegral()).isEqualTo(UPDATED_INTEGRAL);
        assertThat(testStatistics.getCoupon()).isEqualTo(UPDATED_COUPON);
        assertThat(testStatistics.getExpendintegral()).isEqualTo(UPDATED_EXPENDINTEGRAL);
        assertThat(testStatistics.getIncomeintegral()).isEqualTo(UPDATED_INCOMEINTEGRAL);
        assertThat(testStatistics.getExpendcoupon()).isEqualTo(UPDATED_EXPENDCOUPON);
        assertThat(testStatistics.getIncomecoupon()).isEqualTo(UPDATED_INCOMECOUPON);
        assertThat(testStatistics.getProceeds()).isEqualTo(UPDATED_PROCEEDS);
        assertThat(testStatistics.getEarnings()).isEqualTo(UPDATED_EARNINGS);
        assertThat(testStatistics.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testStatistics.getExpense()).isEqualTo(UPDATED_EXPENSE);
        assertThat(testStatistics.getWithdraw()).isEqualTo(UPDATED_WITHDRAW);
        assertThat(testStatistics.getWithdrawsuccess()).isEqualTo(UPDATED_WITHDRAWSUCCESS);
    }

    @Test
    @Transactional
    public void updateNonExistingStatistics() throws Exception {
        int databaseSizeBeforeUpdate = statisticsRepository.findAll().size();

        // Create the Statistics
        StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistics);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStatisticsMockMvc.perform(put("/api/statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statisticsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Statistics in the database
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStatistics() throws Exception {
        // Initialize the database
        statisticsRepository.saveAndFlush(statistics);

        int databaseSizeBeforeDelete = statisticsRepository.findAll().size();

        // Delete the statistics
        restStatisticsMockMvc.perform(delete("/api/statistics/{id}", statistics.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Statistics> statisticsList = statisticsRepository.findAll();
        assertThat(statisticsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Statistics.class);
        Statistics statistics1 = new Statistics();
        statistics1.setId(1L);
        Statistics statistics2 = new Statistics();
        statistics2.setId(statistics1.getId());
        assertThat(statistics1).isEqualTo(statistics2);
        statistics2.setId(2L);
        assertThat(statistics1).isNotEqualTo(statistics2);
        statistics1.setId(null);
        assertThat(statistics1).isNotEqualTo(statistics2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatisticsDTO.class);
        StatisticsDTO statisticsDTO1 = new StatisticsDTO();
        statisticsDTO1.setId(1L);
        StatisticsDTO statisticsDTO2 = new StatisticsDTO();
        assertThat(statisticsDTO1).isNotEqualTo(statisticsDTO2);
        statisticsDTO2.setId(statisticsDTO1.getId());
        assertThat(statisticsDTO1).isEqualTo(statisticsDTO2);
        statisticsDTO2.setId(2L);
        assertThat(statisticsDTO1).isNotEqualTo(statisticsDTO2);
        statisticsDTO1.setId(null);
        assertThat(statisticsDTO1).isNotEqualTo(statisticsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(statisticsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(statisticsMapper.fromId(null)).isNull();
    }
}
