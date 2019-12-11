package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.rewrite.submit_dto.Rewrite_DeviceinfobeanDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * 优惠活动
 *
 * @author sxx
 *
 */
public interface Rewrite_rewriteoldportService {

    Result verificationIdCard(String userid, String idcard,String name);

    Result selectVerification(String userid);
    
    // 保存用户手机型号信息LuoJinShui
    Result saveUserMobileInformation(Rewrite_DeviceinfobeanDTO rewrite_DeviceinfobeanDTO);
}
