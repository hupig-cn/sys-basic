package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.util.CheckUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import com.weisen.www.code.yjf.basic.repository.Rewrite_LinkuserRepository;
import com.weisen.www.code.yjf.basic.security.SecurityUtils;
import com.weisen.www.code.yjf.basic.service.Rewrite_200_PayPasswordService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_ModifyNewPasswordDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordCodeDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordSetDTO;
import com.weisen.www.code.yjf.basic.util.Result;

@Service
@Transactional
public class Rewrite_200_PayPasswordServiceImpl implements Rewrite_200_PayPasswordService {

    private Rewrite_LinkuserRepository userDataRepository;

    private PasswordEncoder passwordEncoder;

    public Rewrite_200_PayPasswordServiceImpl(Rewrite_LinkuserRepository userDataRepository,PasswordEncoder passwordEncoder) {
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result check() {
        String login = SecurityUtils.getCurrentUserLogin().get();
        Linkuser userData = userDataRepository.findByPhone(login);
        if (userData == null) {
            return Result.fail("未获取到当前登录信息");
        }
        String payPassword = userData.getPaypassword();
        return (null == payPassword || StringUtils.isEmpty(payPassword)) ? Result.fail("您未设置支付密码") : Result.suc("成功");
    }

    @Override
    public Result sendCode() {
        String login = SecurityUtils.getCurrentUserLogin().get();
        Linkuser userData = userDataRepository.findByPhone(login);
        if (userData == null) {
            return Result.fail("未获取到当前登录信息");
        }
//        return smsDataService.sendValidationCode(login, Constants.SMS_PAY_PASSWORD, Constants.SMS_TYPE_PAY_PASSWORD);
        return Result.suc("成功");
    }

    @Override
    public Result validateCode(Rewrite_200_PayPasswordCodeDTO payPasswordCodeDTO) {
//        String login = SecurityUtils.getCurrentUserLogin().get();
//        return checkCode(login, payPasswordCodeDTO.getVertifyCode());
        return Result.suc("成功");
    }

    @Override
    public Result updatePassword(Rewrite_200_PayPasswordSetDTO payPasswordSetDTO) {
        String login = SecurityUtils.getCurrentUserLogin().get();
//        Result result = checkCode(login, payPasswordSetDTO.getVertifyCode());
//        if (Result.FAILURE == result.getCode()) {
//            return result;
//        }
        Linkuser userData = userDataRepository.findByPhone(login);
        if(!CheckUtils.checkObj(userData))
            return Result.fail("不存在当前用户信息");
        String payPassword = passwordEncoder.encode(payPasswordSetDTO.getPayPassword());
        userData.setPaypassword(payPassword);
        return Result.suc("设置成功");
    }

    @Override
    public Result checkOldPayPassword(Rewrite_200_PayPasswordDTO payPasswordDTO) {
        String login = SecurityUtils.getCurrentUserLogin().get();
        Linkuser userData = userDataRepository.findByPhone(login);
        return passwordEncoder.matches(payPasswordDTO.getPayPassword(), userData.getPaypassword()) ? Result.suc("验证成功") : Result.fail("验证失败");
    }

    @Override
    public Result modifyNewPassword(Rewrite_200_ModifyNewPasswordDTO modifyNewPasswordDTO) {
        String login = SecurityUtils.getCurrentUserLogin().get();
        Linkuser userData = userDataRepository.findByPhone(login);
        if (!passwordEncoder.matches(modifyNewPasswordDTO.getOldPayPassword(), userData.getPaypassword())) {
            return Result.fail("原支付密码验证失败");
        } else {
            userData.setPaypassword(passwordEncoder.encode(modifyNewPasswordDTO.getNewPayPassword()));
        }
        return Result.suc("修改成功");
    }

//    private Result checkCode (String login, String code) {
//        Linkuser userData = userDataRepository.findByPhone(login);
//        if (userData == null) {
//            return Result.fail("未获取到当前登录信息");
//        }
//        return smsDataService.vaildateCode(login, code, Constants.SMS_PAY_PASSWORD);
//    }
}
