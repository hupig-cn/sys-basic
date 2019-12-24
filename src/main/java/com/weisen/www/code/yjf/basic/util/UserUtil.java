package com.weisen.www.code.yjf.basic.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.weisen.www.code.yjf.basic.repository.rewrite.Rewrite_UserRepository;
import com.weisen.www.code.yjf.basic.security.SecurityUtils;

public class UserUtil {
	
	@Autowired
	private static Rewrite_UserRepository rewrite_UserRepository;
	
	public static Long getCurrentUserId(){
		Long userid = 3L;
		if(SecurityUtils.getCurrentUserLogin().isPresent()) {
			if(rewrite_UserRepository.findJhiUserByLogin(SecurityUtils.getCurrentUserLogin().get()) != null) {
				userid = rewrite_UserRepository.findJhiUserByLogin(SecurityUtils.getCurrentUserLogin().get()).getId();
			}
		}
        return userid;
    }

}
