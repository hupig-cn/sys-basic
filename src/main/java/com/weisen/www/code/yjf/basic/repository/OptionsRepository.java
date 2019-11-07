package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Options;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Options entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OptionsRepository extends JpaRepository<Options, Long> {

}
