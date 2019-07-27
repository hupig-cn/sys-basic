package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.repository.Rewrite_UserorderRepository;
import com.weisen.www.code.yjf.basic.repository.UsercardRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_ReceiptpayService;
import com.weisen.www.code.yjf.basic.service.Rewrite_UsercardService;
import com.weisen.www.code.yjf.basic.service.dto.UsercardDTO;
import com.weisen.www.code.yjf.basic.service.mapper.UserorderMapper;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_UsercardServiceImpl implements Rewrite_UsercardService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_UsercardServiceImpl.class);

    private final UsercardRepository usercardRepository;

    public Rewrite_UsercardServiceImpl(UsercardRepository usercardRepository) {
        this.usercardRepository = usercardRepository;
    }


    @Override
    public Result createUserCard(UsercardDTO usercardDTO) {
        return null;
    }

    @Override
    public Result findCardInfo() {
        return null;
    }
}
