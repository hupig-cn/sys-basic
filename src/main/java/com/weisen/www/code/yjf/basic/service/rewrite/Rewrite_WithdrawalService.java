package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Interface for managing
 * {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
public interface Rewrite_WithdrawalService {
	// 提交提现记录
	Result insertWithdrawal(WithdrawalDTO withdrawalDTO);

	// 获取账号提现记录
	Result getWithdrawalByAccount(String userId, Integer pageNum, Integer pageSize);

	// 获取所有提现记录(根据类型查询)后台
	Result getWithdrawals(Integer pageNum, Integer pageSize, String type);

	// 后台审核提现记录
	Result auditWithdrawal(Long withdrawalid, String type, String content);

	// 获取用户提现信息
	Result getUserInfo(Long id);

	// 获取一条提现数据详细信息
	Result getWithdrawalInfo(Long withdrawalId);

	// 查询商家用户的可用资金和活动资金 LuoJinShui
	Result getAvailableAmoAndActivityAmo(String userId);

	// 可用资金达到50元可提现到余额 LuoJinShui
	Result availableAmoWithdrawalBalance(String userId, String availableAmo);

	// 查询可用资金提现流水明细 LuoJinShui
	Result getWithdrawalDetails(String userId, Integer pageNum, Integer pageSize);
    //Shui
    Result zhuanhuankeyongzhijin(String userId, Integer pageNum, Integer pageSize);
}
