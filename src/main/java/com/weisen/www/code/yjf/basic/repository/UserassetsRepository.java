package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userassets;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Userassets entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserassetsRepository extends JpaRepository<Userassets, Long> {

}
