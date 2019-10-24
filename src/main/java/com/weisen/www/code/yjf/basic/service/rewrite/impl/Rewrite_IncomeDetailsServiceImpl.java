package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_IncomeDetailsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IncomeDetailsService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetIncomeAfferentDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetIncomeListDTO;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_IncomeDetailsServiceImpl implements Rewrite_IncomeDetailsService {

    private final Rewrite_IncomeDetailsRepository incomeDetailsRepository;

    private final Rewrite_UserRepository userRepository;

    private final Rewrite_ReceiptpayRepository receiptpayRepository;

    private final Rewrite_LinkuserRepository linkuserRepository;

    public Rewrite_IncomeDetailsServiceImpl(Rewrite_IncomeDetailsRepository incomeDetailsRepository,
    		Rewrite_ReceiptpayRepository receiptpayRepository,
    		Rewrite_UserRepository userRepository,
    		Rewrite_LinkuserRepository linkuserRepository) {
        this.incomeDetailsRepository = incomeDetailsRepository;
        this.receiptpayRepository = receiptpayRepository;
        this.userRepository = userRepository;
        this.linkuserRepository = linkuserRepository;
    }
    //获取各推荐人总数
	@Override
	public Result getRecommendTotal(String recommendId) {
		//通过id找到
		Long findByUserIdCount = incomeDetailsRepository.findByRecommendIdCount(recommendId);
		return Result.suc("访问成功", findByUserIdCount);
	}

    //获取推荐人列表
	@Override
	public Result getRecommendList(Rewrite_GetIncomeAfferentDTO getIncomeAfferentDTO) {
		//获取当前用户id
		String recommendId = getIncomeAfferentDTO.getRecommendId();
		//获取推荐人总数
		Long recommendIdCount = incomeDetailsRepository.findByRecommendIdCount(recommendId);
		String firstTime = getIncomeAfferentDTO.getFirstTime();
		String lastTime = getIncomeAfferentDTO.getLastTime();
		List<Userlinkuser> recommends= null;
		//如果有时间值，根据时间值查找推荐人数
		if (firstTime!=null && lastTime !=null || !(firstTime.equals("") && lastTime.equals(""))) {
			//如果有时间值，根据时间值来查找
			recommends = incomeDetailsRepository.findByRecommendIdAndTime(recommendId,firstTime,lastTime);

		} else {
			//获取推荐人列表
			recommends = incomeDetailsRepository.findByRecommendid(recommendId);

		}

		receiptpayRepository.getReceiptpayByUserid(recommendId);
		Rewrite_GetIncomeListDTO getIncomeListDTO = new Rewrite_GetIncomeListDTO();
		for (Userlinkuser recommend : recommends) {
			String userid = recommend.getUserid();

			User findJhiUserById = userRepository.findJhiUserById(Long.parseLong(userid));
			String firstName = findJhiUserById.getFirstName();
			String imageUrl = findJhiUserById.getImageUrl();
			System.out.println(firstName);
			System.out.println(imageUrl);

			findJhiUserById.getImageUrl();

			getIncomeListDTO.setFirstName(firstName);
//			getIncomeListDTO.setCreatedate();
			getIncomeListDTO.setRecommendIdCount(recommendIdCount);
		}
		return Result.suc("访问成功！",getIncomeListDTO);
	}

}
