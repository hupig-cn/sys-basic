package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Withdrawaldetails;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_WithdrawaldetailsRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_WithdrawaldetailsService;
import com.weisen.www.code.yjf.basic.service.dto.WithdrawaldetailsDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_WithdrawaldetailsDto;
import com.weisen.www.code.yjf.basic.service.mapper.UserorderMapper;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public Result findUserWithdrawaldetails(Long userid,int startNum,int pageSize) {
        List<Withdrawaldetails> list = rewrite_WithdrawaldetailsRepository.
            getAllUserInfo(userid.toString(),startNum * pageSize,pageSize);

        int count = rewrite_WithdrawaldetailsRepository.getAllUserInfoCount(userid.toString());
        List<Rewrite_WithdrawaldetailsDto> withlist = new ArrayList<>();

        list.forEach(x -> {
            Rewrite_WithdrawaldetailsDto rewrite_WithdrawaldetailsDto = new Rewrite_WithdrawaldetailsDto();
            rewrite_WithdrawaldetailsDto.setTitle(x.getTitle());
            rewrite_WithdrawaldetailsDto.setAmount(x.getAmount());
            rewrite_WithdrawaldetailsDto.setBalance(x.getAfteramount());
            rewrite_WithdrawaldetailsDto.setId(x.getId().toString());
            rewrite_WithdrawaldetailsDto.setType(x.getState());
            withlist.add(rewrite_WithdrawaldetailsDto);
        });

        return Result.suc("成功",list,count);
    }
}
