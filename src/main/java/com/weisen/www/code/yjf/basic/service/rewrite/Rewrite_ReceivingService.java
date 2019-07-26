package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ReceivingDTO;
import com.weisen.www.code.yjf.basic.util.Result;

public interface Rewrite_ReceivingService {
    Result getUserAddress(Long id);

    Result insertUserAddress(Rewrite_ReceivingDTO rewrite_receivingDTO);

    Result updateUserAddress(Rewrite_ReceivingDTO rewrite_receivingDTO);

    Result getDefaultAddress(Long id);

//    Result setDefaultAddress(Long id,Long userId);

    Result getOneUserAddress(Long userid, Long id);
}
