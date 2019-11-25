package com.weisen.www.code.yjf.basic.service;

import java.util.List;

import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_FilesDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Files}.
 */
public interface Rewrite_FilesService {

    /**
     * Save a files.
     *
     * @param filesDTO the entity to save.
     * @return the persisted entity.
     */
	String addsave(Rewrite_FilesDTO rewrite_FilesDTO);

	Rewrite_FilesDTO resave(Rewrite_FilesDTO rewrite_FilesDTO);

    /**
     * Get the "id" files.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Rewrite_FilesDTO findOne(Long id);

    /**
     * Delete the "id" files.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<Rewrite_FilesDTO> findList(Long[] ids);
    
    
    //添加图片宽高
    Result addImageList(Long startNum,Long Id);
    
}
