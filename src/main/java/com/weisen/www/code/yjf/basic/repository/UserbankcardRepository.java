package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userbankcard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Userbankcard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserbankcardRepository extends JpaRepository<Userbankcard, Long> {

}
