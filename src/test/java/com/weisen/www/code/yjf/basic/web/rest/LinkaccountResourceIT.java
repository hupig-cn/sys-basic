package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.repository.LinkaccountRepository;
import com.weisen.www.code.yjf.basic.service.LinkaccountService;
import com.weisen.www.code.yjf.basic.service.dto.LinkaccountDTO;
import com.weisen.www.code.yjf.basic.service.mapper.LinkaccountMapper;
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
 * Integration tests for the {@Link LinkaccountResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class LinkaccountResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

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
    private LinkaccountRepository linkaccountRepository;

    @Autowired
    private LinkaccountMapper linkaccountMapper;

    @Autowired
    private LinkaccountService linkaccountService;

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

    private MockMvc restLinkaccountMockMvc;

    private Linkaccount linkaccount;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LinkaccountResource linkaccountResource = new LinkaccountResource(linkaccountService);
        this.restLinkaccountMockMvc = MockMvcBuilders.standaloneSetup(linkaccountResource)
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
    public static Linkaccount createEntity(EntityManager em) {
        Linkaccount linkaccount = new Linkaccount()
            .userid(DEFAULT_USERID)
            .accounttype(DEFAULT_ACCOUNTTYPE)
            .token(DEFAULT_TOKEN)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return linkaccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Linkaccount createUpdatedEntity(EntityManager em) {
        Linkaccount linkaccount = new Linkaccount()
            .userid(UPDATED_USERID)
            .accounttype(UPDATED_ACCOUNTTYPE)
            .token(UPDATED_TOKEN)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return linkaccount;
    }

    @BeforeEach
    public void initTest() {
        linkaccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createLinkaccount() throws Exception {
        int databaseSizeBeforeCreate = linkaccountRepository.findAll().size();

        // Create the Linkaccount
        LinkaccountDTO linkaccountDTO = linkaccountMapper.toDto(linkaccount);
        restLinkaccountMockMvc.perform(post("/api/linkaccounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkaccountDTO)))
            .andExpect(status().isCreated());

        // Validate the Linkaccount in the database
        List<Linkaccount> linkaccountList = linkaccountRepository.findAll();
        assertThat(linkaccountList).hasSize(databaseSizeBeforeCreate + 1);
        Linkaccount testLinkaccount = linkaccountList.get(linkaccountList.size() - 1);
        assertThat(testLinkaccount.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testLinkaccount.getAccounttype()).isEqualTo(DEFAULT_ACCOUNTTYPE);
        assertThat(testLinkaccount.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testLinkaccount.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testLinkaccount.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testLinkaccount.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testLinkaccount.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testLinkaccount.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testLinkaccount.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testLinkaccount.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createLinkaccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = linkaccountRepository.findAll().size();

        // Create the Linkaccount with an existing ID
        linkaccount.setId(1L);
        LinkaccountDTO linkaccountDTO = linkaccountMapper.toDto(linkaccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinkaccountMockMvc.perform(post("/api/linkaccounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkaccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Linkaccount in the database
        List<Linkaccount> linkaccountList = linkaccountRepository.findAll();
        assertThat(linkaccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLinkaccounts() throws Exception {
        // Initialize the database
        linkaccountRepository.saveAndFlush(linkaccount);

        // Get all the linkaccountList
        restLinkaccountMockMvc.perform(get("/api/linkaccounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(linkaccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].accounttype").value(hasItem(DEFAULT_ACCOUNTTYPE.toString())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.toString())))
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
    public void getLinkaccount() throws Exception {
        // Initialize the database
        linkaccountRepository.saveAndFlush(linkaccount);

        // Get the linkaccount
        restLinkaccountMockMvc.perform(get("/api/linkaccounts/{id}", linkaccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(linkaccount.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.accounttype").value(DEFAULT_ACCOUNTTYPE.toString()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.toString()))
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
    public void getNonExistingLinkaccount() throws Exception {
        // Get the linkaccount
        restLinkaccountMockMvc.perform(get("/api/linkaccounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLinkaccount() throws Exception {
        // Initialize the database
        linkaccountRepository.saveAndFlush(linkaccount);

        int databaseSizeBeforeUpdate = linkaccountRepository.findAll().size();

        // Update the linkaccount
        Linkaccount updatedLinkaccount = linkaccountRepository.findById(linkaccount.getId()).get();
        // Disconnect from session so that the updates on updatedLinkaccount are not directly saved in db
        em.detach(updatedLinkaccount);
        updatedLinkaccount
            .userid(UPDATED_USERID)
            .accounttype(UPDATED_ACCOUNTTYPE)
            .token(UPDATED_TOKEN)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        LinkaccountDTO linkaccountDTO = linkaccountMapper.toDto(updatedLinkaccount);

        restLinkaccountMockMvc.perform(put("/api/linkaccounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkaccountDTO)))
            .andExpect(status().isOk());

        // Validate the Linkaccount in the database
        List<Linkaccount> linkaccountList = linkaccountRepository.findAll();
        assertThat(linkaccountList).hasSize(databaseSizeBeforeUpdate);
        Linkaccount testLinkaccount = linkaccountList.get(linkaccountList.size() - 1);
        assertThat(testLinkaccount.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testLinkaccount.getAccounttype()).isEqualTo(UPDATED_ACCOUNTTYPE);
        assertThat(testLinkaccount.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testLinkaccount.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testLinkaccount.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testLinkaccount.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testLinkaccount.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testLinkaccount.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testLinkaccount.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testLinkaccount.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingLinkaccount() throws Exception {
        int databaseSizeBeforeUpdate = linkaccountRepository.findAll().size();

        // Create the Linkaccount
        LinkaccountDTO linkaccountDTO = linkaccountMapper.toDto(linkaccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinkaccountMockMvc.perform(put("/api/linkaccounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(linkaccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Linkaccount in the database
        List<Linkaccount> linkaccountList = linkaccountRepository.findAll();
        assertThat(linkaccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLinkaccount() throws Exception {
        // Initialize the database
        linkaccountRepository.saveAndFlush(linkaccount);

        int databaseSizeBeforeDelete = linkaccountRepository.findAll().size();

        // Delete the linkaccount
        restLinkaccountMockMvc.perform(delete("/api/linkaccounts/{id}", linkaccount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Linkaccount> linkaccountList = linkaccountRepository.findAll();
        assertThat(linkaccountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Linkaccount.class);
        Linkaccount linkaccount1 = new Linkaccount();
        linkaccount1.setId(1L);
        Linkaccount linkaccount2 = new Linkaccount();
        linkaccount2.setId(linkaccount1.getId());
        assertThat(linkaccount1).isEqualTo(linkaccount2);
        linkaccount2.setId(2L);
        assertThat(linkaccount1).isNotEqualTo(linkaccount2);
        linkaccount1.setId(null);
        assertThat(linkaccount1).isNotEqualTo(linkaccount2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinkaccountDTO.class);
        LinkaccountDTO linkaccountDTO1 = new LinkaccountDTO();
        linkaccountDTO1.setId(1L);
        LinkaccountDTO linkaccountDTO2 = new LinkaccountDTO();
        assertThat(linkaccountDTO1).isNotEqualTo(linkaccountDTO2);
        linkaccountDTO2.setId(linkaccountDTO1.getId());
        assertThat(linkaccountDTO1).isEqualTo(linkaccountDTO2);
        linkaccountDTO2.setId(2L);
        assertThat(linkaccountDTO1).isNotEqualTo(linkaccountDTO2);
        linkaccountDTO1.setId(null);
        assertThat(linkaccountDTO1).isNotEqualTo(linkaccountDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(linkaccountMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(linkaccountMapper.fromId(null)).isNull();
    }
}
