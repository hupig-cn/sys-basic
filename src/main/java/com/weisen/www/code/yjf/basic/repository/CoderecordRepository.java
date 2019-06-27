package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Coderecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Coderecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoderecordRepository extends JpaRepository<Coderecord, Long> {

}
