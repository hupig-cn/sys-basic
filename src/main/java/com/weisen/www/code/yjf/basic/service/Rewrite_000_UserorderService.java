package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Rewrite_000_UserorderService {

    Result alipay (Long orderId);

    void notifyMessage (HttpServletRequest request, HttpServletResponse response);
}
