package com.weisen.www.code.yjf.basic.web.rest;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.config.Constants;
import com.weisen.www.code.yjf.basic.service.Rewrite_200_PayPasswordService;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_ModifyNewPasswordDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordCodeDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordSetDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/payment")
@Api(tags = "200_支付密码管理")
public class Rewrite_200_PayPasswordResource {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Rewrite_200_PayPasswordService payPasswordService;

    public Rewrite_200_PayPasswordResource(Rewrite_200_PayPasswordService payPasswordService) {
        this.payPasswordService = payPasswordService;
    }

    @ApiOperation("检查是否设置密码")
    @Timed
    @GetMapping("/check")
    public Result check () {
        log.debug("检查是否设置密码");
        return payPasswordService.check();
    }

    @ApiOperation("发送支付密码短信验证码")
    @Timed
    @GetMapping("/send-code")
    public Result sendCode () {
        log.debug("检查是否设置密码");
        return payPasswordService.sendCode();
    }

    @ApiOperation("验证支付密码短信验证码")
    @Timed
    @PostMapping("/validate-code")
    public Result validateCode (@RequestBody Rewrite_200_PayPasswordCodeDTO payPasswordCodeDTO) {
        log.debug("验证支付密码短信验证码,{}", payPasswordCodeDTO);
        String code = "";
        if (null != payPasswordCodeDTO) {
            code = payPasswordCodeDTO.getVertifyCode();
        }
        if (StringUtils.isEmpty(code)) {
            return Result.fail("短信验证码不能为空");
        } else if (!Pattern.matches(Constants.SMS_PAY_PASSWORD_CODE_REGEXP, code)) {
            return Result.fail("短信验证码格式不正确");
        }
        return payPasswordService.validateCode(payPasswordCodeDTO);
    }

    @ApiOperation("设置支付密码")
    @Timed
    @PostMapping("/update-password")
    public Result updatePassword (@RequestBody Rewrite_200_PayPasswordSetDTO payPasswordSetDTO) {
        log.debug("设置支付密码,{}", payPasswordSetDTO);
        String payPassword = "";
        if (null != payPasswordSetDTO) {
            payPassword = payPasswordSetDTO.getPayPassword();
        }
        if (StringUtils.isEmpty(payPassword)) {
            return Result.fail("支付密码不能为空");
        } else if (!Pattern.matches(Constants.SMS_PAY_PASSWORD_REGEXP, payPassword)) {
            return Result.fail("支付密码必须为6位数字，请重新设置");
        }
        return payPasswordService.updatePassword(payPasswordSetDTO);
    }

    @ApiOperation("判断原支付密码是否正确")
    @Timed
    @PostMapping("/check-old-password")
    public Result checkOldPassword (@RequestBody Rewrite_200_PayPasswordDTO payPasswordDTO) {
        if (payPasswordDTO == null) {
            return Result.fail("参数不能为空");
        }
        if (StringUtils.isEmpty(payPasswordDTO.getPayPassword())) {
            return Result.fail("原支付密码不能为空");
        } else if (!Pattern.matches(Constants.SMS_PAY_PASSWORD_REGEXP, payPasswordDTO.getPayPassword())) {
            return Result.fail("原支付密码必须为6位数字，请重新设置");
        }
        return payPasswordService.checkOldPayPassword(payPasswordDTO);
    }

    @ApiOperation("根据原支付密码修改支付密码")
    @Timed
    @PostMapping("/modify-new-password")
    public Result modifyNewPassword (@RequestBody Rewrite_200_ModifyNewPasswordDTO modifyNewPasswordDTO) {
        if (modifyNewPasswordDTO == null) {
            return Result.fail("参数不能为空");
        }
        if (StringUtils.isEmpty(modifyNewPasswordDTO.getOldPayPassword())) {
            return Result.fail("原支付密码不能为空");
        } else if (!Pattern.matches(Constants.SMS_PAY_PASSWORD_REGEXP, modifyNewPasswordDTO.getOldPayPassword())) {
            return Result.fail("原支付密码必须为6位数字，请重新设置");
        }
        if (StringUtils.isEmpty(modifyNewPasswordDTO.getNewPayPassword())) {
            return Result.fail("支付密码不能为空");
        }  else if (!Pattern.matches(Constants.SMS_PAY_PASSWORD_REGEXP, modifyNewPasswordDTO.getNewPayPassword())) {
            return Result.fail("支付密码必须为6位数字，请重新设置");
        }
        return payPasswordService.modifyNewPassword(modifyNewPasswordDTO);
    }
}
