package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.dto.submit_dto.Rewrite_submitOsVersionDTO;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_OsversionDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Interface for managing Osversion.
 */
public interface Rewrite_OsversionService {

    Result checkOsVersion(Rewrite_submitOsVersionDTO rewrite_submitOsVersionDTO);

    Result insertOsVersion(Rewrite_OsversionDTO rewrite_osversionDTO);
}
