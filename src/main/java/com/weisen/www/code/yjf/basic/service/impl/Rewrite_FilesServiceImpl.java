package com.weisen.www.code.yjf.basic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Files;
import com.weisen.www.code.yjf.basic.repository.FilesRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_FilesService;
import com.weisen.www.code.yjf.basic.service.dto.FilesDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_FilesDTO;
import com.weisen.www.code.yjf.basic.service.mapper.FilesMapper;
import com.weisen.www.code.yjf.basic.service.util.FileOperation;

/**
 * Service Implementation for managing {@link Files}.
 */
@Service
@Transactional
public class Rewrite_FilesServiceImpl implements Rewrite_FilesService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_FilesServiceImpl.class);

    @Value("${filePath-image}")
    private String imagesFilePath;

    private final FilesRepository filesRepository;

    private final FilesMapper filesMapper;

    public Rewrite_FilesServiceImpl(FilesRepository filesRepository, FilesMapper filesMapper) {
        this.filesRepository = filesRepository;
        this.filesMapper = filesMapper;
    }

    /**
     * Save a files.
     *
     * @param filesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public String addsave(Rewrite_FilesDTO rewrite_FilesDTO) {
        Files files = new Files();
        files.setId(null);
        files.setUserid(rewrite_FilesDTO.getUserid());
        files.setSize(rewrite_FilesDTO.getSize());
        files.setFileContentType(rewrite_FilesDTO.getFileContentType());
        String fileName = rewrite_FilesDTO.getFileContentType();
        fileName = fileName.substring(fileName.indexOf("/") + 1);
        fileName = System.currentTimeMillis() + RandomStringUtils.randomAlphanumeric(6) + "." + fileName;
        files.setName(fileName);
        files.setFile(imagesFilePath);
        if (FileOperation.saveFile(rewrite_FilesDTO.getFile(), imagesFilePath, fileName)) {
            files = filesRepository.save(files);
            return files.getId().toString();
        } else {
            return "文件上传失败";
        }
    }

    @Override
    public Rewrite_FilesDTO resave(Rewrite_FilesDTO rewrite_FilesDTO) {
//    	Files files = new Files();
//    	files.setId(rewrite_FilesDTO.getId()==null?null:rewrite_FilesDTO.getId());
//        Files files = filesMapper.toEntity(rewrite_FilesDTO);
//        files = filesRepository.save(files);
//        return filesMapper.toDto(files);
        return null;
    }

    /**
     * Get one files by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Rewrite_FilesDTO findOne(Long id) {
        Optional<FilesDTO> files = filesRepository.findById(id).map(filesMapper::toDto);
        Rewrite_FilesDTO rewrite_FilesDTO = new Rewrite_FilesDTO();
        rewrite_FilesDTO.setId(files.get().getId());
        rewrite_FilesDTO.setFile(FileOperation.getFile(files.get().getFile() + "/" + files.get().getName()));
        rewrite_FilesDTO.setFileContentType(files.get().getFileContentType());
        rewrite_FilesDTO.setUserid(files.get().getUserid());
        rewrite_FilesDTO.setName(files.get().getName());
        rewrite_FilesDTO.setTarget(files.get().getFile());
        return rewrite_FilesDTO;
    }

    /**
     * Delete the files by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Files : {}", id);
        filesRepository.deleteById(id);
    }

    public List<Rewrite_FilesDTO> findList(Long[] ids) {
        if (0 == ids.length)
            throw new RuntimeException("数据异常");
        ArrayList<Rewrite_FilesDTO> arrayList = new ArrayList<Rewrite_FilesDTO>();
        for (int i = 0; i < ids.length; i++) {
            Rewrite_FilesDTO one = findOne(ids[i]);
            arrayList.add(one);
        }
        return arrayList;
    }
}
