package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.dto.PaymethodDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_PaymethodService {

    Result insert(PaymethodDTO paymethodDTO);

    Result updatePaymethod(PaymethodDTO paymethodDTO);

    Result getPayMethod(String os,Boolean online);
}
