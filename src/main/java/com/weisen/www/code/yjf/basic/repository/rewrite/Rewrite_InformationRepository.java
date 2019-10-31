package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data repository for the Information entity.
 */
@Repository
public interface Rewrite_InformationRepository extends JpaRepository<Information, Long> {
	@Query(value = "select * from information where id = ?2 and  readuserid = ?1 and logicdelete = 0", nativeQuery = true)
	Optional<Information> findInformationByUserIdAndId(String userId, Long id);

	@Query(value = "update information set state = 2 where id = ?1 and  readuserid = ?2 and logicdelete = 0", nativeQuery = true)
	@Modifying
	Integer readInformationById(Long id, String userId);

	@Query(value = "update information set state = 2 where readuserid = ?1 and  state = 1 and logicdelete = 0 ", nativeQuery = true)
	@Modifying
	Integer readAllInformation(String userId);

	@Query(value = "update information set logicdelete = 1 where id in :ids and  readuserid = :userId", nativeQuery = true)
	@Modifying
	Integer deleteInformations(@Param("ids") Long[] ids, @Param("userId") String userId);

	@Query(value = "select count(*) from information where readuserid = ?1 AND logicdelete = 0", nativeQuery = true)
	Integer getCount(String userId);

	@Query(value = "select * from information where readuserid = ?1 AND logicdelete = 0 ORDER BY createdate DESC LIMIT ?2,?3", nativeQuery = true)
	List<Information> getInformations(String userId, Integer pageNum, Integer pageSize);

	List<Information> findByReaduserid(String userId);
}
