package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Osversion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Osversion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OsversionRepository extends JpaRepository<Osversion, Long> {

}
