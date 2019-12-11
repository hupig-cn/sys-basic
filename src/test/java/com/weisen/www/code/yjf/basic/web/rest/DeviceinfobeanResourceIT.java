package com.weisen.www.code.yjf.basic.web.rest;

import com.weisen.www.code.yjf.basic.BasicApp;
import com.weisen.www.code.yjf.basic.config.SecurityBeanOverrideConfiguration;
import com.weisen.www.code.yjf.basic.domain.Deviceinfobean;
import com.weisen.www.code.yjf.basic.repository.DeviceinfobeanRepository;
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
 * Integration tests for the {@Link DeviceinfobeanResource} REST controller.
 */
@SpringBootTest(classes = { SecurityBeanOverrideConfiguration.class, BasicApp.class })
public class DeviceinfobeanResourceIT {

	private static final String DEFAULT_PLATFORMANDROID = "AAAAAAAAAA";

	private static final String DEFAULT_PLATFORMIOS = "AAAAAAAAAA";

	private static final int DEFAULT_USERID = 1;
	private static final int UPDATED_USERID = 1;

	private static final String DEFAULT_PLATFORMNAME = "AAAAAAAAAA";
	private static final String UPDATED_PLATFORMNAME = "BBBBBBBBBB";

	private static final String DEFAULT_DEVICEMANUFACTURER = "AAAAAAAAAA";
	private static final String UPDATED_DEVICEMANUFACTURER = "BBBBBBBBBB";

	private static final String DEFAULT_DEVICEMODEL = "AAAAAAAAAA";
	private static final String UPDATED_DEVICEMODEL = "BBBBBBBBBB";

	private static final String DEFAULT_DEVICEABIS = "AAAAAAAAAA";
	private static final String UPDATED_DEVICEABIS = "BBBBBBBBBB";

	private static final String DEFAULT_SDKVERSIONNAME = "AAAAAAAAAA";
	private static final String UPDATED_SDKVERSIONNAME = "BBBBBBBBBB";

	private static final int DEFAULT_SDKVERSIONCODE = 1;
	private static final int UPDATED_SDKVERSIONCODE = 1;

	private static final String DEFAULT_ROMNAME = "AAAAAAAAAA";
	private static final String UPDATED_ROMNAME = "BBBBBBBBBB";

	private static final String DEFAULT_ROMVERSION = "AAAAAAAAAA";
	private static final String UPDATED_ROMVERSION = "BBBBBBBBBB";

	private static final int DEFAULT_SCREENWIDTH = 1;
	private static final int UPDATED_SCREENWIDTH = 1;

	private static final int DEFAULT_SCREENHEIGHT = 1;
	private static final int UPDATED_SCREENHEIGHT = 1;

	private static final int DEFAULT_APPSCREENWIDTH = 1;
	private static final int UPDATED_APPSCREENWIDTH = 2;

	private static final int DEFAULT_APPSCREENHEIGHT = 1;
	private static final int UPDATED_APPSCREENHEIGHT = 2;

	private static final int DEFAULT_SCREENDENSITYDPI = 1;
	private static final int UPDATED_SCREENDENSITYDPI = 2;

	private static final float DEFAULT_SCREENDENSITY = 1f;
	private static final float UPDATED_SCREENDENSITY = 2f;

	private static final String DEFAULT_APPVERSIONNAME = "AAAAAAAAAA";
	private static final String UPDATED_APPVERSIONNAME = "BBBBBBBBBB";

	private static final int DEFAULT_APPVERSIONCODE = 1;
	private static final int UPDATED_APPVERSIONCODE = 1;

	private static final Boolean DEFAULT_ISROOT = false;
	private static final Boolean UPDATED_ISROOT = true;

	@Autowired
	private DeviceinfobeanRepository deviceinfobeanRepository;

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

	private MockMvc restDeviceinfobeanMockMvc;

	private Deviceinfobean deviceinfobean;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		final DeviceinfobeanResource deviceinfobeanResource = new DeviceinfobeanResource(deviceinfobeanRepository);
		this.restDeviceinfobeanMockMvc = MockMvcBuilders.standaloneSetup(deviceinfobeanResource)
				.setCustomArgumentResolvers(pageableArgumentResolver).setControllerAdvice(exceptionTranslator)
				.setConversionService(createFormattingConversionService()).setMessageConverters(jacksonMessageConverter)
				.setValidator(validator).build();
	}

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it, if
	 * they test an entity which requires the current entity.
	 */
	public static Deviceinfobean createEntity(EntityManager em) {
		Deviceinfobean deviceinfobean = new Deviceinfobean().userid(DEFAULT_USERID).platformname(DEFAULT_PLATFORMNAME)
				.devicemanufacturer(DEFAULT_DEVICEMANUFACTURER).devicemodel(DEFAULT_DEVICEMODEL)
				.deviceabis(DEFAULT_DEVICEABIS).sdkversionname(DEFAULT_SDKVERSIONNAME)
				.sdkversioncode(DEFAULT_SDKVERSIONCODE).romname(DEFAULT_ROMNAME).romversion(DEFAULT_ROMVERSION)
				.screenwidth(DEFAULT_SCREENWIDTH).screenheight(DEFAULT_SCREENHEIGHT)
				.appscreenwidth(DEFAULT_APPSCREENWIDTH).appscreenheight(DEFAULT_APPSCREENHEIGHT)
				.screendensitydpi(DEFAULT_SCREENDENSITYDPI).screendensity(DEFAULT_SCREENDENSITY)
				.appversionname(DEFAULT_APPVERSIONNAME).appversioncode(DEFAULT_APPVERSIONCODE).isroot(DEFAULT_ISROOT);
		return deviceinfobean;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it, if
	 * they test an entity which requires the current entity.
	 */
	public static Deviceinfobean createUpdatedEntity(EntityManager em) {
		Deviceinfobean deviceinfobean = new Deviceinfobean().userid(UPDATED_USERID).platformname(UPDATED_PLATFORMNAME)
				.devicemanufacturer(UPDATED_DEVICEMANUFACTURER).devicemodel(UPDATED_DEVICEMODEL)
				.deviceabis(UPDATED_DEVICEABIS).sdkversionname(UPDATED_SDKVERSIONNAME)
				.sdkversioncode(UPDATED_SDKVERSIONCODE).romname(UPDATED_ROMNAME).romversion(UPDATED_ROMVERSION)
				.screenwidth(UPDATED_SCREENWIDTH).screenheight(UPDATED_SCREENHEIGHT)
				.appscreenwidth(UPDATED_APPSCREENWIDTH).appscreenheight(UPDATED_APPSCREENHEIGHT)
				.screendensitydpi(UPDATED_SCREENDENSITYDPI).screendensity(UPDATED_SCREENDENSITY)
				.appversionname(UPDATED_APPVERSIONNAME).appversioncode(UPDATED_APPVERSIONCODE).isroot(UPDATED_ISROOT);
		return deviceinfobean;
	}

	@BeforeEach
	public void initTest() {
		deviceinfobean = createEntity(em);
	}

	@Test
	@Transactional
	public void createDeviceinfobean() throws Exception {
		int databaseSizeBeforeCreate = deviceinfobeanRepository.findAll().size();

		// Create the Deviceinfobean
		restDeviceinfobeanMockMvc.perform(post("/api/deviceinfobeans").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(deviceinfobean))).andExpect(status().isCreated());

		// Validate the Deviceinfobean in the database
		List<Deviceinfobean> deviceinfobeanList = deviceinfobeanRepository.findAll();
		assertThat(deviceinfobeanList).hasSize(databaseSizeBeforeCreate + 1);
		Deviceinfobean testDeviceinfobean = deviceinfobeanList.get(deviceinfobeanList.size() - 1);
		assertThat(testDeviceinfobean.getUserid()).isEqualTo(DEFAULT_USERID);
		assertThat(testDeviceinfobean.getPlatformname()).isEqualTo(DEFAULT_PLATFORMNAME);
		assertThat(testDeviceinfobean.getDevicemanufacturer()).isEqualTo(DEFAULT_DEVICEMANUFACTURER);
		assertThat(testDeviceinfobean.getDevicemodel()).isEqualTo(DEFAULT_DEVICEMODEL);
		assertThat(testDeviceinfobean.getDeviceabis()).isEqualTo(DEFAULT_DEVICEABIS);
		assertThat(testDeviceinfobean.getSdkversionname()).isEqualTo(DEFAULT_SDKVERSIONNAME);
		assertThat(testDeviceinfobean.getSdkversioncode()).isEqualTo(DEFAULT_SDKVERSIONCODE);
		assertThat(testDeviceinfobean.getRomname()).isEqualTo(DEFAULT_ROMNAME);
		assertThat(testDeviceinfobean.getRomversion()).isEqualTo(DEFAULT_ROMVERSION);
		assertThat(testDeviceinfobean.getScreenwidth()).isEqualTo(DEFAULT_SCREENWIDTH);
		assertThat(testDeviceinfobean.getScreenheight()).isEqualTo(DEFAULT_SCREENHEIGHT);
		assertThat(testDeviceinfobean.getAppscreenwidth()).isEqualTo(DEFAULT_APPSCREENWIDTH);
		assertThat(testDeviceinfobean.getAppscreenheight()).isEqualTo(DEFAULT_APPSCREENHEIGHT);
		assertThat(testDeviceinfobean.getScreendensitydpi()).isEqualTo(DEFAULT_SCREENDENSITYDPI);
		assertThat(testDeviceinfobean.getScreendensity()).isEqualTo(DEFAULT_SCREENDENSITY);
		assertThat(testDeviceinfobean.getAppversionname()).isEqualTo(DEFAULT_APPVERSIONNAME);
		assertThat(testDeviceinfobean.getAppversioncode()).isEqualTo(DEFAULT_APPVERSIONCODE);
		assertThat(testDeviceinfobean.isIsroot()).isEqualTo(DEFAULT_ISROOT);
	}

	@Test
	@Transactional
	public void createDeviceinfobeanWithExistingId() throws Exception {
		int databaseSizeBeforeCreate = deviceinfobeanRepository.findAll().size();

		// Create the Deviceinfobean with an existing ID
		deviceinfobean.setId(1L);

		// An entity with an existing ID cannot be created, so this API call must fail
		restDeviceinfobeanMockMvc.perform(post("/api/deviceinfobeans").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(deviceinfobean))).andExpect(status().isBadRequest());

		// Validate the Deviceinfobean in the database
		List<Deviceinfobean> deviceinfobeanList = deviceinfobeanRepository.findAll();
		assertThat(deviceinfobeanList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	public void getAllDeviceinfobeans() throws Exception {
		// Initialize the database
		deviceinfobeanRepository.saveAndFlush(deviceinfobean);

		// Get all the deviceinfobeanList
		restDeviceinfobeanMockMvc.perform(get("/api/deviceinfobeans?sort=id,desc")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.[*].id").value(hasItem(deviceinfobean.getId().intValue())))
				.andExpect(jsonPath("$.[*].platformandroid").value(hasItem(DEFAULT_PLATFORMANDROID.toString())))
				.andExpect(jsonPath("$.[*].platformios").value(hasItem(DEFAULT_PLATFORMIOS.toString())))
				.andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID)))
				.andExpect(jsonPath("$.[*].platformname").value(hasItem(DEFAULT_PLATFORMNAME.toString())))
				.andExpect(jsonPath("$.[*].devicemanufacturer").value(hasItem(DEFAULT_DEVICEMANUFACTURER.toString())))
				.andExpect(jsonPath("$.[*].devicemodel").value(hasItem(DEFAULT_DEVICEMODEL.toString())))
				.andExpect(jsonPath("$.[*].deviceabis").value(hasItem(DEFAULT_DEVICEABIS.toString())))
				.andExpect(jsonPath("$.[*].sdkversionname").value(hasItem(DEFAULT_SDKVERSIONNAME.toString())))
				.andExpect(jsonPath("$.[*].sdkversioncode").value(hasItem(DEFAULT_SDKVERSIONCODE)))
				.andExpect(jsonPath("$.[*].romname").value(hasItem(DEFAULT_ROMNAME.toString())))
				.andExpect(jsonPath("$.[*].romversion").value(hasItem(DEFAULT_ROMVERSION.toString())))
				.andExpect(jsonPath("$.[*].screenwidth").value(hasItem(DEFAULT_SCREENWIDTH)))
				.andExpect(jsonPath("$.[*].screenheight").value(hasItem(DEFAULT_SCREENHEIGHT)))
				.andExpect(jsonPath("$.[*].appscreenwidth").value(hasItem(DEFAULT_APPSCREENWIDTH)))
				.andExpect(jsonPath("$.[*].appscreenheight").value(hasItem(DEFAULT_APPSCREENHEIGHT)))
				.andExpect(jsonPath("$.[*].screendensitydpi").value(hasItem(DEFAULT_SCREENDENSITYDPI)))
				.andExpect(jsonPath("$.[*].screendensity").value(hasItem(DEFAULT_SCREENDENSITY)))
				.andExpect(jsonPath("$.[*].appversionname").value(hasItem(DEFAULT_APPVERSIONNAME)))
				.andExpect(jsonPath("$.[*].appversioncode").value(hasItem(DEFAULT_APPVERSIONCODE)))
				.andExpect(jsonPath("$.[*].isroot").value(hasItem(DEFAULT_ISROOT.booleanValue())));
	}

	@Test
	@Transactional
	public void getDeviceinfobean() throws Exception {
		// Initialize the database
		deviceinfobeanRepository.saveAndFlush(deviceinfobean);

		// Get the deviceinfobean
		restDeviceinfobeanMockMvc.perform(get("/api/deviceinfobeans/{id}", deviceinfobean.getId()))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id").value(deviceinfobean.getId().intValue()))
				.andExpect(jsonPath("$.platformandroid").value(DEFAULT_PLATFORMANDROID.toString()))
				.andExpect(jsonPath("$.platformios").value(DEFAULT_PLATFORMIOS.toString()))
				.andExpect(jsonPath("$.userid").value(DEFAULT_USERID))
				.andExpect(jsonPath("$.platformname").value(DEFAULT_PLATFORMNAME.toString()))
				.andExpect(jsonPath("$.devicemanufacturer").value(DEFAULT_DEVICEMANUFACTURER.toString()))
				.andExpect(jsonPath("$.devicemodel").value(DEFAULT_DEVICEMODEL.toString()))
				.andExpect(jsonPath("$.deviceabis").value(DEFAULT_DEVICEABIS.toString()))
				.andExpect(jsonPath("$.sdkversionname").value(DEFAULT_SDKVERSIONNAME.toString()))
				.andExpect(jsonPath("$.sdkversioncode").value(DEFAULT_SDKVERSIONCODE))
				.andExpect(jsonPath("$.romname").value(DEFAULT_ROMNAME.toString()))
				.andExpect(jsonPath("$.romversion").value(DEFAULT_ROMVERSION.toString()))
				.andExpect(jsonPath("$.screenwidth").value(DEFAULT_SCREENWIDTH))
				.andExpect(jsonPath("$.screenheight").value(DEFAULT_SCREENHEIGHT))
				.andExpect(jsonPath("$.appscreenwidth").value(DEFAULT_APPSCREENWIDTH))
				.andExpect(jsonPath("$.appscreenheight").value(DEFAULT_APPSCREENHEIGHT))
				.andExpect(jsonPath("$.screendensitydpi").value(DEFAULT_SCREENDENSITYDPI))
				.andExpect(jsonPath("$.screendensity").value(DEFAULT_SCREENDENSITY))
				.andExpect(jsonPath("$.appversionname").value(DEFAULT_APPVERSIONNAME.toString()))
				.andExpect(jsonPath("$.appversioncode").value(DEFAULT_APPVERSIONCODE))
				.andExpect(jsonPath("$.isroot").value(DEFAULT_ISROOT.booleanValue()));
	}

	@Test
	@Transactional
	public void getNonExistingDeviceinfobean() throws Exception {
		// Get the deviceinfobean
		restDeviceinfobeanMockMvc.perform(get("/api/deviceinfobeans/{id}", Long.MAX_VALUE))
				.andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	public void updateDeviceinfobean() throws Exception {
		// Initialize the database
		deviceinfobeanRepository.saveAndFlush(deviceinfobean);

		int databaseSizeBeforeUpdate = deviceinfobeanRepository.findAll().size();

		// Update the deviceinfobean
		Deviceinfobean updatedDeviceinfobean = deviceinfobeanRepository.findById(deviceinfobean.getId()).get();
		// Disconnect from session so that the updates on updatedDeviceinfobean are not
		// directly saved in db
		em.detach(updatedDeviceinfobean);
		updatedDeviceinfobean.userid(UPDATED_USERID).platformname(UPDATED_PLATFORMNAME)
				.devicemanufacturer(UPDATED_DEVICEMANUFACTURER).devicemodel(UPDATED_DEVICEMODEL)
				.deviceabis(UPDATED_DEVICEABIS).sdkversionname(UPDATED_SDKVERSIONNAME)
				.sdkversioncode(UPDATED_SDKVERSIONCODE).romname(UPDATED_ROMNAME).romversion(UPDATED_ROMVERSION)
				.screenwidth(UPDATED_SCREENWIDTH).screenheight(UPDATED_SCREENHEIGHT)
				.appscreenwidth(UPDATED_APPSCREENWIDTH).appscreenheight(UPDATED_APPSCREENHEIGHT)
				.screendensitydpi(UPDATED_SCREENDENSITYDPI).screendensity(UPDATED_SCREENDENSITY)
				.appversionname(UPDATED_APPVERSIONNAME).appversioncode(UPDATED_APPVERSIONCODE).isroot(UPDATED_ISROOT);

		restDeviceinfobeanMockMvc.perform(put("/api/deviceinfobeans").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(updatedDeviceinfobean))).andExpect(status().isOk());

		// Validate the Deviceinfobean in the database
		List<Deviceinfobean> deviceinfobeanList = deviceinfobeanRepository.findAll();
		assertThat(deviceinfobeanList).hasSize(databaseSizeBeforeUpdate);
		Deviceinfobean testDeviceinfobean = deviceinfobeanList.get(deviceinfobeanList.size() - 1);
		assertThat(testDeviceinfobean.getUserid()).isEqualTo(UPDATED_USERID);
		assertThat(testDeviceinfobean.getPlatformname()).isEqualTo(UPDATED_PLATFORMNAME);
		assertThat(testDeviceinfobean.getDevicemanufacturer()).isEqualTo(UPDATED_DEVICEMANUFACTURER);
		assertThat(testDeviceinfobean.getDevicemodel()).isEqualTo(UPDATED_DEVICEMODEL);
		assertThat(testDeviceinfobean.getDeviceabis()).isEqualTo(UPDATED_DEVICEABIS);
		assertThat(testDeviceinfobean.getSdkversionname()).isEqualTo(UPDATED_SDKVERSIONNAME);
		assertThat(testDeviceinfobean.getSdkversioncode()).isEqualTo(UPDATED_SDKVERSIONCODE);
		assertThat(testDeviceinfobean.getRomname()).isEqualTo(UPDATED_ROMNAME);
		assertThat(testDeviceinfobean.getRomversion()).isEqualTo(UPDATED_ROMVERSION);
		assertThat(testDeviceinfobean.getScreenwidth()).isEqualTo(UPDATED_SCREENWIDTH);
		assertThat(testDeviceinfobean.getScreenheight()).isEqualTo(UPDATED_SCREENHEIGHT);
		assertThat(testDeviceinfobean.getAppscreenwidth()).isEqualTo(UPDATED_APPSCREENWIDTH);
		assertThat(testDeviceinfobean.getAppscreenheight()).isEqualTo(UPDATED_APPSCREENHEIGHT);
		assertThat(testDeviceinfobean.getScreendensitydpi()).isEqualTo(UPDATED_SCREENDENSITYDPI);
		assertThat(testDeviceinfobean.getScreendensity()).isEqualTo(UPDATED_SCREENDENSITY);
		assertThat(testDeviceinfobean.getAppversionname()).isEqualTo(UPDATED_APPVERSIONNAME);
		assertThat(testDeviceinfobean.getAppversioncode()).isEqualTo(UPDATED_APPVERSIONCODE);
		assertThat(testDeviceinfobean.isIsroot()).isEqualTo(UPDATED_ISROOT);
	}

	@Test
	@Transactional
	public void updateNonExistingDeviceinfobean() throws Exception {
		int databaseSizeBeforeUpdate = deviceinfobeanRepository.findAll().size();

		// Create the Deviceinfobean

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restDeviceinfobeanMockMvc.perform(put("/api/deviceinfobeans").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(deviceinfobean))).andExpect(status().isBadRequest());

		// Validate the Deviceinfobean in the database
		List<Deviceinfobean> deviceinfobeanList = deviceinfobeanRepository.findAll();
		assertThat(deviceinfobeanList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	public void deleteDeviceinfobean() throws Exception {
		// Initialize the database
		deviceinfobeanRepository.saveAndFlush(deviceinfobean);

		int databaseSizeBeforeDelete = deviceinfobeanRepository.findAll().size();

		// Delete the deviceinfobean
		restDeviceinfobeanMockMvc.perform(
				delete("/api/deviceinfobeans/{id}", deviceinfobean.getId()).accept(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(status().isNoContent());

		// Validate the database contains one less item
		List<Deviceinfobean> deviceinfobeanList = deviceinfobeanRepository.findAll();
		assertThat(deviceinfobeanList).hasSize(databaseSizeBeforeDelete - 1);
	}

	@Test
	@Transactional
	public void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(Deviceinfobean.class);
		Deviceinfobean deviceinfobean1 = new Deviceinfobean();
		deviceinfobean1.setId(1L);
		Deviceinfobean deviceinfobean2 = new Deviceinfobean();
		deviceinfobean2.setId(deviceinfobean1.getId());
		assertThat(deviceinfobean1).isEqualTo(deviceinfobean2);
		deviceinfobean2.setId(2L);
		assertThat(deviceinfobean1).isNotEqualTo(deviceinfobean2);
		deviceinfobean1.setId(null);
		assertThat(deviceinfobean1).isNotEqualTo(deviceinfobean2);
	}
}
