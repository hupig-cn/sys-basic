package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Order;
import com.weisen.www.code.yjf.basic.domain.Specifications;
import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_001_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_OrderRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_001_UserorderService;
import com.weisen.www.code.yjf.basic.service.dto.IntroductionOrderDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/25 16:04
 */
@Service
@Transactional
public class Rewrite_001_UserorderServiceImpl implements Rewrite_001_UserorderService {

    @Value("${images-path}")
    private String imagesPath;

    private final Rewrite_001_UserorderRepository rewrite_001_userorderRepository;

    private final Rewrite_SpecificationsRepository rewrite_specificationsRepository;

    private final Rewrite_OrderRepository rewrite_orderRepository;

    public Rewrite_001_UserorderServiceImpl(Rewrite_001_UserorderRepository rewrite_001_userorderRepository, Rewrite_SpecificationsRepository rewrite_specificationsRepository, Rewrite_OrderRepository rewrite_orderRepository) {
        this.rewrite_001_userorderRepository = rewrite_001_userorderRepository;
        this.rewrite_specificationsRepository = rewrite_specificationsRepository;
        this.rewrite_orderRepository = rewrite_orderRepository;
    }

    @Override
    public Result myUserOrder(String userid) {
        List<Userorder> list = rewrite_001_userorderRepository.findUserorderByUserid(userid);
        List<IntroductionOrderDTO> aa = useerorder(list);
        return Result.suc("??",aa,aa.size());
    }

    @Override
    public Result OrderState(String userid, String orderState) {
        if (!orderState.equals("1")||!orderState.equals("2")||!orderState.equals("3")||!orderState.equals("4")){
            return Result.fail("传入参数有误");
        }
        List<Userorder> userorder = rewrite_001_userorderRepository.findUserorderByUseridAndOrderstatus(userid, orderState);
        List<IntroductionOrderDTO> aa = useerorder(userorder);
        return Result.suc("??",aa,aa.size());
    }

    @Override
    public Result OrdersConfirmation(String userid, String ordercode) {
        Userorder userorderByOrdercode = rewrite_001_userorderRepository.findUserorderByOrdercode(ordercode);
        if (!userorderByOrdercode.getUserid().equals(userid)) {
            return Result.fail("你不是本人不能操作");
        }
        userorderByOrdercode.setOrderstatus("2");//代发货 todo
        userorderByOrdercode.setId(userorderByOrdercode.getId());
        rewrite_001_userorderRepository.save(userorderByOrdercode);
        return Result.suc("修改成功");
    }

    @Override
    public Result Orderdetail(String userid, String orderid) {
        Userorder userorderByOrdercode = rewrite_001_userorderRepository.findUserorderByOrdercode(orderid);
        if (!userorderByOrdercode.getUserid().equals(userid)) {
            return Result.fail("你不是本人不能操作");
        }
        List<Userorder> list = new ArrayList<>();
        list.add(userorderByOrdercode);
        List<IntroductionOrderDTO> useerorder = useerorder(list);
        IntroductionOrderDTO dto = useerorder.get(0);//todo
        Order order = rewrite_orderRepository.findOrderByBigorder(dto.getOrderid());
        return Result.suc("ojbk",order);
    }

    @Override
    public List<IntroductionOrderDTO> useerorder(List<Userorder> a){
        List<IntroductionOrderDTO> aa = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            Userorder userorder = a.get(i);
            //去other出来查到他的图片，价钱，描述，   订单状态，下单时间  --> 点开就详情页面
            IntroductionOrderDTO introductionOrderDTO = new IntroductionOrderDTO();
            introductionOrderDTO.setOrderid(userorder.getId()+"");
            introductionOrderDTO.setOrdercode(userorder.getOrdercode());
            Specifications s = rewrite_specificationsRepository.findSpecificationsByCommodityid(userorder.getOther());
            Long fileid = s.getFileid();
            introductionOrderDTO.setUrl(imagesPath+fileid);
            introductionOrderDTO.setPrice(s.getPrice());
            introductionOrderDTO.setSpecificatinons(s.getSpecifications());
            introductionOrderDTO.setStaus(userorder.getOrderstatus());
            introductionOrderDTO.setPaytime(userorder.getPaytime());
            aa.add(introductionOrderDTO);
        }
        return aa;
    }
}
