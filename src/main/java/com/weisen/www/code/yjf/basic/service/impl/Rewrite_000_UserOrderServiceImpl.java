package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.security.SecurityUtils;
import com.weisen.www.code.yjf.basic.service.Rewrite_000_UserOrderService;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_000_UserOrderServiceImpl implements Rewrite_000_UserOrderService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_000_UserOrderServiceImpl.class);

    @Override
    public Result alipay(String orderId) {
        String username = SecurityUtils.getCurrentUserLogin().get();
        return null;
    }
}
