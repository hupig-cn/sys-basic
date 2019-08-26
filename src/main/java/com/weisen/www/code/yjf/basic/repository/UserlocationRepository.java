package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userlocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Userlocation entity.
 */
@Repository
public interface UserlocationRepository extends JpaRepository<Userlocation, Long> {

}
