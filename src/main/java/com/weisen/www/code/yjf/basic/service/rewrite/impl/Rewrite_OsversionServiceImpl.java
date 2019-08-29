package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.domain.Osversion;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_OsversionRepository;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_submitOsVersionDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_OsversionService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_OsversionDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_OsversionMapper;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Service Implementation for managing Osversion.
 */
@Service
@Transactional
public class Rewrite_OsversionServiceImpl implements Rewrite_OsversionService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_OsversionServiceImpl.class);

    private final Rewrite_OsversionRepository osversionRepository;

    private final Rewrite_OsversionMapper osversionMapper;

    public Rewrite_OsversionServiceImpl(Rewrite_OsversionRepository osversionRepository, Rewrite_OsversionMapper osversionMapper) {
        this.osversionRepository = osversionRepository;
        this.osversionMapper = osversionMapper;
    }

    public Result checkOsVersion(Rewrite_submitOsVersionDTO rewrite_submitOsVersionDTO) {
        if(!CheckUtils.checkObj(rewrite_submitOsVersionDTO))
            return Result.fail("校验数据错误");
        else if (!CheckUtils.checkString(rewrite_submitOsVersionDTO.getOs()))
            return Result.fail("设备信息错误");
        else if(!CheckUtils.checkString(rewrite_submitOsVersionDTO.getVersion()))
            return Result.fail("版本信息错误");
        else {
            Map<String,String> versionByOs = osversionRepository.findVersionByOs(rewrite_submitOsVersionDTO.getOs());
            HashMap<String,String>  version = new HashMap<>();
            version.putAll(versionByOs);
            ArrayList<String> loginfo = new ArrayList<>();
            for (String s : version.get("loginfo").split("|")) {
                loginfo.add(s);
            }
            version.put("loginfo",loginfo.toString());
            return Result.suc("",version);
        }
    }

    /**
     * 未测试，更新日志保存的格式为： 修改字体 | 修改主题 | 修改界面
     * 版本号格式为：1.0.0 如果切割后不能转化为数字会出错
     * @param rewrite_osversionDTO
     * @return
     */
    public Result insertOsVersion(Rewrite_OsversionDTO rewrite_osversionDTO) {
        if(!CheckUtils.checkObj(rewrite_osversionDTO))
            return Result.fail("保存数据不能为空");
        else if(!CheckUtils.checkString(rewrite_osversionDTO.getApkurl()))
            return Result.fail("下载地址不能为空");
        else if(!CheckUtils.checkString(rewrite_osversionDTO.getOs()))
            return Result.fail("设备类型不能为空");
        else if(!CheckUtils.checkString(rewrite_osversionDTO.getVersion()))
            return Result.fail("版本号不能为空");
        else if(!CheckUtils.checkIntegerByZero(rewrite_osversionDTO.getType()))
            return Result.fail("更新类型不能为空和0");
        else {
            rewrite_osversionDTO.setCreatedate(DateUtils.getDateForNow());
            Osversion osversion = osversionMapper.toEntity(rewrite_osversionDTO);
            Osversion save = osversionRepository.save(osversion);
            if(!CheckUtils.checkObj(save))
                return Result.fail("数据库繁忙保存失败");
            return Result.suc("保存成功");
        }
    }
}
