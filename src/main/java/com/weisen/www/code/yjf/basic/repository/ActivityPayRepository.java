package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.ActivityPay;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivityPay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityPayRepository extends JpaRepository<ActivityPay, Long> {

}
