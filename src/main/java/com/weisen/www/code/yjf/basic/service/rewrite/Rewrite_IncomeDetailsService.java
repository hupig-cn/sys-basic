package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_IncomeDetailsService {
    //获取各推荐人总数
	 Result getRecommendTotal(String recommendId);
	 
	 
	 //获取各推荐人总数
	 Result getRecommendList(String userId);
}
