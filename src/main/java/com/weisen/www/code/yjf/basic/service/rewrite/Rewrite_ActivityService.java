package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.util.Result;

/**
 * 优惠活动
 *
 * @author sxx
 *
 */
public interface Rewrite_ActivityService {

	// 查询用户优惠活动流水
	Result queryAmount(String userId, Integer pageNum, Integer pageSize);

	// Shui
	Result getConversionFunds(String userId, Integer pageNum, Integer pageSize);

	// 查询商家用户的可用资金和活动资金 LuoJinShui
	Result getAvailableAmoAndActivityAmo(String userId);

	// 可用资金达到50元可提现到余额 LuoJinShui
	Result availableAmoWithdrawalBalance(String userId, String availableAmo);

	// 查询可用资金提现流水明细 LuoJinShui
	Result getWithdrawalDetails(String userId, Integer pageNum, Integer pageSize);

    Result lunbotu(Integer type);
    
    Result ruleRead(Long uid);
    
    Result updaterule(Long userid);
}
