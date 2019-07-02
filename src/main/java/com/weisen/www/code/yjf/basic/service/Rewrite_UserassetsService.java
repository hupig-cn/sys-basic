package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;

public interface Rewrite_UserassetsService {
	
	//查询用户余额
	Rewrite_PriceDTO findUserBalance(Long userId);
	
}
