package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Linkuser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Linkuser entity.
 */
@Repository
public interface LinkuserRepository extends JpaRepository<Linkuser, Long> {

}
