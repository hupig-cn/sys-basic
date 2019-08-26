package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Userlinkuser entity.
 */
@Repository
public interface UserlinkuserRepository extends JpaRepository<Userlinkuser, Long> {

}
