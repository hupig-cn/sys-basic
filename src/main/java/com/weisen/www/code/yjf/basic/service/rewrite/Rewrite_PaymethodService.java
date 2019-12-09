package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitPayMethodDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_submitPayMethodDTO2;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_PaymethodService {

    Result insert(PaymethodDTO paymethodDTO);

    Result updatePaymethod(PaymethodDTO paymethodDTO);

    Result getPayMethod(Rewrite_submitPayMethodDTO rewrite_submitPayMethodDTO);

    Result getPayMethod2(Rewrite_submitPayMethodDTO2 rewrite_submitPayMethodDTO2);
}
