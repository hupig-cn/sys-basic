package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_MessageTemplateRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_MessageTemplateService;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_MessageTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_MessageTemplateServiceImpl implements Rewrite_MessageTemplateService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_MessageTemplateServiceImpl.class);

    private final Rewrite_MessageTemplateRepository messageTemplateRepository;

    private final Rewrite_MessageTemplateMapper messageTemplateMapper;

    public Rewrite_MessageTemplateServiceImpl(Rewrite_MessageTemplateRepository messageTemplateRepository, Rewrite_MessageTemplateMapper messageTemplateMapper) {
        this.messageTemplateRepository = messageTemplateRepository;
        this.messageTemplateMapper = messageTemplateMapper;
    }

}
