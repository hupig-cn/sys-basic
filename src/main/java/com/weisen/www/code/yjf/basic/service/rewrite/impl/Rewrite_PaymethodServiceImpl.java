package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.domain.Paymethod;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_PaymethodRepository;
import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.service.mapper.PaymethodMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_PaymethodService;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class Rewrite_PaymethodServiceImpl implements Rewrite_PaymethodService {

    private final PaymethodMapper paymethodMapper;
    private final Rewrite_PaymethodRepository rewrite_paymethodRepository;

    public Rewrite_PaymethodServiceImpl(PaymethodMapper paymethodMapper, Rewrite_PaymethodRepository rewrite_paymethodRepository) {
        this.paymethodMapper = paymethodMapper;
        this.rewrite_paymethodRepository = rewrite_paymethodRepository;
    }

    public Result insert(PaymethodDTO paymethodDTO) {
        if (!CheckUtils.checkObj(paymethodDTO))
            return Result.fail();
        else if (!CheckUtils.checkString(paymethodDTO.getCreator()))
            return Result.fail();
        else if (!CheckUtils.checkString(paymethodDTO.getOrder()))
            return Result.fail();
        else if (!CheckUtils.checkString(paymethodDTO.getOs()))
            return Result.fail();
        else if (!CheckUtils.checkString(paymethodDTO.getPayname()))
            return Result.fail();
        else if (!CheckUtils.checkString(paymethodDTO.getOrder()))
            return Result.fail();
        else {
            Paymethod paymethod = paymethodMapper.toEntity(paymethodDTO);
            Paymethod save = rewrite_paymethodRepository.save(paymethod);
            if (!CheckUtils.checkObj(save))
                return Result.fail();
            return Result.suc("保存成功");
        }
    }

    public Result updatePaymethod(PaymethodDTO paymethodDTO) {
        if (!CheckUtils.checkObj(paymethodDTO))
            return Result.fail();
        else if (!CheckUtils.checkLongByZero(paymethodDTO.getId()))
            return Result.fail();
        else if (!CheckUtils.checkString(paymethodDTO.getModifier()))
            return Result.fail();
        else {
            Optional<Paymethod> byId = rewrite_paymethodRepository.findById(paymethodDTO.getId());
            Paymethod paymethod = byId.get();
            paymethod.setModifierdate(DateUtils.getDateForNow());
            paymethod.setModifiernum(paymethod.getModifiernum() + 1);
            paymethod.setModifier(paymethodDTO.getModifier());
            if (CheckUtils.checkString(paymethodDTO.getMessages()))
                paymethod.setMessages(paymethodDTO.getMessages());
            else if (CheckUtils.checkString(paymethodDTO.getOrder()))
                paymethod.setOrder(paymethodDTO.getOrder());
            else if (CheckUtils.checkString(paymethodDTO.getOs()))
                paymethod.setOs(paymethodDTO.getOs());
            else if (CheckUtils.checkString(paymethodDTO.getPayname()))
                paymethod.setOs(paymethodDTO.getPayname());
            else
                return Result.fail("没有修改数据");
            Paymethod paymethod1 = rewrite_paymethodRepository.saveAndFlush(paymethod);
            if (!CheckUtils.checkObj(paymethod1))
                return Result.fail("网络繁忙");
            return Result.suc("修改成功");
        }
    }


    public Result getPayMethod(String os, Boolean online) {
        if (!CheckUtils.checkString(os))
            return Result.fail();
        else {
            List<Paymethod> payMethodByOsAndOnline = rewrite_paymethodRepository.findPayMethodByOsAndOnline(os, online);
            if (!CheckUtils.checkList(payMethodByOsAndOnline))
                return Result.fail();
            return Result.suc("", payMethodByOsAndOnline);
        }
    }

}
