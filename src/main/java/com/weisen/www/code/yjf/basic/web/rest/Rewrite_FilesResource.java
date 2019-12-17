package com.weisen.www.code.yjf.basic.web.rest;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.weisen.www.code.yjf.basic.domain.Files;
import com.weisen.www.code.yjf.basic.repository.FilesRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_FilesService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_FilesDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_submitBasicDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Files}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "000-文件操作接口")
public class Rewrite_FilesResource {
	
	/**
	 * 文件保存位置
	 */
	@Value("${filePath-image}")
	private String filePathImage;
	
	/**
	 * 文件访问路径
	 */
	@Value("${images-path}")
	private String imagespath;
	
    private final Logger log = LoggerFactory.getLogger(Rewrite_FilesResource.class);
    
    private final FilesRepository filesRepository;

    private static final String ENTITY_NAME = "basicFiles";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Rewrite_FilesService rewrite_FilesService;

    public Rewrite_FilesResource(Rewrite_FilesService rewrite_FilesService,FilesRepository filesRepository) {
        this.rewrite_FilesService = rewrite_FilesService;
        this.filesRepository = filesRepository;
    }
    
    @GetMapping("/public/getFiles/{id}")
	public void detail(@PathVariable Long id, HttpServletResponse response) {
		Rewrite_FilesDTO rewrite_FilesDTO = rewrite_FilesService.findOne(id);
		if (rewrite_FilesDTO == null) {
			response.setStatus(404);
			return;
		}
		File targetFile = new File(filePathImage + rewrite_FilesDTO.getName());
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(filePathImage + rewrite_FilesDTO.getName()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			response.reset();
			response.addHeader("Content-Length", "" + targetFile.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());

			String fileName = rewrite_FilesDTO.getName();
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			String contentType = getContentType(suffix);
			if (contentType != null) {
				response.setContentType(contentType);
			} else {
				// 其余文件以下载形式
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition", "attachment;filename=" + rewrite_FilesDTO.getName());
			}
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    private String getContentType(String suffix) {
		String contentType = null;
		switch (suffix) {
		case ".jpg":
			contentType = "image/jpeg";
			break;
		case ".jpeg":
			contentType = "image/jpeg";
			break;
		case ".png":
			contentType = "image/png";
			break;
		case ".bmp":
			contentType = "application/x-bmp";
			break;
		}
		return contentType;
	}

    /**
     * {@code POST  /files} : Create a new files.
     *
     * @param filesDTO the filesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new filesDTO, or with status {@code 400 (Bad Request)} if the files has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/myfilesadd")
	@ApiOperation(value = "文件上传接口")
    public String createFiles(@RequestBody Rewrite_FilesDTO rewrite_FilesDTO) throws URISyntaxException {
        if (rewrite_FilesDTO.getId() != null) {
            throw new BadRequestAlertException("A new files cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return rewrite_FilesService.addsave(rewrite_FilesDTO);
    }

    /**
     * {@code PUT  /files} : Updates an existing files.
     *
     * @param filesDTO the filesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated filesDTO,
     * or with status {@code 400 (Bad Request)} if the filesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the filesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/myfilesre")
	@ApiOperation(value = "文件修改替换接口（未完成）")
    public ResponseEntity<Rewrite_FilesDTO> updateFiles(@RequestBody Rewrite_FilesDTO rewrite_FilesDTO) throws URISyntaxException {
        if (rewrite_FilesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Rewrite_FilesDTO result = rewrite_FilesService.resave(rewrite_FilesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rewrite_FilesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /files/:id} : get the "id" files.
     *
     * @param id the id of the filesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the filesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/myfiles/{id}")
	@ApiOperation(value = "下载一个文件")
    public Rewrite_FilesDTO getFiles(@PathVariable Long id) {
        log.debug("REST request to get Files : {}", id);
        Rewrite_FilesDTO rewrite_FilesDTO = rewrite_FilesService.findOne(id);
        return rewrite_FilesDTO;
    }

    @GetMapping("/public/myfiles/{id}")
    @ApiOperation(value = "下载一个文件")
    public Rewrite_FilesDTO getFilesex(@PathVariable Long id) {
        log.debug("REST request to get Files : {}", id);
        Rewrite_FilesDTO rewrite_FilesDTO = rewrite_FilesService.findOne(id);
        return rewrite_FilesDTO;
    }


    @PostMapping("/public/myfiles-list")
	@ApiOperation(value = "下载多个文件")
    public List<Rewrite_FilesDTO> getFilesList(@RequestBody Rewrite_submitBasicDTO basicDTO) {
        List<Rewrite_FilesDTO> rewrite_FilesDTO = rewrite_FilesService.findList(basicDTO.getIds());
        return rewrite_FilesDTO;
    }

    /**
     * {@code DELETE  /files/:id} : delete the "id" files.
     *
     * @param id the id of the filesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/myfiles/{id}")
	@ApiOperation(value = "删除一个文件")
    public ResponseEntity<Void> deleteFiles(@PathVariable Long id) {
        log.debug("REST request to delete Files : {}", id);
        rewrite_FilesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    
    @PostMapping("/upload")
	@Timed
	@ApiOperation(value = "文件上传")
	public Result upload(@RequestParam(name = "file") MultipartFile[] multipartFile) {
		Result result = null;
		List<String> imageList = new ArrayList<>();
		if (multipartFile != null && multipartFile.length > 0) {
			for (MultipartFile file : multipartFile) {
				if (file.getSize() > 10 * 1024 * 1024) {
					return Result.fail("上传失败，文件大小不能超过10M");
				}
				try {
					String uuid = createFile(file);
					imageList.add(uuid);
				} catch (IllegalStateException | IOException e) {
					log.error(e.getMessage());
				}
			}
			result = Result.suc("上传成功", imageList, imageList.size());
		}
		return result;
	}
    
    private String createFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
		String originFilename = multipartFile.getOriginalFilename();
		String suffix = originFilename.substring(originFilename.lastIndexOf('.'));
		// 获取图片大小
		long filesSize = multipartFile.getSize(); 							//文件字节
		String uuidString = UUID.randomUUID().toString();					//UUid
		String fileName = System.currentTimeMillis() + RandomStringUtils.randomAlphanumeric(6) + suffix; //时间戳文件名
		String target = filePathImage + fileName;				
		
		File destFile = new File(target);
		// write file
		multipartFile.transferTo(destFile);									//存放
		int width = 0;														
		int height = 0;
		File file = new File(target);
		if (suffix.endsWith(".jpg") || suffix.endsWith(".jpeg") || suffix.endsWith(".png") || suffix.endsWith(".gif") || suffix.endsWith(".webp")) {
			FileInputStream fis = new FileInputStream(file);				
			BufferedImage bufferedImg = ImageIO.read(fis);					
			width = bufferedImg.getWidth();									//图片宽度
			height = bufferedImg.getHeight();	 							//图片高度
		}
		// create in database
		Files dataFileDTO = new Files();
		dataFileDTO.setUserid("3");
		dataFileDTO.setName(fileName);
		dataFileDTO.setUuid(uuidString);
		dataFileDTO.setHeight(height);
		dataFileDTO.setWidth(width);
		dataFileDTO.setSize((int)filesSize);
		dataFileDTO.setFile(filePathImage);
		dataFileDTO.setFileContentType(getContentType(suffix));
		Files files = filesRepository.save(dataFileDTO);
		Files imgfiles = files;
		imgfiles.setUrl(imagespath + files.getId());
		filesRepository.save(imgfiles);
		return imgfiles.getId().toString();
	}
    
    
	/**
	 * 手动添加图片宽高
	 *
	 * @author sxx
	 * @date 2019-11-22 15:43:31
	 */
	@PostMapping("/addImage/List")
	@ApiOperation("手动添加图片宽高")
	public ResponseEntity<Result> addImageList(Long startNum,Long Id) {
		Result result = rewrite_FilesService.addImageList(startNum,Id);
		log.debug("访问地址: {},传入值: {},返回值: {}", "/additionalImage/List", "无", result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
	
	
	/**
	 * 自动添加图片宽高
	 *
	 * @author sxx
	 * @date 2019-11-26 09:43:31
	 */
	@PostMapping("/autoAddImage")
	@ApiOperation("自动添加图片宽高")
	public ResponseEntity<Result> autoAddImage() {
		Result result = rewrite_FilesService.autoAddImage();
		log.debug("访问地址: {},传入值: {},返回值: {}", "/additionalImage/List", "无", result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
	
	@PostMapping("/public/demo/uploadi")
	@ApiOperation("上传图片")
	public Result uploadi(@RequestParam(name = "file") MultipartFile multipartFile) {
		Result result = null;
		List<String> imageList = new ArrayList<>();
			if (multipartFile.getSize() > 10 * 1024 * 1024) {
				return Result.fail("上传失败，文件大小不能超过10M");
			}
			try {
				String id = createFile(multipartFile);
				imageList.add(id);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}
			result = Result.suc("上传成功", imageList);
		return result;
	}
	
	/**
	 * 获取图片时间
	 *
	 * @author sxx
	 * @date 2019-12-13 15:43:31
	 */
	@PostMapping("/gei/file/time")
	@ApiOperation("获取图片时间")
	public ResponseEntity<Result> getFileCreateTime() {
		Result result = rewrite_FilesService.getFileCreateTime();
		log.debug("访问地址: {},传入值: {},返回值: {}", "/gei/file/time", "无", result);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
	}
	
	
}
