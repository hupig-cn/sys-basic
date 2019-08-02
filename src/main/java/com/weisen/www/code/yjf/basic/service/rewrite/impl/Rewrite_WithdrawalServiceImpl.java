package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.domain.*;
import com.weisen.www.code.yjf.basic.repository.*;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_WithdrawalRepository;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_WithOneInfo;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_WithdrawalInfo;
import com.weisen.www.code.yjf.basic.service.mapper.WithdrawalMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_WithdrawalService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_WithdrawalMapper;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.service.util.WithdrawalConstant;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_WithdrawalServiceImpl implements Rewrite_WithdrawalService {

    private final Rewrite_WithdrawalRepository rewrite_withdrawalRepository;

    private final Rewrite_WithdrawalMapper rewrite_withdrawalMapper;

    private final WithdrawalMapper withdrawalMapper;

    private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

    private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

    private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

    private final Rewrite_UserbankcardRepository rewrite_UserbankcardRepository;

    private final Rewrite_WithdrawaldetailsRepository rewrite_WithdrawaldetailsRepository;

    public Rewrite_WithdrawalServiceImpl(Rewrite_WithdrawalRepository rewrite_withdrawalRepository, Rewrite_WithdrawalMapper rewrite_withdrawalMapper,
                                         Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository, Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository,
                                         Rewrite_UserassetsRepository rewrite_UserassetsRepository, Rewrite_UserbankcardRepository rewrite_UserbankcardRepository
                                        , Rewrite_WithdrawaldetailsRepository rewrite_WithdrawaldetailsRepository,WithdrawalMapper withdrawalMapper) {
        this.rewrite_withdrawalRepository = rewrite_withdrawalRepository;
        this.rewrite_withdrawalMapper = rewrite_withdrawalMapper;
        this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
        this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
        this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
        this.rewrite_UserbankcardRepository = rewrite_UserbankcardRepository;
        this.rewrite_WithdrawaldetailsRepository = rewrite_WithdrawaldetailsRepository;
        this.withdrawalMapper = withdrawalMapper;
    }

    /**
     * 提交提现记录
     *
     * @param rewrite_withdrawalDTO
     * @return
     */
    public Result insertWithdrawal(WithdrawalDTO rewrite_withdrawalDTO) {
        if (!CheckUtils.checkObj(rewrite_withdrawalDTO))
            return Result.fail("提交信息异常");
        else if (!CheckUtils.checkString(rewrite_withdrawalDTO.getUserid()))
            return Result.fail("账号信息异常");

        Userassets userassets = rewrite_UserassetsRepository.findByUserid(rewrite_withdrawalDTO.getUserid());
        if(null == userassets){
            return Result.fail("用户不存在");
        }
        int judg = new BigDecimal(rewrite_withdrawalDTO.getWithdrawalamount()).compareTo(new BigDecimal(userassets.getUsablebalance()));
        if(judg > 0 ){
            return Result.fail("您的可用余额不足");
        }

        BigDecimal useBan = new BigDecimal(userassets.getUsablebalance());
        BigDecimal frozen = new BigDecimal(userassets.getFrozenbalance());
        BigDecimal amount = new BigDecimal(rewrite_withdrawalDTO.getWithdrawalamount());
        // 更改资产状态
        userassets.setUsablebalance(useBan.subtract(amount).setScale(3).toString());
        userassets.setFrozenbalance(frozen.add(amount).setScale(3).toString());
        userassets = rewrite_UserassetsRepository.saveAndFlush(userassets);
        //提现表
        Withdrawal withdrawal = withdrawalMapper.toEntity(rewrite_withdrawalDTO);
        withdrawal.setLogicdelete(false);
        withdrawal.setCreatedate(DateUtils.getDateForNow());
        withdrawal.setWithdrawaltype(WithdrawalConstant.IN_READY); // 提现中
        Withdrawal save = rewrite_withdrawalRepository.save(withdrawal);
        if (!CheckUtils.checkObj(save))
            return Result.fail();
        //提现流水表
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
            List<Withdrawal> withdrawalByAccount = rewrite_withdrawalRepository.getWithdrawalByAccount(userId, pageNum * pageSize, pageSize);
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
    public Result getWithdrawals(Integer pageNum, Integer pageSize) {
        System.out.println(pageNum + pageSize + "");
        if (!CheckUtils.checkPageInfo(pageNum, pageSize))
            return Result.fail("分页信息异常");
        else {
            List<Withdrawal> withdrawals = rewrite_withdrawalRepository.getWithdrawals(pageNum * pageSize, pageSize);
            if (!CheckUtils.checkList(withdrawals))
                return Result.fail("数据库异常");
            List<Rewrite_WithdrawalDTO> rewrite_withdrawalDTOS = rewrite_withdrawalMapper.toDto(withdrawals);
            Integer count = rewrite_withdrawalRepository.getCountByAccount(null);
            return Result.suc("获取成功", rewrite_withdrawalDTOS, count);
        }
    }

    /**
     * 后台审核提现记录
     *
     * @param withdrawalid
     * @param type
     * @return
     */
    public Result auditWithdrawal(Long withdrawalid, String type,String content) {
        if (!CheckUtils.checkLongByZero(withdrawalid))
            return Result.fail("审核数据异常");

        if(type.equals(WithdrawalConstant.SUCCESS)){
            Withdrawal withdrawal = rewrite_withdrawalRepository.getOne(withdrawalid);
            withdrawal.setWithdrawaltype(WithdrawalConstant.ALREADY);
            withdrawal.setModifierdate(TimeUtil.getDate());
            rewrite_withdrawalRepository.saveAndFlush(withdrawal);

            Withdrawaldetails withdrawaldetails = rewrite_WithdrawaldetailsRepository.findByWithdrawalid(withdrawal.getId().toString());
            withdrawaldetails.setState(WithdrawalConstant.ALREADY);
            withdrawaldetails.setModifierdate(TimeUtil.getDate());
            rewrite_WithdrawaldetailsRepository.save(withdrawaldetails);

            Userassets userassets = rewrite_UserassetsRepository.findByUserid(withdrawaldetails.getUserid());

            BigDecimal balance = new BigDecimal(userassets.getBalance());
            BigDecimal frozen = new BigDecimal(userassets.getFrozenbalance());
            BigDecimal amount = new BigDecimal(withdrawaldetails.getAmount());

            userassets.setBalance(balance.subtract(amount).setScale(3).toString());
            userassets.setFrozenbalance(frozen.subtract(amount).setScale(3).toString());
            rewrite_UserassetsRepository.saveAndFlush(userassets);

        }else if(type.equals(WithdrawalConstant.FAIL_S)){  // 审批不通过
            // 提现表
            Withdrawal withdrawal = rewrite_withdrawalRepository.getOne(withdrawalid);
            withdrawal.setWithdrawaltype(WithdrawalConstant.FAIL);
            withdrawal.setModifierdate(TimeUtil.getDate());
            rewrite_withdrawalRepository.saveAndFlush(withdrawal);
            //提现流水表
            Withdrawaldetails withdrawaldetails = rewrite_WithdrawaldetailsRepository.findByWithdrawalid(withdrawal.getId().toString());
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
            failWithdrawaldetails.setType(WithdrawalConstant.INCOME);
            failWithdrawaldetails.setTitle("提现失败，资金回退");
            failWithdrawaldetails.setOther(content); // 审核失败原因  后台操作填写
            rewrite_WithdrawaldetailsRepository.save(failWithdrawaldetails);

        }else{
            return Result.fail("请传入正确的参数");
        }

        return Result.suc("成功");
    }

    // 获取用户提现信息
    @Override
    public Result getUserInfo(Long id) {
        List<Receiptpay> rec= rewrite_ReceiptpayRepository.findAllByUseridAndDealtype(id.toString(), ReceiptpayConstant.BALANCE_INCOME_DIR);
        Optional sum = rec.stream().map(x -> x.getAmount()).reduce(BigDecimal::add);

        // 可提现
//        List<Withdrawal> reday = rewrite_withdrawalRepository.findAllByUseridAndtyOrWithdrawaltype(id.toString(), WithdrawalConstant.READY);
        // 冻结
//        List<Withdrawal> FROZEN = rewrite_withdrawalRepository.findAllByUseridAndtyOrWithdrawaltype(id.toString(), WithdrawalConstant.READY);
//        Optional sumFROZEN = FROZEN.stream().map(x -> new BigDecimal(x.getWithdrawalamount())).reduce(BigDecimal::add);
        // 提现中
        List<Withdrawal> IN_READY = rewrite_withdrawalRepository.findAllByUseridAndWithdrawaltype(id.toString(), WithdrawalConstant.IN_READY);
        Optional sumIN_READY= IN_READY.stream().map(x -> new BigDecimal(x.getWithdrawalamount())).reduce(BigDecimal::add);
        // 已提现
        List<Withdrawal> ALREADY = rewrite_withdrawalRepository.findAllByUseridAndWithdrawaltype(id.toString(), WithdrawalConstant.ALREADY);
        Optional sumALREADY = ALREADY.stream().map(x -> new BigDecimal(x.getWithdrawalamount())).reduce(BigDecimal::add);

        long count = rewrite_UserlinkuserRepository.countAllByRecommendid(id.toString());

        Userassets userassets = rewrite_UserassetsRepository.findByUserid(id.toString());

        Rewrite_WithdrawalInfo rewrite_WithdrawalInfo = new Rewrite_WithdrawalInfo(sum.toString(),userassets.getFrozenbalance(),
            sumIN_READY.toString(),userassets.getUsablebalance(),sumALREADY.toString(),String.valueOf(count));
        return Result.suc("成功",rewrite_WithdrawalInfo);
    }

    // 获取一条提现数据详细信息
    @Override
    public Result getWithdrawalInfo(Long withdrawalId) {
        Withdrawal withdrawal = rewrite_withdrawalRepository.getOne(withdrawalId);
        Rewrite_WithOneInfo rewrite_WithOneInfo = new Rewrite_WithOneInfo();

        if(withdrawal.getBankcardid() != null && !"".equals(withdrawal.getBankcardid())){
            Userbankcard userbankcard = rewrite_UserbankcardRepository.getOne(Long.valueOf(withdrawal.getBankcardid()));
            rewrite_WithOneInfo.setBankuser(userbankcard.getRealname());
            rewrite_WithOneInfo.setBankaccount(userbankcard.getBankcard());
            rewrite_WithOneInfo.setBankname(userbankcard.getBanktype());
        }

        rewrite_WithOneInfo.setStime(withdrawal.getCreatedate());
        rewrite_WithOneInfo.setAmount(withdrawal.getWithdrawalamount());
        rewrite_WithOneInfo.setType(withdrawal.getGatheringway());
        rewrite_WithOneInfo.setType(withdrawal.getWithdrawaltype());
        rewrite_WithOneInfo.setEtime(withdrawal.getModifierdate());
        rewrite_WithOneInfo.setExtro(withdrawal.getOther());

        return Result.suc("成功",rewrite_WithOneInfo);
    }
}
