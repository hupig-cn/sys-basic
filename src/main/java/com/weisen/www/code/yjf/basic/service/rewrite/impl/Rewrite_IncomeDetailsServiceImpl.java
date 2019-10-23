package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_IncomeDetailsRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IncomeDetailsService;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_IncomeDetailsServiceImpl implements Rewrite_IncomeDetailsService {

    private final Rewrite_IncomeDetailsRepository incomeDetailsRepository;
    
    public Rewrite_IncomeDetailsServiceImpl(Rewrite_IncomeDetailsRepository incomeDetailsRepository ) {
        this.incomeDetailsRepository = incomeDetailsRepository;
    }
    //获取各推荐人总数
	@Override
	public Result getRecommendTotal(String userId) {
		Long findByUserIdCount = incomeDetailsRepository.findByUserIdCount(userId);
		return Result.suc("访问成功", findByUserIdCount);
	}
	
    //获取推荐人列表
	@Override
	public Result getRecommendList(String userid) {
		Userlinkuser findByUserId = incomeDetailsRepository.findByUserid(userid);
		
		findByUserId.getRecommendid();
		return null;
	}

}
