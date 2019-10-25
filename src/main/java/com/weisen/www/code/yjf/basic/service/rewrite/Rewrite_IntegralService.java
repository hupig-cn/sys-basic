package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.util.Result;

/**
 * 查询用户积分明细
 * 
 * @author LuoJinShui
 *
 */
public interface Rewrite_IntegralService {
	// 查询用户积分明细
	Result getIntegral(String userId);

	// 查询用户积分支出收入
	Result getExpenditure(String userId);

}
