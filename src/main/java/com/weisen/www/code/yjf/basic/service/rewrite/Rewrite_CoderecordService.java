package com.weisen.www.code.yjf.basic.service.rewrite;


import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_submitCoderecordDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_CoderecordService {
    Result sendCode(Rewrite_submitCoderecordDTO coderecordDTO);

    Result checkCode(Rewrite_submitCoderecordDTO coderecordDTO);
}
