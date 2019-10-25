//package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_004_订单;
//
//import com.weisen.www.code.yjf.basic.service.Rewrite_000_UserorderService;
//import io.swagger.annotations.Api;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author: 阮铭辉
// * @Date: 2019/10/25 15:52
// */
//@RestController
//@RequestMapping("/api/userOrder")
//@Api(tags = "02_余额")
//public class Rewrite_UserOrderResource {
//
//    private final Logger log = LoggerFactory.getLogger(Rewrite_UserOrderResource.class);
//
//    private final Rewrite_000_UserorderService rewrite_userorderService;
//
//    public Rewrite_UserOrderResource(Rewrite_000_UserorderService rewrite_userorderService) {
//        this.rewrite_userorderService = rewrite_userorderService;
//    }
//
//
////    @PostMapping("/myUserOrder")
////    @ApiOperation("我的订单")
////    public ResponseEntity<?> myUserOrder(@RequestParam(required = false) String startTime,
////                                             @RequestParam(required = false) String endTime,
////                                             @RequestParam(required = false) String userid) throws ParseException {
////        Result result = rewrite_userorderService.myUserOrder(userid,startTime,endTime);
////        log.debug("访问地址: {},传入值: {},返回值: {}","/api/balance/myUserOrder", "传入值:"+userid, result);
////        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
////
////    }
//}
