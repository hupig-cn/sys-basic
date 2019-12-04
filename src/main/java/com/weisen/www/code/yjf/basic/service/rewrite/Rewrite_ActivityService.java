package com.weisen.www.code.yjf.basic.service.rewrite;


import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetActivityPayDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * 优惠活动
 *
 * @author sxx
 *
 */
public interface Rewrite_ActivityService {

	// 查询用户优惠活动流水
	Result queryAmount(Rewrite_GetActivityPayDTO getActivityPayDTO);

    //Shui
    Result zhuanhuankeyongzhijin(String userId, Integer pageNum, Integer pageSize);
}
