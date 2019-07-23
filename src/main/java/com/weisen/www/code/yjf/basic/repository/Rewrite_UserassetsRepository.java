package com.weisen.www.code.yjf.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Userassets;

@Repository
public interface Rewrite_UserassetsRepository extends JpaRepository<Userassets, Long> {
	// 根据用户id查询用户资产
	Userassets findByUserid(String userid);
}	