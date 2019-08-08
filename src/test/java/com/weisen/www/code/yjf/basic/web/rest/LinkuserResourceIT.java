package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.repository.LinkuserRepository;
import com.weisen.www.code.yjf.basic.service.LinkuserService;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;
import com.weisen.www.code.yjf.basic.service.mapper.LinkuserMapper;
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
 * Integration tests for the {@Link LinkuserResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class LinkuserResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ALIPAY = "AAAAAAAAAA";
    private static final String UPDATED_ALIPAY = "BBBBBBBBBB";

    private static final String DEFAULT_WECHAT = "AAAAAAAAAA";
    private static final String UPDATED_WECHAT = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IDCARD = "AAAAAAAAAA";
    private static final String UPDATED_IDCARD = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final String DEFAULT_PAYPASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PAYPASSWORD = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYCOUNT = 1;
    private static final Integer UPDATED_PAYCOUNT = 2;

    private static final String DEFAULT_PAYLASTTIME = "AAAAAAAAAA";
    private static final String UPDATED_PAYLASTTIME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTY = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOGINNUM = 1;
    private static final Integer UPDATED_LOGINNUM = 2;

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
    private LinkuserRepository linkuserRepository;

    @Autowired
    private LinkuserMapper linkuserMapper;

    @Autowired
    private LinkuserService linkuserService;

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

    private MockMvc restLinkuserMockMvc;

    private Linkuser linkuser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LinkuserResource linkuserResource = new LinkuserResource(linkuserService);
        this.restLinkuserMockMvc = MockMvcBuilders.standaloneSetup(linkuserResource)
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
    public static Linkuser createEntity(EntityManager em) {
        Linkuser linkuser = new Linkuser()
            .userid(DEFAULT_USERID)
            .phone(DEFAULT_PHONE)
            .alipay(DEFAULT_ALIPAY)
            .wechat(DEFAULT_WECHAT)
            .name(DEFAULT_NAME)
            .idcard(DEFAULT_IDCARD)
            .sex(DEFAULT_SEX)
            .paypassword(DEFAULT_PAYPASSWORD)
            .paycount(DEFAULT_PAYCOUNT)
            .paylasttime(DEFAULT_PAYLASTTIME)
            .address(DEFAULT_ADDRESS)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .county(DEFAULT_COUNTY)
            .loginnum(DEFAULT_LOGINNUM)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return linkuser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Linkuser createUpdatedEntity(EntityManager em) {
        Linkuser linkuser = new Linkuser()
            .userid(UPDATED_USERID)
            .phone(UPDATED_PHONE)
            .alipay(UPDATED_ALIPAY)
            .wechat(UPDATED_WECHAT)
            .name(UPDATED_NAME)
            .idcard(UPDATED_IDCARD)
            .sex(UPDATED_SEX)
            .paypassword(UPDATED_PAYPASSWORD)
            .paycount(UPDATED_PAYCOUNT)
            .paylasttime(UPDATED_PAYLASTTIME)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .county(UPDATED_COUNTY)
            .loginnum(UPDATED_LOGINNUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return linkuser;
    }

    @BeforeEach
    public void initTest() {
        linkuser = createEntity(em);
    }

    @Test
    @Transactional
    public void createLinkuser() throws Exception {
        int databaseSizeBeforeCreate = linkuserRepository.findAll().size();

        // Create the Linkuser
        LinkuserDTO linkuserDTO = linkuserMapper.toDto(linkuser);
        restLinkuserMockMvc.perform(post("/api/linkusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkuserDTO)))
            .andExpect(status().isCreated());

        // Validate the Linkuser in the database
        List<Linkuser> linkuserList = linkuserRepository.findAll();
        assertThat(linkuserList).hasSize(databaseSizeBeforeCreate + 1);
        Linkuser testLinkuser = linkuserList.get(linkuserList.size() - 1);
        assertThat(testLinkuser.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testLinkuser.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testLinkuser.getAlipay()).isEqualTo(DEFAULT_ALIPAY);
        assertThat(testLinkuser.getWechat()).isEqualTo(DEFAULT_WECHAT);
        assertThat(testLinkuser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLinkuser.getIdcard()).isEqualTo(DEFAULT_IDCARD);
        assertThat(testLinkuser.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testLinkuser.getPaypassword()).isEqualTo(DEFAULT_PAYPASSWORD);
        assertThat(testLinkuser.getPaycount()).isEqualTo(DEFAULT_PAYCOUNT);
        assertThat(testLinkuser.getPaylasttime()).isEqualTo(DEFAULT_PAYLASTTIME);
        assertThat(testLinkuser.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testLinkuser.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testLinkuser.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLinkuser.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testLinkuser.getLoginnum()).isEqualTo(DEFAULT_LOGINNUM);
        assertThat(testLinkuser.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testLinkuser.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testLinkuser.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testLinkuser.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testLinkuser.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testLinkuser.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testLinkuser.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createLinkuserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = linkuserRepository.findAll().size();

        // Create the Linkuser with an existing ID
        linkuser.setId(1L);
        LinkuserDTO linkuserDTO = linkuserMapper.toDto(linkuser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinkuserMockMvc.perform(post("/api/linkusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkuserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Linkuser in the database
        List<Linkuser> linkuserList = linkuserRepository.findAll();
        assertThat(linkuserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLinkusers() throws Exception {
        // Initialize the database
        linkuserRepository.saveAndFlush(linkuser);

        // Get all the linkuserList
        restLinkuserMockMvc.perform(get("/api/linkusers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(linkuser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].alipay").value(hasItem(DEFAULT_ALIPAY.toString())))
            .andExpect(jsonPath("$.[*].wechat").value(hasItem(DEFAULT_WECHAT.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].idcard").value(hasItem(DEFAULT_IDCARD.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].paypassword").value(hasItem(DEFAULT_PAYPASSWORD.toString())))
            .andExpect(jsonPath("$.[*].paycount").value(hasItem(DEFAULT_PAYCOUNT)))
            .andExpect(jsonPath("$.[*].paylasttime").value(hasItem(DEFAULT_PAYLASTTIME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY.toString())))
            .andExpect(jsonPath("$.[*].loginnum").value(hasItem(DEFAULT_LOGINNUM)))
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
    public void getLinkuser() throws Exception {
        // Initialize the database
        linkuserRepository.saveAndFlush(linkuser);

        // Get the linkuser
        restLinkuserMockMvc.perform(get("/api/linkusers/{id}", linkuser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(linkuser.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.alipay").value(DEFAULT_ALIPAY.toString()))
            .andExpect(jsonPath("$.wechat").value(DEFAULT_WECHAT.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.idcard").value(DEFAULT_IDCARD.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.paypassword").value(DEFAULT_PAYPASSWORD.toString()))
            .andExpect(jsonPath("$.paycount").value(DEFAULT_PAYCOUNT))
            .andExpect(jsonPath("$.paylasttime").value(DEFAULT_PAYLASTTIME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY.toString()))
            .andExpect(jsonPath("$.loginnum").value(DEFAULT_LOGINNUM))
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
    public void getNonExistingLinkuser() throws Exception {
        // Get the linkuser
        restLinkuserMockMvc.perform(get("/api/linkusers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLinkuser() throws Exception {
        // Initialize the database
        linkuserRepository.saveAndFlush(linkuser);

        int databaseSizeBeforeUpdate = linkuserRepository.findAll().size();

        // Update the linkuser
        Linkuser updatedLinkuser = linkuserRepository.findById(linkuser.getId()).get();
        // Disconnect from session so that the updates on updatedLinkuser are not directly saved in db
        em.detach(updatedLinkuser);
        updatedLinkuser
            .userid(UPDATED_USERID)
            .phone(UPDATED_PHONE)
            .alipay(UPDATED_ALIPAY)
            .wechat(UPDATED_WECHAT)
            .name(UPDATED_NAME)
            .idcard(UPDATED_IDCARD)
            .sex(UPDATED_SEX)
            .paypassword(UPDATED_PAYPASSWORD)
            .paycount(UPDATED_PAYCOUNT)
            .paylasttime(UPDATED_PAYLASTTIME)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .county(UPDATED_COUNTY)
            .loginnum(UPDATED_LOGINNUM)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        LinkuserDTO linkuserDTO = linkuserMapper.toDto(updatedLinkuser);

        restLinkuserMockMvc.perform(put("/api/linkusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkuserDTO)))
            .andExpect(status().isOk());

        // Validate the Linkuser in the database
        List<Linkuser> linkuserList = linkuserRepository.findAll();
        assertThat(linkuserList).hasSize(databaseSizeBeforeUpdate);
        Linkuser testLinkuser = linkuserList.get(linkuserList.size() - 1);
        assertThat(testLinkuser.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testLinkuser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testLinkuser.getAlipay()).isEqualTo(UPDATED_ALIPAY);
        assertThat(testLinkuser.getWechat()).isEqualTo(UPDATED_WECHAT);
        assertThat(testLinkuser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLinkuser.getIdcard()).isEqualTo(UPDATED_IDCARD);
        assertThat(testLinkuser.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testLinkuser.getPaypassword()).isEqualTo(UPDATED_PAYPASSWORD);
        assertThat(testLinkuser.getPaycount()).isEqualTo(UPDATED_PAYCOUNT);
        assertThat(testLinkuser.getPaylasttime()).isEqualTo(UPDATED_PAYLASTTIME);
        assertThat(testLinkuser.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testLinkuser.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testLinkuser.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLinkuser.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testLinkuser.getLoginnum()).isEqualTo(UPDATED_LOGINNUM);
        assertThat(testLinkuser.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testLinkuser.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testLinkuser.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testLinkuser.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testLinkuser.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testLinkuser.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testLinkuser.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingLinkuser() throws Exception {
        int databaseSizeBeforeUpdate = linkuserRepository.findAll().size();

        // Create the Linkuser
        LinkuserDTO linkuserDTO = linkuserMapper.toDto(linkuser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinkuserMockMvc.perform(put("/api/linkusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkuserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Linkuser in the database
        List<Linkuser> linkuserList = linkuserRepository.findAll();
        assertThat(linkuserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLinkuser() throws Exception {
        // Initialize the database
        linkuserRepository.saveAndFlush(linkuser);

        int databaseSizeBeforeDelete = linkuserRepository.findAll().size();

        // Delete the linkuser
        restLinkuserMockMvc.perform(delete("/api/linkusers/{id}", linkuser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Linkuser> linkuserList = linkuserRepository.findAll();
        assertThat(linkuserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Linkuser.class);
        Linkuser linkuser1 = new Linkuser();
        linkuser1.setId(1L);
        Linkuser linkuser2 = new Linkuser();
        linkuser2.setId(linkuser1.getId());
        assertThat(linkuser1).isEqualTo(linkuser2);
        linkuser2.setId(2L);
        assertThat(linkuser1).isNotEqualTo(linkuser2);
        linkuser1.setId(null);
        assertThat(linkuser1).isNotEqualTo(linkuser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinkuserDTO.class);
        LinkuserDTO linkuserDTO1 = new LinkuserDTO();
        linkuserDTO1.setId(1L);
        LinkuserDTO linkuserDTO2 = new LinkuserDTO();
        assertThat(linkuserDTO1).isNotEqualTo(linkuserDTO2);
        linkuserDTO2.setId(linkuserDTO1.getId());
        assertThat(linkuserDTO1).isEqualTo(linkuserDTO2);
        linkuserDTO2.setId(2L);
        assertThat(linkuserDTO1).isNotEqualTo(linkuserDTO2);
        linkuserDTO1.setId(null);
        assertThat(linkuserDTO1).isNotEqualTo(linkuserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(linkuserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(linkuserMapper.fromId(null)).isNull();
    }
}
