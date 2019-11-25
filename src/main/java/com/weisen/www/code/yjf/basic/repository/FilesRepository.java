package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Files;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Files entity.
 */
@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {

	@Query(value = "select * from files where id = ?1", nativeQuery = true)
	Files findByIds(Long id);
//	
//	@Query(value = "select id from files ", nativeQuery = true)
//	List<Long> findByid2();
}
