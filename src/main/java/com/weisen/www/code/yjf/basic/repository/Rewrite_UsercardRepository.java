package com.weisen.www.code.yjf.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weisen.www.code.yjf.basic.domain.Usercard;

public interface Rewrite_UsercardRepository extends JpaRepository<Usercard, Long> {

	Usercard findByLogo(String logo);

	// 根据id查找银行卡列表信息
	@Query(value = "select * from usercard where id = ?1", nativeQuery = true)
	Usercard findById2(Long id);

}
