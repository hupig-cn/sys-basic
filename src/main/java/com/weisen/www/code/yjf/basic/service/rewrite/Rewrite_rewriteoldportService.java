package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.util.Result;

/**
 * 优惠活动
 *
 * @author sxx
 *
 */
public interface Rewrite_rewriteoldportService {

    Result verificationIdCard(String userid, String idcard);

    Result selectVerification(String userid);
}
