package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Advertisement;
import com.weisen.www.code.yjf.basic.repository.AdvertisementRepository;
import com.weisen.www.code.yjf.basic.service.AdvertisementService;
import com.weisen.www.code.yjf.basic.service.dto.AdvertisementDTO;
import com.weisen.www.code.yjf.basic.service.mapper.AdvertisementMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.weisen.www.code.yjf.basic.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link AdvertisementResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class AdvertisementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INTRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INTRODUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE_FORMAT = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE_LINK = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE_LINK = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 1;
    private static final Integer UPDATED_SORT = 2;

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final Integer DEFAULT_LINK_TYPE = 1;
    private static final Integer UPDATED_LINK_TYPE = 2;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_STATE = 1;
    private static final Integer UPDATED_STATE = 0;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private AdvertisementService advertisementService;

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

    private MockMvc restAdvertisementMockMvc;

    private Advertisement advertisement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdvertisementResource advertisementResource = new AdvertisementResource(advertisementService);
        this.restAdvertisementMockMvc = MockMvcBuilders.standaloneSetup(advertisementResource)
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
    public static Advertisement createEntity(EntityManager em) {
        Advertisement advertisement = new Advertisement()
            .name(DEFAULT_NAME)
            .introduction(DEFAULT_INTRODUCTION)
            .pictureFormat(DEFAULT_PICTURE_FORMAT)
            .pictureLink(DEFAULT_PICTURE_LINK)
            .sort(DEFAULT_SORT)
            .link(DEFAULT_LINK)
            .linkType(DEFAULT_LINK_TYPE)
            .type(DEFAULT_TYPE)
            .state(DEFAULT_STATE)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return advertisement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Advertisement createUpdatedEntity(EntityManager em) {
        Advertisement advertisement = new Advertisement()
            .name(UPDATED_NAME)
            .introduction(UPDATED_INTRODUCTION)
            .pictureFormat(UPDATED_PICTURE_FORMAT)
            .pictureLink(UPDATED_PICTURE_LINK)
            .sort(UPDATED_SORT)
            .link(UPDATED_LINK)
            .linkType(UPDATED_LINK_TYPE)
            .type(UPDATED_TYPE)
            .state(UPDATED_STATE)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return advertisement;
    }

    @BeforeEach
    public void initTest() {
        advertisement = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdvertisement() throws Exception {
        int databaseSizeBeforeCreate = advertisementRepository.findAll().size();

        // Create the Advertisement
        AdvertisementDTO advertisementDTO = advertisementMapper.toDto(advertisement);
        restAdvertisementMockMvc.perform(post("/api/advertisements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertisementDTO)))
            .andExpect(status().isCreated());

        // Validate the Advertisement in the database
        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeCreate + 1);
        Advertisement testAdvertisement = advertisementList.get(advertisementList.size() - 1);
        assertThat(testAdvertisement.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdvertisement.getIntroduction()).isEqualTo(DEFAULT_INTRODUCTION);
        assertThat(testAdvertisement.getPictureFormat()).isEqualTo(DEFAULT_PICTURE_FORMAT);
        assertThat(testAdvertisement.getPictureLink()).isEqualTo(DEFAULT_PICTURE_LINK);
        assertThat(testAdvertisement.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testAdvertisement.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testAdvertisement.getLinkType()).isEqualTo(DEFAULT_LINK_TYPE);
        assertThat(testAdvertisement.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAdvertisement.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testAdvertisement.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testAdvertisement.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createAdvertisementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = advertisementRepository.findAll().size();

        // Create the Advertisement with an existing ID
        advertisement.setId(1L);
        AdvertisementDTO advertisementDTO = advertisementMapper.toDto(advertisement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdvertisementMockMvc.perform(post("/api/advertisements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertisementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Advertisement in the database
        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertisementRepository.findAll().size();
        // set the field null
        advertisement.setName(null);

        // Create the Advertisement, which fails.
        AdvertisementDTO advertisementDTO = advertisementMapper.toDto(advertisement);

        restAdvertisementMockMvc.perform(post("/api/advertisements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertisementDTO)))
            .andExpect(status().isBadRequest());

        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertisementRepository.findAll().size();
        // set the field null
        advertisement.setState(null);

        // Create the Advertisement, which fails.
        AdvertisementDTO advertisementDTO = advertisementMapper.toDto(advertisement);

        restAdvertisementMockMvc.perform(post("/api/advertisements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertisementDTO)))
            .andExpect(status().isBadRequest());

        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertisementRepository.findAll().size();
        // set the field null
        advertisement.setCreatedDate(null);

        // Create the Advertisement, which fails.
        AdvertisementDTO advertisementDTO = advertisementMapper.toDto(advertisement);

        restAdvertisementMockMvc.perform(post("/api/advertisements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertisementDTO)))
            .andExpect(status().isBadRequest());

        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastModifiedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertisementRepository.findAll().size();
        // set the field null
        advertisement.setLastModifiedDate(null);

        // Create the Advertisement, which fails.
        AdvertisementDTO advertisementDTO = advertisementMapper.toDto(advertisement);

        restAdvertisementMockMvc.perform(post("/api/advertisements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertisementDTO)))
            .andExpect(status().isBadRequest());

        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdvertisements() throws Exception {
        // Initialize the database
        advertisementRepository.saveAndFlush(advertisement);

        // Get all the advertisementList
        restAdvertisementMockMvc.perform(get("/api/advertisements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(advertisement.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].introduction").value(hasItem(DEFAULT_INTRODUCTION.toString())))
            .andExpect(jsonPath("$.[*].pictureFormat").value(hasItem(DEFAULT_PICTURE_FORMAT.toString())))
            .andExpect(jsonPath("$.[*].pictureLink").value(hasItem(DEFAULT_PICTURE_LINK.toString())))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK.toString())))
            .andExpect(jsonPath("$.[*].linkType").value(hasItem(DEFAULT_LINK_TYPE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getAdvertisement() throws Exception {
        // Initialize the database
        advertisementRepository.saveAndFlush(advertisement);

        // Get the advertisement
        restAdvertisementMockMvc.perform(get("/api/advertisements/{id}", advertisement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(advertisement.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.introduction").value(DEFAULT_INTRODUCTION.toString()))
            .andExpect(jsonPath("$.pictureFormat").value(DEFAULT_PICTURE_FORMAT.toString()))
            .andExpect(jsonPath("$.pictureLink").value(DEFAULT_PICTURE_LINK.toString()))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK.toString()))
            .andExpect(jsonPath("$.linkType").value(DEFAULT_LINK_TYPE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdvertisement() throws Exception {
        // Get the advertisement
        restAdvertisementMockMvc.perform(get("/api/advertisements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdvertisement() throws Exception {
        // Initialize the database
        advertisementRepository.saveAndFlush(advertisement);

        int databaseSizeBeforeUpdate = advertisementRepository.findAll().size();

        // Update the advertisement
        Advertisement updatedAdvertisement = advertisementRepository.findById(advertisement.getId()).get();
        // Disconnect from session so that the updates on updatedAdvertisement are not directly saved in db
        em.detach(updatedAdvertisement);
        updatedAdvertisement
            .name(UPDATED_NAME)
            .introduction(UPDATED_INTRODUCTION)
            .pictureFormat(UPDATED_PICTURE_FORMAT)
            .pictureLink(UPDATED_PICTURE_LINK)
            .sort(UPDATED_SORT)
            .link(UPDATED_LINK)
            .linkType(UPDATED_LINK_TYPE)
            .type(UPDATED_TYPE)
            .state(UPDATED_STATE)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        AdvertisementDTO advertisementDTO = advertisementMapper.toDto(updatedAdvertisement);

        restAdvertisementMockMvc.perform(put("/api/advertisements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertisementDTO)))
            .andExpect(status().isOk());

        // Validate the Advertisement in the database
        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeUpdate);
        Advertisement testAdvertisement = advertisementList.get(advertisementList.size() - 1);
        assertThat(testAdvertisement.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdvertisement.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testAdvertisement.getPictureFormat()).isEqualTo(UPDATED_PICTURE_FORMAT);
        assertThat(testAdvertisement.getPictureLink()).isEqualTo(UPDATED_PICTURE_LINK);
        assertThat(testAdvertisement.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testAdvertisement.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testAdvertisement.getLinkType()).isEqualTo(UPDATED_LINK_TYPE);
        assertThat(testAdvertisement.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAdvertisement.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testAdvertisement.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testAdvertisement.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdvertisement() throws Exception {
        int databaseSizeBeforeUpdate = advertisementRepository.findAll().size();

        // Create the Advertisement
        AdvertisementDTO advertisementDTO = advertisementMapper.toDto(advertisement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdvertisementMockMvc.perform(put("/api/advertisements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertisementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Advertisement in the database
        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdvertisement() throws Exception {
        // Initialize the database
        advertisementRepository.saveAndFlush(advertisement);

        int databaseSizeBeforeDelete = advertisementRepository.findAll().size();

        // Delete the advertisement
        restAdvertisementMockMvc.perform(delete("/api/advertisements/{id}", advertisement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Advertisement> advertisementList = advertisementRepository.findAll();
        assertThat(advertisementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Advertisement.class);
        Advertisement advertisement1 = new Advertisement();
        advertisement1.setId(1L);
        Advertisement advertisement2 = new Advertisement();
        advertisement2.setId(advertisement1.getId());
        assertThat(advertisement1).isEqualTo(advertisement2);
        advertisement2.setId(2L);
        assertThat(advertisement1).isNotEqualTo(advertisement2);
        advertisement1.setId(null);
        assertThat(advertisement1).isNotEqualTo(advertisement2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdvertisementDTO.class);
        AdvertisementDTO advertisementDTO1 = new AdvertisementDTO();
        advertisementDTO1.setId(1L);
        AdvertisementDTO advertisementDTO2 = new AdvertisementDTO();
        assertThat(advertisementDTO1).isNotEqualTo(advertisementDTO2);
        advertisementDTO2.setId(advertisementDTO1.getId());
        assertThat(advertisementDTO1).isEqualTo(advertisementDTO2);
        advertisementDTO2.setId(2L);
        assertThat(advertisementDTO1).isNotEqualTo(advertisementDTO2);
        advertisementDTO1.setId(null);
        assertThat(advertisementDTO1).isNotEqualTo(advertisementDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(advertisementMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(advertisementMapper.fromId(null)).isNull();
    }
}
