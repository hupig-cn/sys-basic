package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.*;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_MerchantRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_WithdrawalRepository;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_WithOneInfo;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_WithdrawalInfo;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_WithdrawalShowDTO;
import com.weisen.www.code.yjf.basic.service.impl.UserlinkuserServiceImpl;
import com.weisen.www.code.yjf.basic.service.mapper.WithdrawalMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_WithdrawalService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ActivityPayDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ActivitySerDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_WithdrawalMapper;
import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.service.util.SendCode;
import com.weisen.www.code.yjf.basic.service.util.TimeUtil;
import com.weisen.www.code.yjf.basic.service.util.WithdrawalConstant;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_WithdrawalServiceImpl implements Rewrite_WithdrawalService {

	private final Logger log = LoggerFactory.getLogger(UserlinkuserServiceImpl.class);

	private final Rewrite_WithdrawalRepository rewrite_withdrawalRepository;

	private final Rewrite_WithdrawalMapper rewrite_withdrawalMapper;

	private final WithdrawalMapper withdrawalMapper;

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

	private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

	private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

	private final Rewrite_UserbankcardRepository rewrite_UserbankcardRepository;

	private final Rewrite_WithdrawaldetailsRepository rewrite_WithdrawaldetailsRepository;

	private final Rewrite_MerchantRepository rewrite_MerchantRepository;

	private final ActivitySerRepository rewrite_ActivitySerRepository;

	private final ActivityPayRepository rewrite_ActivityPayRepository;

	public Rewrite_WithdrawalServiceImpl(Rewrite_WithdrawalRepository rewrite_withdrawalRepository,
			Rewrite_WithdrawalMapper rewrite_withdrawalMapper,
			Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository,
			Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository,
			Rewrite_UserassetsRepository rewrite_UserassetsRepository,
			Rewrite_UserbankcardRepository rewrite_UserbankcardRepository,
			Rewrite_WithdrawaldetailsRepository rewrite_WithdrawaldetailsRepository, WithdrawalMapper withdrawalMapper,
			Rewrite_LinkuserRepository rewrite_LinkuserRepository,
			Rewrite_MerchantRepository rewrite_MerchantRepository, ActivitySerRepository rewrite_ActivitySerRepository,
			ActivityPayRepository rewrite_ActivityPayRepository) {
		this.rewrite_withdrawalRepository = rewrite_withdrawalRepository;
		this.rewrite_withdrawalMapper = rewrite_withdrawalMapper;
		this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
		this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
		this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
		this.rewrite_UserbankcardRepository = rewrite_UserbankcardRepository;
		this.rewrite_WithdrawaldetailsRepository = rewrite_WithdrawaldetailsRepository;
		this.withdrawalMapper = withdrawalMapper;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
		this.rewrite_MerchantRepository = rewrite_MerchantRepository;
		this.rewrite_ActivitySerRepository = rewrite_ActivitySerRepository;
		this.rewrite_ActivityPayRepository = rewrite_ActivityPayRepository;
	}

	/**
	 * 提交提现记录
	 *
	 * @param rewrite_withdrawalDTO
	 * @return
	 */
	public synchronized Result insertWithdrawal(WithdrawalDTO rewrite_withdrawalDTO) {
		log.debug("insertWithdrawal" + rewrite_withdrawalDTO);
		if (!CheckUtils.checkObj(rewrite_withdrawalDTO))
			return Result.fail("提交信息异常");
		else if (!CheckUtils.checkString(rewrite_withdrawalDTO.getUserid()))
			return Result.fail("账号信息异常");

		Userassets userassets = rewrite_UserassetsRepository.findByUserid(rewrite_withdrawalDTO.getUserid());
		if (null == userassets) {
			return Result.fail("用户不存在");
		}
		int judg = new BigDecimal(rewrite_withdrawalDTO.getWithdrawalamount())
				.compareTo(new BigDecimal(userassets.getUsablebalance()));
		if (judg > 0) {
			return Result.fail("您的可用余额不足");
		}
		Linkuser Linkuser = rewrite_LinkuserRepository.findByUserid(rewrite_withdrawalDTO.getUserid());

		if (rewrite_withdrawalDTO.getGatheringway().equals(WithdrawalConstant.WECHAT) && null == Linkuser.getWechat()) {
			return Result.fail("请您绑定微信账号");
		} else if (rewrite_withdrawalDTO.getGatheringway().equals(WithdrawalConstant.ALI)
				&& null == Linkuser.getAlipay()) {
			return Result.fail("请您绑定支付宝账号");
		}

		BigDecimal useBan = new BigDecimal(userassets.getUsablebalance());
		BigDecimal frozen = new BigDecimal(userassets.getFrozenbalance());
		BigDecimal amount = new BigDecimal(rewrite_withdrawalDTO.getWithdrawalamount());
		// 更改资产状态
		userassets.setUsablebalance(useBan.subtract(amount).setScale(3).toString());
		userassets.setFrozenbalance(frozen.add(amount).setScale(3).toString());
		userassets = rewrite_UserassetsRepository.saveAndFlush(userassets);
		// 提现表
		Withdrawal withdrawal = withdrawalMapper.toEntity(rewrite_withdrawalDTO);
		withdrawal.setLogicdelete(false);
		withdrawal.setCreatedate(DateUtils.getDateForNow());
		withdrawal.setWithdrawaltype(WithdrawalConstant.IN_READY); // 提现中
		Withdrawal save = rewrite_withdrawalRepository.save(withdrawal);
		if (!CheckUtils.checkObj(save))
			return Result.fail();
		// 提现流水表
		Withdrawaldetails withdrawaldetails = new Withdrawaldetails();
		withdrawaldetails.setAmount(amount.toString());
		withdrawaldetails.setUserid(rewrite_withdrawalDTO.getUserid());
		withdrawaldetails.setAfteramount(userassets.getUsablebalance().toString());
		withdrawaldetails.setWithdrawalid(save.getId().toString());
		withdrawaldetails.setCreatedate(TimeUtil.getDate());
		withdrawaldetails.setType(WithdrawalConstant.PAY);
		withdrawaldetails.setTitle(WithdrawalConstant.getInfo(rewrite_withdrawalDTO.getGatheringway()));
		withdrawaldetails.setState(WithdrawalConstant.IN_READY);
		rewrite_WithdrawaldetailsRepository.save(withdrawaldetails);

//        String result = 
		SendCode.issue("13794340607", "用户注册", "0000");

		return Result.suc("提交成功");
	}

	/**
	 * 获取账号提现记录
	 *
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Result getWithdrawalByAccount(String userId, Integer pageNum, Integer pageSize) {
		if (!CheckUtils.checkString(userId))
			return Result.fail("账号信息异常");
		else if (!CheckUtils.checkPageInfo(pageNum, pageSize))
			return Result.fail("分页信息错误");
		else {
			List<Withdrawal> withdrawalByAccount = rewrite_withdrawalRepository.getWithdrawalByAccount(userId,
					pageNum * pageSize, pageSize);
			if (!CheckUtils.checkList(withdrawalByAccount))
				return Result.fail("获取数据为空");
			Integer countByAccount = rewrite_withdrawalRepository.getCountByAccount(userId);
			List<Rewrite_WithdrawalDTO> rewrite_withdrawalDTOS = rewrite_withdrawalMapper.toDto(withdrawalByAccount);
			return Result.suc("获取成功", rewrite_withdrawalDTOS, countByAccount);
		}
	}

	/**
	 * 后台获取所有提现记录
	 *
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Result getWithdrawals(Integer pageNum, Integer pageSize, String type) {
		System.out.println(pageNum + pageSize + "");
		if (!CheckUtils.checkPageInfo(pageNum, pageSize))
			return Result.fail("分页信息异常");
		else {
			List<Withdrawal> withdrawals = rewrite_withdrawalRepository.getWithdrawals(type, pageNum * pageSize,
					pageSize);
			if (!CheckUtils.checkList(withdrawals))
				return Result.fail("数据库异常");

			List<Rewrite_WithdrawalShowDTO> list = new ArrayList<>();
			withdrawals.forEach(x -> {
				Linkuser linkuser = rewrite_LinkuserRepository.findByUserid(x.getUserid());
				Rewrite_WithdrawalShowDTO rewrite_WithdrawalShowDTO = new Rewrite_WithdrawalShowDTO();
				rewrite_WithdrawalShowDTO.setId(x.getId().toString());
				rewrite_WithdrawalShowDTO.setUserAccount(linkuser.getPhone());
				rewrite_WithdrawalShowDTO.setUserName(linkuser.getName());
				rewrite_WithdrawalShowDTO.setAmount(x.getWithdrawalamount());
				rewrite_WithdrawalShowDTO.setStartTime(x.getCreatedate());
				rewrite_WithdrawalShowDTO.setEndTime(x.getModifierdate());
				rewrite_WithdrawalShowDTO.setIncomeWay(WithdrawalConstant.getInfo(x.getGatheringway())); // 提现方式
				rewrite_WithdrawalShowDTO.setState(x.getWithdrawaltype()); // 提现状态
				if (x.getGatheringway().equals(WithdrawalConstant.BANK_CARD)) {
					Optional<Userbankcard> op = rewrite_UserbankcardRepository
							.findById(Long.valueOf(x.getBankcardid()));
					if (op.isPresent()) {
						Userbankcard userbankcard = op.get();
						rewrite_WithdrawalShowDTO.setIncomeAccount(userbankcard.getBankcard()); // 银行卡卡号（银行账号）
						rewrite_WithdrawalShowDTO.setIncomeName(userbankcard.getRealname()); // 银行卡姓名
						rewrite_WithdrawalShowDTO.setBelongBankName(userbankcard.getBanktype()); // 所属银行（开户银行）
					} else {
						rewrite_WithdrawalShowDTO.setIncomeAccount("用户银行卡不存在");
					}
				} else if (x.getGatheringway().equals(WithdrawalConstant.ALI)) {
					rewrite_WithdrawalShowDTO.setIncomeAccount(linkuser.getAlipay());
					rewrite_WithdrawalShowDTO.setIncomeName(linkuser.getAlipayname());
				} else if (x.getGatheringway().equals(WithdrawalConstant.WECHAT)) {
					rewrite_WithdrawalShowDTO.setIncomeAccount(linkuser.getWechat());
					rewrite_WithdrawalShowDTO.setIncomeName(linkuser.getWechatname());
				}
				list.add(rewrite_WithdrawalShowDTO);
			});

			Long count = rewrite_withdrawalRepository.countAllByWithdrawaltype(type);
			return Result.suc("获取成功", list, count.intValue());
		}
	}

	/**
	 * 后台审核提现记录
	 *
	 * @param withdrawalid
	 * @param type
	 * @return
	 */
	public synchronized Result auditWithdrawal(Long withdrawalid, String type, String content) {
		log.debug("auditWithdrawal", withdrawalid, type);
		if (!CheckUtils.checkLongByZero(withdrawalid))
			return Result.fail("审核数据异常");

		Optional<Withdrawal> op = rewrite_withdrawalRepository.findById(withdrawalid);
		if (!op.isPresent()) {
			return Result.fail("提现记录不存在");
		} else if (op.isPresent() && !op.get().getWithdrawaltype().equals("1")) {
			return Result.fail("该提现已经被审核过了");
		}

		if (type.equals(WithdrawalConstant.SUCCESS)) {
			Withdrawal withdrawal = rewrite_withdrawalRepository.getOne(withdrawalid);
			Userassets userassets = rewrite_UserassetsRepository.findByUserid(withdrawal.getUserid());

			BigDecimal balance = new BigDecimal(userassets.getBalance());
			BigDecimal frozen = new BigDecimal(userassets.getFrozenbalance());
			BigDecimal amount = new BigDecimal(withdrawal.getWithdrawalamount());

			if (balance.subtract(amount).compareTo(new BigDecimal("0")) < 0) { // 用户余额减掉后如果是负数的话
				return Result.fail("用户数据库资金出现异常");
			}

			withdrawal.setWithdrawaltype(WithdrawalConstant.ALREADY);
			withdrawal.setModifierdate(TimeUtil.getDate());
			rewrite_withdrawalRepository.saveAndFlush(withdrawal);

			Withdrawaldetails withdrawaldetails = rewrite_WithdrawaldetailsRepository
					.findByWithdrawalid(withdrawal.getId().toString());
			withdrawaldetails.setState(WithdrawalConstant.ALREADY);
			withdrawaldetails.setModifierdate(TimeUtil.getDate());
			rewrite_WithdrawaldetailsRepository.save(withdrawaldetails);

			userassets.setBalance(balance.subtract(amount).setScale(3).toString());
			userassets.setFrozenbalance(frozen.subtract(amount).setScale(3).toString());
			rewrite_UserassetsRepository.saveAndFlush(userassets);

			// 提现成功需要产生流水，优化时间：2019年11月12日16:57:17
			Receiptpay receiptpay = new Receiptpay();
			receiptpay.setPayway(OrderConstant.BALANCE_PAY); // 扣了余额
			receiptpay.setDealtype(ReceiptpayConstant.WITHDRAWAL_PAY); // 余额支出
			receiptpay.setUserid(op.get().getUserid());
			receiptpay.setAmount(amount); // 提现金额
			receiptpay.setDealstate(ReceiptpayConstant.PAY); // 支出
			receiptpay.setCreatedate(TimeUtil.getDate());
			receiptpay.setBalance(new BigDecimal(userassets.getBalance()));
			receiptpay.setUseablebalance(new BigDecimal(userassets.getUsablebalance()));
			receiptpay.setFreezebalance(new BigDecimal(userassets.getFrozenbalance()));
			receiptpay.setCoupon(new BigDecimal(userassets.getCouponsum()));
			receiptpay.setIntegral(new BigDecimal(userassets.getIntegral()));
			receiptpay.setExplain("提现" + amount + "元");
			rewrite_ReceiptpayRepository.save(receiptpay);

		} else if (type.equals(WithdrawalConstant.FAIL_S)) { // 审批不通过
			// 提现表
			Withdrawal withdrawal = rewrite_withdrawalRepository.getOne(withdrawalid);
			withdrawal.setWithdrawaltype(WithdrawalConstant.FAIL);
			withdrawal.setModifierdate(TimeUtil.getDate());
			rewrite_withdrawalRepository.saveAndFlush(withdrawal);
			// 提现流水表
			Withdrawaldetails withdrawaldetails = rewrite_WithdrawaldetailsRepository
					.findByWithdrawalid(withdrawal.getId().toString());
			withdrawaldetails.setState(WithdrawalConstant.FAIL);
			withdrawaldetails.setModifierdate(TimeUtil.getDate());
			rewrite_WithdrawaldetailsRepository.save(withdrawaldetails);
			// 更改我的资产
			Userassets userassets = rewrite_UserassetsRepository.findByUserid(withdrawaldetails.getUserid());

			BigDecimal usebalance = new BigDecimal(userassets.getUsablebalance());
			BigDecimal frozen = new BigDecimal(userassets.getFrozenbalance());
			BigDecimal amount = new BigDecimal(withdrawaldetails.getAmount());

			userassets.setUsablebalance(usebalance.add(amount).setScale(3).toString());
			userassets.setFrozenbalance(frozen.subtract(amount).setScale(3).toString());
			userassets = rewrite_UserassetsRepository.saveAndFlush(userassets);

			Withdrawaldetails failWithdrawaldetails = new Withdrawaldetails();
			failWithdrawaldetails.setUserid(withdrawaldetails.getUserid());
			failWithdrawaldetails.setState(WithdrawalConstant.FAIL);
			failWithdrawaldetails.setWithdrawalway(withdrawaldetails.getWithdrawalway());
			failWithdrawaldetails.setAmount(amount.toString());
			failWithdrawaldetails.setAfteramount(userassets.getUsablebalance());
			failWithdrawaldetails.setCreatedate(TimeUtil.getDate());
			failWithdrawaldetails.setWithdrawalid(withdrawal.getId().toString());
			failWithdrawaldetails.setType(WithdrawalConstant.INCOME);
			failWithdrawaldetails.setTitle("提现失败，资金回退");
			failWithdrawaldetails.setOther(content); // 审核失败原因 后台操作填写
			rewrite_WithdrawaldetailsRepository.save(failWithdrawaldetails);

		} else {
			return Result.fail("请传入正确的参数");
		}

		return Result.suc("成功");
	}

	// 获取用户提现信息
	@Override
	public Result getUserInfo(Long id) {
		List<Receiptpay> rec = rewrite_ReceiptpayRepository.findAllByUseridAndDealtype(id.toString(),
				ReceiptpayConstant.BALANCE_INCOME_DIR);
		Optional<?> sum = rec.stream().map(x -> x.getAmount()).reduce(BigDecimal::add);

		// 可提现
//        List<Withdrawal> reday = rewrite_withdrawalRepository.findAllByUseridAndtyOrWithdrawaltype(id.toString(), WithdrawalConstant.READY);
		// 冻结
//        List<Withdrawal> FROZEN = rewrite_withdrawalRepository.findAllByUseridAndtyOrWithdrawaltype(id.toString(), WithdrawalConstant.READY);
//        Optional sumFROZEN = FROZEN.stream().map(x -> new BigDecimal(x.getWithdrawalamount())).reduce(BigDecimal::add);
		// 提现中
		List<Withdrawal> IN_READY = rewrite_withdrawalRepository.findAllByUseridAndWithdrawaltype(id.toString(),
				WithdrawalConstant.IN_READY);
		Optional<?> sumIN_READY = IN_READY.stream().map(x -> new BigDecimal(x.getWithdrawalamount()))
				.reduce(BigDecimal::add);
		// 已提现
		List<Withdrawal> ALREADY = rewrite_withdrawalRepository.findAllByUseridAndWithdrawaltype(id.toString(),
				WithdrawalConstant.ALREADY);
		Optional<?> sumALREADY = ALREADY.stream().map(x -> new BigDecimal(x.getWithdrawalamount()))
				.reduce(BigDecimal::add);

		long count = rewrite_UserlinkuserRepository.countAllByRecommendid(id.toString());

		Userassets userassets = rewrite_UserassetsRepository.findByUserid(id.toString());

		Rewrite_WithdrawalInfo rewrite_WithdrawalInfo = new Rewrite_WithdrawalInfo(sum.toString(),
				userassets.getFrozenbalance(), sumIN_READY.toString(), userassets.getUsablebalance(),
				sumALREADY.toString(), String.valueOf(count));
		return Result.suc("成功", rewrite_WithdrawalInfo);
	}

	// 获取一条提现数据详细信息
	@Override
	public Result getWithdrawalInfo(Long withdrawalId) {
		Withdrawaldetails withdrawaldetails = rewrite_WithdrawaldetailsRepository.getOne(withdrawalId);
		if (null == withdrawaldetails) {
			Result.suc("空数据", null);
		}
		Withdrawal withdrawal = rewrite_withdrawalRepository.getOne(Long.valueOf(withdrawaldetails.getWithdrawalid()));
		if (null == withdrawal) {
			Result.suc("空数据", null);
		}

		Rewrite_WithOneInfo rewrite_WithOneInfo = new Rewrite_WithOneInfo();

		if (withdrawal.getBankcardid() != null && !"".equals(withdrawal.getBankcardid())) {
			Userbankcard userbankcard = rewrite_UserbankcardRepository.getOne(Long.valueOf(withdrawal.getBankcardid()));
			rewrite_WithOneInfo.setBankuser(userbankcard.getRealname()); // 银行卡姓名
			rewrite_WithOneInfo.setBankaccount(userbankcard.getBankcard()); // 银行卡卡号（银行账号）
			rewrite_WithOneInfo.setBankname(userbankcard.getBanktype()); // 所属银行（开户银行）
		}
		Linkuser Linkuser = rewrite_LinkuserRepository.findByUserid(withdrawal.getUserid());
		if (withdrawal.getGatheringway().equals(WithdrawalConstant.ALI)) {
			rewrite_WithOneInfo.setAlipay(Linkuser.getAlipay());
			rewrite_WithOneInfo.setAlipayName(Linkuser.getAlipayname());
		} else if (withdrawal.getGatheringway().equals(WithdrawalConstant.WECHAT)) {
			rewrite_WithOneInfo.setWechat(Linkuser.getWechat());
			rewrite_WithOneInfo.setWechatName(Linkuser.getWechatname());
		}

		rewrite_WithOneInfo.setStime(withdrawaldetails.getCreatedate());
		rewrite_WithOneInfo.setAmount(withdrawaldetails.getAmount());
		rewrite_WithOneInfo.setType(withdrawal.getGatheringway());
		rewrite_WithOneInfo.setStatus(withdrawaldetails.getState());
		rewrite_WithOneInfo.setEtime(withdrawaldetails.getModifierdate());
		rewrite_WithOneInfo.setExtro(withdrawaldetails.getOther());

		return Result.suc("成功", rewrite_WithOneInfo);
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
			List<ActivityPay> activityPaysList = rewrite_ActivityPayRepository.findByUserIdAndType(userId,
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
}
