package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_MercProfitDto;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_UserPriceDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ReceiptpayMapper;
import com.weisen.www.code.yjf.basic.service.util.ProfitConstant;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.service.util.TimeUtil;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class Rewrite_ReceiptpayServiceImpl implements Rewrite_ReceiptpayService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_ReceiptpayServiceImpl.class);

	private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

	private final ReceiptpayMapper receiptpayMapper;

	private final Rewrite_UserassetsService rewrite_UserassetsService;

	public Rewrite_ReceiptpayServiceImpl(Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository,
			ReceiptpayMapper receiptpayMapper,Rewrite_UserassetsService rewrite_UserassetsService) {
		this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
		this.receiptpayMapper = receiptpayMapper;
		this.rewrite_UserassetsService = rewrite_UserassetsService;
	}

	// 查询商家今日收入 (商家端)
	@Override
	public Rewrite_PriceDTO selectTodayIncome(Long userId) {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String startTime = today + " 00:00:00";
		String endTime = today + " 23:59:59";
		List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
				startTime, endTime, ReceiptpayConstant.BALANCE_INCOME);
		BigDecimal price = new BigDecimal("0");
		for (Receiptpay list : receiptpay) {
			price = price.add(list.getAmount());
		}
		Rewrite_PriceDTO rewrite_PriceDTO = new Rewrite_PriceDTO(price.toString());
		return rewrite_PriceDTO;
	}

	// 获取昨日收款 （商家）
	@Override
	public Rewrite_PriceDTO selectYesterday(Long userId) {
		String today = new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
		String startTime = today + " 00:00:00";
		String endTime = today + " 23:59:59";
		List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
				startTime, endTime, ReceiptpayConstant.BALANCE_INCOME);
		BigDecimal price = new BigDecimal("0");
		for (Receiptpay list : receiptpay) {
			price = price.add(list.getAmount());
		}
		Rewrite_PriceDTO rewrite_PriceDTO = new Rewrite_PriceDTO(price.toString());
		return rewrite_PriceDTO;
	}

    /**
     * 创建收支明细
     * @param userId
     * @param type
     * @param sourcer
     * @param sourcerId
     * @param amout
     * @return
     */
    public Result createReceiptpay(String userId, String type, String sourcer, String sourcerId, String amout) {
        return null;
    }

    // 查询商家各项详细收益
    @Override
    public Rewrite_MercProfitDto getProfitInfo(Long userId) {
        Rewrite_UserPriceDTO rewrite_PriceDTO = rewrite_UserassetsService.findUserBalance(userId);
        if(rewrite_PriceDTO == null){
            return null;
        }
        Rewrite_PriceDTO pricere = selectYesterday(userId);

        String today = new SimpleDateFormat("yyyy-MM").format(new Date());
        String startTime =  today + "-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = TimeUtil.getPreMonth(sdf.format(new Date())) + "-01 00:00:00";
        // 当月销售
        List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            startTime, endTime, ReceiptpayConstant.BALANCE_INCOME);
        BigDecimal price = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            price = price.add(list.getAmount());
        }
        String lastTime =TimeUtil.getLastMonth(sdf.format(new Date())) + "-01 00:00:00";
        //上个月
        List<Receiptpay> lastreceiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            lastTime, startTime, ReceiptpayConstant.BALANCE_INCOME);
        BigDecimal lastprice = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            lastprice = lastprice.add(list.getAmount());
        }
        // 总销售额
        List<Receiptpay> allreceiptpay = rewrite_ReceiptpayRepository.findAllByUseridAndDealtype(userId.toString(),ReceiptpayConstant.BALANCE_INCOME);
        BigDecimal totalprice = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            totalprice = totalprice.add(list.getAmount());
        }

        Rewrite_MercProfitDto rewrite_MercProfitDto = new Rewrite_MercProfitDto(rewrite_PriceDTO.getBalance(),
            new BigDecimal(pricere.getPrice()),price,lastprice,totalprice);
        return rewrite_MercProfitDto;
    }

    // 查询用户的各项收益
    @Override
    public Result getUserPrifitInfo(Long userId) {
        Rewrite_MercProfitDto rewrite_MercProfitDto = getProfitInfo(userId);

        String today = new SimpleDateFormat("yyyy-MM").format(new Date());
        String startTime =  today + "-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = TimeUtil.getPreMonth(sdf.format(new Date())) + "-01 00:00:00";
        // 当月分销
        List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            startTime, endTime, ReceiptpayConstant.BALANCE_INCOME_DIR);
        BigDecimal price = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            price = price.add(list.getAmount());
        }
        String lastTime =TimeUtil.getLastMonth(sdf.format(new Date())) + "-01 00:00:00";
        //上个月
        List<Receiptpay> lastreceiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            lastTime, startTime, ReceiptpayConstant.BALANCE_INCOME_DIR);
        BigDecimal lastprice = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            lastprice = lastprice.add(list.getAmount());
        }
        // 总销分销
        List<Receiptpay> allreceiptpay = rewrite_ReceiptpayRepository.findAllByUseridAndDealtype(userId.toString(),ReceiptpayConstant.BALANCE_INCOME_DIR);
        BigDecimal totalprice = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            totalprice = totalprice.add(list.getAmount());
        }
        // 昨日
        String retoday = new SimpleDateFormat("yyyy-MM-dd")
            .format(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
        String restartTime = retoday + " 00:00:00";
        String reendTime = retoday + " 23:59:59";
        List<Receiptpay> lareceiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            restartTime, reendTime, ReceiptpayConstant.BALANCE_INCOME_DIR);
        BigDecimal laprice = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            laprice = laprice.add(list.getAmount());
        }

        rewrite_MercProfitDto.setYestoday_income(rewrite_MercProfitDto.getYestoday_income().add(laprice));
        rewrite_MercProfitDto.setThis_month(rewrite_MercProfitDto.getThis_month().add(price));
        rewrite_MercProfitDto.setLast_month(rewrite_MercProfitDto.getLast_month().add(lastprice));
        rewrite_MercProfitDto.setAmount(rewrite_MercProfitDto.getAmount().add(totalprice));
        return Result.suc("成功",rewrite_MercProfitDto);
    }

    // 获取提现记录
	@Override
	public List<ReceiptpayDTO> selcetMoneyRecord(Long userId) {
		List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndDealtype(userId.toString(),
				ProfitConstant.FIVE);
		return receiptpayMapper.toDto(receiptpay);
	}

}
