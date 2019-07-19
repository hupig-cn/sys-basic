package com.weisen.www.code.yjf.basic.service;

import java.util.Optional;

import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Linkuser}.
 */
public interface Rewrite_LinkuserService {

    String authentication(LinkuserDTO linkuserDTO);
    
    Optional<LinkuserDTO> findByUserid(String userid);

}
