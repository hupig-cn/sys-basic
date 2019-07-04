package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Coupon;
import com.weisen.www.code.yjf.basic.repository.CouponRepository;
import com.weisen.www.code.yjf.basic.service.CouponService;
import com.weisen.www.code.yjf.basic.service.dto.CouponDTO;
import com.weisen.www.code.yjf.basic.service.mapper.CouponMapper;
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
 * Integration tests for the {@Link CouponResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class CouponResourceIT {

    private static final String DEFAULT_COUPONTYPE = "AAAAAAAAAA";
    private static final String UPDATED_COUPONTYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LINEON = false;
    private static final Boolean UPDATED_LINEON = true;

    private static final Boolean DEFAULT_LINEUNDER = false;
    private static final Boolean UPDATED_LINEUNDER = true;

    private static final Boolean DEFAULT_INTEGRAL = false;
    private static final Boolean UPDATED_INTEGRAL = true;

    private static final Boolean DEFAULT_PROFIT = false;
    private static final Boolean UPDATED_PROFIT = true;

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
    private CouponRepository couponRepository;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponService couponService;

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

    private MockMvc restCouponMockMvc;

    private Coupon coupon;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CouponResource couponResource = new CouponResource(couponService);
        this.restCouponMockMvc = MockMvcBuilders.standaloneSetup(couponResource)
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
    public static Coupon createEntity(EntityManager em) {
        Coupon coupon = new Coupon()
            .coupontype(DEFAULT_COUPONTYPE)
            .lineon(DEFAULT_LINEON)
            .lineunder(DEFAULT_LINEUNDER)
            .integral(DEFAULT_INTEGRAL)
            .profit(DEFAULT_PROFIT)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return coupon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Coupon createUpdatedEntity(EntityManager em) {
        Coupon coupon = new Coupon()
            .coupontype(UPDATED_COUPONTYPE)
            .lineon(UPDATED_LINEON)
            .lineunder(UPDATED_LINEUNDER)
            .integral(UPDATED_INTEGRAL)
            .profit(UPDATED_PROFIT)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return coupon;
    }

    @BeforeEach
    public void initTest() {
        coupon = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoupon() throws Exception {
        int databaseSizeBeforeCreate = couponRepository.findAll().size();

        // Create the Coupon
        CouponDTO couponDTO = couponMapper.toDto(coupon);
        restCouponMockMvc.perform(post("/api/coupons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(couponDTO)))
            .andExpect(status().isCreated());

        // Validate the Coupon in the database
        List<Coupon> couponList = couponRepository.findAll();
        assertThat(couponList).hasSize(databaseSizeBeforeCreate + 1);
        Coupon testCoupon = couponList.get(couponList.size() - 1);
        assertThat(testCoupon.getCoupontype()).isEqualTo(DEFAULT_COUPONTYPE);
        assertThat(testCoupon.isLineon()).isEqualTo(DEFAULT_LINEON);
        assertThat(testCoupon.isLineunder()).isEqualTo(DEFAULT_LINEUNDER);
        assertThat(testCoupon.isIntegral()).isEqualTo(DEFAULT_INTEGRAL);
        assertThat(testCoupon.isProfit()).isEqualTo(DEFAULT_PROFIT);
        assertThat(testCoupon.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testCoupon.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testCoupon.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testCoupon.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testCoupon.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testCoupon.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testCoupon.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createCouponWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = couponRepository.findAll().size();

        // Create the Coupon with an existing ID
        coupon.setId(1L);
        CouponDTO couponDTO = couponMapper.toDto(coupon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCouponMockMvc.perform(post("/api/coupons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(couponDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Coupon in the database
        List<Coupon> couponList = couponRepository.findAll();
        assertThat(couponList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCoupons() throws Exception {
        // Initialize the database
        couponRepository.saveAndFlush(coupon);

        // Get all the couponList
        restCouponMockMvc.perform(get("/api/coupons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coupon.getId().intValue())))
            .andExpect(jsonPath("$.[*].coupontype").value(hasItem(DEFAULT_COUPONTYPE.toString())))
            .andExpect(jsonPath("$.[*].lineon").value(hasItem(DEFAULT_LINEON.booleanValue())))
            .andExpect(jsonPath("$.[*].lineunder").value(hasItem(DEFAULT_LINEUNDER.booleanValue())))
            .andExpect(jsonPath("$.[*].integral").value(hasItem(DEFAULT_INTEGRAL.booleanValue())))
            .andExpect(jsonPath("$.[*].profit").value(hasItem(DEFAULT_PROFIT.booleanValue())))
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
    public void getCoupon() throws Exception {
        // Initialize the database
        couponRepository.saveAndFlush(coupon);

        // Get the coupon
        restCouponMockMvc.perform(get("/api/coupons/{id}", coupon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coupon.getId().intValue()))
            .andExpect(jsonPath("$.coupontype").value(DEFAULT_COUPONTYPE.toString()))
            .andExpect(jsonPath("$.lineon").value(DEFAULT_LINEON.booleanValue()))
            .andExpect(jsonPath("$.lineunder").value(DEFAULT_LINEUNDER.booleanValue()))
            .andExpect(jsonPath("$.integral").value(DEFAULT_INTEGRAL.booleanValue()))
            .andExpect(jsonPath("$.profit").value(DEFAULT_PROFIT.booleanValue()))
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
    public void getNonExistingCoupon() throws Exception {
        // Get the coupon
        restCouponMockMvc.perform(get("/api/coupons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoupon() throws Exception {
        // Initialize the database
        couponRepository.saveAndFlush(coupon);

        int databaseSizeBeforeUpdate = couponRepository.findAll().size();

        // Update the coupon
        Coupon updatedCoupon = couponRepository.findById(coupon.getId()).get();
        // Disconnect from session so that the updates on updatedCoupon are not directly saved in db
        em.detach(updatedCoupon);
        updatedCoupon
            .coupontype(UPDATED_COUPONTYPE)
            .lineon(UPDATED_LINEON)
            .lineunder(UPDATED_LINEUNDER)
            .integral(UPDATED_INTEGRAL)
            .profit(UPDATED_PROFIT)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        CouponDTO couponDTO = couponMapper.toDto(updatedCoupon);

        restCouponMockMvc.perform(put("/api/coupons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(couponDTO)))
            .andExpect(status().isOk());

        // Validate the Coupon in the database
        List<Coupon> couponList = couponRepository.findAll();
        assertThat(couponList).hasSize(databaseSizeBeforeUpdate);
        Coupon testCoupon = couponList.get(couponList.size() - 1);
        assertThat(testCoupon.getCoupontype()).isEqualTo(UPDATED_COUPONTYPE);
        assertThat(testCoupon.isLineon()).isEqualTo(UPDATED_LINEON);
        assertThat(testCoupon.isLineunder()).isEqualTo(UPDATED_LINEUNDER);
        assertThat(testCoupon.isIntegral()).isEqualTo(UPDATED_INTEGRAL);
        assertThat(testCoupon.isProfit()).isEqualTo(UPDATED_PROFIT);
        assertThat(testCoupon.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testCoupon.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testCoupon.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testCoupon.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testCoupon.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testCoupon.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testCoupon.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingCoupon() throws Exception {
        int databaseSizeBeforeUpdate = couponRepository.findAll().size();

        // Create the Coupon
        CouponDTO couponDTO = couponMapper.toDto(coupon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCouponMockMvc.perform(put("/api/coupons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(couponDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Coupon in the database
        List<Coupon> couponList = couponRepository.findAll();
        assertThat(couponList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoupon() throws Exception {
        // Initialize the database
        couponRepository.saveAndFlush(coupon);

        int databaseSizeBeforeDelete = couponRepository.findAll().size();

        // Delete the coupon
        restCouponMockMvc.perform(delete("/api/coupons/{id}", coupon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Coupon> couponList = couponRepository.findAll();
        assertThat(couponList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coupon.class);
        Coupon coupon1 = new Coupon();
        coupon1.setId(1L);
        Coupon coupon2 = new Coupon();
        coupon2.setId(coupon1.getId());
        assertThat(coupon1).isEqualTo(coupon2);
        coupon2.setId(2L);
        assertThat(coupon1).isNotEqualTo(coupon2);
        coupon1.setId(null);
        assertThat(coupon1).isNotEqualTo(coupon2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CouponDTO.class);
        CouponDTO couponDTO1 = new CouponDTO();
        couponDTO1.setId(1L);
        CouponDTO couponDTO2 = new CouponDTO();
        assertThat(couponDTO1).isNotEqualTo(couponDTO2);
        couponDTO2.setId(couponDTO1.getId());
        assertThat(couponDTO1).isEqualTo(couponDTO2);
        couponDTO2.setId(2L);
        assertThat(couponDTO1).isNotEqualTo(couponDTO2);
        couponDTO1.setId(null);
        assertThat(couponDTO1).isNotEqualTo(couponDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(couponMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(couponMapper.fromId(null)).isNull();
    }
}
