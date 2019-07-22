package com.weisen.www.code.yjf.basic.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkaccountRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_AlipayService;
import com.weisen.www.code.yjf.basic.util.AlipayUtil;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.Rewrite_Constant;

@Service
@Transactional
public class Rewrite_AlipayServiceImpl implements Rewrite_AlipayService {

    private Rewrite_LinkaccountRepository linkaccountRepository;

    public Rewrite_AlipayServiceImpl(Rewrite_LinkaccountRepository linkaccountRepository) {
        this.linkaccountRepository = linkaccountRepository;
    }

    @Override
    public String scaning(String userid, String authCode) {
        AlipaySystemOauthTokenResponse userInfo = AlipayUtil.getUserInfo(authCode);
        if (userInfo == null) {
            return "获取支付宝会员信息失败";
        }
//        String alipayUserId = userInfo.getUserId();
//        //获取用户是否存在
//        int alipayCount = linkaccountRepository.countByAccountType(alipayUserId, Rewrite_Constant.ACCOUNTTYPE_ALIPAY);
//        boolean checkFlag = alipayCount > 0;
//        if (null != userId && !checkFlag) { //绑定支付宝账号
//            Linkaccount linkaccount = new Linkaccount();
//            linkaccount.setUserid(userId);
//            linkaccount.setAccounttype(Rewrite_Constant.ACCOUNTTYPE_ALIPAY);
//            linkaccount.setToken(alipayUserId);
//            linkaccount.setCreator(userId);
//            linkaccount.setCreatedate("创建时间");
//            linkaccountRepository.save(linkaccount);
//        }
//        return checkFlag ? Result.fail("用户已注册") : Result.suc(alipayUserId, "用户可以注册");
        return "";
    }

}
