package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_UsercardService {

    // 创建银行卡信息
    Result createUserCard(UsercardDTO usercardDTO);

    // 查询所有银行卡信息
    Result findCardInfo();


}
