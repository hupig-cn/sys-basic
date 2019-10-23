package com.weisen.www.code.yjf.basic.web.rewrite.rewrite_001_收益明细;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_IncomeDetailsService;

import io.swagger.annotations.Api;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Withdrawal}.
 */
@RestController
@RequestMapping("/api/incomeDetails")
@Api(tags = "001-收益明细")
public class Rewrite_IncomeDetailsResource {


    private final Rewrite_IncomeDetailsService incomeDetailsService;

    public Rewrite_IncomeDetailsResource(Rewrite_IncomeDetailsService incomeDetailsService) {
        this.incomeDetailsService = incomeDetailsService;
    }
 
}
