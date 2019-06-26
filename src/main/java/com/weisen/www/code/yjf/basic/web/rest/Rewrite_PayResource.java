//package com.weisen.www.code.yjf.basic.web.rest;
//
//import com.weisen.www.code.yjf.basic.service.dto.Rewrite_PayDTO;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/public")
//@Api("000-系统支付操作")
//public class Rewrite_PayResource {
//
//    @PostMapping("/yuePay")
//    @ApiOperation("余额支付")
//    public ResponseEntity<?> yuePay (@RequestBody Rewrite_PayDTO payDTO) {
//        String orderNumber = payDTO.getOrderNumber();
//        if (orderNumber.startsWith("F")) {//点餐支付
//
//        } else if (orderNumber.startsWith("S")) {//商城支付
//            
//        }
//        return null;
//    }
//}
