package com.weisen.www.code.yjf.basic.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_MercProfitDto;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_ProfitDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/receiptpay")
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

    /**
     * 查询商家各项详细收益
     * @param userId
     * @return
     */
    @GetMapping("/getProfitInfo/{userId}")
    @ApiOperation(value = "查询商家各项详细收益")
    @Timed
    public ResponseEntity<Result> getProfitInfo(@PathVariable Long userId) {
        Rewrite_MercProfitDto rewrite_MercProfitDto = rewrite_ReceiptpayService.getProfitInfo(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(Result.suc("成功",rewrite_MercProfitDto)));
    }

    /**
     * 查询用户的各项收益
     * @param userId
     * @return
     */
    @GetMapping("/getUserPrifitInfo/{userId}")
    @ApiOperation(value = "查询用户的各项收益")
    @Timed
    public ResponseEntity<Result> getUserPrifitInfo(@PathVariable Long userId) {
        Result result = rewrite_ReceiptpayService.getUserPrifitInfo(userId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    @GetMapping("/getUserProfit/{userid}")
    @ApiOperation(value = "查询用户的各项收益")
    @Timed
    public ResponseEntity<Rewrite_ProfitDTO> getUserProfit(@PathVariable String userid) {
    	Rewrite_ProfitDTO rewrite_ProfitDTO = rewrite_ReceiptpayService.getUserProfit(userid);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rewrite_ProfitDTO));
    }

    @GetMapping("/findMerchantProfitInfo/{userid}&{startPage}&{pageSize}")
    @ApiOperation(value = "查询用户商家端收益列表倒叙(意哥需求)")
    @Timed
    public ResponseEntity<Result> findMerchantProfitInfo(@PathVariable String userid, @PathVariable int startPage, @PathVariable int pageSize) {
        Result result = rewrite_ReceiptpayService.findMerchantProfitInfo(userid,startPage,pageSize);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    @GetMapping("admin/findByUserAccountOrSomething/{userAccount}&{startPage}&{pageSize}")
    @ApiOperation(value = "根据用户账号查询详细收益（分页，暂时没有多条件）admin")
    @Timed
    public ResponseEntity<Result> findByUserAccountOrSomething(@PathVariable String userAccount, @PathVariable int startPage, @PathVariable int pageSize) {
        Result result = rewrite_ReceiptpayService.findByUserAccountOrSomething(userAccount,startPage,pageSize);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
