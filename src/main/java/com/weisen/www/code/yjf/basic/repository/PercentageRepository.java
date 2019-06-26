package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Percentage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Percentage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PercentageRepository extends JpaRepository<Percentage, Long> {

}
