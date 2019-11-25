package com.weisen.www.code.yjf.basic.service;

import java.util.List;

import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_MercProfitDto;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_ProfitDTO;
import com.weisen.www.code.yjf.basic.util.Result;

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
    Rewrite_MercProfitDto getProfitInfo(Long userId);

    // 查询用户的各项收益
    Result getUserPrifitInfo(Long userId);
    
    // 查询用户的各项收益(推广端)
    Rewrite_ProfitDTO getUserProfit(String userid);

    // 查询用户商家端收益列表倒叙
    Result findMerchantProfitInfo(String userId,int startPage,int pageSize);

    // 根据用户账号查询详细收益（分页，暂时没有多条件）
    Result findByUserAccountOrSomething(String userAccount,int pageIndex,int pageSize);

	Result findReceiptpayList(String userAccount, String dealtype, String dealstate, int pageIndex, int pageSize);
}
