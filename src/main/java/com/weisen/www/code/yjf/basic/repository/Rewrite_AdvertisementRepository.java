package com.weisen.www.code.yjf.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.yjf.basic.domain.Advertisement;

/**
 * Spring Data  repository for the Advertisement entity.
 */
@Repository
public interface Rewrite_AdvertisementRepository extends JpaRepository<Advertisement, Long> {

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM advertisement a WHERE a.jhi_sort = :jhi_sort")
	Integer getCountBySort(@Param("jhi_sort") Integer integer);

	@Query(nativeQuery = true, value = "SELECT * FROM advertisement a WHERE a.state = 1 ORDER BY a.jhi_sort ASC LIMIT :startIndex, :displayLength")
	List<Advertisement> findList(@Param("startIndex") int startIndex,@Param("displayLength") int displayLength);

	@Query(nativeQuery = true, value = "SELECT * FROM advertisement a WHERE (:name is null or a.name like %:name%) and (:type is null or a.jhi_type = :type) ORDER BY a.created_date DESC LIMIT :startIndex, :displayLength")
	List<Advertisement> findListByName(@Param("name") String name,@Param("type") Integer type,@Param("startIndex") int startIndex,@Param("displayLength") int displayLength);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM advertisement a WHERE (:name is null or a.name like %:name%) and (:type is null or a.jhi_type = :type)")
	Integer getCountByName(@Param("name") String name,@Param("type") Integer type);

    @Query(nativeQuery = true, value = "SELECT * FROM advertisement WHERE jhi_type = ?1")
    List<Advertisement> findAdvertisementByAdvType(String type);
}
