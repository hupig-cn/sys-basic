package com.weisen.www.code.yjf.basic.web.rest;

import java.util.List;
import java.util.Optional;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/weisen/userorder")
@Api(tags = "000-用户收支明细")
public class Rewrite_ReceiptpayResource {
	
	private Rewrite_ReceiptpayService rewrite_ReceiptpayService;

    public Rewrite_ReceiptpayResource(Rewrite_ReceiptpayService rewrite_ReceiptpayService) {
        this.rewrite_ReceiptpayService = rewrite_ReceiptpayService;
    }
    
    /**
     * 查询商家今日收入 (商家端)
     * @param userId
     * @return
     */
    @GetMapping("/selectTodayIncome/{userId}")
    @ApiOperation(value = "查询商家今日收入 (商家端)")
    @Timed
    public ResponseEntity<Result> selectTodayIncome(@PathVariable Long userId) {
    	Rewrite_PriceDTO rewrite_PriceDTO = rewrite_ReceiptpayService.selectTodayIncome(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",rewrite_PriceDTO)));
    }
    
    /**
     * 获取昨日收款 （商家）
     * @param userId
     * @return
     */
    @GetMapping("/selectYesterday/{userId}")
    @ApiOperation(value = "获取昨日收款 （商家）")
    @Timed
    public ResponseEntity<Result> selectYesterday(@PathVariable Long userId) {
    	Rewrite_PriceDTO rewrite_PriceDTO = rewrite_ReceiptpayService.selectYesterday(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",rewrite_PriceDTO)));
    }

    /**
     * 获取提现记录
     * @param userId
     * @return
     */
    @GetMapping("/selcetMoneyRecord/{userId}")
    @ApiOperation(value = "获取提现记录")
    @Timed
    public ResponseEntity<Result> selcetMoneyRecord(@PathVariable Long userId) {
        List<ReceiptpayDTO> receiptpay = rewrite_ReceiptpayService.selcetMoneyRecord(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",receiptpay)));
    }
	
}
