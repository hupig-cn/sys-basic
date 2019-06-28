package com.weisen.www.code.yjf.basic.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_PriceDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ReceiptpayMapper;
import com.weisen.www.code.yjf.basic.service.util.ProfitConstant;

@Service
@Transactional
public class Rewrite_ReceiptpayServiceImpl implements Rewrite_ReceiptpayService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_ReceiptpayServiceImpl.class);

	private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

	private final ReceiptpayMapper receiptpayMapper;

	public Rewrite_ReceiptpayServiceImpl(Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository,
			ReceiptpayMapper receiptpayMapper) {
		this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
		this.receiptpayMapper = receiptpayMapper;
	}

	// 查询商家今日收入 (商家端)
	@Override
	public Rewrite_PriceDTO selectTodayIncome(Long userId) {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String startTime = today + " 00:00:00";
		String endTime = today + " 23:59:59";
		List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
				startTime, endTime, ProfitConstant.SHOP_PROFIT);
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
				startTime, endTime, ProfitConstant.SHOP_PROFIT);
		BigDecimal price = new BigDecimal("0");
		for (Receiptpay list : receiptpay) {
			price = price.add(list.getAmount());
		}
		Rewrite_PriceDTO rewrite_PriceDTO = new Rewrite_PriceDTO(price.toString());
		return rewrite_PriceDTO;
	}
	
	// 获取提现记录
	@Override
	public List<ReceiptpayDTO> selcetMoneyRecord(Long userId) {
		List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndDealtype(userId.toString(),
				ProfitConstant.FIVE);
		return receiptpayMapper.toDto(receiptpay);
	}

}
