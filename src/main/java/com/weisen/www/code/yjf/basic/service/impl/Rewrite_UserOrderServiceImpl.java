//package com.weisen.www.code.yjf.basic.service.impl;
//
//import com.weisen.www.code.yjf.basic.domain.Userorder;
//import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
//import com.weisen.www.code.yjf.basic.service.Rewrite_UserOrderService;
//import com.weisen.www.code.yjf.basic.service.dto.UserorderDTO;
//import com.weisen.www.code.yjf.basic.service.mapper.UserorderMapper;
//import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//@Transactional
//public class Rewrite_UserOrderServiceImpl implements Rewrite_UserOrderService {
//
//    private final Logger log = LoggerFactory.getLogger(Rewrite_UserOrderServiceImpl.class);
//
//    private final Rewrite_UserorderRepository rewrite_UserorderRepository;
//
//    private final UserorderMapper userorderMapper;
//
//    public Rewrite_UserOrderServiceImpl(Rewrite_UserorderRepository rewrite_UserorderRepository, UserorderMapper userorderMapper) {
//        this.rewrite_UserorderRepository = rewrite_UserorderRepository;
//        this.userorderMapper = userorderMapper;
//    }
//
//    // 获取用户当日的订单量（区分端）
//    @Override
//    public int getTodayOrderNum(Long userId) {
//        List<Userorder> list = rewrite_UserorderRepository.findAllByUserid(userId.toString());
//        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String startTime =  today + " 00:00:00";
//        String endTime = today + " 23:59:59";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        int count = 0;
//        for (Userorder x :list){
//            try {
//                if(sdf.parse(x.getCreatedate()).compareTo(sdf.parse(startTime)) >= 0 &&
//                    sdf.parse(x.getCreatedate()).compareTo(sdf.parse(endTime)) <= 0){
//                    count++;
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return count;
//    }
//
//    // 获取待付款订单
//    @Override
//    public List<UserorderDTO> getUnpaidOrder(Long userId) {
//        List<Userorder> list = rewrite_UserorderRepository.findAllByUseridAndstateAndOrderstatus(userId.toString(), OrderConstant.UN_PAID);
//        return userorderMapper.toDto(list);
//    }
//
//    // 获取已支付订单
//    @Override
//    public List<UserorderDTO> getPaidOrder(Long userId) {
//        List<Userorder> list = rewrite_UserorderRepository.findAllByUseridAndstateAndOrderstatus(userId.toString(), OrderConstant.PAID);
//        return userorderMapper.toDto(list);
//    }
//
//    // 获取退款订单
//    @Override
//    public List<UserorderDTO> getRefundOrder(Long userId) {
//        List<Userorder> list = rewrite_UserorderRepository.findAllByUseridAndstateAndOrderstatus(userId.toString(), OrderConstant.REFUNDED);
//        return userorderMapper.toDto(list);
//    }
//
//    // 获取当日订单
//    @Override
//    public List<UserorderDTO> getTodayOrder(Long userId) {
//        List<Userorder> list = rewrite_UserorderRepository.findAllByUserid(userId.toString());
//        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        String startTime =  today + " 00:00:00";  // 当天开始时间
//        String endTime = today + " 23:59:59";     // 当天结束时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        List<Userorder> dto = new ArrayList<>();
//        int count = 0;
//        for (Userorder x :list){
//            try {
//                if(sdf.parse(x.getCreatedate()).compareTo(sdf.parse(startTime)) >= 0 &&
//                    sdf.parse(x.getCreatedate()).compareTo(sdf.parse(endTime)) <= 0){
//                    dto.add(x);
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return userorderMapper.toDto(dto);
//    }
//
//    // 获取用户全部订单
//    @Override
//    public List<UserorderDTO> getAllOrder(Long userId) {
//
//
//        return null;
//    }
//}
