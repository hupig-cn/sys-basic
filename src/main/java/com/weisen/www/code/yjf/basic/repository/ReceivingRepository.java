package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Receiving;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Receiving entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReceivingRepository extends JpaRepository<Receiving, Long> {

}
