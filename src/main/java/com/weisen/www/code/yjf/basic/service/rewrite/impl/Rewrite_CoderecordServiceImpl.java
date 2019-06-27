package com.weisen.www.code.yjf.basic.service.rewrite.impl;

import com.weisen.www.code.yjf.basic.config.Constants;
import com.weisen.www.code.yjf.basic.domain.Coderecord;
import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_CoderecordRepository;
import com.weisen.www.code.yjf.basic.service.rewrite.Rewrite_CoderecordService;
import com.weisen.www.code.yjf.basic.service.rewrite.mapper.Rewrite_CoderecordMapper;
import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_submitCoderecordDTO;
import com.weisen.www.code.yjf.basic.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Rewrite_CoderecordServiceImpl implements Rewrite_CoderecordService {

    private final Rewrite_CoderecordMapper rewrite_coderecordMapper;

    private final Rewrite_CoderecordRepository rewrite_coderecordRepository;

    public Rewrite_CoderecordServiceImpl(Rewrite_CoderecordMapper rewrite_coderecordMapper, Rewrite_CoderecordRepository rewrite_coderecordRepository) {
        this.rewrite_coderecordMapper = rewrite_coderecordMapper;
        this.rewrite_coderecordRepository = rewrite_coderecordRepository;
    }

    public Result sendCode(Rewrite_submitCoderecordDTO coderecordDTO) {
        if (!CheckUtils.checkObj(coderecordDTO)) {
            return Result.fail("数据异常");
        } else if (!CheckUtils.checkString(coderecordDTO.getAct())) {
            return Result.fail("发送动作异常");
        } else if (!CheckUtils.checkPhoneNumber(coderecordDTO.getPhone())) {
            return Result.fail("电话号码异常");
        }
        Integer number = 1;
        String TemplateCode = null;
        String type = null;
        String s = null;
        Integer integer = null;
        //区分不同的类型,设置校验次数
        switch (coderecordDTO.getAct()) {
            case Constants.SMS_REGISTER:
                number = Constants.REGISTER_NUMBER;
                TemplateCode = Constants.REGISTER;
                type = "注册";
                break;
            case Constants.SMS_CHANGE_PASSWORD:
                number = Constants.CHANGE_PASSWORD_NUMBER;
                TemplateCode = Constants.CHANGE_INFO;
                type = "修改密码";
                break;
            default:
                return Result.fail("发送短信动作异常");
        }
        //根据电话号码和类型查询
        Coderecord codeRecord = rewrite_coderecordRepository.findCodeByPhoneAndAct(coderecordDTO.getPhone(), coderecordDTO.getAct());
        //不存在则创建验证码并且发送
        String code = CodeUtil.getCode().toString();
        if (null == codeRecord) {
            Coderecord coderecord = new Coderecord();
            coderecord.setCreateDate(DateUtils.getDateForNow());
            coderecord.setUpdateDate(DateUtils.getDateForNow());
            coderecord.setNum(1);
            coderecord.setPhone(coderecordDTO.getPhone());
            coderecord.setCode(code.toString());
            coderecord.setType(coderecordDTO.getAct());
            Coderecord save = rewrite_coderecordRepository.save(coderecord);
            if (null == save) {
                return Result.fail("发送失败");
            } else {
                s = SmsUtils.SmsSendByAli(coderecordDTO.getPhone(), code, TemplateCode);
                if ("OK".equals(s))
                    s = "发送成功";
                return Result.suc(s);
            }
        } else {
            //存在
            //判断是否是当天的验证码,是则传入旧时间去update,并且将次数设置为1
            //            if()
            //判断次数是否超过当天的次数,超过则不发送验证码,并且提示超过了.
            //判断修改时间是否超过一分钟,一分钟内不允许重复发送
            //判断修改时间是否超过了三分钟,超过三分钟
            if (DateUtils.checkNowByLongToBoolean(codeRecord.getUpdateDate(), Long.valueOf(1000 * 60 * 60 * 24))) {
                integer = rewrite_coderecordRepository.updateCodeByTime(codeRecord.getId(), code, codeRecord.getUpdateDate(), DateUtils.getDateForNow());
            } else if (number <= codeRecord.getNum()) {
                return Result.fail("超过了" + type + "验证码的次数限制,请明天再试");
            }
            else if (!DateUtils.checkNowByMinthToBoolean(codeRecord.getUpdateDate(), Long.valueOf(1000 * 60 * 1))) {
                return Result.fail("一分钟内请勿重复发送");
            }  else if (!DateUtils.checkNowByMinthToBoolean(codeRecord.getUpdateDate(), Long.valueOf(1000 * 60 * 3))) {
                //超过三分钟限制
                s = SmsUtils.SmsSendByAli(coderecordDTO.getPhone(), codeRecord.getCode(), TemplateCode);
                if ("OK".equals(s))
                    s = "发送成功";
                //释放String引用
                TemplateCode = null;
                type = null;
                return Result.suc(s);
            } else {
                integer = rewrite_coderecordRepository.updateCodeByNumber(codeRecord.getId(), codeRecord.getNum(), code, DateUtils.getDateForNow());
            }
            if (!CheckUtils.checkIntegerByZero(integer)) {
                return Result.fail("发送失败");
            } else {
                s = SmsUtils.SmsSendByAli(coderecordDTO.getPhone(), code, TemplateCode);
                if ("OK".equals(s))
                    s = "发送成功";
                //释放String引用
                TemplateCode = null;
                type = null;
                return Result.suc(s);
            }
        }
    }

    public Result checkCode(Rewrite_submitCoderecordDTO coderecordDTO) {
        if (!CheckUtils.checkObj(coderecordDTO))
            return Result.fail("数据异常");
        else if (!CheckUtils.checkPhoneNumber(coderecordDTO.getPhone()))
            return Result.fail("电话号码异常");
        else if (!CheckUtils.checkString(coderecordDTO.getCode()))
            return Result.fail("电话号码异常");
        else if (!CheckUtils.checkString(coderecordDTO.getAct()))
            return Result.fail("验证类型错误");
        switch (coderecordDTO.getAct()) {
            case Constants.SMS_REGISTER:
                break;
            case Constants.SMS_CHANGE_PASSWORD:
                break;
            default:
                return Result.fail("验证动作异常");
        }
        Coderecord codeByPhoneAndAct = rewrite_coderecordRepository.findCodeByPhoneAndActAndCode(coderecordDTO.getPhone(), coderecordDTO.getAct(), coderecordDTO.getCode());
        if (null == codeByPhoneAndAct)
            return Result.fail("验证码错误,或已失效");
        return Result.suc("验证成功");
    }
}
