package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Userbankcard;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserbankcardRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_UserbankcardService;
import com.weisen.www.code.yjf.basic.service.dto.UserbankcardDTO;
import com.weisen.www.code.yjf.basic.service.dto.show_dto.Rewrite_BankCardDTO;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Rewrite_UserbankcardServiceImpl implements Rewrite_UserbankcardService {

    private final Logger log = LoggerFactory.getLogger(UserbankcardServiceImpl.class);

    private final Rewrite_UserbankcardRepository rewrite_UserbankcardRepository;

    private final Rewrite_LinkuserRepository rewrite_LinkuserRepository;

    public Rewrite_UserbankcardServiceImpl(Rewrite_UserbankcardRepository rewrite_UserbankcardRepository,
                                           Rewrite_LinkuserRepository rewrite_LinkuserRepository) {
        this.rewrite_UserbankcardRepository = rewrite_UserbankcardRepository;
        this.rewrite_LinkuserRepository = rewrite_LinkuserRepository;
    }

    //查询用户银行卡列表
    @Override
    public Result findAllUserBankCard(Long userId) {
        List<Userbankcard> list = rewrite_UserbankcardRepository.findAllByUserid(userId.toString());
        List<Rewrite_BankCardDTO> listbank = new ArrayList<>();

        list.forEach(x-> {
            Rewrite_BankCardDTO rewrite_BankCardDTO = new Rewrite_BankCardDTO();
            rewrite_BankCardDTO.setId(x.getId());
            rewrite_BankCardDTO.setBankname(x.getBanktype());
            rewrite_BankCardDTO.setBanknum(x.getBankcard().substring(x.getBankcard().length()-4,x.getBankcard().length()));
            rewrite_BankCardDTO.setBankuser(x.getRealname());
            rewrite_BankCardDTO.setLogo(x.getBankicon());
            listbank.add(rewrite_BankCardDTO);
        });

        return Result.suc("成功",listbank);
    }

    // 用户添加银行卡
    @Override
    public Result createBankCard(UserbankcardDTO userbankcardDTO) {
        Userbankcard userbankcard = new Userbankcard();
        userbankcard.setBankcard(userbankcardDTO.getBankcard());
        userbankcard.setBankicon(userbankcardDTO.getBankicon());
        userbankcard.setBankphone(userbankcardDTO.getBankphone());
        userbankcard.setBanktype(userbankcardDTO.getBanktype());
        userbankcard.setRealname(userbankcardDTO.getRealname());
        userbankcard.setCreatedate(TimeUtil.getDate());
        userbankcard.setUserid(userbankcardDTO.getUserid());
        rewrite_UserbankcardRepository.save(userbankcard);

        return Result.suc("success");
    }

    // 用户删除银行卡
    @Override
    public Result deleteBackCard(Long bankcardId) {
        rewrite_UserbankcardRepository.deleteById(bankcardId);
        return Result.suc("success");
    }


}
