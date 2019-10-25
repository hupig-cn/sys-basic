package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Merchant;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_IncomeDetailsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IncomeDetailsService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetIncomeAfferentDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetIncomeListDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ProfitListDTO;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_IncomeDetailsServiceImpl implements Rewrite_IncomeDetailsService {

	private final Rewrite_IncomeDetailsRepository incomeDetailsRepository;

	private final Rewrite_UserRepository userRepository;

	private final Rewrite_ReceiptpayRepository receiptpayRepository;

	private final Rewrite_MerchantRepository merchantRepository;

	private final Rewrite_UserlinkuserRepository userLinkUserRepository;

	public Rewrite_IncomeDetailsServiceImpl(Rewrite_IncomeDetailsRepository incomeDetailsRepository,
			Rewrite_ReceiptpayRepository receiptpayRepository,
			Rewrite_UserRepository userRepository,
			Rewrite_MerchantRepository merchantRepository,
			Rewrite_UserlinkuserRepository userLinkUserRepository) {
		this.incomeDetailsRepository = incomeDetailsRepository;
		this.receiptpayRepository = receiptpayRepository;
		this.userRepository = userRepository;
		this.merchantRepository = merchantRepository;
		this.userLinkUserRepository = userLinkUserRepository;
	}
	//获取各推荐人总数
	@Override
	public Result getRecommendTotal(String recommendId) {
		//通过id找到
		Long findByUserIdCount = incomeDetailsRepository.findByRecommendIdCount(recommendId);
		//		BigDecimal bigDecimal = new BigDecimal(0).setScale(4, BigDecimal.ROUND_DOWN);
		//		List<BigDecimal> amounts = receiptpayRepository.findReceiptpayByUserid(recommendId);
		//		if (amounts!=null) {
		//			
		//			for (BigDecimal amount : amounts) {
		//			bigDecimal = bigDecimal.add(amount);
		//			}
		//		}
		return Result.suc("访问成功", findByUserIdCount);
	}

	//获取推荐人列表
	@Override
	public Result getRecommendList(Rewrite_GetIncomeAfferentDTO getIncomeAfferentDTO) {
		//封装返回DTO
		List<Rewrite_GetIncomeListDTO> incomeListDTO = new ArrayList<Rewrite_GetIncomeListDTO>();

		//获取当前用户id
		String recommendId = getIncomeAfferentDTO.getRecommendId();
		//前端返回的查找时间
		Long first = getIncomeAfferentDTO.getFirstTime();
		Long last = getIncomeAfferentDTO.getLastTime();

		Integer pageNum = getIncomeAfferentDTO.getPageNum();
		Integer pageSize = getIncomeAfferentDTO.getPageSize();
		List<Userlinkuser> recommends= null;
		//如果有时间值，根据时间值查找推荐人数
		if ((first!=null && last !=null) && (first!=0 && last != 0)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String firstTime = sdf.format(new Date(first));
			System.out.println("开始时间:"+firstTime);
			String lastTime = sdf.format(new Date(last));
			System.out.println("结束时间:"+lastTime);

			//如果有时间值，根据时间值来查找
			recommends = incomeDetailsRepository.findByRecommendIdAndTimeAndPage(recommendId,firstTime,lastTime,pageNum*pageSize,pageSize);
			//如果有数据，进行遍历，并到收支明细表和login库中获取数据
			if (recommends != null || !recommendId.equals("")) {

				//遍历数据
				for (Userlinkuser userlinkuser : recommends) {
					Rewrite_GetIncomeListDTO getIncomeListDTO = new Rewrite_GetIncomeListDTO();
					//获取被推荐人用户id
					String userid = userlinkuser.getUserid();
					//获取创建时间
					String createdate = userlinkuser.getCreatedate();
					//获取收支明细表中对应
					List<Receiptpay> receiptpays = receiptpayRepository.getReceiptpayByUseridAndSourcerAndTime(recommendId,userid,firstTime,lastTime);
					//如果找得到数据
					BigDecimal sumAmount = new BigDecimal(0).setScale(4, BigDecimal.ROUND_DOWN);
					//如果收支明细表中有数据，查找获利金额
					if (receiptpays != null) {
						for (Receiptpay receiptpay : receiptpays) {
							BigDecimal amount = receiptpay.getAmount().setScale(4, BigDecimal.ROUND_DOWN);
							if(amount!=null) {
								sumAmount=sumAmount.add(amount);
							}
						}
					}
					//获取被推荐人longin库资料
					User jhiUser = userRepository.findJhiUserById(Long.parseLong(userid));
					String firstName = jhiUser.getFirstName();
					String imageUrl = jhiUser.getImageUrl();
					//是否是商家
					Merchant merchant = merchantRepository.findByUserid(userid);
					//是否是事业合伙人
					Userlinkuser partner = userLinkUserRepository.findByUserid(userid);
					//是商家不是事业合伙人
					if (merchant!=null && !(partner.getPartner())) {
						getIncomeListDTO.setMerchant("商家");
						getIncomeListDTO.setPartner("");
						getIncomeListDTO.setVip("");
					}else if (partner.getPartner() && merchant==null) {
						//是事业合伙人不是商家
						getIncomeListDTO.setPartner("事业合伙人");
						getIncomeListDTO.setVip("");
						getIncomeListDTO.setMerchant("");
					}else if (partner.getPartner() && merchant!=null) {
						//既是事业合伙人，也是商家
						getIncomeListDTO.setPartner("事业合伙人");
						getIncomeListDTO.setVip("");
						getIncomeListDTO.setMerchant("商家");
					}else if (merchant==null && !(partner.getPartner())) {
						//既不是事业合伙人，也不是商家     会员
						getIncomeListDTO.setPartner("");
						getIncomeListDTO.setVip("会员");
						getIncomeListDTO.setMerchant("");
					}


					//将数据返回
					getIncomeListDTO.setImageUrl(imageUrl);
					getIncomeListDTO.setCreatedate(createdate);
					getIncomeListDTO.setFirstName(firstName);
					getIncomeListDTO.setAmount(sumAmount);
					incomeListDTO.add(getIncomeListDTO);
				}

			} else {
				return Result.suc("暂时没有数据");
			}
		} else {
			//如果没有时间值，则查找的时候进行分页查找
			List<Userlinkuser> recommendData = incomeDetailsRepository.findByRecommendIdAndPage(recommendId,pageNum*pageSize,pageSize);
			for (Userlinkuser userlinkuser : recommendData) {
				Rewrite_GetIncomeListDTO getIncomeListDTO = new Rewrite_GetIncomeListDTO();
				String userid = userlinkuser.getUserid();
				//获取创建时间
				String createdate = userlinkuser.getCreatedate();
				//获取收支明细表中对应
				List<Receiptpay> receiptpays = receiptpayRepository.findByUseridAndSourcer(recommendId,userid);
				//如果找得到数据
				BigDecimal sumAmount = new BigDecimal(0).setScale(4, BigDecimal.ROUND_DOWN);
				if (receiptpays != null) {
					for (Receiptpay receiptpay : receiptpays) {
						BigDecimal amount = receiptpay.getAmount();
						if (amount!=null || !(amount.equals(""))) {
							sumAmount=sumAmount.add(amount);
						}
					}
				}
				//获取被推荐人longin库资料
				User jhiUser = userRepository.findJhiUserById(Long.parseLong(userid));
				String firstName = jhiUser.getFirstName();
				String imageUrl = jhiUser.getImageUrl();
				//是否是商家
				Merchant merchant = merchantRepository.findByUserid(userid);
				//是否是事业合伙人
				Userlinkuser partner = userLinkUserRepository.findByUserid(userid);
				//是商家不是事业合伙人
				if (merchant!=null && !(partner.getPartner())) {
					getIncomeListDTO.setMerchant("商家");
					getIncomeListDTO.setPartner("");
					getIncomeListDTO.setVip("");
				}else if (partner.getPartner() && merchant==null) {
					//是事业合伙人不是商家
					getIncomeListDTO.setPartner("事业合伙人");
					getIncomeListDTO.setVip("");
					getIncomeListDTO.setMerchant("");
				}else if (partner.getPartner() && merchant!=null) {
					//既是事业合伙人，也是商家
					getIncomeListDTO.setPartner("事业合伙人");
					getIncomeListDTO.setVip("");
					getIncomeListDTO.setMerchant("商家");
				}else if (merchant==null && !(partner.getPartner())) {
					//既不是事业合伙人，也不是商家     会员
					getIncomeListDTO.setPartner("");
					getIncomeListDTO.setVip("会员");
					getIncomeListDTO.setMerchant("");
				}

				getIncomeListDTO.setImageUrl(imageUrl);
				getIncomeListDTO.setCreatedate(createdate);
				getIncomeListDTO.setFirstName(firstName);
				getIncomeListDTO.setAmount(sumAmount);
				incomeListDTO.add(getIncomeListDTO);
			}
		}
		return Result.suc("访问成功！",incomeListDTO);
	}

	//获取收益列表
	@Override
	public Result getProfitList(String userId,Long first,Long last) {
		Rewrite_ProfitListDTO profitListDTO = null;
		BigDecimal bigDecimal = new BigDecimal(0).setScale(4, BigDecimal.ROUND_DOWN);
		Long oneDayTime = 86400000L;
		Long lastDayTime = first+oneDayTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		while (lastDayTime<last){

			profitListDTO = new Rewrite_ProfitListDTO();
			String firstTime = sdf.format(new Date(first));
			String lastTime = sdf.format(new Date(lastDayTime));
			List<Receiptpay> receiptpays = receiptpayRepository.findReceiptpayByUseridAndTime(userId,firstTime,lastTime);
			for (Receiptpay receiptpay : receiptpays) {
				BigDecimal amount = receiptpay.getAmount();
				bigDecimal = bigDecimal.add(amount);
				profitListDTO.setAmount(bigDecimal);
			}

			lastDayTime += oneDayTime;
		}
		List<Receiptpay> receiptpays = receiptpayRepository.findReceiptpayByUserid(userId);
		if (receiptpays!=null) {
			for (Receiptpay receiptpay : receiptpays) {
				BigDecimal amount = receiptpay.getAmount();
				bigDecimal = bigDecimal.add(amount);
			}
			profitListDTO.setAllAmount(bigDecimal);
		}
		return Result.suc("访问成功！", profitListDTO);
	}

}
