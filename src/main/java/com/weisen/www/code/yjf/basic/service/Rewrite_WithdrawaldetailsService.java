package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.WithdrawaldetailsDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_WithdrawaldetailsService {

    // 创建明细
    Result createWithdrawaldetails(WithdrawaldetailsDTO withdrawaldetailsDTO);

    // 查询用户的提现明细列表
    Result findUserWithdrawaldetails(Long userid);


}
