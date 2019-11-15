package com.weisen.www.code.yjf.basic.service.rewrite;


import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetIncomeAfferentDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_IncomeDetailsService {
    //获取各推荐人总数
	 Result getRecommendTotal(String recommendId);
	 
	 
	 //获取各推荐人列表
	 Result getRecommendList(Rewrite_GetIncomeAfferentDTO getIncomeAfferentDTO);
	 
	 //获取各推荐人列表
	 Result getProfitList(String userId);
	 
	 //用户信息列表
	 Result userInformationList(String userId,Integer Type,Integer pageNum ,Integer pageSize);
	 
	 //查询用户商家端收益列表倒叙(重写)
	 Result newFindMerchantProfitInfo(String userid, Integer startPage, Integer pageSize);
	 
	 
	 //获取各推荐人列表(为兼容新版本写的)
	 Result getOldProfitList(String userId);
}
