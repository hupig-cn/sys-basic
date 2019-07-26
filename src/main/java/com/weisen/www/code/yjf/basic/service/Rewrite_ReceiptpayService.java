package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import java.util.List;

public interface Rewrite_ReceiptpayService {

    // 查询商家今日收入
    Rewrite_PriceDTO selectTodayIncome(Long userId);

    // 获取提现记录
    List<ReceiptpayDTO> selcetMoneyRecord(Long userId);

    // 获取昨日收款
    Rewrite_PriceDTO selectYesterday(Long userId);

    //创建收支明细
    Result createReceiptpay(String userId,String type,String sourcer,String sourcerId,String amout);

    // 查询商家各项详细收益
    Result getProfitInfo(Long userId);

}
