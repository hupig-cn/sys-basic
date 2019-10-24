package com.weisen.www.code.yjf.basic.repository.rewrite;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weisen.www.code.yjf.basic.domain.User;

public interface Rewrite_UserRepository  extends JpaRepository<User, Long> {

	//根据id查找login库中的数据
	@Query(value = "select * from login.jhi_user where id = ?1", nativeQuery = true)
	User findJhiUserById(Long id);
	
}
