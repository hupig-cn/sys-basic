package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_ModifyNewPasswordDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordCodeDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordDTO;
import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_200_PayPasswordSetDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_200_PayPasswordService {

    Result check ();

    Result sendCode ();

    Result validateCode (Rewrite_200_PayPasswordCodeDTO payPasswordCodeDTO);

    Result updatePassword(Rewrite_200_PayPasswordSetDTO payPasswordSetDTO);

    Result checkOldPayPassword (Rewrite_200_PayPasswordDTO payPasswordDTO);

    Result modifyNewPassword (Rewrite_200_ModifyNewPasswordDTO modifyNewPasswordDTO);
}
