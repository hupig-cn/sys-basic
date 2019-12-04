package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ActivityPay2DTO;
import com.weisen.www.code.yjf.basic.service.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.repository.ActivityPayRepository;
import com.weisen.www.code.yjf.basic.repository.ActivitySerRepository;
import com.weisen.www.code.yjf.basic.repository.FilesRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkaccountRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_ActivityService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ActAmoDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_GetActivityPayDTO;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_ActivityServiceImpl implements Rewrite_ActivityService {

    private final ActivityPayRepository activityPayRepository;

    private final ActivitySerRepository activitySerRepository;

	private final Rewrite_LinkaccountRepository linkaccountRepository;

	private final Rewrite_UserRepository userRepository;

	private final FilesRepository filesRepository;

	private final Rewrite_MerchantRepository rewrite_MerchantRepository;

	public Rewrite_ActivityServiceImpl(ActivityPayRepository activityPayRepository,
                                       ActivitySerRepository activitySerRepository, FilesRepository filesRepository,
                                       Rewrite_UserRepository userRepository, Rewrite_LinkaccountRepository linkaccountRepository, Rewrite_MerchantRepository rewrite_merchantRepository) {
		this.activityPayRepository = activityPayRepository;
		this.filesRepository = filesRepository;
		this.activitySerRepository = activitySerRepository;
		this.linkaccountRepository = linkaccountRepository;
		this.userRepository = userRepository;
        rewrite_MerchantRepository = rewrite_merchantRepository;
    }



	/**
	 *  优惠活动
	 *
	 * @author sxx
	 */
	@Override
	public Result queryAmount(Rewrite_GetActivityPayDTO getActivityPayDTO) {
		String userId = getActivityPayDTO.getUserId();
		Integer pageNum = getActivityPayDTO.getPageNum();
		Long firstTime = getActivityPayDTO.getFirstTime();
		Long lastTime = getActivityPayDTO.getLastTime();
		//解析时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

		String first = sdf.format(new Date(firstTime));
		String last = sdf.format(new Date(lastTime));
		Integer pageSize = getActivityPayDTO.getPageSize();
		List<ActivityPay> userActivityPayList = activityPayRepository.findByUserId(userId, first,last,pageNum,pageSize);
		String firstName = null;
		String url = null;
		LinkedList<Rewrite_ActAmoDTO> actAmoDTOList = new LinkedList<>();
		if (!userActivityPayList.isEmpty()) {

			for (ActivityPay userActivityPay : userActivityPayList) {
				Rewrite_ActAmoDTO rewrite_ActAmoDTO = new Rewrite_ActAmoDTO();
				//资金来源用户id
				String sourceId = userActivityPay.getSource();
				String createTime = userActivityPay.getCreateTime();
				BigDecimal transformationAmo = userActivityPay.getTransformationAmo();
				BigDecimal incomeAmo = userActivityPay.getIncomeAmo();
				User jhiUserData = userRepository.findJhiUserById(Long.parseLong(sourceId));
				/**用户刚支付时可能是用微信和支付宝付款，当用户注册时，会删掉其中一个id，因此login库可能没有该用户的数据，
				 * 因此昵称备注为    已合并用户
				 *
				 * 用户如果注册时没有写名称，则默认昵称为Auto，当用户昵称为Auto时，查找linkaccount表判断是什么类型用户。
				 */
				if (jhiUserData != null) {
					// 获取来源用户login库jhi_user表昵称
					firstName = jhiUserData.getFirstName();
//					//通过头像id查找url
//					if (jhiUserData.getImageUrl()==null || jhiUserData.getImageUrl().equals("")) {
					//当用户昵称为Auto时，查找linkaccount表判断是什么类型用户
					if (firstName.equals("Auto")) {
						Linkaccount linkaccount = linkaccountRepository.findFirstByUserid(sourceId);
						if (linkaccount != null) {
							String other = linkaccount.getOther();
							if (other.equals("微信")) {
								firstName = "微信用户";
								//微信头像URL
								url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3155";
							} else if (other.equals("支付宝")) {
								firstName = "支付宝用户";
								//支付宝头像url
								url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3142";
							} else {
								firstName = "未注册用户";
								//未注册头像url
								url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3327";
							}
						} else {
							firstName = "未注册用户";
							//未注册头像url
							url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3327";
						}
					}else {
						if(jhiUserData.getImageUrl()==null || jhiUserData.getImageUrl().equals("")){
							url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3332";
						}else {
							Files files = filesRepository.findByIds(Long.parseLong(jhiUserData.getImageUrl()));
							url = files.getUrl();

						}
					}
				}else {
					firstName="已注册用户";
					//合并之后的用户头像url
					url = "http://app.yuanscore.com:8083/services/basic/api/public/getFiles/3327";
				}

				rewrite_ActAmoDTO.setUserUrl(url);
				rewrite_ActAmoDTO.setUserNickName(firstName);
				rewrite_ActAmoDTO.setPaymentAmount(incomeAmo);
				rewrite_ActAmoDTO.setTime(createTime);
				rewrite_ActAmoDTO.setTransFormAmount(transformationAmo);
				actAmoDTOList.add(rewrite_ActAmoDTO);
			}
		}else {
			return Result.suc("暂无活动流水!");
		}

		return Result.suc("访问成功",actAmoDTOList);
	}
    @Override
    public Result zhuanhuankeyongzhijin(String userId, Integer pageNum, Integer pageSize) {
        Merchant merchant = rewrite_MerchantRepository.findByUserid(userId);
        // 判断该用户是否是商家
        if (merchant == null) {
            return Result.fail("您不是商家!不能对此进行操作!");
        } else {
            List<Rewrite_ActivityPay2DTO> rewrite_ActivityPayDTOs = new ArrayList<Rewrite_ActivityPay2DTO>();
            List<ActivityPay> activityPaysList = activityPayRepository.findByUserIdAndType2(userId,pageNum * pageSize, pageSize);
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

}
