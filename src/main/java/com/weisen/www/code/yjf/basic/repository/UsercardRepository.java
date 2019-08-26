package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Usercard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Usercard entity.
 */
@Repository
public interface UsercardRepository extends JpaRepository<Usercard, Long> {

}
