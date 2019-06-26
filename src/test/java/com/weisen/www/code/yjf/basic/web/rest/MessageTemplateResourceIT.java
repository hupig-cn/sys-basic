package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.MessageTemplate;
import com.weisen.www.code.yjf.basic.repository.MessageTemplateRepository;
import com.weisen.www.code.yjf.basic.service.MessageTemplateService;
import com.weisen.www.code.yjf.basic.service.dto.MessageTemplateDTO;
import com.weisen.www.code.yjf.basic.service.mapper.MessageTemplateMapper;
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
 * Integration tests for the {@Link MessageTemplateResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class MessageTemplateResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIERDATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIERDATE = "BBBBBBBBBB";

    private static final Long DEFAULT_MODIFIERNUM = 1L;
    private static final Long UPDATED_MODIFIERNUM = 2L;

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @Autowired
    private MessageTemplateService messageTemplateService;

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

    private MockMvc restMessageTemplateMockMvc;

    private MessageTemplate messageTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MessageTemplateResource messageTemplateResource = new MessageTemplateResource(messageTemplateService);
        this.restMessageTemplateMockMvc = MockMvcBuilders.standaloneSetup(messageTemplateResource)
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
    public static MessageTemplate createEntity(EntityManager em) {
        MessageTemplate messageTemplate = new MessageTemplate()
            .type(DEFAULT_TYPE)
            .content(DEFAULT_CONTENT)
            .createDate(DEFAULT_CREATE_DATE)
            .creator(DEFAULT_CREATOR)
            .modifier(DEFAULT_MODIFIER)
            .modifierdate(DEFAULT_MODIFIERDATE)
            .modifiernum(DEFAULT_MODIFIERNUM)
            .title(DEFAULT_TITLE)
            .status(DEFAULT_STATUS);
        return messageTemplate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageTemplate createUpdatedEntity(EntityManager em) {
        MessageTemplate messageTemplate = new MessageTemplate()
            .type(UPDATED_TYPE)
            .content(UPDATED_CONTENT)
            .createDate(UPDATED_CREATE_DATE)
            .creator(UPDATED_CREATOR)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .title(UPDATED_TITLE)
            .status(UPDATED_STATUS);
        return messageTemplate;
    }

    @BeforeEach
    public void initTest() {
        messageTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createMessageTemplate() throws Exception {
        int databaseSizeBeforeCreate = messageTemplateRepository.findAll().size();

        // Create the MessageTemplate
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(messageTemplate);
        restMessageTemplateMockMvc.perform(post("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the MessageTemplate in the database
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        MessageTemplate testMessageTemplate = messageTemplateList.get(messageTemplateList.size() - 1);
        assertThat(testMessageTemplate.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMessageTemplate.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testMessageTemplate.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testMessageTemplate.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testMessageTemplate.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testMessageTemplate.getModifierdate()).isEqualTo(DEFAULT_MODIFIERDATE);
        assertThat(testMessageTemplate.getModifiernum()).isEqualTo(DEFAULT_MODIFIERNUM);
        assertThat(testMessageTemplate.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMessageTemplate.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMessageTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = messageTemplateRepository.findAll().size();

        // Create the MessageTemplate with an existing ID
        messageTemplate.setId(1L);
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(messageTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessageTemplateMockMvc.perform(post("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMessageTemplates() throws Exception {
        // Initialize the database
        messageTemplateRepository.saveAndFlush(messageTemplate);

        // Get all the messageTemplateList
        restMessageTemplateMockMvc.perform(get("/api/message-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER.toString())))
            .andExpect(jsonPath("$.[*].modifierdate").value(hasItem(DEFAULT_MODIFIERDATE.toString())))
            .andExpect(jsonPath("$.[*].modifiernum").value(hasItem(DEFAULT_MODIFIERNUM.intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getMessageTemplate() throws Exception {
        // Initialize the database
        messageTemplateRepository.saveAndFlush(messageTemplate);

        // Get the messageTemplate
        restMessageTemplateMockMvc.perform(get("/api/message-templates/{id}", messageTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(messageTemplate.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER.toString()))
            .andExpect(jsonPath("$.modifierdate").value(DEFAULT_MODIFIERDATE.toString()))
            .andExpect(jsonPath("$.modifiernum").value(DEFAULT_MODIFIERNUM.intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMessageTemplate() throws Exception {
        // Get the messageTemplate
        restMessageTemplateMockMvc.perform(get("/api/message-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMessageTemplate() throws Exception {
        // Initialize the database
        messageTemplateRepository.saveAndFlush(messageTemplate);

        int databaseSizeBeforeUpdate = messageTemplateRepository.findAll().size();

        // Update the messageTemplate
        MessageTemplate updatedMessageTemplate = messageTemplateRepository.findById(messageTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedMessageTemplate are not directly saved in db
        em.detach(updatedMessageTemplate);
        updatedMessageTemplate
            .type(UPDATED_TYPE)
            .content(UPDATED_CONTENT)
            .createDate(UPDATED_CREATE_DATE)
            .creator(UPDATED_CREATOR)
            .modifier(UPDATED_MODIFIER)
            .modifierdate(UPDATED_MODIFIERDATE)
            .modifiernum(UPDATED_MODIFIERNUM)
            .title(UPDATED_TITLE)
            .status(UPDATED_STATUS);
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(updatedMessageTemplate);

        restMessageTemplateMockMvc.perform(put("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the MessageTemplate in the database
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeUpdate);
        MessageTemplate testMessageTemplate = messageTemplateList.get(messageTemplateList.size() - 1);
        assertThat(testMessageTemplate.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMessageTemplate.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testMessageTemplate.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testMessageTemplate.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMessageTemplate.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testMessageTemplate.getModifierdate()).isEqualTo(UPDATED_MODIFIERDATE);
        assertThat(testMessageTemplate.getModifiernum()).isEqualTo(UPDATED_MODIFIERNUM);
        assertThat(testMessageTemplate.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMessageTemplate.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMessageTemplate() throws Exception {
        int databaseSizeBeforeUpdate = messageTemplateRepository.findAll().size();

        // Create the MessageTemplate
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(messageTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessageTemplateMockMvc.perform(put("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMessageTemplate() throws Exception {
        // Initialize the database
        messageTemplateRepository.saveAndFlush(messageTemplate);

        int databaseSizeBeforeDelete = messageTemplateRepository.findAll().size();

        // Delete the messageTemplate
        restMessageTemplateMockMvc.perform(delete("/api/message-templates/{id}", messageTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageTemplate.class);
        MessageTemplate messageTemplate1 = new MessageTemplate();
        messageTemplate1.setId(1L);
        MessageTemplate messageTemplate2 = new MessageTemplate();
        messageTemplate2.setId(messageTemplate1.getId());
        assertThat(messageTemplate1).isEqualTo(messageTemplate2);
        messageTemplate2.setId(2L);
        assertThat(messageTemplate1).isNotEqualTo(messageTemplate2);
        messageTemplate1.setId(null);
        assertThat(messageTemplate1).isNotEqualTo(messageTemplate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageTemplateDTO.class);
        MessageTemplateDTO messageTemplateDTO1 = new MessageTemplateDTO();
        messageTemplateDTO1.setId(1L);
        MessageTemplateDTO messageTemplateDTO2 = new MessageTemplateDTO();
        assertThat(messageTemplateDTO1).isNotEqualTo(messageTemplateDTO2);
        messageTemplateDTO2.setId(messageTemplateDTO1.getId());
        assertThat(messageTemplateDTO1).isEqualTo(messageTemplateDTO2);
        messageTemplateDTO2.setId(2L);
        assertThat(messageTemplateDTO1).isNotEqualTo(messageTemplateDTO2);
        messageTemplateDTO1.setId(null);
        assertThat(messageTemplateDTO1).isNotEqualTo(messageTemplateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(messageTemplateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(messageTemplateMapper.fromId(null)).isNull();
    }
}
