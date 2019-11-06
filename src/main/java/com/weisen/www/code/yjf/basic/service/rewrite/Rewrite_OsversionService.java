package com.weisen.www.code.yjf.basic.service.rewrite;

import com.weisen.www.code.yjf.basic.service.dto.AppControlDataDTO;
import com.weisen.www.code.yjf.basic.service.dto.AppControlUpdateDTO;
import com.weisen.www.code.yjf.basic.util.Result;

/**
 * Service Interface for managing Osversion.
 */
public interface Rewrite_OsversionService {

//    Result checkOsVersion(Rewrite_submitOsVersionDTO rewrite_submitOsVersionDTO);
//
//    Result insertOsVersion(Rewrite_OsversionDTO rewrite_osversionDTO);
//
//    Result getOsVersionList(Rewrite_submitOsVersionListDTO rewrite_submitOsVersionListDTO);
    
	/**
	 * App版本更新
	 */
	Result appVersionUpdate(AppControlUpdateDTO appControllerUpdateDTO);
	
	/**
	 * 添加App版本
	 */
	Result adminAddAppControl(AppControlDataDTO appControllerDataDTO);
//	
	/**
	 * 修改App版本
	 */
	Result adminUpdateControl();
}
