package com.weisen.www.code.yjf.basic.service.impl;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.weisen.www.code.yjf.basic.repository.Rewrite_000_LinkaccountRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_000_AlipayService;
import com.weisen.www.code.yjf.basic.util.AlipayUtil;
import com.weisen.www.code.yjf.basic.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_000_AlipayServiceImpl implements Rewrite_000_AlipayService {

    private Rewrite_000_LinkaccountRepository linkaccountRepository;

    public Rewrite_000_AlipayServiceImpl(Rewrite_000_LinkaccountRepository linkaccountRepository) {
        this.linkaccountRepository = linkaccountRepository;
    }

    @Override
    public Result scaning(String authCode) {
        AlipaySystemOauthTokenResponse userInfo = AlipayUtil.getUserInfo(authCode);
        if (userInfo == null) {
            return Result.fail("获取支付宝会员信息失败");
        }
        String userId = userInfo.getUserId();
        //获取用户是否存在
        int alipayCount = linkaccountRepository.countByAlipayAccount(userId);
        return alipayCount > 0 ? Result.suc("用户已注册") : Result.fail("用户未注册");
    }

}
