package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.UserassetsDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_UserPriceDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_UserassetsService {
	
	//查询用户余额
    Rewrite_UserPriceDTO findUserBalance(Long userId);

	// 查询用户的余额 积分 优惠券
    Rewrite_PriceDTO findUserInfo(Long userId);

    // 查询用户的可用余额 积分 优惠券
    UserassetsDTO findUserAssets(String userid);
    
    Result rechangeYue (String mobile, String yue);
    
    Result deductYue (String mobile, String yue);
	
}
