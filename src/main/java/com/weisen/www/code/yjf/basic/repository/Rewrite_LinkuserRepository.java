package com.weisen.www.code.yjf.basic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Linkuser;

/**
 * Spring Data repository for the Linkuser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_LinkuserRepository extends JpaRepository<Linkuser, Long> {

	Optional<Linkuser> findByUserid(String userid);
	
	Linkuser findByIdcard(String idcard);

}
