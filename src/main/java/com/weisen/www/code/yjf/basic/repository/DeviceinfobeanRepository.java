package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Deviceinfobean;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Deviceinfobean entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceinfobeanRepository extends JpaRepository<Deviceinfobean, Long> {

}
