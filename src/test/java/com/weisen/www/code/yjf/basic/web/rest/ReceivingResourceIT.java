package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Receiving;
import com.weisen.www.code.yjf.basic.repository.ReceivingRepository;
import com.weisen.www.code.yjf.basic.service.ReceivingService;
import com.weisen.www.code.yjf.basic.service.dto.ReceivingDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ReceivingMapper;
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
 * Integration tests for the {@Link ReceivingResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, BasicApp.class})
public class ReceivingResourceIT {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_CONSIGNEE = "AAAAAAAAAA";
    private static final String UPDATED_CONSIGNEE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_AREAID = "AAAAAAAAAA";
    private static final String UPDATED_AREAID = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISDEFAULT = false;
    private static final Boolean UPDATED_ISDEFAULT = true;

    private static final String DEFAULT_CREATETIME = "AAAAAAAAAA";
    private static final String UPDATED_CREATETIME = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATETIME = "AAAAAAAAAA";
    private static final String UPDATED_UPDATETIME = "BBBBBBBBBB";

    @Autowired
    private ReceivingRepository receivingRepository;

    @Autowired
    private ReceivingMapper receivingMapper;

    @Autowired
    private ReceivingService receivingService;

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

    private MockMvc restReceivingMockMvc;

    private Receiving receiving;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReceivingResource receivingResource = new ReceivingResource(receivingService);
        this.restReceivingMockMvc = MockMvcBuilders.standaloneSetup(receivingResource)
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
    public static Receiving createEntity(EntityManager em) {
        Receiving receiving = new Receiving()
            .userid(DEFAULT_USERID)
            .consignee(DEFAULT_CONSIGNEE)
            .mobile(DEFAULT_MOBILE)
            .areaid(DEFAULT_AREAID)
            .address(DEFAULT_ADDRESS)
            .isdefault(DEFAULT_ISDEFAULT)
            .createtime(DEFAULT_CREATETIME)
            .updatetime(DEFAULT_UPDATETIME);
        return receiving;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Receiving createUpdatedEntity(EntityManager em) {
        Receiving receiving = new Receiving()
            .userid(UPDATED_USERID)
            .consignee(UPDATED_CONSIGNEE)
            .mobile(UPDATED_MOBILE)
            .areaid(UPDATED_AREAID)
            .address(UPDATED_ADDRESS)
            .isdefault(UPDATED_ISDEFAULT)
            .createtime(UPDATED_CREATETIME)
            .updatetime(UPDATED_UPDATETIME);
        return receiving;
    }

    @BeforeEach
    public void initTest() {
        receiving = createEntity(em);
    }

    @Test
    @Transactional
    public void createReceiving() throws Exception {
        int databaseSizeBeforeCreate = receivingRepository.findAll().size();

        // Create the Receiving
        ReceivingDTO receivingDTO = receivingMapper.toDto(receiving);
        restReceivingMockMvc.perform(post("/api/receivings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receivingDTO)))
            .andExpect(status().isCreated());

        // Validate the Receiving in the database
        List<Receiving> receivingList = receivingRepository.findAll();
        assertThat(receivingList).hasSize(databaseSizeBeforeCreate + 1);
        Receiving testReceiving = receivingList.get(receivingList.size() - 1);
        assertThat(testReceiving.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testReceiving.getConsignee()).isEqualTo(DEFAULT_CONSIGNEE);
        assertThat(testReceiving.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testReceiving.getAreaid()).isEqualTo(DEFAULT_AREAID);
        assertThat(testReceiving.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testReceiving.isIsdefault()).isEqualTo(DEFAULT_ISDEFAULT);
        assertThat(testReceiving.getCreatetime()).isEqualTo(DEFAULT_CREATETIME);
        assertThat(testReceiving.getUpdatetime()).isEqualTo(DEFAULT_UPDATETIME);
    }

    @Test
    @Transactional
    public void createReceivingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = receivingRepository.findAll().size();

        // Create the Receiving with an existing ID
        receiving.setId(1L);
        ReceivingDTO receivingDTO = receivingMapper.toDto(receiving);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReceivingMockMvc.perform(post("/api/receivings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receivingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Receiving in the database
        List<Receiving> receivingList = receivingRepository.findAll();
        assertThat(receivingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReceivings() throws Exception {
        // Initialize the database
        receivingRepository.saveAndFlush(receiving);

        // Get all the receivingList
        restReceivingMockMvc.perform(get("/api/receivings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(receiving.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].consignee").value(hasItem(DEFAULT_CONSIGNEE.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].areaid").value(hasItem(DEFAULT_AREAID.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].isdefault").value(hasItem(DEFAULT_ISDEFAULT.booleanValue())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].updatetime").value(hasItem(DEFAULT_UPDATETIME.toString())));
    }
    
    @Test
    @Transactional
    public void getReceiving() throws Exception {
        // Initialize the database
        receivingRepository.saveAndFlush(receiving);

        // Get the receiving
        restReceivingMockMvc.perform(get("/api/receivings/{id}", receiving.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(receiving.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.consignee").value(DEFAULT_CONSIGNEE.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.areaid").value(DEFAULT_AREAID.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.isdefault").value(DEFAULT_ISDEFAULT.booleanValue()))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.updatetime").value(DEFAULT_UPDATETIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReceiving() throws Exception {
        // Get the receiving
        restReceivingMockMvc.perform(get("/api/receivings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReceiving() throws Exception {
        // Initialize the database
        receivingRepository.saveAndFlush(receiving);

        int databaseSizeBeforeUpdate = receivingRepository.findAll().size();

        // Update the receiving
        Receiving updatedReceiving = receivingRepository.findById(receiving.getId()).get();
        // Disconnect from session so that the updates on updatedReceiving are not directly saved in db
        em.detach(updatedReceiving);
        updatedReceiving
            .userid(UPDATED_USERID)
            .consignee(UPDATED_CONSIGNEE)
            .mobile(UPDATED_MOBILE)
            .areaid(UPDATED_AREAID)
            .address(UPDATED_ADDRESS)
            .isdefault(UPDATED_ISDEFAULT)
            .createtime(UPDATED_CREATETIME)
            .updatetime(UPDATED_UPDATETIME);
        ReceivingDTO receivingDTO = receivingMapper.toDto(updatedReceiving);

        restReceivingMockMvc.perform(put("/api/receivings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receivingDTO)))
            .andExpect(status().isOk());

        // Validate the Receiving in the database
        List<Receiving> receivingList = receivingRepository.findAll();
        assertThat(receivingList).hasSize(databaseSizeBeforeUpdate);
        Receiving testReceiving = receivingList.get(receivingList.size() - 1);
        assertThat(testReceiving.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testReceiving.getConsignee()).isEqualTo(UPDATED_CONSIGNEE);
        assertThat(testReceiving.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testReceiving.getAreaid()).isEqualTo(UPDATED_AREAID);
        assertThat(testReceiving.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testReceiving.isIsdefault()).isEqualTo(UPDATED_ISDEFAULT);
        assertThat(testReceiving.getCreatetime()).isEqualTo(UPDATED_CREATETIME);
        assertThat(testReceiving.getUpdatetime()).isEqualTo(UPDATED_UPDATETIME);
    }

    @Test
    @Transactional
    public void updateNonExistingReceiving() throws Exception {
        int databaseSizeBeforeUpdate = receivingRepository.findAll().size();

        // Create the Receiving
        ReceivingDTO receivingDTO = receivingMapper.toDto(receiving);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceivingMockMvc.perform(put("/api/receivings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receivingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Receiving in the database
        List<Receiving> receivingList = receivingRepository.findAll();
        assertThat(receivingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReceiving() throws Exception {
        // Initialize the database
        receivingRepository.saveAndFlush(receiving);

        int databaseSizeBeforeDelete = receivingRepository.findAll().size();

        // Delete the receiving
        restReceivingMockMvc.perform(delete("/api/receivings/{id}", receiving.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Receiving> receivingList = receivingRepository.findAll();
        assertThat(receivingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Receiving.class);
        Receiving receiving1 = new Receiving();
        receiving1.setId(1L);
        Receiving receiving2 = new Receiving();
        receiving2.setId(receiving1.getId());
        assertThat(receiving1).isEqualTo(receiving2);
        receiving2.setId(2L);
        assertThat(receiving1).isNotEqualTo(receiving2);
        receiving1.setId(null);
        assertThat(receiving1).isNotEqualTo(receiving2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceivingDTO.class);
        ReceivingDTO receivingDTO1 = new ReceivingDTO();
        receivingDTO1.setId(1L);
        ReceivingDTO receivingDTO2 = new ReceivingDTO();
        assertThat(receivingDTO1).isNotEqualTo(receivingDTO2);
        receivingDTO2.setId(receivingDTO1.getId());
        assertThat(receivingDTO1).isEqualTo(receivingDTO2);
        receivingDTO2.setId(2L);
        assertThat(receivingDTO1).isNotEqualTo(receivingDTO2);
        receivingDTO1.setId(null);
        assertThat(receivingDTO1).isNotEqualTo(receivingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(receivingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(receivingMapper.fromId(null)).isNull();
    }
}
