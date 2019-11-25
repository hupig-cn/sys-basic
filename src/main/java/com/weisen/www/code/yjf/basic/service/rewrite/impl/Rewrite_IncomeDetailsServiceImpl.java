package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Files;
import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.domain.Merchant;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.User;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.FilesRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkaccountRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_IncomeDetailsRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IncomeDetailsService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetIncomeAfferentDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetIncomeListDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_NewFindMerchantProfitInfoDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ProfitListDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_RecommondCountDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_UserInformationListDTO;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.service.util.TimeUtil;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_IncomeDetailsServiceImpl implements Rewrite_IncomeDetailsService {

	private final Rewrite_IncomeDetailsRepository incomeDetailsRepository;

	private final Rewrite_UserRepository userRepository;

	private final Rewrite_ReceiptpayRepository receiptpayRepository;

	private final Rewrite_MerchantRepository merchantRepository;

	private final Rewrite_LinkaccountRepository linkaccountRepository;

	private final Rewrite_UserlinkuserRepository userLinkUserRepository;
	
	private final FilesRepository filesRepository;
	
	public Rewrite_IncomeDetailsServiceImpl(Rewrite_IncomeDetailsRepository incomeDetailsRepository,
			Rewrite_ReceiptpayRepository receiptpayRepository, Rewrite_UserRepository userRepository,
			Rewrite_MerchantRepository merchantRepository, Rewrite_LinkaccountRepository linkaccountRepository,
			FilesRepository filesRepository,
			Rewrite_UserlinkuserRepository userLinkUserRepository) {
		this.incomeDetailsRepository = incomeDetailsRepository;
		this.receiptpayRepository = receiptpayRepository;
		this.userRepository = userRepository;
		this.filesRepository = filesRepository;
		this.merchantRepository = merchantRepository;
		this.userLinkUserRepository = userLinkUserRepository;
		this.linkaccountRepository = linkaccountRepository;
	}

	// 获取推荐人收益信息
	@Override
	public Result getRecommendTotal(String recommendId) {

		// 获取被推荐人longin库资料
		User jhiUser = userRepository.findJhiUserById(Long.parseLong(recommendId));
		if (jhiUser == null) {
			return Result.fail("不存在该用户！");
		}
		// 通过id找到推荐人数量
		List<String> recommendIdfindByUserId = incomeDetailsRepository.findByRecommendid(recommendId);
		if (recommendIdfindByUserId == null) {
			return Result.suc("暂无推荐人数据！");
		}
		Rewrite_RecommondCountDTO recommondCountDTO = new Rewrite_RecommondCountDTO();

		Long merchantCount = 0L;
		Long partnerCount = 0L;
		Long allCount = 0L;
		for (String userid : recommendIdfindByUserId) {
			// 是否是商家，如果是，每次加1
			Merchant merchant = merchantRepository.findByUserid(userid);
			if (merchant != null) {
				merchantCount += 1L;
			}
			// 是否是事业合伙人，如果是，每次加1
			Userlinkuser partner = userLinkUserRepository.findByUserid(userid);
			if (partner.getPartner()) {
				partnerCount += 1L;
			}
			allCount += 1;
		}

		// 获取总推荐收益
		BigDecimal bigDecimal = new BigDecimal(0).setScale(3, BigDecimal.ROUND_DOWN);
		List<BigDecimal> amounts = receiptpayRepository.findReceiptpayByUserid(recommendId);
		// 如果有数据，保存
		if (amounts != null) {
			for (BigDecimal amount : amounts) {
				bigDecimal = bigDecimal.add(amount);
			}
		}
		recommondCountDTO.setPartnerCount(partnerCount);
		recommondCountDTO.setMerchantCount(merchantCount);
		recommondCountDTO.setAllCount(allCount);
		recommondCountDTO.setAmount(bigDecimal);

		return Result.suc("访问成功", recommondCountDTO);
	}

	// 获取推荐流水明细
	@Override
	public Result getRecommendList(Rewrite_GetIncomeAfferentDTO getIncomeAfferentDTO) {

		// 封装返回DTO
		List<Rewrite_GetIncomeListDTO> incomeListDTO = new ArrayList<Rewrite_GetIncomeListDTO>();

		// 获取当前用户id
		String recommendId = getIncomeAfferentDTO.getRecommendId();

		// 获取被推荐人longin库资料
		User jhiUser = userRepository.findJhiUserById(Long.parseLong(recommendId));
		if (jhiUser == null) {
			return Result.fail("不存在该用户！");
		}

		// 前端返回的查找时间
		Long first = getIncomeAfferentDTO.getFirstTime();
		Long last = getIncomeAfferentDTO.getLastTime();
		
		//分页页数和条数
		Integer pageNum = getIncomeAfferentDTO.getPageNum();
		Integer pageSize = getIncomeAfferentDTO.getPageSize();
		// List<Userlinkuser> recommends= null;
		// 如果有时间值，根据时间值查找推荐人数
		if ((first != null && last != null) && (first != 0 && last != 0)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
			// 如果传过来的时间单位不是毫秒，时间要乘以1000
			if (first < 111111111111L || last < 111111111111L) {
				first = first * 1000L;
				last = last * 1000L;
			}
			String firstTime = sdf.format(new Date(first));
			String lastTime = sdf.format(new Date(last));
			
			//查找流水表，找到用户流水类型为9和10的记录
			List<Receiptpay> amountSumList = receiptpayRepository.findReceiptpayByUseridAndTime(recommendId,
					firstTime, lastTime);
			BigDecimal amountSum = new BigDecimal(0).setScale(3, BigDecimal.ROUND_DOWN);
			//如果数据不为空，算出总额
			if (!amountSumList.isEmpty()) {
				for (Receiptpay receiptpay : amountSumList) {
					BigDecimal amount = receiptpay.getAmount();
					amountSum = amountSum.add(amount);

				}
			}
			
			//通过用户找到流水记录和分页
			List<Receiptpay> receiptpayList = receiptpayRepository.findReceiptpayByUseridAndTimeAndPage(recommendId,
					firstTime, lastTime, pageNum * pageSize, pageSize);
			//如果数据不为空
			if (!receiptpayList.isEmpty()) {
				String firstName = null;

				for (Receiptpay receiptpay : receiptpayList) {
					Rewrite_GetIncomeListDTO getIncomeListDTO = new Rewrite_GetIncomeListDTO();
					//获取单条记录的来源用户id和流水金额
					String sourcerId = receiptpay.getSourcer();
					BigDecimal amount = receiptpay.getAmount();
					//查找创建时间
					String createdate = receiptpay.getCreatedate();
					//根据来源用户id查找  login数据库 
					User jhiUserData = userRepository.findJhiUserById(Long.parseLong(sourcerId));
					/**用户刚支付时可能是用微信和支付宝付款，当用户注册时，会删掉其中一个id，因此login库可能没有该用户的数据，
					 * 因此昵称备注为    已合并用户
					 * 
					 * 用户如果注册时没有写名称，则默认昵称为Auto，当用户昵称为Auto时，查找linkaccount表判断是什么类型用户。
					*/
					if (jhiUserData != null) {
						// 获取推荐人login库jhi_user表昵称
						firstName = jhiUserData.getFirstName();
						//当用户昵称为Auto时，查找linkaccount表判断是什么类型用户
						if (firstName.equals("Auto")) {
							Linkaccount linkaccount = linkaccountRepository.findFirstByUserid(sourcerId);
							if (linkaccount != null) {
								String other = linkaccount.getOther();
								if (other.equals("微信")) {
									firstName = "微信用户";
								} else if (other.equals("支付宝")) {
									firstName = "支付宝用户";
								} else {
									firstName = "未注册用户";
								}
							} else {
								firstName = "未注册用户";
							}
						}
					}else {
						firstName="已注册用户";
					}
					//判断收益类型
					if (receiptpay.getDealtype().equals("9")) {
						getIncomeListDTO.setDealtype("推荐收益");
					} else if (receiptpay.getDealtype().equals("10")) {
						getIncomeListDTO.setDealtype("合伙收益");
					}
					// 是否是商家
					Merchant merchant = merchantRepository.findByUserid(sourcerId);
					// 是否是事业合伙人
					Userlinkuser partner = userLinkUserRepository.findByUserid(sourcerId);
					/**用户刚支付时可能是用微信和支付宝付款，当用户注册时，会删掉其中一个用户id，
					 * 因此userLinkUser库可能没有该推荐用户的数据
					*/
					if (partner!=null) {
						
						// 是商家不是事业合伙人
						if (merchant != null && !(partner.getPartner())) {
							getIncomeListDTO.setMerchant("商家");
							getIncomeListDTO.setPartner("");
							getIncomeListDTO.setVip("");
						} else if (partner.getPartner() && merchant == null) {
							// 是事业合伙人不是商家
							getIncomeListDTO.setPartner("事业合伙人");
							getIncomeListDTO.setVip("");
							getIncomeListDTO.setMerchant("");
						} else if (partner.getPartner() && merchant != null) {
							// 既是事业合伙人，也是商家
							getIncomeListDTO.setPartner("事业合伙人");
							getIncomeListDTO.setVip("");
							getIncomeListDTO.setMerchant("商家");
						} else if (merchant == null && !(partner.getPartner())) {
							// 既不是事业合伙人，也不是商家 会员
							getIncomeListDTO.setPartner("");
							getIncomeListDTO.setVip("会员");
							getIncomeListDTO.setMerchant("");
						}
					}else {
						// 该用户数据已被删除，既不是事业合伙人，也不是商家、 会员
						getIncomeListDTO.setPartner("");
						getIncomeListDTO.setVip("");
						getIncomeListDTO.setMerchant("");
					}

					getIncomeListDTO.setAmountSum(amountSum);
					getIncomeListDTO.setFirstName(firstName);
					getIncomeListDTO.setAmount(amount);
					Date createDate = null;
					try {
						createDate = sdf.parse(createdate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					getIncomeListDTO.setCreatedate(TimeUtil.getTime(createDate));
					incomeListDTO.add(getIncomeListDTO);

				}
			} else {
				return Result.suc("暂时没有数据");
			}
		}
		return Result.suc("访问成功！", incomeListDTO);
	}

	
	// 获取推荐流水明细（新接口）
	@Override
	public Result getRecommendList2(Rewrite_GetIncomeAfferentDTO getIncomeAfferentDTO) {

		// 封装返回DTO
		List<Rewrite_GetIncomeListDTO> incomeListDTO = new ArrayList<Rewrite_GetIncomeListDTO>();
		String url = null;
		// 获取当前用户id
		String recommendId = getIncomeAfferentDTO.getRecommendId();

		// 获取被推荐人longin库资料
		User jhiUser = userRepository.findJhiUserById(Long.parseLong(recommendId));
		if (jhiUser == null) {
			return Result.fail("不存在该用户！");
		}
		


		// 前端返回的查找时间
		Long first = getIncomeAfferentDTO.getFirstTime();
		Long last = getIncomeAfferentDTO.getLastTime();
		
		//分页页数和条数
		Integer pageNum = getIncomeAfferentDTO.getPageNum();
		Integer pageSize = getIncomeAfferentDTO.getPageSize();
		// List<Userlinkuser> recommends= null;
		// 如果有时间值，根据时间值查找推荐人数
		if ((first != null && last != null) && (first != 0 && last != 0)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
			// 如果传过来的时间单位不是毫秒，时间要乘以1000
			if (first < 111111111111L || last < 111111111111L) {
				first = first * 1000L;
				last = last * 1000L;
			}
			String firstTime = sdf.format(new Date(first));
			String lastTime = sdf.format(new Date(last));
			
			//查找流水表，找到用户流水类型为9和10的记录
			List<Receiptpay> amountSumList = receiptpayRepository.findReceiptpayByUseridAndTime(recommendId,
					firstTime, lastTime);
			BigDecimal amountSum = new BigDecimal(0).setScale(3, BigDecimal.ROUND_DOWN);
			//如果数据不为空，算出总额
			if (!amountSumList.isEmpty()) {
				for (Receiptpay receiptpay : amountSumList) {
					BigDecimal amount = receiptpay.getAmount();
					amountSum = amountSum.add(amount);

				}
			}
			
			//通过用户找到流水记录和分页
			List<Receiptpay> receiptpayList = receiptpayRepository.findReceiptpayByUseridAndTimeAndPage(recommendId,
					firstTime, lastTime, pageNum * pageSize, pageSize);
			//如果数据不为空
			if (!receiptpayList.isEmpty()) {
				String firstName = null;

				for (Receiptpay receiptpay : receiptpayList) {
					Rewrite_GetIncomeListDTO getIncomeListDTO = new Rewrite_GetIncomeListDTO();
					//获取单条记录的来源用户id和流水金额
					String sourcerId = receiptpay.getSourcer();
					BigDecimal amount = receiptpay.getAmount();
					//查找创建时间
					String createdate = receiptpay.getCreatedate();
					//根据来源用户id查找  login数据库 
					User jhiUserData = userRepository.findJhiUserById(Long.parseLong(sourcerId));
					/**用户刚支付时可能是用微信和支付宝付款，当用户注册时，会删掉其中一个id，因此login库可能没有该用户的数据，
					 * 因此昵称备注为    已合并用户
					 * 
					 * 用户如果注册时没有写名称，则默认昵称为Auto，当用户昵称为Auto时，查找linkaccount表判断是什么类型用户。
					*/
					if (jhiUserData != null) {
						//通过头像id查找url
						if (jhiUserData.getImageUrl()==null || jhiUserData.getImageUrl().equals("")) {
							url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/17";
						} else {
							Files files = filesRepository.findByIds(Long.parseLong(jhiUserData.getImageUrl()));
							url = files.getUrl();
						}
						
						// 获取推荐人login库jhi_user表昵称
						firstName = jhiUserData.getFirstName();
						//当用户昵称为Auto时，查找linkaccount表判断是什么类型用户
						if (firstName.equals("Auto")) {
							Linkaccount linkaccount = linkaccountRepository.findFirstByUserid(sourcerId);
							if (linkaccount != null) {
								String other = linkaccount.getOther();
								if (other.equals("微信")) {
									firstName = "微信用户";
								} else if (other.equals("支付宝")) {
									firstName = "支付宝用户";
								} else {
									firstName = "未注册用户";
								}
							} else {
								firstName = "未注册用户";
							}
						}
					}else {
						firstName="已注册用户";
						//合并之后的用户头像url
						url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/17";
					}
					//判断收益类型
					if (receiptpay.getDealtype().equals("9")) {
						getIncomeListDTO.setDealtype("推荐收益");
					} else if (receiptpay.getDealtype().equals("10")) {
						getIncomeListDTO.setDealtype("合伙收益");
					}
					// 是否是商家
					Merchant merchant = merchantRepository.findByUserid(sourcerId);
					// 是否是事业合伙人
					Userlinkuser partner = userLinkUserRepository.findByUserid(sourcerId);
					/**用户刚支付时可能是用微信和支付宝付款，当用户注册时，会删掉其中一个用户id，
					 * 因此userLinkUser库可能没有该推荐用户的数据
					*/
					if (partner!=null) {
						
						// 是商家不是事业合伙人
						if (merchant != null && !(partner.getPartner())) {
							getIncomeListDTO.setMerchant("商家");
							getIncomeListDTO.setPartner("");
							getIncomeListDTO.setVip("");
						} else if (partner.getPartner() && merchant == null) {
							// 是事业合伙人不是商家
							getIncomeListDTO.setPartner("事业合伙人");
							getIncomeListDTO.setVip("");
							getIncomeListDTO.setMerchant("");
						} else if (partner.getPartner() && merchant != null) {
							// 既是事业合伙人，也是商家
							getIncomeListDTO.setPartner("事业合伙人");
							getIncomeListDTO.setVip("");
							getIncomeListDTO.setMerchant("商家");
						} else if (merchant == null && !(partner.getPartner())) {
							// 既不是事业合伙人，也不是商家 会员
							getIncomeListDTO.setPartner("");
							getIncomeListDTO.setVip("会员");
							getIncomeListDTO.setMerchant("");
						}
					}else {
						// 该用户数据已被删除，既不是事业合伙人，也不是商家、 会员
						getIncomeListDTO.setPartner("");
						getIncomeListDTO.setVip("");
						getIncomeListDTO.setMerchant("");
					}

					getIncomeListDTO.setAmountSum(amountSum);
					getIncomeListDTO.setFirstName(firstName);
					getIncomeListDTO.setAmount(amount);
					getIncomeListDTO.setImageUrlId(url);
					Date createDate = null;
					try {
						createDate = sdf.parse(createdate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					getIncomeListDTO.setCreatedate(TimeUtil.getTime(createDate));
					incomeListDTO.add(getIncomeListDTO);

				}
			} else {
				return Result.suc("暂时没有数据");
			}
		}
		return Result.suc("访问成功！", incomeListDTO);
	}
	//
	// //如果有时间值，根据时间值来查找
	// recommends =
	// incomeDetailsRepository.findByRecommendIdAndPage(recommendId,pageNum*pageSize,pageSize);
	// //如果有数据，进行遍历，并到收支明细表和login库中获取数据
	// if (!recommends.isEmpty()) {
	// String firstName = null;
	// String imageUrl = null;
	// //遍历数据
	// for (Userlinkuser userlinkuser : recommends) {
	// Rewrite_GetIncomeListDTO getIncomeListDTO = new Rewrite_GetIncomeListDTO();
	// //获取被推荐人用户id
	// String userid = userlinkuser.getUserid();
	// //获取被推荐人longin库资料

	// imageUrl = jhiUserData.getImageUrl();
	// //获取创建时间
	// String createdate = userlinkuser.getCreatedate();
	// //获取收支明细表中对应
	// List<BigDecimal> amounts =
	// receiptpayRepository.getReceiptpayByUseridAndSourcerAndTime(recommendId,userid,firstTime,lastTime);
	// //如果找得到数据
	// BigDecimal sumAmount = new BigDecimal(0).setScale(3, BigDecimal.ROUND_DOWN);
	// //如果收支明细表中有数据，查找获利金额
	// if (!amounts.isEmpty()) {
	// for (BigDecimal amount : amounts) {
	// sumAmount=sumAmount.add(amount);
	// }
	// }
	//

	//
	//
	// //将数据返回
	// getIncomeListDTO.setImageUrl(imageUrl);
	// getIncomeListDTO.setCreatedate(createdate);
	// getIncomeListDTO.setFirstName(firstName);
	// getIncomeListDTO.setAmount(sumAmount);
	// incomeListDTO.add(getIncomeListDTO);
	// }
	//
	// } else {
	// return Result.suc("暂时没有数据");
	// }
	// } else {
	// //如果没有时间值，则查找的时候进行分页查找
	// List<Userlinkuser> recommendData =
	// incomeDetailsRepository.findByRecommendIdAndPage(recommendId,pageNum*pageSize,pageSize);
	// if (!recommendData.isEmpty()) {
	// //获取被推荐人longin库jhi_user表头像和昵称
	// String firstName = null;
	// String imageUrl = null;
	// for (Userlinkuser userlinkuser : recommendData) {
	// Rewrite_GetIncomeListDTO getIncomeListDTO = new Rewrite_GetIncomeListDTO();
	// String userid = userlinkuser.getUserid();
	// //获取被推荐人longin库资料
	// User jhiUserData = userRepository.findJhiUserById(Long.parseLong(userid));
	// //获取推荐人login库jhi_user表昵称和头像url
	// firstName = jhiUserData.getFirstName();
	// imageUrl = jhiUserData.getImageUrl();
	// //获取创建时间
	// String createdate = userlinkuser.getCreatedate();
	// //获取收支明细表中对应
	// List<Receiptpay> receiptpays =
	// receiptpayRepository.findByUseridAndSourcer(recommendId,userid);
	// //如果找得到数据
	// BigDecimal sumAmount = new BigDecimal(0).setScale(3, BigDecimal.ROUND_DOWN);
	// if (!receiptpays.isEmpty()) {
	//
	// for (Receiptpay receiptpay : receiptpays) {
	// BigDecimal amount = receiptpay.getAmount();
	// if (amount!=null || !(amount.equals(""))) {
	// sumAmount=sumAmount.add(amount);
	// }
	// }
	// }
	//
	//
	// //是否是商家
	// Merchant merchant = merchantRepository.findByUserid(userid);
	// //是否是事业合伙人
	// Userlinkuser partner = userLinkUserRepository.findByUserid(userid);
	// //是商家不是事业合伙人
	// if (merchant!=null && !(partner.getPartner())) {
	// getIncomeListDTO.setMerchant("商家");
	// getIncomeListDTO.setPartner("");
	// getIncomeListDTO.setVip("");
	// }else if (partner.getPartner() && merchant==null) {
	// //是事业合伙人不是商家
	// getIncomeListDTO.setPartner("事业合伙人");
	// getIncomeListDTO.setVip("");
	// getIncomeListDTO.setMerchant("");
	// }else if (partner.getPartner() && merchant!=null) {
	// //既是事业合伙人，也是商家
	// getIncomeListDTO.setPartner("事业合伙人");
	// getIncomeListDTO.setVip("");
	// getIncomeListDTO.setMerchant("商家");
	// }else if (merchant==null && !(partner.getPartner())) {
	// //既不是事业合伙人，也不是商家 会员
	// getIncomeListDTO.setPartner("");
	// getIncomeListDTO.setVip("会员");
	// getIncomeListDTO.setMerchant("");
	// }
	//
	// getIncomeListDTO.setImageUrl(imageUrl);
	// getIncomeListDTO.setCreatedate(createdate);
	// getIncomeListDTO.setFirstName(firstName);
	// getIncomeListDTO.setAmount(sumAmount);
	// incomeListDTO.add(getIncomeListDTO);
	// }
	// }

	// return null;

	// 获取收益列表
	@Override
	public Result getProfitList(String userId) {
		// 获取被推荐人longin库资料
		User jhiUser = userRepository.findJhiUserById(Long.parseLong(userId));
		if (jhiUser == null) {
			return Result.fail("不存在该用户！");
		}

		List<Rewrite_ProfitListDTO> profitListDTO = new ArrayList<>();
		// 获取今天零点的时间戳
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Long nowZeroTime = calendar.getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 7天前零点的时间
		Long oldZeroTime = nowZeroTime - (86400000L * 6);

		// 一天的毫秒值
		Long oneDayTime = 86400000L;
		// 7天前零点到6天前的前一秒
		Long lastDayTime = oldZeroTime + oneDayTime - 1000L;

		// 如果7天前的时间加上每一天的时间小于现在时间，说明是今天到七天前的数据
		do {
			BigDecimal bigDecimal = new BigDecimal(0).setScale(3, BigDecimal.ROUND_DOWN);
			Rewrite_ProfitListDTO profitDTO = new Rewrite_ProfitListDTO();
			// 查找数据的开始时间
			String firstTime = sdf.format(new Date(oldZeroTime));
			// 查找数据的结束时间
			String lastTime = sdf.format(new Date(lastDayTime));
			List<Receiptpay> receiptpayList = receiptpayRepository.findReceiptpayByUseridAndTime(userId, firstTime,
					lastTime);
			if (!receiptpayList.isEmpty()) {
				for (Receiptpay receiptpay : receiptpayList) {
					BigDecimal amount = receiptpay.getAmount();
					// 如果有数据，添加到DTO中
					bigDecimal = bigDecimal.add(amount);
				}
			}
			profitDTO.setEarn("" + bigDecimal);
			profitDTO.setDate(firstTime);
			profitListDTO.add(profitDTO);

			// 将时间加1天
			oldZeroTime += oneDayTime;
			lastDayTime += oneDayTime;
		} while (oldZeroTime < (nowZeroTime + oneDayTime));

		return Result.suc("访问成功！", profitListDTO);
	}

	// 获取收益列表(为兼容新版本新写接口)
	@Override
	public Result getProfitList2(String userId) {
		// 获取被推荐人longin库资料
		User jhiUser = userRepository.findJhiUserById(Long.parseLong(userId));
		if (jhiUser == null) {
			return Result.fail("不存在该用户！");
		}

		List<Rewrite_ProfitListDTO> profitListDTO = new ArrayList<>();
		// 获取今天零点的时间戳
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Long nowZeroTime = calendar.getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

		// 7天前零点的时间
		Long oldZeroTime = nowZeroTime - (86400000L * 6);

		// 一天的毫秒值
		Long oneDayTime = 86400000L;
		// 7天前零点到6天前的前一秒
		Long lastDayTime = oldZeroTime + oneDayTime - 1000L;

		// 如果7天前的时间加上每一天的时间小于现在时间，说明是今天到七天前的数据
		do {
			BigDecimal bigDecimal = new BigDecimal(0).setScale(3, BigDecimal.ROUND_DOWN);
			Rewrite_ProfitListDTO profitDTO = new Rewrite_ProfitListDTO();
			// 查找数据的开始时间
			String firstTime = sdf.format(new Date(oldZeroTime));
			// 查找数据的结束时间
			String lastTime = sdf.format(new Date(lastDayTime));
			List<Receiptpay> receiptpayList = receiptpayRepository.findReceiptpayByUseridAndTime(userId, firstTime,
					lastTime);
			if (!receiptpayList.isEmpty()) {
				for (Receiptpay receiptpay : receiptpayList) {
					BigDecimal amount = receiptpay.getAmount();
					// 如果有数据，添加到DTO中
					bigDecimal = bigDecimal.add(amount);
				}
			}
			profitDTO.setEarn("" + bigDecimal);
			Date createDate = null;
			try {
				createDate = sdf.parse(lastTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			profitDTO.setDate(TimeUtil.getTime(createDate));
			profitListDTO.add(profitDTO);

			// 将时间加1天
			oldZeroTime += oneDayTime;
			lastDayTime += oneDayTime;
		} while (oldZeroTime < (nowZeroTime + oneDayTime));

		return Result.suc("访问成功！", profitListDTO);
	}

	// 推荐用户信息列表
	@Override
	public Result userInformationList(String recommendId, Integer Type, Integer pageNum, Integer pageSize) {
		// 通过id找到推荐人数量
		List<Userlinkuser> recommendIdfindByUserId = null;
		if (Type == 0) {
			// 通过id找到推荐人数量
			recommendIdfindByUserId = incomeDetailsRepository.findByRecommendIdAndPage(recommendId, pageNum * pageSize,
					pageSize);
		} else {
			// 通过id找到推荐人数量
			recommendIdfindByUserId = incomeDetailsRepository.findByRecommendId(recommendId);
		}
		List<Rewrite_UserInformationListDTO> userInformationList = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		String createdate = null;
		if (Type == 0) {
			for (Userlinkuser recommender : recommendIdfindByUserId) {
				String userid = recommender.getUserid();
				createdate = recommender.getCreatedate();
				// 获取被推荐人login库资料
				User jhiUser = userRepository.findJhiUserById(Long.parseLong(userid));
				if (jhiUser == null) {
					return Result.fail("不存在该用户！");
				}
				Rewrite_UserInformationListDTO userInformationListDTO = new Rewrite_UserInformationListDTO();

				String login = jhiUser.getLogin();
				String sublogin = login.substring(login.length() - 4);
				Linkaccount linkaccount = linkaccountRepository.findFirstByUserid(userid);
				if (linkaccount != null) {
					String other = linkaccount.getOther();
					if (other == null) {
						userInformationListDTO.setAccounttype("圆积分");
					} else if (other.equals("支付宝")) {
						userInformationListDTO.setAccounttype("支付宝");
					} else {
						userInformationListDTO.setAccounttype("微信");
					}
				} else {
					userInformationListDTO.setAccounttype("圆积分");
				}
				Date createDate = null;
				try {
					createDate = sdf.parse(createdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				userInformationListDTO.setCreatedate(TimeUtil.getTime(createDate));
				userInformationListDTO.setLogin(sublogin);
				userInformationList.add(userInformationListDTO);
			}
		} else {

			for (Userlinkuser recommender : recommendIdfindByUserId) {
				String userid = recommender.getUserid();
				createdate = recommender.getCreatedate();
				// 获取被推荐人login库资料
				User jhiUser = userRepository.findJhiUserById(Long.parseLong(userid));
				if (jhiUser == null) {
					return Result.fail("不存在该用户！");
				}
				Rewrite_UserInformationListDTO userInformationListDTO = new Rewrite_UserInformationListDTO();
				if (Type == 1) {
					// 是否是商家
					Merchant merchant = merchantRepository.findByUserid(userid);
					if (merchant != null) {
						String login = jhiUser.getLogin();
						String sublogin = login.substring(login.length() - 4);
						Linkaccount linkaccount = linkaccountRepository.findFirstByUserid(userid);
						if (linkaccount != null) {
							String other = linkaccount.getOther();
							if (other == null) {
								userInformationListDTO.setAccounttype("圆积分");
							} else if (other.equals("支付宝")) {
								userInformationListDTO.setAccounttype("支付宝");
							} else if (other.equals("微信")) {
								userInformationListDTO.setAccounttype("微信");
							}
						} else {
							userInformationListDTO.setAccounttype("圆积分");
						}
						Date createDate = null;
						try {
							createDate = sdf.parse(createdate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						userInformationListDTO.setCreatedate(TimeUtil.getTime(createDate));
						userInformationListDTO.setLogin(sublogin);
						userInformationList.add(userInformationListDTO);
					}

				}
				if (Type == 2) {
					// 是否是事业合伙人
					Userlinkuser partner = userLinkUserRepository.findByUserid(userid);
					if (partner.getPartner()) {
						String login = jhiUser.getLogin();
						String sublogin = login.substring(login.length() - 4);
						Linkaccount linkaccount = linkaccountRepository.findFirstByUserid(userid);
						if (linkaccount != null) {
							String other = linkaccount.getOther();
							if (other == null) {
								userInformationListDTO.setAccounttype("圆积分");
							} else if (other.equals("支付宝")) {
								userInformationListDTO.setAccounttype("支付宝");
							} else if (other.equals("微信")) {
								userInformationListDTO.setAccounttype("微信");
							}
						} else {
							userInformationListDTO.setAccounttype("圆积分");
						}
						Date createDate = null;
						try {
							createDate = sdf.parse(createdate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						userInformationListDTO.setCreatedate(TimeUtil.getTime(createDate));
						userInformationListDTO.setLogin(sublogin);
						userInformationList.add(userInformationListDTO);

					}

				}
			}
			if (userInformationList.isEmpty()) {
				return Result.suc("暂时没有数据");
			}
			Integer size = userInformationList.size();
			Integer pageNo = pageNum * pageSize;
			if (pageNo + pageSize > size) {
				if (pageNo > size) {
					userInformationList = null;
				} else {
					userInformationList = userInformationList.subList(pageNo, size);
				}
			} else {
				userInformationList = userInformationList.subList(pageNo, pageNo + pageSize);
			}

		}
		if (userInformationList == null) {
			return Result.suc("暂时没有数据");
		}

		return Result.suc("访问成功！", userInformationList);
	}

	// 查询用户商家端收益列表倒叙(重写)
	@Override
	public Result newFindMerchantProfitInfo(String userid, Integer startPage, Integer pageSize) {
		// 获取用户login库资料
		User jhiUser = userRepository.findJhiUserById(Long.parseLong(userid));
		if (jhiUser == null) {
			return Result.fail("不存在该用户！");
		}

		// 根据用户id和收入状态查询，分页并倒叙
		List<Receiptpay> list = receiptpayRepository.getAllByMerchantAndType(userid, ReceiptpayConstant.BALANCE_INCOME,
				startPage * pageSize, pageSize);
		if (list.isEmpty()) {
			return Result.suc("暂无收益记录！");
		}
		// 用list封装数据
		List<Rewrite_NewFindMerchantProfitInfoDTO> newFindMerchantProfitInfoList = new ArrayList<>();
		// 遍历并保存
		for (Receiptpay receiptpay : list) {
			Rewrite_NewFindMerchantProfitInfoDTO newFindMerchantProfitInfo = new Rewrite_NewFindMerchantProfitInfoDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
			Date createDate = null;
			try {
				createDate = sdf.parse(receiptpay.getCreatedate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			newFindMerchantProfitInfo.setCreatedate(TimeUtil.getTime(createDate));
			newFindMerchantProfitInfo.setBenefit(receiptpay.getBenefit());
			newFindMerchantProfitInfo.setAmount(receiptpay.getAmount());
			newFindMerchantProfitInfo.setBonus(receiptpay.getBonus());
			newFindMerchantProfitInfo.setCreator(receiptpay.getCreator());
			newFindMerchantProfitInfo.setDealstate(receiptpay.getDealstate());
			newFindMerchantProfitInfo.setDealtype(receiptpay.getDealtype());
			newFindMerchantProfitInfo.setFreezedate(receiptpay.getFreezedate());
			newFindMerchantProfitInfo.setHappendate(receiptpay.getHappendate());
			newFindMerchantProfitInfo.setPayway(receiptpay.getPayway());
			newFindMerchantProfitInfo.setOther("支付" + receiptpay.getBenefit() + "元(" + receiptpay.getAmount() + ")");
			newFindMerchantProfitInfo.setUserid(receiptpay.getUserid());
			newFindMerchantProfitInfo.setSourcer(receiptpay.getSourcer());
			newFindMerchantProfitInfo.setModifier(receiptpay.getModifier());
			newFindMerchantProfitInfo.setModifierdate(receiptpay.getModifierdate());
			newFindMerchantProfitInfo.setModifiernum(receiptpay.getModifiernum());
			newFindMerchantProfitInfoList.add(newFindMerchantProfitInfo);
		}

		return Result.suc("访问成功！", newFindMerchantProfitInfoList);
	}

	/**
	 * 查询推荐榜前十位用户
	 * 
	 * @author LuoJinShui
	 */
	@Override
	public Result recommendationList(String userId) {
		// 获取用户login库资料
		User jhiUser = userRepository.findJhiUserById(Long.parseLong(userId));
		if (jhiUser == null) {
			return Result.fail("当前用户不存在!");
		} else {
			List<Map<String, Object>> userlinkusers = userLinkUserRepository.getRecommendisList();
			return Result.suc("查询成功!", userlinkusers, userlinkusers.size());
		}
	}
}
