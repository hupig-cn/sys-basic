package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_IncomeDetailsRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IncomeDetailsService;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_IncomeDetailsServiceImpl implements Rewrite_IncomeDetailsService {

    private final Rewrite_IncomeDetailsRepository incomeDetailsRepository;
    
    private final Rewrite_ReceiptpayRepository receiptpayRepository;
    
    public Rewrite_IncomeDetailsServiceImpl(Rewrite_IncomeDetailsRepository incomeDetailsRepository,
    		Rewrite_ReceiptpayRepository receiptpayRepository) {
        this.incomeDetailsRepository = incomeDetailsRepository;
        this.receiptpayRepository = receiptpayRepository;
    }
    //获取各推荐人总数
	@Override
	public Result getRecommendTotal(String recommendId) {
		Long findByUserIdCount = incomeDetailsRepository.findByRecommendIdCount(recommendId);
		return Result.suc("访问成功", findByUserIdCount);
	}
	
    //获取推荐人列表
	@Override
	public Result getRecommendList(String userid) {
		List<Userlinkuser> findByUserId = incomeDetailsRepository.findByUserid(userid);
		for (Userlinkuser userlinkuser : findByUserId) {
			Receiptpay findReceiptpayByUid = receiptpayRepository.findReceiptpayByUid(userlinkuser.getRecommendid());
			findReceiptpayByUid.getDealtype();
			
			
		}
		return null;
	}

}
