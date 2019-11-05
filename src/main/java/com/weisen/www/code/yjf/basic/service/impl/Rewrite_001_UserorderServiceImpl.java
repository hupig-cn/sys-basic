package com.weisen.www.code.yjf.basic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Order;
import com.weisen.www.code.yjf.basic.domain.Specifications;
import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_001_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_OrderRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_SpecificationsRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_001_UserorderService;
import com.weisen.www.code.yjf.basic.service.dto.IntroductionOrderDTO;
import com.weisen.www.code.yjf.basic.service.dto.OrderDTO;
import com.weisen.www.code.yjf.basic.util.Result;

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

    public Rewrite_001_UserorderServiceImpl(Rewrite_001_UserorderRepository rewrite_001_userorderRepository, Rewrite_SpecificationsRepository rewrite_specificationsRepository, Rewrite_OrderRepository rewrite_orderRepository ) {
        this.rewrite_001_userorderRepository = rewrite_001_userorderRepository;
        this.rewrite_specificationsRepository = rewrite_specificationsRepository;
        this.rewrite_orderRepository = rewrite_orderRepository;
    }

    @Override
    public Result myUserOrder(String userid,String orderState,Integer pageNum,Integer pageSize) {
        List<IntroductionOrderDTO> aaa = new ArrayList<>();
        List<IntroductionOrderDTO> aa ;
        if (pageNum == null || pageSize == null ){
            pageNum = 0;
            pageSize = 10;
        }
        if (orderState.equals("0")){
            List<Userorder> list = rewrite_001_userorderRepository.findUserorderByUserid(userid);
            if (list == null){
                return Result.fail("输入参数有误");
            }else if (list.size() == 0){
                return Result.fail("暂无订单");
            }
            aa = useerorder(list);
        }else if (!orderState.equals("1") && !orderState.equals("2") && !orderState.equals("3")&& !orderState.equals("4")&& !orderState.equals("5")){
            return Result.fail("传入参数有误");
        }else {
        List<Userorder> userorder = rewrite_001_userorderRepository.findUserorderByUseridAndOrderstatus(userid, orderState);
        if (userorder == null) {
            return Result.fail("输入参数有误");
        }else if (userorder.size() == 0){
            return Result.fail("暂无这个状态的订单");
        }
            aa = useerorder(userorder);
        }

        for (int i = 1 + (pageSize * (pageNum)); i <= (pageNum+1) * (pageSize); i++) {
            if (i > aa.size()) {
                return Result.suc("查询成功，这是最后一页了", aaa, aaa.size());
            }
            IntroductionOrderDTO introductionOrderDTO = aa.get(i-1);
            aaa.add(introductionOrderDTO);
        }

        return Result.suc("查询成功",aaa,aaa.size());
    }


    @Override
    public Result OrdersConfirmation(String userid, String ordercode) {
        Userorder userorderByOrdercode = rewrite_001_userorderRepository.findUserorderByOrdercode(ordercode);
        if (userorderByOrdercode == null){
            return Result.fail("传入参数有误");
        } else if (!userorderByOrdercode.getUserid().equals(userid)) {
            return Result.fail("你不是本人不能操作");
        }
        userorderByOrdercode.setOrderstatus("2");
        userorderByOrdercode.setId(userorderByOrdercode.getId());
        rewrite_001_userorderRepository.save(userorderByOrdercode);
        return Result.suc("修改成功");
    }

    @Override
    public Result Orderdetail(String userid, String ordercode) {
        Userorder userorderByOrdercode = rewrite_001_userorderRepository.findUserorderByOrdercode(ordercode);
        if (userorderByOrdercode == null){
            return Result.fail("请输入正确的订单编号");
        } else if (!userorderByOrdercode.getUserid().equals(userid)) {
            return Result.fail("你不是本人不能操作");
        }
        List<Userorder> list = new ArrayList<>();
        list.add(userorderByOrdercode);
        List<IntroductionOrderDTO> useerorder = useerorder(list);
        IntroductionOrderDTO dto = useerorder.get(0);
        Order order = rewrite_orderRepository.findOrderByBigorder(dto.getOrderid());
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder(dto);
        orderDTO.setConsignee(order.getConsignee());
        orderDTO.setMobile(order.getMobile());
        orderDTO.setAddress(order.getAddress());
        return Result.suc("查询成功",orderDTO);
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
            introductionOrderDTO.setOneprice(s.getPrice()+"");
            introductionOrderDTO.setCreatedate(userorder.getCreatedate());
            introductionOrderDTO.setOther(userorder.getOther());

            if (userorder.getExpressCompany() == null || userorder.getExpressCompany().equals("")){
                introductionOrderDTO.setExpress_company("未支付暂时还未发货");
            }else {
                introductionOrderDTO.setExpress_company(userorder.getExpressCompany());
            }

            if (userorder.getExpressNo() == null || userorder.getExpressNo().equals("")){
                introductionOrderDTO.setExpress_no("未支付暂无订单号");
            }else {
                introductionOrderDTO.setExpress_no(userorder.getExpressNo());
            }

            if (userorder.getPayway() == null || userorder.getPayway().equals("")){
                introductionOrderDTO.setPayway("未支付");
            }else if (userorder.getPayway().equals("1")){
                introductionOrderDTO.setPayway("支付宝支付");
            }else if(userorder.getPayway().equals("2")){
                introductionOrderDTO.setPayway("微信支付");
            }else if(userorder.getPayway().equals("3")){
                introductionOrderDTO.setPayway("余额支付");
            }else if(userorder.getPayway().equals("4")){
                introductionOrderDTO.setPayway("积分支付");
            }else if(userorder.getPayway().equals("5")){
                introductionOrderDTO.setPayway("优惠卷支付");
            }
            Order orderByBigorder = rewrite_orderRepository.findOrderByBigorder(userorder.getId() + "");
            if (orderByBigorder != null){
                introductionOrderDTO.setNum(orderByBigorder.getNum());
            }else {
                introductionOrderDTO.setNum("1");
            }
            aa.add(introductionOrderDTO);
        }
        return aa;
    }
}
