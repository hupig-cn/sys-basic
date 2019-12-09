package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.domain.Files;
import com.weisen.www.code.yjf.basic.domain.Paymethod;
import com.weisen.www.code.yjf.basic.repository.FilesRepository;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_PaymethodRepository;
import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.service.mapper.PaymethodMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_PaymethodService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitPayMethodDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitPayMethodDTO2;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitPayMethodDTO3;
import com.weisen.www.code.yjf.basic.util.CheckUtils;
import com.weisen.www.code.yjf.basic.util.DateUtils;
import com.weisen.www.code.yjf.basic.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Rewrite_PaymethodServiceImpl implements Rewrite_PaymethodService {

    private final PaymethodMapper paymethodMapper;
    private final Rewrite_PaymethodRepository rewrite_paymethodRepository;
    private final FilesRepository filesRepository;

    public Rewrite_PaymethodServiceImpl(PaymethodMapper paymethodMapper, Rewrite_PaymethodRepository rewrite_paymethodRepository, FilesRepository filesRepository) {
        this.paymethodMapper = paymethodMapper;
        this.rewrite_paymethodRepository = rewrite_paymethodRepository;
        this.filesRepository = filesRepository;
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

    public Result getPayMethod(Rewrite_submitPayMethodDTO rewrite_submitPayMethodDTO) {
        if (!CheckUtils.checkString(rewrite_submitPayMethodDTO.getOs()))
            return Result.fail("付款终端错误");
        else {
            List<Paymethod> payMethodByOsAndOnline = rewrite_paymethodRepository.findPayMethodByOsAndOnline(rewrite_submitPayMethodDTO.getOs(), rewrite_submitPayMethodDTO.getOnline());
            if (!CheckUtils.checkList(payMethodByOsAndOnline))
                return Result.fail("获取支付方式失败");
            return Result.suc("获取成功", payMethodByOsAndOnline);
        }
    }

    public Result getPayMethod2(Rewrite_submitPayMethodDTO2 r) {
        if (!CheckUtils.checkString(r.getOs()))
            return Result.fail("付款终端错误");
        else {
            List<Rewrite_submitPayMethodDTO3> a = new ArrayList<>();
            List<Rewrite_submitPayMethodDTO3> ac = new ArrayList<>();
            List<Paymethod> payMethodByOsAndOnline = rewrite_paymethodRepository.findPayMethodByOsAndOnline(r.getOs(), r.getOnline());
            if (!CheckUtils.checkList(payMethodByOsAndOnline)){
                return Result.fail("获取支付方式失败");
            }
            for (int i = 0; i < payMethodByOsAndOnline.size(); i++) {
                Rewrite_submitPayMethodDTO3 c = new Rewrite_submitPayMethodDTO3();

                Paymethod paymethod = payMethodByOsAndOnline.get(i);
                String title = paymethod.getTitle();
                String other = paymethod.getOther();
                Files ids = filesRepository.findByIds(Long.valueOf(other));
                Integer width = ids.getWidth();
                Integer height = ids.getHeight();
                Integer size = ids.getSize();
                String url = ids.getUrl();

                c.setUrl(url);
                c.setTitle(title);
                c.setFilesid(other);
                c.setWidth(width);
                c.setHeight(height);
                c.setSize(size);

                ac.add(c);
            }
            Collections.reverse(ac);
            if (r.getGoodsid().equals(1)){
                for (int i = 0; i < ac.size(); i++) {
                    Rewrite_submitPayMethodDTO3 dto3 = ac.get(i);
                    if (dto3.getTitle().equals("支付宝")||dto3.getTitle().equals("余额")||dto3.getTitle().equals("微信支付")){
                        a.add(dto3);
                    }
                }
                return Result.suc("获取成功", a);
            }else if(r.getGoodsid().equals(0)){
                for (int i = 0; i < ac.size(); i++) {
                    Rewrite_submitPayMethodDTO3 dto3 = ac.get(i);
                    if (dto3.getTitle().equals("余额")||dto3.getTitle().equals("积分支付")){
                        a.add(dto3);
                    }
                }
                return Result.suc("获取成功", a);
            }else {

                return Result.suc("获取成功", ac);

            }
        }
    }

}
