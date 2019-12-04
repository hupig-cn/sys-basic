package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.*;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_AdvertisingDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ActivityPay2DTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ActivityPayDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ActivitySerDTO;
import com.weisen.www.code.yjf.basic.service.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_ActivityService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ActAmoDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetActivityPayDTO;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_ActivityServiceImpl implements Rewrite_ActivityService {

	private final ActivityPayRepository rewrite_ActivityPayRepository;

	private final ActivitySerRepository rewrite_ActivitySerRepository;

	private final Rewrite_LinkaccountRepository linkaccountRepository;

	private final Rewrite_UserRepository userRepository;

	private final FilesRepository filesRepository;

	private final Rewrite_MerchantRepository rewrite_MerchantRepository;

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

	private final Rewrite_AdvertisementRepository rewrite_advertisementRepository;

	public Rewrite_ActivityServiceImpl(ActivityPayRepository rewrite_ActivityPayRepository,
                                       ActivitySerRepository rewrite_ActivitySerRepository, FilesRepository filesRepository,
                                       Rewrite_UserRepository userRepository, Rewrite_LinkaccountRepository linkaccountRepository,
                                       Rewrite_MerchantRepository rewrite_merchantRepository,
                                       Rewrite_UserassetsRepository rewrite_UserassetsRepository,
                                       Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository, Rewrite_AdvertisementRepository rewrite_advertisementRepository) {
		this.rewrite_ActivityPayRepository = rewrite_ActivityPayRepository;
		this.filesRepository = filesRepository;
		this.rewrite_ActivitySerRepository = rewrite_ActivitySerRepository;
		this.linkaccountRepository = linkaccountRepository;
		this.userRepository = userRepository;
		this.rewrite_MerchantRepository = rewrite_merchantRepository;
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
        this.rewrite_advertisementRepository = rewrite_advertisementRepository;
    }

	/**
	 * 优惠活动
	 *
	 * @author sxx
	 */
	@Override
	public Result queryAmount(Rewrite_GetActivityPayDTO getActivityPayDTO) {
		String userId = getActivityPayDTO.getUserId();
		Integer pageNum = getActivityPayDTO.getPageNum();
		Long firstTime = getActivityPayDTO.getFirstTime();
		Long lastTime = getActivityPayDTO.getLastTime();
		// 解析时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

		String first = sdf.format(new Date(firstTime));
		String last = sdf.format(new Date(lastTime));
		Integer pageSize = getActivityPayDTO.getPageSize();
		List<ActivityPay> userActivityPayList = rewrite_ActivityPayRepository.findByUserId(userId, first, last, pageNum,
				pageSize);
		String firstName = null;
		String url = null;
		LinkedList<Rewrite_ActAmoDTO> actAmoDTOList = new LinkedList<>();
		if (!userActivityPayList.isEmpty()) {

			for (ActivityPay userActivityPay : userActivityPayList) {
				Rewrite_ActAmoDTO rewrite_ActAmoDTO = new Rewrite_ActAmoDTO();
				// 资金来源用户id
				String sourceId = userActivityPay.getSource();
				String createTime = userActivityPay.getCreateTime();
				BigDecimal transformationAmo = userActivityPay.getTransformationAmo();
				BigDecimal incomeAmo = userActivityPay.getIncomeAmo();
				User jhiUserData = userRepository.findJhiUserById(Long.parseLong(sourceId));
				/**
				 * 用户刚支付时可能是用微信和支付宝付款，当用户注册时，会删掉其中一个id，因此login库可能没有该用户的数据， 因此昵称备注为 已合并用户
				 *
				 * 用户如果注册时没有写名称，则默认昵称为Auto，当用户昵称为Auto时，查找linkaccount表判断是什么类型用户。
				 */
				if (jhiUserData != null) {
					// 获取来源用户login库jhi_user表昵称
					firstName = jhiUserData.getFirstName();
//					//通过头像id查找url
//					if (jhiUserData.getImageUrl()==null || jhiUserData.getImageUrl().equals("")) {
					// 当用户昵称为Auto时，查找linkaccount表判断是什么类型用户
					if (firstName.equals("Auto")) {
						Linkaccount linkaccount = linkaccountRepository.findFirstByUserid(sourceId);
						if (linkaccount != null) {
							String other = linkaccount.getOther();
							if (other.equals("微信")) {
								firstName = "微信用户";
								// 微信头像URL
								url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3155";
							} else if (other.equals("支付宝")) {
								firstName = "支付宝用户";
								// 支付宝头像url
								url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3142";
							} else {
								firstName = "未注册用户";
								// 未注册头像url
								url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3327";
							}
						} else {
							firstName = "未注册用户";
							// 未注册头像url
							url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3327";
						}
					} else {
						if (jhiUserData.getImageUrl() == null || jhiUserData.getImageUrl().equals("")) {
							url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3332";
						} else {
							Files files = filesRepository.findByIds(Long.parseLong(jhiUserData.getImageUrl()));
							url = files.getUrl();

						}
					}
				} else {
					firstName = "已注册用户";
					// 合并之后的用户头像url
					url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3327";
				}

				rewrite_ActAmoDTO.setUserUrl(url);
				rewrite_ActAmoDTO.setUserNickName(firstName);
				rewrite_ActAmoDTO.setPaymentAmount(incomeAmo);
				rewrite_ActAmoDTO.setTime(createTime);
				rewrite_ActAmoDTO.setTransFormAmount(transformationAmo);
				actAmoDTOList.add(rewrite_ActAmoDTO);
			}
		} else {
			return Result.suc("暂无活动流水!");
		}

		return Result.suc("访问成功", actAmoDTOList);
	}

	// 查询商家用户可用资金和活动资金 LuoJinShui
	@Override
	public Result getAvailableAmoAndActivityAmo(String userId) {
		Merchant merchant = rewrite_MerchantRepository.findByUserid(userId);
		ActivitySer activitySer = rewrite_ActivitySerRepository.findByUserId(userId);
		// 判断该用户是否是商家
		if (merchant == null) {
			return Result.fail("您不是商家!不能对此进行操作!");
		}
		// 判断该活动商家是否已参加
		if (activitySer == null) {
			return Result.fail("暂时没有您的记录!");
		} else {
			Rewrite_ActivitySerDTO rewrite_ActivitySerDTO = new Rewrite_ActivitySerDTO();
			// 拿到商家用户ID
			rewrite_ActivitySerDTO.setUserId(activitySer.getUserId());
			// 拿到商家活动资金
			rewrite_ActivitySerDTO.setActivityAmo(activitySer.getActivityAmo());
			// 拿到商家可用资金
			rewrite_ActivitySerDTO.setAvailableAmo(activitySer.getAvailableAmo());
			return Result.suc("查询成功!", rewrite_ActivitySerDTO);
		}
	}

	// 可用资金达到50元可提现到余额 LuoJinShui
	@Override
	public Result availableAmoWithdrawalBalance(String userId, String availableAmo) {
		Merchant merchant = rewrite_MerchantRepository.findByUserid(userId);
		ActivitySer activitySer = rewrite_ActivitySerRepository.findByUserId(userId);
		// 判断该用户是否是商家
		if (merchant == null) {
			return Result.fail("您不是商家!不能对此进行操作!");
		}
		// 判断该活动商家是否已参加
		if (activitySer == null) {
			return Result.fail("暂时没有您的记录!无法提现!");
		} else {
			// 拿到商家实际的可用资金
			String availableAmoMoney = activitySer.getAvailableAmo().toString();
			// 商家输入的金额
			BigDecimal userMoney = new BigDecimal(availableAmo);
			// 商家实际的可用资金
			BigDecimal businessMoney = new BigDecimal(availableAmoMoney);
			// 达到50元才可提现到余额
			BigDecimal fiftyMoney = new BigDecimal("50.00");

			// 判断提现金额是否是整数
			if (new BigDecimal(userMoney.intValue()).compareTo(userMoney) == -1) {
				return Result.fail("提现金额只能是整数哦!");
			}

			// 判断输入的金额是否超过实际的可用金额
			// userMoney > businessMoney
			if (userMoney.compareTo(businessMoney) == 1) {
				return Result.fail("您的可用资金没有这么多哦!");
			}

			// 判断输入的金额是否达到提现额度条件
			// fiftyMoney > userMoney
			if (fiftyMoney.compareTo(userMoney) == 1) {
				return Result.fail("最低提现额度需要达到50元哦!");
			} else {

				// 新增一条记录到活动流水表
				ActivityPay activityPay = new ActivityPay();
				// 提现商家ID
				activityPay.setUserId(userId);
				// 提现类型
				activityPay.setType(3);
				// 还没提现的可用资金
				activityPay.setTransformationAmo(businessMoney);
				// 提现金额
				activityPay.setIncomeAmo(userMoney.setScale(0));
				// 提现时间
				activityPay.setCreateTime(TimeUtil.getDate());

				// 新增一条记录到收益明细流水表
				Receiptpay receiptpay = new Receiptpay();
				// 收款人ID
				receiptpay.setUserid(userId);
				// 资金来源
				receiptpay.setSourcer(userId);
				// 单笔资金额度
				receiptpay.setBenefit(userMoney.setScale(0).toString());
				// 资金交易类型
				receiptpay.setDealtype("12");
				// 产生流水时间
				receiptpay.setCreatedate(TimeUtil.getDate());

				// 根据userID找到商家资产表
				Userassets userassets = rewrite_UserassetsRepository.findByUserid(userId);
				// 将商家实时余额拿到转成BigDecimal类型
				BigDecimal balanceMoney = new BigDecimal(userassets.getBalance());
				BigDecimal usablebalanceMoney = new BigDecimal(userassets.getUsablebalance());
				// 实时余额加提现金额并保存到资产表
				userassets.setBalance(userMoney.add(balanceMoney).setScale(3).toString());
				userassets.setUsablebalance(userMoney.add(usablebalanceMoney).setScale(3).toString());
				// 修改资产时间
				userassets.setModifierdate(TimeUtil.getDate());

				// 操作商家ID
				activitySer.setUserId(userId);
				// 可用资金减去提现的金额
				activitySer.setAvailableAmo(businessMoney.subtract(userMoney));
				// 拿到以往的提现金额
				BigDecimal cashWithdrawalMoney = new BigDecimal(activitySer.getCashWithdrawal().toString());
				// 将以往提现的总金额跟提现的金额相加(累加)
				activitySer.setCashWithdrawal(userMoney.add(cashWithdrawalMoney));
				// 修改时间
				activitySer.setUpdateTime(TimeUtil.getDate());

				// 保存一条提现记录
				rewrite_ActivityPayRepository.save(activityPay);
				// 提现后修改活动服务表的数据
				rewrite_ActivitySerRepository.saveAndFlush(activitySer);
				// 将提现金额加到商家资产表的余额里
				rewrite_UserassetsRepository.saveAndFlush(userassets);
				// 新增一条收益明细记录
				rewrite_ReceiptpayRepository.save(receiptpay);
				return Result.suc("提现成功!", activityPay);
			}
		}
	}

	// 查询可用资金流水明细 LuoJinShui
	@Override
	public Result getWithdrawalDetails(String userId, Integer pageNum, Integer pageSize) {
		Merchant merchant = rewrite_MerchantRepository.findByUserid(userId);
		// 判断该用户是否是商家
		if (merchant == null) {
			return Result.fail("您不是商家!不能对此进行操作!");
		} else {
			List<Rewrite_ActivityPayDTO> rewrite_ActivityPayDTOs = new ArrayList<Rewrite_ActivityPayDTO>();
			List<ActivityPay> activityPaysList = rewrite_ActivityPayRepository.findByUserIdAndType(userId, 3,
					pageNum * pageSize, pageSize);
			for (ActivityPay activityPay : activityPaysList) {
				Rewrite_ActivityPayDTO activityPayDTO = new Rewrite_ActivityPayDTO();
				// 操作的商家ID
				activityPayDTO.setUserId(activityPay.getUserId());
				// 流水类型
				if (activityPay.getType().equals(3)) {
					activityPayDTO.setType(activityPay.getType());
					activityPayDTO.setStatus(0);
					activityPayDTO.setExplain("可用资金提现");
				}
				// 未提现之前的可用资金
				activityPayDTO.setTransformationAmo(activityPay.getTransformationAmo());
				// 单笔提现金额
				activityPayDTO.setIncomeAmo(activityPay.getIncomeAmo());
				String dataStr = activityPay.getCreateTime();
				// 转换时间格式显示今天、昨天,如果是当前年份不显示年份,不是当前年份就显示出来
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
				Date createDate = null;
				try {
					createDate = sdf.parse(dataStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				activityPayDTO.setCreateDate(TimeUtil.getTime(createDate));
				rewrite_ActivityPayDTOs.add(activityPayDTO);
			}
			return Result.suc("查询成功!", rewrite_ActivityPayDTOs, rewrite_ActivityPayDTOs.size());
		}
	}


    @Override
    public Result zhuanhuankeyongzhijin(String userId, Integer pageNum, Integer pageSize) {
        Merchant merchant = rewrite_MerchantRepository.findByUserid(userId);
        // 判断该用户是否是商家
        if (merchant == null) {
            return Result.fail("您不是商家!不能对此进行操作!");
        } else {
            List<Rewrite_ActivityPay2DTO> rewrite_ActivityPayDTOs = new ArrayList<Rewrite_ActivityPay2DTO>();
            List<ActivityPay> activityPaysList = rewrite_ActivityPayRepository.findByUserIdAndType(userId, 2,
                pageNum * pageSize, pageSize);
            for (ActivityPay activityPay : activityPaysList) {
                Rewrite_ActivityPay2DTO activityPayDTO = new Rewrite_ActivityPay2DTO();
                // 操作的商家ID
                activityPayDTO.setUserId(activityPay.getUserId());
                // 流水类型
                if (activityPay.getType().equals(2)) {
                    activityPayDTO.setType(activityPay.getType());
                    activityPayDTO.setStatus(1);
                    activityPayDTO.setExplain("活动资金转换");
                }

                activityPayDTO.setTransformationAmo(activityPay.getIncomeAmo());

                activityPayDTO.setIncomeAmo(activityPay.getTransformationAmo());
                String dataStr = activityPay.getCreateTime();

                // 转换时间格式显示今天、昨天,如果是当前年份不显示年份,不是当前年份就显示出来
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                Date createDate = null;
                try {
                    createDate = sdf.parse(dataStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                activityPayDTO.setCreateDate(TimeUtil.getTime(createDate));
                rewrite_ActivityPayDTOs.add(activityPayDTO);
            }
            return Result.suc("查询成功!", rewrite_ActivityPayDTOs, rewrite_ActivityPayDTOs.size());
        }
    }

    @Override
    public Result lunbotu(Integer type) {
        List<Advertisement> all = new ArrayList<>();
	    if (type == 0){
         all = rewrite_advertisementRepository.findAll();
        }else if(type == 1){
	        all = rewrite_advertisementRepository.findAdvertisementByAdvType(type);
        }
        List<Rewrite_AdvertisingDTO> list = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            Rewrite_AdvertisingDTO ac = new Rewrite_AdvertisingDTO();
            Advertisement advertisement = all.get(i);
            String pictureLink = advertisement.getPictureLink();
            Files files = filesRepository.findByIds(Long.valueOf(pictureLink));
            Integer height = files.getHeight();
            Integer width = files.getWidth();
            ac.setAdvertisement(advertisement);
            ac.setHeight(height);
            ac.setWidth(width);
            list.add(ac);
        }

        return Result.suc("查询成功",list,list.size());
    }


}