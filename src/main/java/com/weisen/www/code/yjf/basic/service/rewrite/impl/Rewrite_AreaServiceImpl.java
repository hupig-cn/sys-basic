package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Area;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_AreaRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_AreaService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_AreaDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_AreaMapper;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_AreaServiceImpl implements Rewrite_AreaService {

    private final Rewrite_AreaRepository areaRepository;

    private final Rewrite_AreaMapper areaMapper;

    public Rewrite_AreaServiceImpl(Rewrite_AreaRepository areaRepository, Rewrite_AreaMapper areaMapper) {
        this.areaRepository = areaRepository;
        this.areaMapper = areaMapper;
    }


    public Result findNextAreaByName(String name) {
        Area nextAreaByName = areaRepository.findNextAreaByName(name);
        if (nextAreaByName == null)
            return Result.fail("查询信息异常");
        Rewrite_AreaDTO rewrite_areaDTOS = areaMapper.toDto(nextAreaByName);
        return Result.suc("获取成功",rewrite_areaDTOS);
    }


}
