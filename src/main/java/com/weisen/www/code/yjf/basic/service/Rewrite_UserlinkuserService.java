package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Userlinkuser}.
 */
public interface Rewrite_UserlinkuserService {
	
	String findRecommendName(String userid);
	
	Boolean getMyPartner(String userid);

	// 分页查询用户的推荐人（时间 电话或token 做处理）
    Result findAllByRecommendAndInfo(String userid,int startPage,int pageSize);

}
