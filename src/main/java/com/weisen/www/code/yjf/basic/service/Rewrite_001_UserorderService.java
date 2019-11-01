package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.service.dto.IntroductionOrderDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/25 16:03
 */
public interface Rewrite_001_UserorderService {
    Result myUserOrder(String userid,String orderState,Integer pageNum,Integer pageSize);

    Result OrdersConfirmation(String userid, String orderid);

    Result Orderdetail(String userid, String ordercode);

    List<IntroductionOrderDTO> useerorder(List<Userorder> a);

}