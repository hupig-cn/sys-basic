package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserassetsService;
import com.weisen.www.code.yjf.basic.service.dto.ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.*;
import com.weisen.www.code.yjf.basic.service.mapper.ReceiptpayMapper;
import com.weisen.www.code.yjf.basic.service.util.OrderConstant;
import com.weisen.www.code.yjf.basic.service.util.ProfitConstant;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.service.util.TimeUtil;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class Rewrite_ReceiptpayServiceImpl implements Rewrite_ReceiptpayService {

	private final Logger log = LoggerFactory.getLogger(Rewrite_ReceiptpayServiceImpl.class);

	private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

	private final ReceiptpayMapper receiptpayMapper;

	private final Rewrite_UserassetsService rewrite_UserassetsService;

	private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

	private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

	public Rewrite_ReceiptpayServiceImpl(Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository,
			ReceiptpayMapper receiptpayMapper,Rewrite_UserassetsService rewrite_UserassetsService,
                                         Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository,
                                         Rewrite_LinkuserRepository rewrite_LinkuserRepository) {
		this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
		this.receiptpayMapper = receiptpayMapper;
		this.rewrite_UserassetsService = rewrite_UserassetsService;
		this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
		this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
	}

	// 查询商家今日收入 (商家端)
	@Override
	public Rewrite_PriceDTO selectTodayIncome(Long userId) {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String startTime = today + " 00:00:00";
		String endTime = today + " 23:59:59";
		List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
				startTime, endTime, ReceiptpayConstant.BALANCE_INCOME);
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
        Rewrite_PriceDTO rewrite_PriceDTO = new Rewrite_PriceDTO();
		String today = new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
		String startTime = today + " 00:00:00";
		String endTime = today + " 23:59:59";
		List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
				startTime, endTime, ReceiptpayConstant.BALANCE_INCOME);
        Optional thisPrice = receiptpay.stream().map(Receiptpay::getAmount).reduce(BigDecimal::add);
        if(thisPrice.isPresent()){
            rewrite_PriceDTO.setPrice(thisPrice.get().toString());
        }else{
            rewrite_PriceDTO.setPrice("0");
        }
		return rewrite_PriceDTO;
	}

    /**
     * 创建收支明细
     * @param userId
     * @param type
     * @param sourcer
     * @param sourcerId
     * @param amout
     * @return
     */
    public Result createReceiptpay(String userId, String type, String sourcer, String sourcerId, String amout) {
        return null;
    }

    // 查询商家各项详细收益
    @Override
    public Rewrite_MercProfitDto getProfitInfo(Long userId) {
        Rewrite_MercProfitDto rewrite_MercProfitDto = new Rewrite_MercProfitDto();

        Rewrite_UserPriceDTO rewrite_PriceDTO = rewrite_UserassetsService.findUserBalance(userId);
        if(rewrite_PriceDTO == null){
            return null;
        }
        rewrite_MercProfitDto.setBalance(rewrite_PriceDTO.getBalance());

        Rewrite_PriceDTO pricere = selectYesterday(userId);
        rewrite_MercProfitDto.setYestoday_income(new BigDecimal(pricere.getPrice()));

        String today = new SimpleDateFormat("yyyy-MM").format(new Date());
        String startTime =  today + "-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = TimeUtil.getPreMonth(sdf.format(new Date())) + "-01 00:00:00";
        // 当月销售
        List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            startTime, endTime, ReceiptpayConstant.BALANCE_INCOME);
        log.debug("getProfitInfo--------------" +receiptpay.toString());
        Optional thisPrice = receiptpay.stream().map(Receiptpay::getAmount).reduce(BigDecimal::add);
        if(thisPrice.isPresent()){
            rewrite_MercProfitDto.setThis_month(new BigDecimal(thisPrice.get().toString()));
        }else{
            rewrite_MercProfitDto.setThis_month(new BigDecimal("0"));
        }

        String lastTime =TimeUtil.getLastMonth(sdf.format(new Date())) + "-01 00:00:00";
        //上个月
        List<Receiptpay> lastreceiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            lastTime, startTime, ReceiptpayConstant.BALANCE_INCOME);
        Optional lastPrice = lastreceiptpay.stream().map(Receiptpay::getAmount).reduce(BigDecimal::add);
        if(lastPrice.isPresent()){
            rewrite_MercProfitDto.setLast_month(new BigDecimal(lastPrice.get().toString()));
        }else{
            rewrite_MercProfitDto.setLast_month(new BigDecimal("0"));
        }

        // 总销售额
        List<Receiptpay> allreceiptpay = rewrite_ReceiptpayRepository.findAllByUseridAndDealtype(userId.toString(),ReceiptpayConstant.BALANCE_INCOME);
        BigDecimal totalprice = new BigDecimal("0");
        for (Receiptpay list : allreceiptpay) {
            totalprice = totalprice.add(list.getAmount());
        }
        rewrite_MercProfitDto.setAmount(totalprice);

        return rewrite_MercProfitDto;
    }

    // 查询用户的各项收益
    @Override
    public Result getUserPrifitInfo(Long userId) {
        Rewrite_MercProfitDto rewrite_MercProfitDto = getProfitInfo(userId);

        String today = new SimpleDateFormat("yyyy-MM").format(new Date());
        String startTime =  today + "-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = TimeUtil.getPreMonth(sdf.format(new Date())) + "-01 00:00:00";
        // 当月分销
        List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            startTime, endTime, ReceiptpayConstant.BALANCE_INCOME_DIR);
        BigDecimal price = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            price = price.add(list.getAmount());
        }
        String lastTime =TimeUtil.getLastMonth(sdf.format(new Date())) + "-01 00:00:00";
        //上个月
        List<Receiptpay> lastreceiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            lastTime, startTime, ReceiptpayConstant.BALANCE_INCOME_DIR);
        BigDecimal lastprice = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            lastprice = lastprice.add(list.getAmount());
        }
        // 总销分销
        List<Receiptpay> allreceiptpay = rewrite_ReceiptpayRepository.findAllByUseridAndDealtype(userId.toString(),ReceiptpayConstant.BALANCE_INCOME_DIR);
        BigDecimal totalprice = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            totalprice = totalprice.add(list.getAmount());
        }
        // 昨日
        String retoday = new SimpleDateFormat("yyyy-MM-dd")
            .format(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
        String restartTime = retoday + " 00:00:00";
        String reendTime = retoday + " 23:59:59";
        List<Receiptpay> lareceiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndTime(userId.toString(),
            restartTime, reendTime, ReceiptpayConstant.BALANCE_INCOME_DIR);
        BigDecimal laprice = new BigDecimal("0");
        for (Receiptpay list : receiptpay) {
            laprice = laprice.add(list.getAmount());
        }

        rewrite_MercProfitDto.setYestoday_income(rewrite_MercProfitDto.getYestoday_income().add(laprice));
        rewrite_MercProfitDto.setThis_month(rewrite_MercProfitDto.getThis_month().add(price));
        rewrite_MercProfitDto.setLast_month(rewrite_MercProfitDto.getLast_month().add(lastprice));
        rewrite_MercProfitDto.setAmount(rewrite_MercProfitDto.getAmount().add(totalprice));
        return Result.suc("成功",rewrite_MercProfitDto);
    }

    // 获取提现记录
	@Override
	public List<ReceiptpayDTO> selcetMoneyRecord(Long userId) {
		List<Receiptpay> receiptpay = rewrite_ReceiptpayRepository.getReceiptpayByUseridAndDealtype(userId.toString(),
				ProfitConstant.FIVE);
		return receiptpayMapper.toDto(receiptpay);
	}

    // 查询用户的各项收益(推广端)
	@Override
	public Rewrite_ProfitDTO getUserProfit(String userid) {
		Rewrite_ProfitDTO rewrite_ProfitDTO = new Rewrite_ProfitDTO();

        String today = new SimpleDateFormat("yyyy-MM-dd")
            .format(new Date());
        String startTime = today + " 00:00:00";
        String endTime = today + " 23:59:59";

        // 用户id
        rewrite_ProfitDTO.setId(Long.valueOf(userid));
        // 今日推荐人数量
        Long todayCount = rewrite_UserlinkuserRepository.countAllByCreatedateBetweenAndRecommendid(startTime,endTime, userid.toString());
        rewrite_ProfitDTO.setTodayrecommend(todayCount.toString());

        // 今日分销的收入数量
        List<Receiptpay> todayReceiptpay = rewrite_ReceiptpayRepository.
            findInfoByTime(userid,startTime,endTime,ReceiptpayConstant.BALANCE_INCOME_DIR,ReceiptpayConstant.BALANCE_INCOME_PER);
        Optional todayPrice = todayReceiptpay.stream().map(Receiptpay::getAmount).reduce(BigDecimal::add);
        if(todayPrice.isPresent()){
            rewrite_ProfitDTO.setTodayprofit(todayPrice.get().toString());
        }else{
            rewrite_ProfitDTO.setTodayprofit("0");
        }


        // 昨日的分销收入
        String retoday = new SimpleDateFormat("yyyy-MM-dd")
            .format(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
        String restartTime = retoday + " 00:00:00";
        String reendTime = retoday + " 23:59:59";

        List<Receiptpay> lastReceiptpay = rewrite_ReceiptpayRepository.
            findInfoByTime(userid,restartTime,reendTime,ReceiptpayConstant.BALANCE_INCOME_DIR,ReceiptpayConstant.BALANCE_INCOME_PER);
        Optional lastPrice = lastReceiptpay.stream().map(Receiptpay::getAmount).reduce(BigDecimal::add);
        if(lastPrice.isPresent()){
            rewrite_ProfitDTO.setTodaylastprofit(lastPrice.get().toString());
        }else{
            rewrite_ProfitDTO.setTodaylastprofit("0");
        }

        // 本月的分销收入
        String todayMonth = new SimpleDateFormat("yyyy-MM").format(new Date());
        String toMonthTime =  todayMonth + "-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nextTime = TimeUtil.getPreMonth(sdf.format(new Date())) + "-01 00:00:00";

        List<Receiptpay> thisMonthReceiptpay = rewrite_ReceiptpayRepository.
            findInfoByTime(userid,toMonthTime,nextTime,ReceiptpayConstant.BALANCE_INCOME_DIR,ReceiptpayConstant.BALANCE_INCOME_PER);
        Optional thisMonth = thisMonthReceiptpay.stream().map(Receiptpay::getAmount).reduce(BigDecimal::add);
        if(thisMonth.isPresent()){
            rewrite_ProfitDTO.setMonthprofit(thisMonth.get().toString());
        }else{
            rewrite_ProfitDTO.setMonthprofit("0");
        }

        // 上月的分销收入
        String lastMonthTime =TimeUtil.getLastMonth(sdf.format(new Date())) + "-01 00:00:00";

        List<Receiptpay> lastMonthReceiptpay = rewrite_ReceiptpayRepository.
            findInfoByTime(userid,lastMonthTime,toMonthTime,ReceiptpayConstant.BALANCE_INCOME_DIR,ReceiptpayConstant.BALANCE_INCOME_PER);
        Optional lastMonth = lastMonthReceiptpay.stream().map(Receiptpay::getAmount).reduce(BigDecimal::add);
        if(lastMonth.isPresent()){
            rewrite_ProfitDTO.setMonthlastprofit(lastMonth.get().toString());
        }else{
            rewrite_ProfitDTO.setMonthlastprofit("0");
        }

        // 总分销收入
        List<Receiptpay> allReceiptpay = rewrite_ReceiptpayRepository.getAllInfo(userid,ReceiptpayConstant.BALANCE_INCOME_DIR,ReceiptpayConstant.BALANCE_INCOME_PER);
        Optional allprice = allReceiptpay.stream().map(Receiptpay::getAmount).reduce(BigDecimal::add);
        if(allprice.isPresent()){
            rewrite_ProfitDTO.setTotalprofit(allprice.get().toString());
        }else{
            rewrite_ProfitDTO.setTotalprofit("0");
        }

         // 总推荐人数
        Long allCount = rewrite_UserlinkuserRepository.countAllByRecommendid(userid.toString());
        rewrite_ProfitDTO.setTotalrecommend(allCount.toString());

		return rewrite_ProfitDTO;
    }

    // 查询用户商家端收益列表倒叙(意哥需求)
    @Override
    public Result findMerchantProfitInfo(String userId,int startPage,int pageSize) {
        List<Receiptpay> list = rewrite_ReceiptpayRepository.getAllByMerchantAndType
            (userId,ReceiptpayConstant.BALANCE_INCOME,startPage * pageSize,pageSize);
        List<Rewrite_MerchantShow> merList = new ArrayList<>();

        receiptpayMapper.toDto(list).forEach(x -> {
            Rewrite_MerchantShow<Rewrite_MerchantShow> rewrite_MerchantShow = new Rewrite_MerchantShow();
            rewrite_MerchantShow.setTime(x.getCreatedate().substring(0,7));
            x.setCreatedate(x.getCreatedate().substring(6,x.getCreatedate().length()));
//            x.setOther(OrderConstant.getpayInfo(x.getPayway())+" - 收款"+Float.parseFloat(x.getAmount().toString())+"元");
            x.setOther("支付"+x.getBenefit()+"元，收款"+x.getAmount()+"元("+OrderConstant.getpayInfo2(x.getPayway())+")");
            rewrite_MerchantShow.setSingleClass(x);
            merList.add(rewrite_MerchantShow);
        });

        Map<String,List<Rewrite_MerchantShow>> map = merList.stream().
            collect(Collectors.groupingBy(Rewrite_MerchantShow::getTime));

        List<Rewrite_MerchantShow> showList = new ArrayList<>();
        map.forEach((x,y) ->{
            Rewrite_MerchantShow<Rewrite_MerchantShow> rewrite_MerchantShow = new Rewrite_MerchantShow();
            rewrite_MerchantShow.setTime(x);
            rewrite_MerchantShow.setList(y);
            showList.add(rewrite_MerchantShow);
        });

        return Result.suc("成功",showList);
    }

    // 根据用户账号查询详细收益（分页，暂时没有多条件）admin
    @Override
    public Result findByUserAccountOrSomething(String userAccount,int pageIndex,int pageSize) {
        Linkuser linkuser = rewrite_LinkuserRepository.findByPhone(userAccount);
        if (linkuser == null) {
            return Result.fail("用户不存在");
        }
        List<Receiptpay> list = rewrite_ReceiptpayRepository.getAllByUserSomething(
            linkuser.getUserid(), pageIndex * pageSize,pageSize);

        List<Rewrite_UserReceiptpayDTO> userList = new ArrayList<>();
        list.forEach(x -> {
            Rewrite_UserReceiptpayDTO rewrite_UserReceiptpayDTO = new Rewrite_UserReceiptpayDTO();
            rewrite_UserReceiptpayDTO.setId(x.getId().toString());
            rewrite_UserReceiptpayDTO.setAmount(x.getAmount().toString());
            rewrite_UserReceiptpayDTO.setUserAccount(linkuser.getPhone());
            rewrite_UserReceiptpayDTO.setCreateTime(x.getCreatedate());
            rewrite_UserReceiptpayDTO.setOther("-");
            rewrite_UserReceiptpayDTO.setOrderid("-");
            rewrite_UserReceiptpayDTO.setRelationOrder("-");
            rewrite_UserReceiptpayDTO.setDescribe("-");
            userList.add(rewrite_UserReceiptpayDTO);
        });

        Long count = rewrite_ReceiptpayRepository.countAllByUserid(linkuser.getUserid());
        return Result.suc("成功",userList,count.intValue());
    }

}
