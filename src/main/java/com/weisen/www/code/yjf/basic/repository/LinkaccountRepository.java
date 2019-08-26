package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Linkaccount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Linkaccount entity.
 */
@Repository
public interface LinkaccountRepository extends JpaRepository<Linkaccount, Long> {

}
