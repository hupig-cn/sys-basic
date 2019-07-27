package com.weisen.www.code.yjf.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Userlocation;


/**
 * Spring Data  repository for the Userlocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_UserlocationRepository extends JpaRepository<Userlocation, Long> {
	
	Userlocation findByUserid(String userid);

}
