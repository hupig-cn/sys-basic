package com.weisen.www.code.yjf.basic.service.impl;

//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.weisen.domain.UserData;
//import com.weisen.repository.Rewrite_000_UserDataRepository;
//import com.weisen.service.Rewrite_000_SmsDataService;
//import com.weisen.www.code.yjf.basic.config.Constants;
//import com.weisen.www.code.yjf.basic.domain.Linkuser;
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

//    private Rewrite_000_UserDataRepository userDataRepository;

//    private Rewrite_000_SmsDataService smsDataService;

//    private PasswordEncoder passwordEncoder;

    public Rewrite_200_PayPasswordServiceImpl(
//    		Rewrite_000_UserDataRepository userDataRepository,
//          Rewrite_000_SmsDataService smsDataService,
//          PasswordEncoder passwordEncoder
          ) {
//        this.userDataRepository = userDataRepository;
//        this.smsDataService = smsDataService;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result check() {
//        String login = SecurityUtils.getCurrentUserLogin().get();
//        UserData userData = userDataRepository.getByMobile(login);
//        if (userData == null) {
//            return Result.fail("未获取到当前登录信息");
//        }
//        String payPassword = userData.getPayPassword();
//        return (null == payPassword || StringUtils.isEmpty(payPassword)) ? Result.fail("您未设置支付密码") : Result.suc("成功");
        return Result.suc("成功");
    }

    @Override
    public Result sendCode() {
//        String login = SecurityUtils.getCurrentUserLogin().get();
//        UserData userData = userDataRepository.getByMobile(login);
//        if (userData == null) {
//            return Result.fail("未获取到当前登录信息");
//        }
//        return smsDataService.sendValidationCode(login, Constants.SMS_PAY_PASSWORD, Constants.SMS_TYPE_PAY_PASSWORD);
    	return Result.suc("成功");
    }

    @Override
    public Result validateCode(Rewrite_200_PayPasswordCodeDTO payPasswordCodeDTO) {
        String login = SecurityUtils.getCurrentUserLogin().get();
        return checkCode(login, payPasswordCodeDTO.getVertifyCode());
    }

    @Override
    public Result updatePassword(Rewrite_200_PayPasswordSetDTO payPasswordSetDTO) {
//        String login = SecurityUtils.getCurrentUserLogin().get();
//        Result result = checkCode(login, payPasswordSetDTO.getVertifyCode());
//        if (Result.FAILURE == result.getCode()) {
//            return result;
//        }
//        Linkuser userData = userDataRepository.getByMobile(login);
//        String payPassword = passwordEncoder.encode(payPasswordSetDTO.getPayPassword());
//        userData.setFrozenState(Constants.STATE_NORMAL);
//        userData.setPayErrorCount(0);
//        userData.setFrozenTime(null);
//        userData.setPayErrorTime(null);
//        userData.setPayPassword(payPassword);
//        return Result.suc("设置成功");
    	return Result.suc("成功");
    }

    @Override
    public Result checkOldPayPassword(Rewrite_200_PayPasswordDTO payPasswordDTO) {
//        String login = SecurityUtils.getCurrentUserLogin().get();
//        UserData userData = userDataRepository.getByMobile(login);
//        return passwordEncoder.matches(payPasswordDTO.getPayPassword(), userData.getPayPassword()) ? Result.suc("验证成功") : Result.fail("验证失败");
    	return Result.suc("成功");
    }

    @Override
    public Result modifyNewPassword(Rewrite_200_ModifyNewPasswordDTO modifyNewPasswordDTO) {
//        String login = SecurityUtils.getCurrentUserLogin().get();
//        UserData userData = userDataRepository.getByMobile(login);
//        if (!passwordEncoder.matches(modifyNewPasswordDTO.getOldPayPassword(), userData.getPayPassword())) {
//            return Result.fail("原支付密码验证失败");
//        } else {
//            userData.setPayPassword(passwordEncoder.encode(modifyNewPasswordDTO.getNewPayPassword()));
//        }
//        return Result.suc("修改成功");
    	return Result.suc("成功");
    }

    private Result checkCode (String login, String code) {
//        UserData userData = userDataRepository.getByMobile(login);
//        if (userData == null) {
//            return Result.fail("未获取到当前登录信息");
//        }
//        return smsDataService.vaildateCode(login, code, Constants.SMS_PAY_PASSWORD);
    	return Result.suc("成功");
    }
}
