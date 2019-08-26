package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Messagetemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Messagetemplate entity.
 */
@Repository
public interface MessagetemplateRepository extends JpaRepository<Messagetemplate, Long> {

}
