package com.weisen.www.code.yjf.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Files;


/**
 * Spring Data  repository for the Files entity.
 */
@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {

	//通过id找到该记录
	@Query(value = "select * from files where id = ?1", nativeQuery = true)
	Files findByIds(Long id);

}
