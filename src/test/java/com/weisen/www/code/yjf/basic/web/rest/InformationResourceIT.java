package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Information;
import com.weisen.www.code.yjf.basic.repository.InformationRepository;
import com.weisen.www.code.yjf.basic.service.InformationService;
import com.weisen.www.code.yjf.basic.service.dto.InformationDTO;
import com.weisen.www.code.yjf.basic.service.mapper.InformationMapper;
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
 * Integration tests for the {@Link InformationResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class InformationResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SENDUSERID = "AAAAAAAAAA";
    private static final String UPDATED_SENDUSERID = "BBBBBBBBBB";

    private static final String DEFAULT_READUSERID = "AAAAAAAAAA";
    private static final String UPDATED_READUSERID = "BBBBBBBBBB";

    private static final String DEFAULT_SENDDATE = "AAAAAAAAAA";
    private static final String UPDATED_SENDDATE = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

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
    private InformationRepository informationRepository;

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private InformationService informationService;

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

    private MockMvc restInformationMockMvc;

    private Information information;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InformationResource informationResource = new InformationResource(informationService);
        this.restInformationMockMvc = MockMvcBuilders.standaloneSetup(informationResource)
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
    public static Information createEntity(EntityManager em) {
        Information information = new Information()
            .type(DEFAULT_TYPE)
            .senduserid(DEFAULT_SENDUSERID)
            .readuserid(DEFAULT_READUSERID)
            .senddate(DEFAULT_SENDDATE)
            .title(DEFAULT_TITLE)
            .content(DEFAULT_CONTENT)
            .state(DEFAULT_STATE)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return information;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Information createUpdatedEntity(EntityManager em) {
        Information information = new Information()
            .type(UPDATED_TYPE)
            .senduserid(UPDATED_SENDUSERID)
            .readuserid(UPDATED_READUSERID)
            .senddate(UPDATED_SENDDATE)
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return information;
    }

    @BeforeEach
    public void initTest() {
        information = createEntity(em);
    }

    @Test
    @Transactional
    public void createInformation() throws Exception {
        int databaseSizeBeforeCreate = informationRepository.findAll().size();

        // Create the Information
        InformationDTO informationDTO = informationMapper.toDto(information);
        restInformationMockMvc.perform(post("/api/information")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informationDTO)))
            .andExpect(status().isCreated());

        // Validate the Information in the database
        List<Information> informationList = informationRepository.findAll();
        assertThat(informationList).hasSize(databaseSizeBeforeCreate + 1);
        Information testInformation = informationList.get(informationList.size() - 1);
        assertThat(testInformation.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testInformation.getSenduserid()).isEqualTo(DEFAULT_SENDUSERID);
        assertThat(testInformation.getReaduserid()).isEqualTo(DEFAULT_READUSERID);
        assertThat(testInformation.getSenddate()).isEqualTo(DEFAULT_SENDDATE);
        assertThat(testInformation.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testInformation.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testInformation.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testInformation.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testInformation.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testInformation.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testInformation.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testInformation.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testInformation.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testInformation.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createInformationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = informationRepository.findAll().size();

        // Create the Information with an existing ID
        information.setId(1L);
        InformationDTO informationDTO = informationMapper.toDto(information);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInformationMockMvc.perform(post("/api/information")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Information in the database
        List<Information> informationList = informationRepository.findAll();
        assertThat(informationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInformation() throws Exception {
        // Initialize the database
        informationRepository.saveAndFlush(information);

        // Get all the informationList
        restInformationMockMvc.perform(get("/api/information?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(information.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].senduserid").value(hasItem(DEFAULT_SENDUSERID.toString())))
            .andExpect(jsonPath("$.[*].readuserid").value(hasItem(DEFAULT_READUSERID.toString())))
            .andExpect(jsonPath("$.[*].senddate").value(hasItem(DEFAULT_SENDDATE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
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
    public void getInformation() throws Exception {
        // Initialize the database
        informationRepository.saveAndFlush(information);

        // Get the information
        restInformationMockMvc.perform(get("/api/information/{id}", information.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(information.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.senduserid").value(DEFAULT_SENDUSERID.toString()))
            .andExpect(jsonPath("$.readuserid").value(DEFAULT_READUSERID.toString()))
            .andExpect(jsonPath("$.senddate").value(DEFAULT_SENDDATE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
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
    public void getNonExistingInformation() throws Exception {
        // Get the information
        restInformationMockMvc.perform(get("/api/information/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInformation() throws Exception {
        // Initialize the database
        informationRepository.saveAndFlush(information);

        int databaseSizeBeforeUpdate = informationRepository.findAll().size();

        // Update the information
        Information updatedInformation = informationRepository.findById(information.getId()).get();
        // Disconnect from session so that the updates on updatedInformation are not directly saved in db
        em.detach(updatedInformation);
        updatedInformation
            .type(UPDATED_TYPE)
            .senduserid(UPDATED_SENDUSERID)
            .readuserid(UPDATED_READUSERID)
            .senddate(UPDATED_SENDDATE)
            .title(UPDATED_TITLE)
            .content(UPDATED_CONTENT)
            .state(UPDATED_STATE)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        InformationDTO informationDTO = informationMapper.toDto(updatedInformation);

        restInformationMockMvc.perform(put("/api/information")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informationDTO)))
            .andExpect(status().isOk());

        // Validate the Information in the database
        List<Information> informationList = informationRepository.findAll();
        assertThat(informationList).hasSize(databaseSizeBeforeUpdate);
        Information testInformation = informationList.get(informationList.size() - 1);
        assertThat(testInformation.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testInformation.getSenduserid()).isEqualTo(UPDATED_SENDUSERID);
        assertThat(testInformation.getReaduserid()).isEqualTo(UPDATED_READUSERID);
        assertThat(testInformation.getSenddate()).isEqualTo(UPDATED_SENDDATE);
        assertThat(testInformation.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testInformation.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testInformation.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testInformation.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testInformation.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testInformation.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testInformation.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testInformation.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testInformation.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testInformation.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingInformation() throws Exception {
        int databaseSizeBeforeUpdate = informationRepository.findAll().size();

        // Create the Information
        InformationDTO informationDTO = informationMapper.toDto(information);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInformationMockMvc.perform(put("/api/information")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(informationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Information in the database
        List<Information> informationList = informationRepository.findAll();
        assertThat(informationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInformation() throws Exception {
        // Initialize the database
        informationRepository.saveAndFlush(information);

        int databaseSizeBeforeDelete = informationRepository.findAll().size();

        // Delete the information
        restInformationMockMvc.perform(delete("/api/information/{id}", information.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Information> informationList = informationRepository.findAll();
        assertThat(informationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Information.class);
        Information information1 = new Information();
        information1.setId(1L);
        Information information2 = new Information();
        information2.setId(information1.getId());
        assertThat(information1).isEqualTo(information2);
        information2.setId(2L);
        assertThat(information1).isNotEqualTo(information2);
        information1.setId(null);
        assertThat(information1).isNotEqualTo(information2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformationDTO.class);
        InformationDTO informationDTO1 = new InformationDTO();
        informationDTO1.setId(1L);
        InformationDTO informationDTO2 = new InformationDTO();
        assertThat(informationDTO1).isNotEqualTo(informationDTO2);
        informationDTO2.setId(informationDTO1.getId());
        assertThat(informationDTO1).isEqualTo(informationDTO2);
        informationDTO2.setId(2L);
        assertThat(informationDTO1).isNotEqualTo(informationDTO2);
        informationDTO1.setId(null);
        assertThat(informationDTO1).isNotEqualTo(informationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(informationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(informationMapper.fromId(null)).isNull();
    }
}
