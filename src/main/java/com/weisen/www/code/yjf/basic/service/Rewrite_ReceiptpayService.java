package com.weisen.www.code.yjf.basic.service;

import java.util.List;

import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;

public interface Rewrite_ReceiptpayService {

    // 查询商家今日收入
    Rewrite_PriceDTO selectTodayIncome(Long userId);

    // 获取提现记录
    List<ReceiptpayDTO> selcetMoneyRecord(Long userId);

    // 获取昨日收款
    Rewrite_PriceDTO selectYesterday(Long userId);

}
