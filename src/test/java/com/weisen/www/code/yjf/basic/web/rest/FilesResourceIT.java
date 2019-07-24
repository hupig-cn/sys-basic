package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Files;
import com.weisen.www.code.yjf.basic.repository.FilesRepository;
import com.weisen.www.code.yjf.basic.service.FilesService;
import com.weisen.www.code.yjf.basic.service.dto.FilesDTO;
import com.weisen.www.code.yjf.basic.service.mapper.FilesMapper;
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
 * Integration tests for the {@Link FilesResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class FilesResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer UPDATED_SIZE = 2;

    private static final String DEFAULT_FILE = "AAAAAAAAAA";
    private static final String UPDATED_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_CONTENT_TYPE = "BBBBBBBBBB";

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    private FilesService filesService;

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

    private MockMvc restFilesMockMvc;

    private Files files;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FilesResource filesResource = new FilesResource(filesService);
        this.restFilesMockMvc = MockMvcBuilders.standaloneSetup(filesResource)
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
    public static Files createEntity(EntityManager em) {
        Files files = new Files()
            .userid(DEFAULT_USERID)
            .name(DEFAULT_NAME)
            .size(DEFAULT_SIZE)
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE);
        return files;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Files createUpdatedEntity(EntityManager em) {
        Files files = new Files()
            .userid(UPDATED_USERID)
            .name(UPDATED_NAME)
            .size(UPDATED_SIZE)
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE);
        return files;
    }

    @BeforeEach
    public void initTest() {
        files = createEntity(em);
    }

    @Test
    @Transactional
    public void createFiles() throws Exception {
        int databaseSizeBeforeCreate = filesRepository.findAll().size();

        // Create the Files
        FilesDTO filesDTO = filesMapper.toDto(files);
        restFilesMockMvc.perform(post("/api/files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isCreated());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeCreate + 1);
        Files testFiles = filesList.get(filesList.size() - 1);
        assertThat(testFiles.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testFiles.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFiles.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testFiles.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testFiles.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFilesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = filesRepository.findAll().size();

        // Create the Files with an existing ID
        files.setId(1L);
        FilesDTO filesDTO = filesMapper.toDto(files);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFilesMockMvc.perform(post("/api/files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        // Get all the filesList
        restFilesMockMvc.perform(get("/api/files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(files.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(DEFAULT_FILE.toString())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        // Get the files
        restFilesMockMvc.perform(get("/api/files/{id}", files.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(files.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE))
            .andExpect(jsonPath("$.file").value(DEFAULT_FILE.toString()))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFiles() throws Exception {
        // Get the files
        restFilesMockMvc.perform(get("/api/files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        int databaseSizeBeforeUpdate = filesRepository.findAll().size();

        // Update the files
        Files updatedFiles = filesRepository.findById(files.getId()).get();
        // Disconnect from session so that the updates on updatedFiles are not directly saved in db
        em.detach(updatedFiles);
        updatedFiles
            .userid(UPDATED_USERID)
            .name(UPDATED_NAME)
            .size(UPDATED_SIZE)
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE);
        FilesDTO filesDTO = filesMapper.toDto(updatedFiles);

        restFilesMockMvc.perform(put("/api/files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isOk());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeUpdate);
        Files testFiles = filesList.get(filesList.size() - 1);
        assertThat(testFiles.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testFiles.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFiles.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testFiles.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testFiles.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFiles() throws Exception {
        int databaseSizeBeforeUpdate = filesRepository.findAll().size();

        // Create the Files
        FilesDTO filesDTO = filesMapper.toDto(files);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFilesMockMvc.perform(put("/api/files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(filesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Files in the database
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFiles() throws Exception {
        // Initialize the database
        filesRepository.saveAndFlush(files);

        int databaseSizeBeforeDelete = filesRepository.findAll().size();

        // Delete the files
        restFilesMockMvc.perform(delete("/api/files/{id}", files.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Files> filesList = filesRepository.findAll();
        assertThat(filesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Files.class);
        Files files1 = new Files();
        files1.setId(1L);
        Files files2 = new Files();
        files2.setId(files1.getId());
        assertThat(files1).isEqualTo(files2);
        files2.setId(2L);
        assertThat(files1).isNotEqualTo(files2);
        files1.setId(null);
        assertThat(files1).isNotEqualTo(files2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FilesDTO.class);
        FilesDTO filesDTO1 = new FilesDTO();
        filesDTO1.setId(1L);
        FilesDTO filesDTO2 = new FilesDTO();
        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
        filesDTO2.setId(filesDTO1.getId());
        assertThat(filesDTO1).isEqualTo(filesDTO2);
        filesDTO2.setId(2L);
        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
        filesDTO1.setId(null);
        assertThat(filesDTO1).isNotEqualTo(filesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(filesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(filesMapper.fromId(null)).isNull();
    }
}
