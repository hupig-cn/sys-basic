package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;

public interface Rewrite_ReceiptpayService {

    // 查询商家今日收入
    Rewrite_PriceDTO selectTodayIncome(Long userId);

    // 获取商家余额
    Rewrite_PriceDTO selcetBalance(Long userId);

    // 获取昨日收款
    Rewrite_PriceDTO selectYesterday(Long userId);

}
