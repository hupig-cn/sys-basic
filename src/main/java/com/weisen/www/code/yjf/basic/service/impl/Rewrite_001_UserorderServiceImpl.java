package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_001_UserorderRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_001_UserorderService;
import com.weisen.www.code.yjf.basic.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/25 16:04
 */
@Service
@Transactional
public class Rewrite_001_UserorderServiceImpl implements Rewrite_001_UserorderService {

    private final Rewrite_001_UserorderRepository rewrite_001_userorderRepository;

    public Rewrite_001_UserorderServiceImpl(Rewrite_001_UserorderRepository rewrite_001_userorderRepository) {
        this.rewrite_001_userorderRepository = rewrite_001_userorderRepository;
    }

    @Override
    public Result myUserOrder(String userid) {
        List<Userorder> list = rewrite_001_userorderRepository.findUserorderByUserid(userid);
        
        return Result.suc("??",list);
    }
}
