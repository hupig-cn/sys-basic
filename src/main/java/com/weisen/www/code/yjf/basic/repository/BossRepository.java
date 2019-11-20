package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Boss;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Boss entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BossRepository extends JpaRepository<Boss, Long> {

}
