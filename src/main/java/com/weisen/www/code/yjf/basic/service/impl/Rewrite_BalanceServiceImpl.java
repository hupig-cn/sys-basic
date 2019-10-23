package com.weisen.www.code.yjf.basic.service.impl;
import java.math.BigDecimal;

import com.weisen.www.code.yjf.basic.domain.Receiptpay;
import com.weisen.www.code.yjf.basic.domain.Userassets;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ReceiptpayRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserassetsRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.Rewrite_BalanceService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ReceiptpayDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import java.util.*;

import org.springframework.stereotype.Service;

/**
 * @Author: 阮铭辉
 * @Date: 2019/10/23 11:38
 */
@Service
public class Rewrite_BalanceServiceImpl implements Rewrite_BalanceService {

    private final Rewrite_UserassetsRepository rewrite_userassetsRepository;

    private final Rewrite_ReceiptpayRepository rewrite_receiptpayRepository;

    private final Rewrite_UserlinkuserRepository rewrite_userlinkuserRepository;

    public Rewrite_BalanceServiceImpl(Rewrite_UserassetsRepository rewrite_userassetsRepository, Rewrite_ReceiptpayRepository rewrite_receiptpayRepository, Rewrite_UserlinkuserRepository rewrite_userlinkuserRepository) {
        this.rewrite_userassetsRepository = rewrite_userassetsRepository;
        this.rewrite_receiptpayRepository = rewrite_receiptpayRepository;
        this.rewrite_userlinkuserRepository = rewrite_userlinkuserRepository;
    }


    @Override
    public Result findAllBalance(String userid) {
        Userassets byUserid = rewrite_userassetsRepository.findByUserid(userid);
        return Result.suc("查询成功",byUserid.getUsablebalance());
    }

    @Override
    public Result Receiptpaylist(String userid, String endTime, String startTime) {
        List<Receiptpay> Receiptpay = rewrite_receiptpayRepository.findByTimeAndUserid(userid,startTime,endTime);
        List<Receiptpay> sourcer = rewrite_receiptpayRepository.findByTimeAndSourcer(userid, startTime, endTime);
        Set<Rewrite_ReceiptpayDTO> a = new HashSet<>();
        for (int i = 0; i < Receiptpay.size(); i++) {
            Rewrite_ReceiptpayDTO b = new Rewrite_ReceiptpayDTO();
            Receiptpay receiptpay = Receiptpay.get(i);

            b.setId(receiptpay.getId()+"");
            b.setAmount(receiptpay.getAmount());
            b.setSourcer(receiptpay.getSourcer());
            b.setDealtype(receiptpay.getDealtype());
            b.setHappendate(receiptpay.getCreatedate());
            b.setUserid(receiptpay.getUserid());
            b.setSourcername(".");//todo
            b.setPayway(receiptpay.getPayway());
            b.setStats(1L);
            a.add(b);
        }
        for (int i = 0; i < sourcer.size(); i++) {
            Rewrite_ReceiptpayDTO b = new Rewrite_ReceiptpayDTO();
            Receiptpay receiptpay = sourcer.get(i);

            b.setId(receiptpay.getId()+"");
            b.setAmount(receiptpay.getAmount());
            b.setSourcer(receiptpay.getSourcer());
            b.setDealtype(receiptpay.getDealtype());
            b.setHappendate(receiptpay.getCreatedate());
            b.setUserid(receiptpay.getUserid());
            b.setSourcername(".");//todo
            b.setPayway(receiptpay.getPayway());
            b.setStats(0L);
            a.add(b);
        }
        List<Rewrite_ReceiptpayDTO> c = new ArrayList<>(a);

        c.sort(new Comparator<Rewrite_ReceiptpayDTO>() {
            @Override
            public int compare(Rewrite_ReceiptpayDTO o1, Rewrite_ReceiptpayDTO o2) {
                return o1.getHappendate().compareTo(o2.getHappendate());
            }
        });

        return Result.suc("查询成功",c,c.size());
    }

    @Override
    public Result receiptpays(Long id,String userid) {
        Receiptpay byId = rewrite_receiptpayRepository.findReceiptpayById(id);
        Rewrite_ReceiptpayDTO b = new Rewrite_ReceiptpayDTO();
        b.setId(byId.getId()+"");
        b.setAmount(byId.getAmount());
        b.setSourcer(byId.getSourcer());
        b.setDealtype(byId.getDealtype());
        b.setHappendate(byId.getCreatedate());
        b.setUserid(byId.getUserid());
        b.setSourcername(".");//todo
        b.setPayway(byId.getPayway());
        if (byId.getUserid().equals(userid)){
            b.setStats(1L);
        }else {
            b.setStats(0L);
        }
        return Result.suc("查询成功",b);
    }
}
