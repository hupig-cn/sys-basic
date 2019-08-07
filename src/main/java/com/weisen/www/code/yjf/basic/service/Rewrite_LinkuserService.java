package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.service.dto.LinkuserDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitMemberDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Linkuser}.
 */
public interface Rewrite_LinkuserService {

    String authentication(LinkuserDTO linkuserDTO);

    Linkuser findByUserid(String userid);

    String queryRealName(String userid);

    Result getMemberInfo(Rewrite_submitMemberDTO rewrite_submitMemberDTO);
}
