package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Linkuser}.
 */
public interface Rewrite_LinkuserService {

    String authentication(LinkuserDTO linkuserDTO);
    
    Linkuser findByUserid(String userid);
    
    String queryRealName(String userid);

}
