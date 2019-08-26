package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userorder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Userorder entity.
 */
@Repository
public interface UserorderRepository extends JpaRepository<Userorder, Long> {

}
