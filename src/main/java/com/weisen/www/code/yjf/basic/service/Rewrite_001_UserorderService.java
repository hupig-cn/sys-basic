package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.service.dto.IntroductionOrderDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_UserOrderDTO;
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

    Result CreateOrder(Rewrite_UserOrderDTO dto);

    Result cheakpaypassword(String userid, String pass);

    Result myfilesList(Integer pageSize, Integer pageNum,Integer type,Integer condition);

    Result myfilesLists(String commityid);
}
