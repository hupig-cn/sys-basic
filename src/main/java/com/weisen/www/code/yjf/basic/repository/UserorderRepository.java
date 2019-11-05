package com.weisen.www.code.yjf.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Userorder;


/**
 * Spring Data  repository for the Userorder entity.
 */
@Repository
public interface UserorderRepository extends JpaRepository<Userorder, Long> {

}
