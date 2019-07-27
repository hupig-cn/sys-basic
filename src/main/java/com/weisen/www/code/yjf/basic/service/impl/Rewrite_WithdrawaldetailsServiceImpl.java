package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_WithdrawaldetailsRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_WithdrawaldetailsService;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawaldetailsDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserorderMapper;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_WithdrawaldetailsServiceImpl implements Rewrite_WithdrawaldetailsService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_WithdrawaldetailsServiceImpl.class);

    private final Rewrite_WithdrawaldetailsRepository rewrite_WithdrawaldetailsRepository;

    public Rewrite_WithdrawaldetailsServiceImpl(Rewrite_WithdrawaldetailsRepository rewrite_WithdrawaldetailsRepository) {
        this.rewrite_WithdrawaldetailsRepository = rewrite_WithdrawaldetailsRepository;
    }

    // 创建明细
    @Override
    public Result createWithdrawaldetails(WithdrawaldetailsDTO withdrawaldetailsDTO) {
        return null;
    }

    // 查询用户的提现明细列表
    @Override
    public Result findUserWithdrawaldetails(Long userid) {



        return null;
    }
}
