package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.math.BigDecimal;
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
		//封装返回DTO
		Rewrite_GetIncomeListDTO getIncomeListDTO = new Rewrite_GetIncomeListDTO();
		//获取当前用户id
		String recommendId = getIncomeAfferentDTO.getRecommendId();
		//获取推荐人总数
		Long recommendIdCount = incomeDetailsRepository.findByRecommendIdCount(recommendId);
		//前端返回的查找时间
		String firstTime = getIncomeAfferentDTO.getFirstTime();
		String lastTime = getIncomeAfferentDTO.getLastTime();
		Integer pageNum = getIncomeAfferentDTO.getPageNum();
		Integer pageSize = getIncomeAfferentDTO.getPageSize();
		List<Userlinkuser> recommends= null;
		//如果有时间值，根据时间值查找推荐人数
		if (firstTime!=null && lastTime !=null || !(firstTime.equals("") && lastTime.equals(""))) {
			//如果有时间值，根据时间值来查找
			recommends = incomeDetailsRepository.findByRecommendIdAndTimeAndPage(recommendId,firstTime,lastTime,pageNum*pageSize,pageSize);
			//如果有数据，进行遍历，并到收支明细表和login库中获取数据
			if (recommends != null || !recommendId.equals("")) {

				//遍历数据
				for (Userlinkuser userlinkuser : recommends) {
					//获取被推荐人用户id
					String userid = userlinkuser.getUserid();
					//获取创建时间
					String createdate = userlinkuser.getCreatedate();
					//获取收支明细表中对应
					List<Receiptpay> receiptpays = receiptpayRepository.getReceiptpayByUseridAndSourcerAndTime(recommendId,userid,firstTime,lastTime);
					//如果找得到数据
					BigDecimal bonus = new BigDecimal(0);
					if (receiptpays != null) {
						for (Receiptpay receiptpay : receiptpays) {
							BigDecimal bonu = receiptpay.getBonus();
							bonus.add(bonu);
						}
					}
					//获取被推荐人longin库资料
					User jhiUser = userRepository.findJhiUserById(Long.parseLong(userid));
					String firstName = jhiUser.getFirstName();
					String imageUrl = jhiUser.getImageUrl();
					
					getIncomeListDTO.setImageUrl(imageUrl);
					getIncomeListDTO.setCreatedate(createdate);
					getIncomeListDTO.setRecommendIdCount(recommendIdCount);
					getIncomeListDTO.setFirstName(firstName);
					getIncomeListDTO.setBonus(bonus);
					
				}
			} else {
				
				System.out.println("没有数据！");
				
			}
		} else {
			//如果没有时间值，则查找的时候进行分页查找
			List<Userlinkuser> recommendData = incomeDetailsRepository.findByRecommendIdAndPage(recommendId,pageNum*pageSize,pageSize);
			for (Userlinkuser userlinkuser : recommendData) {
				String userid = userlinkuser.getUserid();
				
				
			}

		}
		


		for (Userlinkuser recommend : recommends) {
			String userid = recommend.getUserid();
			

			

		}
		return Result.suc("访问成功！",getIncomeListDTO);
	}

}
