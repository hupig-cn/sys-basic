package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Osversion;
import com.weisen.www.code.yjf.basic.repository.OsversionRepository;
import com.weisen.www.code.yjf.basic.service.OsversionService;
import com.weisen.www.code.yjf.basic.service.dto.OsversionDTO;
import com.weisen.www.code.yjf.basic.service.mapper.OsversionMapper;
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
 * Test class for the OsversionResource REST controller.
 *
 * @see OsversionResource
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class OsversionResourceIntTest {

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_LOGINFO = "AAAAAAAAAA";
    private static final String UPDATED_LOGINFO = "BBBBBBBBBB";

    private static final String DEFAULT_OS = "AAAAAAAAAA";
    private static final String UPDATED_OS = "BBBBBBBBBB";

    private static final String DEFAULT_OSVERSION = "AAAAAAAAAA";
    private static final String UPDATED_OSVERSION = "BBBBBBBBBB";

    private static final String DEFAULT_APKURL = "AAAAAAAAAA";
    private static final String UPDATED_APKURL = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEDATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDATE = "BBBBBBBBBB";

    @Autowired
    private OsversionRepository osversionRepository;

    @Autowired
    private OsversionMapper osversionMapper;

    @Autowired
    private OsversionService osversionService;

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

    private MockMvc restOsversionMockMvc;

    private Osversion osversion;

//    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OsversionResource osversionResource = new OsversionResource(osversionService);
        this.restOsversionMockMvc = MockMvcBuilders.standaloneSetup(osversionResource)
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
    public static Osversion createEntity(EntityManager em) {
        Osversion osversion = new Osversion()
            .version(DEFAULT_VERSION)
            .loginfo(DEFAULT_LOGINFO)
            .os(DEFAULT_OS)
            .osversion(DEFAULT_OSVERSION)
            .apkurl(DEFAULT_APKURL)
            .type(DEFAULT_TYPE)
            .picture(DEFAULT_PICTURE)
            .createdate(DEFAULT_CREATEDATE);
        return osversion;
    }

//    @Before
    public void initTest() {
        osversion = createEntity(em);
    }

    @Test
    @Transactional
    public void createOsversion() throws Exception {
        int databaseSizeBeforeCreate = osversionRepository.findAll().size();

        // Create the Osversion
        OsversionDTO osversionDTO = osversionMapper.toDto(osversion);
        restOsversionMockMvc.perform(post("/api/osversions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(osversionDTO)))
            .andExpect(status().isCreated());

        // Validate the Osversion in the database
        List<Osversion> osversionList = osversionRepository.findAll();
        assertThat(osversionList).hasSize(databaseSizeBeforeCreate + 1);
        Osversion testOsversion = osversionList.get(osversionList.size() - 1);
        assertThat(testOsversion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testOsversion.getLoginfo()).isEqualTo(DEFAULT_LOGINFO);
        assertThat(testOsversion.getOs()).isEqualTo(DEFAULT_OS);
        assertThat(testOsversion.getOsversion()).isEqualTo(DEFAULT_OSVERSION);
        assertThat(testOsversion.getApkurl()).isEqualTo(DEFAULT_APKURL);
        assertThat(testOsversion.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testOsversion.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testOsversion.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
    }

    @Test
    @Transactional
    public void createOsversionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = osversionRepository.findAll().size();

        // Create the Osversion with an existing ID
        osversion.setId(1L);
        OsversionDTO osversionDTO = osversionMapper.toDto(osversion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOsversionMockMvc.perform(post("/api/osversions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(osversionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Osversion in the database
        List<Osversion> osversionList = osversionRepository.findAll();
        assertThat(osversionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOsversions() throws Exception {
        // Initialize the database
        osversionRepository.saveAndFlush(osversion);

        // Get all the osversionList
        restOsversionMockMvc.perform(get("/api/osversions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(osversion.getId().intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].loginfo").value(hasItem(DEFAULT_LOGINFO.toString())))
            .andExpect(jsonPath("$.[*].os").value(hasItem(DEFAULT_OS.toString())))
            .andExpect(jsonPath("$.[*].osversion").value(hasItem(DEFAULT_OSVERSION.toString())))
            .andExpect(jsonPath("$.[*].apkurl").value(hasItem(DEFAULT_APKURL.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())));
    }
    
    @Test
    @Transactional
    public void getOsversion() throws Exception {
        // Initialize the database
        osversionRepository.saveAndFlush(osversion);

        // Get the osversion
        restOsversionMockMvc.perform(get("/api/osversions/{id}", osversion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(osversion.getId().intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.loginfo").value(DEFAULT_LOGINFO.toString()))
            .andExpect(jsonPath("$.os").value(DEFAULT_OS.toString()))
            .andExpect(jsonPath("$.osversion").value(DEFAULT_OSVERSION.toString()))
            .andExpect(jsonPath("$.apkurl").value(DEFAULT_APKURL.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOsversion() throws Exception {
        // Get the osversion
        restOsversionMockMvc.perform(get("/api/osversions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOsversion() throws Exception {
        // Initialize the database
        osversionRepository.saveAndFlush(osversion);

        int databaseSizeBeforeUpdate = osversionRepository.findAll().size();

        // Update the osversion
        Osversion updatedOsversion = osversionRepository.findById(osversion.getId()).get();
        // Disconnect from session so that the updates on updatedOsversion are not directly saved in db
        em.detach(updatedOsversion);
        updatedOsversion
            .version(UPDATED_VERSION)
            .loginfo(UPDATED_LOGINFO)
            .os(UPDATED_OS)
            .osversion(UPDATED_OSVERSION)
            .apkurl(UPDATED_APKURL)
            .type(UPDATED_TYPE)
            .picture(UPDATED_PICTURE)
            .createdate(UPDATED_CREATEDATE);
        OsversionDTO osversionDTO = osversionMapper.toDto(updatedOsversion);

        restOsversionMockMvc.perform(put("/api/osversions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(osversionDTO)))
            .andExpect(status().isOk());

        // Validate the Osversion in the database
        List<Osversion> osversionList = osversionRepository.findAll();
        assertThat(osversionList).hasSize(databaseSizeBeforeUpdate);
        Osversion testOsversion = osversionList.get(osversionList.size() - 1);
        assertThat(testOsversion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testOsversion.getLoginfo()).isEqualTo(UPDATED_LOGINFO);
        assertThat(testOsversion.getOs()).isEqualTo(UPDATED_OS);
        assertThat(testOsversion.getOsversion()).isEqualTo(UPDATED_OSVERSION);
        assertThat(testOsversion.getApkurl()).isEqualTo(UPDATED_APKURL);
        assertThat(testOsversion.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOsversion.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testOsversion.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOsversion() throws Exception {
        int databaseSizeBeforeUpdate = osversionRepository.findAll().size();

        // Create the Osversion
        OsversionDTO osversionDTO = osversionMapper.toDto(osversion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOsversionMockMvc.perform(put("/api/osversions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(osversionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Osversion in the database
        List<Osversion> osversionList = osversionRepository.findAll();
        assertThat(osversionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOsversion() throws Exception {
        // Initialize the database
        osversionRepository.saveAndFlush(osversion);

        int databaseSizeBeforeDelete = osversionRepository.findAll().size();

        // Delete the osversion
        restOsversionMockMvc.perform(delete("/api/osversions/{id}", osversion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Osversion> osversionList = osversionRepository.findAll();
        assertThat(osversionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Osversion.class);
        Osversion osversion1 = new Osversion();
        osversion1.setId(1L);
        Osversion osversion2 = new Osversion();
        osversion2.setId(osversion1.getId());
        assertThat(osversion1).isEqualTo(osversion2);
        osversion2.setId(2L);
        assertThat(osversion1).isNotEqualTo(osversion2);
        osversion1.setId(null);
        assertThat(osversion1).isNotEqualTo(osversion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OsversionDTO.class);
        OsversionDTO osversionDTO1 = new OsversionDTO();
        osversionDTO1.setId(1L);
        OsversionDTO osversionDTO2 = new OsversionDTO();
        assertThat(osversionDTO1).isNotEqualTo(osversionDTO2);
        osversionDTO2.setId(osversionDTO1.getId());
        assertThat(osversionDTO1).isEqualTo(osversionDTO2);
        osversionDTO2.setId(2L);
        assertThat(osversionDTO1).isNotEqualTo(osversionDTO2);
        osversionDTO1.setId(null);
        assertThat(osversionDTO1).isNotEqualTo(osversionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(osversionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(osversionMapper.fromId(null)).isNull();
    }
}
