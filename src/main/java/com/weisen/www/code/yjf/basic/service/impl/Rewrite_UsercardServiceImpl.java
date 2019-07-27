package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Usercard;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.UsercardRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_UsercardService;
import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UsercardMapper;
import com.weisen.www.code.yjf.basic.service.mapper.UserorderMapper;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Rewrite_UsercardServiceImpl implements Rewrite_UsercardService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_UsercardServiceImpl.class);

    private final UsercardRepository usercardRepository;

    private final UsercardMapper usercardMapper;

    public Rewrite_UsercardServiceImpl(UsercardRepository usercardRepository,UsercardMapper usercardMapper) {
        this.usercardRepository = usercardRepository;
        this.usercardMapper = usercardMapper;
    }

    // 创建银行卡信息
    @Override
    public Result createUserCard(UsercardDTO usercardDTO) {
        Usercard usercard =  new Usercard();
        usercard.setBank(usercardDTO.getBank());
        usercard.setBankname(usercardDTO.getBankname());
        usercard.setLogo(usercardDTO.getLogo());
        usercardRepository.save(usercard);

        return Result.suc("成功");
    }

    // 查询所有银行卡信息
    @Override
    public Result findCardInfo() {
        List<Usercard> list = usercardRepository.findAll();
        return Result.suc("success",usercardMapper.toDto(list));
    }
}
