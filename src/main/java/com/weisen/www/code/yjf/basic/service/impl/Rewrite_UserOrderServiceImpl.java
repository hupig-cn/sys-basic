package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserOrderService;
import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_OrderCoDto;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AnOrder;
import com.weisen.www.code.yjf.basic.service.mapper.UserorderMapper;
import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class Rewrite_UserOrderServiceImpl implements Rewrite_UserOrderService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_UserOrderServiceImpl.class);

    private final Rewrite_UserorderRepository rewrite_UserorderRepository;

    private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

    private final UserorderMapper userorderMapper;

    private final Rewrite_ReceiptpayService rewrite_ReceiptpayService;

    public Rewrite_UserOrderServiceImpl(Rewrite_UserorderRepository rewrite_UserorderRepository,
                                        UserorderMapper userorderMapper,
                                        Rewrite_ReceiptpayService rewrite_ReceiptpayService
                                        ,Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository) {
        this.rewrite_UserorderRepository = rewrite_UserorderRepository;
        this.userorderMapper = userorderMapper;
        this.rewrite_ReceiptpayService = rewrite_ReceiptpayService;
        this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
    }

    // 获取用户当日的订单量（区分端）
    @Override
    public int getTodayOrderNum(Long userId) {
        List<Userorder> list = rewrite_UserorderRepository.findAllByPayee(userId.toString());
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startTime =  today + " 00:00:00";
        String endTime = today + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int count = 0;
        for (Userorder x :list){
            try {
                if(sdf.parse(x.getCreatedate()).compareTo(sdf.parse(startTime)) >= 0 &&
                    sdf.parse(x.getCreatedate()).compareTo(sdf.parse(endTime)) <= 0){
                    count++;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    // 获取待付款订单
    @Override
    public List<UserorderDTO> getUnpaidOrder(Long userId) {
        List<Userorder> list = rewrite_UserorderRepository.findAllByPayeeAndOrderstatus(userId.toString(), OrderConstant.UN_PAID);
        return userorderMapper.toDto(list);
    }

    // 获取已支付订单
    @Override
    public List<UserorderDTO> getPaidOrder(Long userId) {
        List<Userorder> list = rewrite_UserorderRepository.findAllByPayeeAndOrderstatus(userId.toString(), OrderConstant.PAID);
        return userorderMapper.toDto(list);
    }

    // 获取退款订单
    @Override
    public List<UserorderDTO> getRefundOrder(Long userId) {
        List<Userorder> list = rewrite_UserorderRepository.findAllByPayeeAndOrderstatus(userId.toString(), OrderConstant.REFUNDED);
        return userorderMapper.toDto(list);
    }

    // 获取当日订单
    @Override
    public List<UserorderDTO> getTodayOrder(Long userId) {
        List<Userorder> list = rewrite_UserorderRepository.findAllByPayee(userId.toString());
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String startTime =  today + " 00:00:00";  // 当天开始时间
        String endTime = today + " 23:59:59";     // 当天结束时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Userorder> dto = new ArrayList<>();
        int count = 0;
        for (Userorder x :list){
            try {
                if(sdf.parse(x.getCreatedate()).compareTo(sdf.parse(startTime)) >= 0 &&
                    sdf.parse(x.getCreatedate()).compareTo(sdf.parse(endTime)) <= 0){
                    dto.add(x);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return userorderMapper.toDto(dto);
    }

    // 获取用户全部订单
    @Override
    public List<UserorderDTO> getAllOrder(Long userId) {
        List<Userorder> userorder = rewrite_UserorderRepository.findAllByUserid(userId.toString());
        return userorderMapper.toDto(userorder);
    }

    //用户下单
    @Override
    public Result placeAnOrder(Rewrite_AnOrder rewrite_AnOrder) {
        if(rewrite_AnOrder.getProductid().equals("1")){
            Userlinkuser userlinkuser = rewrite_UserlinkuserRepository.findByUserid(rewrite_AnOrder.getUserId());
            if(userlinkuser.isPartner() == true){
                return Result.fail("您已经是圆帅了");
            }
        }

        if(null == rewrite_AnOrder.getUserId()){

        }else if(null == rewrite_AnOrder.getPrice()){

        } ////
        Userorder userorder = new Userorder();
        userorder.setOrdercode(OrderConstant.getOrderCode(rewrite_AnOrder.getUserId()));
        userorder.setOrderstatus(OrderConstant.UN_PAID);  // 待支付
        userorder.setSum(rewrite_AnOrder.getPrice());
        userorder.setUserid(rewrite_AnOrder.getUserId());
        userorder.setPayresult("");  // ??
        userorder.setCreator(null);
        userorder.setOther(rewrite_AnOrder.getOther());
        userorder.setCreatedate(TimeUtil.getDate());
        userorder.setOther(rewrite_AnOrder.getProductid());
        rewrite_UserorderRepository.save(userorder);

        return Result.suc("成功", userorder.getId());
    }

    // 收益+当日订单+各种订单状态
    @Override
    public Result somethingData(Long userId) {
        int unpaid = getUnpaidOrder(userId).size();
        int day_order = getTodayOrderNum(userId);
        int paid = getPaidOrder(userId).size();
        int refund = getRefundOrder(userId).size();
        Rewrite_PriceDTO rewrite_PriceDTO = rewrite_ReceiptpayService.selectTodayIncome(userId);
        Rewrite_OrderCoDto rewrite_OrderCoDto = new Rewrite_OrderCoDto(rewrite_PriceDTO.getPrice(),day_order,unpaid,paid,refund);
        return Result.suc("成功",rewrite_OrderCoDto);
    }

    public static void main(String[] args) {
    	String str = "2019-04-01";
    	System.out.println(str.subSequence(0, 7));
	}

}
