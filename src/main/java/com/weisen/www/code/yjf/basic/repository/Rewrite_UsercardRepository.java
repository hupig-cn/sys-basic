package com.weisen.www.code.yjf.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weisen.www.code.yjf.basic.domain.Usercard;

public interface Rewrite_UsercardRepository extends JpaRepository<Usercard, Long> {

	Usercard findByLogo(String logo);

}
