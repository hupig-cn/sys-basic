package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_rewriteoldportService;
import com.weisen.www.code.yjf.basic.util.Result;
import com.weisen.www.code.yjf.basic.util.shenfenzhenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_rewriteoldportServiceImpl implements Rewrite_rewriteoldportService {

    private final Rewrite_LinkuserRepository rewrite_linkuserRepository;

    public Rewrite_rewriteoldportServiceImpl(Rewrite_LinkuserRepository rewrite_linkuserRepository) {
        this.rewrite_linkuserRepository = rewrite_linkuserRepository;
    }

    @Override
    public Result verificationIdCard(String userid, String idcard,String name) {
        Linkuser byIdcard = rewrite_linkuserRepository.findByIdcard(idcard);
        if (byIdcard != null){
            return Result.fail("该身份证已被注册");
        }
        Linkuser byUserid = rewrite_linkuserRepository.findByUserid(userid);
        if (byUserid == null){
          return Result.fail("没有该用户");
        } else if (byUserid.getIdcard() != null){
            return Result.fail("已验证，请勿重复验证");
        }
        //boolean idNumber = shenfenzhenUtil.isIDNumber(idcard);
        //boolean b = shenfenzhenUtil.ClearName(name);//百家姓
        //boolean b1 = shenfenzhenUtil.checkNameChese(name);//判断是否全部为中文
        int length = name.toCharArray().length;
        if (length >=2 && length <=4){
            if (!shenfenzhenUtil.isIDNumber(idcard)){
                return Result.fail("身份证输入错误");
            } else if (!shenfenzhenUtil.ClearName(name)){
                return Result.fail("输入特殊姓氏，请联系客服验证个人信息");
            }else if (!shenfenzhenUtil.checkNameChese(name)) {
                return Result.fail("请输入全部汉字");
            }else {
                Long id = byUserid.getId();
                byUserid.setId(id);
                byUserid.setIdcard(idcard);
                byUserid.setName(name);
                rewrite_linkuserRepository.save(byUserid);
                return Result.suc("验证成功!");
            }
        }else {
            return Result.fail("名字太长或者太短，请联系客服验证个人信息");
        }

    }

    @Override
    public Result selectVerification(String userid) {
        Linkuser byUserid = rewrite_linkuserRepository.findByUserid(userid);
        if (byUserid == null){
            return Result.fail("没有该用户");
        } else if (byUserid.getIdcard() != null){
            return Result.suc("已验证");
        }else {
            return Result.fail("未验证");
        }
    }
}
