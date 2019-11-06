package com.weisen.www.code.yjf.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Osversion;


/**
 * Spring Data  repository for the Osversion entity.
 */
@Repository
public interface OsversionRepository extends JpaRepository<Osversion, Long> {

}
