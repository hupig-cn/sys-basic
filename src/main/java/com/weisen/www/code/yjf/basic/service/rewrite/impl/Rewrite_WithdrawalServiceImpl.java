package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.domain.Userbankcard;
import com.weisen.www.code.yjf.basic.domain.Withdrawal;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserbankcardRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_WithdrawalRepository;
import com.weisen.www.code.yjf.basic.security.SecurityUtils;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_WithOneInfo;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_WithdrawalInfo;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_WithdrawalService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_WithdrawalDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_WithdrawalMapper;
import com.weisen.www.code.yjf.basic.service.util.ReceiptpayConstant;
import com.weisen.www.code.yjf.basic.service.util.WithdrawalConstant;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;
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

    private final Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository;

    private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

    private final Rewrite_UserassetsRepository rewrite_UserassetsRepository;

    private final Rewrite_UserbankcardRepository rewrite_UserbankcardRepository;

    public Rewrite_WithdrawalServiceImpl(Rewrite_WithdrawalRepository rewrite_withdrawalRepository, Rewrite_WithdrawalMapper rewrite_withdrawalMapper,
                                         Rewrite_ReceiptpayRepository rewrite_ReceiptpayRepository,Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository,
                                         Rewrite_UserassetsRepository rewrite_UserassetsRepository,Rewrite_UserbankcardRepository rewrite_UserbankcardRepository) {
        this.rewrite_withdrawalRepository = rewrite_withdrawalRepository;
        this.rewrite_withdrawalMapper = rewrite_withdrawalMapper;
        this.rewrite_ReceiptpayRepository = rewrite_ReceiptpayRepository;
        this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
        this.rewrite_UserassetsRepository = rewrite_UserassetsRepository;
        this.rewrite_UserbankcardRepository = rewrite_UserbankcardRepository;
    }

    /**
     * 提交提现记录
     *
     * @param rewrite_withdrawalDTO
     * @return
     */
    public Result insertWithdrawal(Rewrite_WithdrawalDTO rewrite_withdrawalDTO) {
        if (!CheckUtils.checkObj(rewrite_withdrawalDTO))
            return Result.fail("提交信息异常");
        else if (!CheckUtils.checkString(rewrite_withdrawalDTO.getUserid()))
            return Result.fail("账号信息异常");
        else if (!CheckUtils.checkString(rewrite_withdrawalDTO.getCreator()))
            return Result.fail("提现申请信息异常");
        else {
            Withdrawal withdrawal = rewrite_withdrawalMapper.toEntity(rewrite_withdrawalDTO);
            withdrawal.setLogicdelete(false);
            withdrawal.setCreatedate(DateUtils.getDateForNow());
            Withdrawal save = rewrite_withdrawalRepository.save(withdrawal);
            if (!CheckUtils.checkObj(save))
                return Result.fail();
        }

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
     * 后台审核通过提现记录
     *
     * @param id
     *
     * @return
     */
    public Result auditWithdrawal(Long id, String other, String Modifier) {
        if (!CheckUtils.checkLongByZero(id))
            return Result.fail("审核数据异常");
        else {
            Optional<Withdrawal> byId = rewrite_withdrawalRepository.findById(id);
            Withdrawal withdrawal = byId.get();
            Integer integer = rewrite_withdrawalRepository.auditWithdrawal(id, other, Modifier);
            if (!CheckUtils.checkIntegerByZero(integer))
                return Result.fail("数据库修改失败");
            // TODO: 2019/6/28 审核扣除账号金额,并且生成收支流水
            //审核通过扣减账号审核金额
            //根据userId找到当前用户,然后根据提现上面所填写的内容扣除
            //用户资金扣除,生成收支明细
        }
        return Result.suc("审核通过");
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
        List<Withdrawal> IN_READY = rewrite_withdrawalRepository.findAllByUseridAndWithdrawaltype(id.toString(), WithdrawalConstant.READY);
        Optional sumIN_READY= IN_READY.stream().map(x -> new BigDecimal(x.getWithdrawalamount())).reduce(BigDecimal::add);
        // 已提现
        List<Withdrawal> ALREADY = rewrite_withdrawalRepository.findAllByUseridAndWithdrawaltype(id.toString(), WithdrawalConstant.READY);
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
