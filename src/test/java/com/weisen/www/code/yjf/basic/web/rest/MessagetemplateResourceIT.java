package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Messagetemplate;
import com.weisen.www.code.yjf.basic.repository.MessagetemplateRepository;
import com.weisen.www.code.yjf.basic.service.MessagetemplateService;
import com.weisen.www.code.yjf.basic.service.dto.MessagetemplateDTO;
import com.weisen.www.code.yjf.basic.service.mapper.MessagetemplateMapper;
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
 * Integration tests for the {@Link MessagetemplateResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class MessagetemplateResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

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
    private MessagetemplateRepository messagetemplateRepository;

    @Autowired
    private MessagetemplateMapper messagetemplateMapper;

    @Autowired
    private MessagetemplateService messagetemplateService;

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

    private MockMvc restMessagetemplateMockMvc;

    private Messagetemplate messagetemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MessagetemplateResource messagetemplateResource = new MessagetemplateResource(messagetemplateService);
        this.restMessagetemplateMockMvc = MockMvcBuilders.standaloneSetup(messagetemplateResource)
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
    public static Messagetemplate createEntity(EntityManager em) {
        Messagetemplate messagetemplate = new Messagetemplate()
            .type(DEFAULT_TYPE)
            .content(DEFAULT_CONTENT)
            .title(DEFAULT_TITLE)
            .status(DEFAULT_STATUS)
            .creator(DEFAULT_CREATOR)
            .createdate(DEFAULT_CREATEDATE)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .logicdelete(DEFAULT_LOGICDELETE)
            .other(DEFAULT_OTHER);
        return messagetemplate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Messagetemplate createUpdatedEntity(EntityManager em) {
        Messagetemplate messagetemplate = new Messagetemplate()
            .type(UPDATED_TYPE)
            .content(UPDATED_CONTENT)
            .title(UPDATED_TITLE)
            .status(UPDATED_STATUS)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        return messagetemplate;
    }

    @BeforeEach
    public void initTest() {
        messagetemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createMessagetemplate() throws Exception {
        int databaseSizeBeforeCreate = messagetemplateRepository.findAll().size();

        // Create the Messagetemplate
        MessagetemplateDTO messagetemplateDTO = messagetemplateMapper.toDto(messagetemplate);
        restMessagetemplateMockMvc.perform(post("/api/messagetemplates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messagetemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the Messagetemplate in the database
        List<Messagetemplate> messagetemplateList = messagetemplateRepository.findAll();
        assertThat(messagetemplateList).hasSize(databaseSizeBeforeCreate + 1);
        Messagetemplate testMessagetemplate = messagetemplateList.get(messagetemplateList.size() - 1);
        assertThat(testMessagetemplate.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMessagetemplate.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testMessagetemplate.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMessagetemplate.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMessagetemplate.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testMessagetemplate.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testMessagetemplate.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testMessagetemplate.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testMessagetemplate.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testMessagetemplate.isLogicdelete()).isEqualTo(DEFAULT_LOGICDELETE);
        assertThat(testMessagetemplate.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createMessagetemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = messagetemplateRepository.findAll().size();

        // Create the Messagetemplate with an existing ID
        messagetemplate.setId(1L);
        MessagetemplateDTO messagetemplateDTO = messagetemplateMapper.toDto(messagetemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessagetemplateMockMvc.perform(post("/api/messagetemplates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messagetemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Messagetemplate in the database
        List<Messagetemplate> messagetemplateList = messagetemplateRepository.findAll();
        assertThat(messagetemplateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMessagetemplates() throws Exception {
        // Initialize the database
        messagetemplateRepository.saveAndFlush(messagetemplate);

        // Get all the messagetemplateList
        restMessagetemplateMockMvc.perform(get("/api/messagetemplates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messagetemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
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
    public void getMessagetemplate() throws Exception {
        // Initialize the database
        messagetemplateRepository.saveAndFlush(messagetemplate);

        // Get the messagetemplate
        restMessagetemplateMockMvc.perform(get("/api/messagetemplates/{id}", messagetemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(messagetemplate.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
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
    public void getNonExistingMessagetemplate() throws Exception {
        // Get the messagetemplate
        restMessagetemplateMockMvc.perform(get("/api/messagetemplates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMessagetemplate() throws Exception {
        // Initialize the database
        messagetemplateRepository.saveAndFlush(messagetemplate);

        int databaseSizeBeforeUpdate = messagetemplateRepository.findAll().size();

        // Update the messagetemplate
        Messagetemplate updatedMessagetemplate = messagetemplateRepository.findById(messagetemplate.getId()).get();
        // Disconnect from session so that the updates on updatedMessagetemplate are not directly saved in db
        em.detach(updatedMessagetemplate);
        updatedMessagetemplate
            .type(UPDATED_TYPE)
            .content(UPDATED_CONTENT)
            .title(UPDATED_TITLE)
            .status(UPDATED_STATUS)
            .creator(UPDATED_CREATOR)
            .createdate(UPDATED_CREATEDATE)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .logicdelete(UPDATED_LOGICDELETE)
            .other(UPDATED_OTHER);
        MessagetemplateDTO messagetemplateDTO = messagetemplateMapper.toDto(updatedMessagetemplate);

        restMessagetemplateMockMvc.perform(put("/api/messagetemplates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messagetemplateDTO)))
            .andExpect(status().isOk());

        // Validate the Messagetemplate in the database
        List<Messagetemplate> messagetemplateList = messagetemplateRepository.findAll();
        assertThat(messagetemplateList).hasSize(databaseSizeBeforeUpdate);
        Messagetemplate testMessagetemplate = messagetemplateList.get(messagetemplateList.size() - 1);
        assertThat(testMessagetemplate.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMessagetemplate.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testMessagetemplate.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMessagetemplate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMessagetemplate.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMessagetemplate.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testMessagetemplate.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testMessagetemplate.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testMessagetemplate.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testMessagetemplate.isLogicdelete()).isEqualTo(UPDATED_LOGICDELETE);
        assertThat(testMessagetemplate.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingMessagetemplate() throws Exception {
        int databaseSizeBeforeUpdate = messagetemplateRepository.findAll().size();

        // Create the Messagetemplate
        MessagetemplateDTO messagetemplateDTO = messagetemplateMapper.toDto(messagetemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessagetemplateMockMvc.perform(put("/api/messagetemplates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messagetemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Messagetemplate in the database
        List<Messagetemplate> messagetemplateList = messagetemplateRepository.findAll();
        assertThat(messagetemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMessagetemplate() throws Exception {
        // Initialize the database
        messagetemplateRepository.saveAndFlush(messagetemplate);

        int databaseSizeBeforeDelete = messagetemplateRepository.findAll().size();

        // Delete the messagetemplate
        restMessagetemplateMockMvc.perform(delete("/api/messagetemplates/{id}", messagetemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Messagetemplate> messagetemplateList = messagetemplateRepository.findAll();
        assertThat(messagetemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Messagetemplate.class);
        Messagetemplate messagetemplate1 = new Messagetemplate();
        messagetemplate1.setId(1L);
        Messagetemplate messagetemplate2 = new Messagetemplate();
        messagetemplate2.setId(messagetemplate1.getId());
        assertThat(messagetemplate1).isEqualTo(messagetemplate2);
        messagetemplate2.setId(2L);
        assertThat(messagetemplate1).isNotEqualTo(messagetemplate2);
        messagetemplate1.setId(null);
        assertThat(messagetemplate1).isNotEqualTo(messagetemplate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessagetemplateDTO.class);
        MessagetemplateDTO messagetemplateDTO1 = new MessagetemplateDTO();
        messagetemplateDTO1.setId(1L);
        MessagetemplateDTO messagetemplateDTO2 = new MessagetemplateDTO();
        assertThat(messagetemplateDTO1).isNotEqualTo(messagetemplateDTO2);
        messagetemplateDTO2.setId(messagetemplateDTO1.getId());
        assertThat(messagetemplateDTO1).isEqualTo(messagetemplateDTO2);
        messagetemplateDTO2.setId(2L);
        assertThat(messagetemplateDTO1).isNotEqualTo(messagetemplateDTO2);
        messagetemplateDTO1.setId(null);
        assertThat(messagetemplateDTO1).isNotEqualTo(messagetemplateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(messagetemplateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(messagetemplateMapper.fromId(null)).isNull();
    }
}
