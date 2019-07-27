package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_WithdrawalDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
public interface Rewrite_WithdrawalService {
    //提交提现记录
    Result insertWithdrawal(Rewrite_WithdrawalDTO rewrite_withdrawalDTO);
    //获取账号提现记录
    Result getWithdrawalByAccount(String userId, Integer pageNum, Integer pageSize);
    //本地获取所有提现记录
    Result getWithdrawals(Integer pageNum, Integer pageSize);
    //后台审核通过提现
    Result auditWithdrawal(Long id, String other, String modifier);

    // 获取用户提现信息
    Result getUserInfo(Long id);

    // 获取一条提现数据详细信息
    Result getWithdrawalInfo(Long withdrawalId);
}
