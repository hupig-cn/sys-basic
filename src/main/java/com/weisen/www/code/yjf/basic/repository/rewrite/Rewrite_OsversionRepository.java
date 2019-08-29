package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Osversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Spring Data  repository for the Osversion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_OsversionRepository extends JpaRepository<Osversion, Long> {
    @Query(value = "SELECT * FROM osversion WHERE os = ?1 ORDER BY createdate DESC LIMIT 1",nativeQuery = true)
    Map<String,String> findVersionByOs(String os);
    @Query(value = "SELECT * FROM osversion ORDER BY createdate DESC LIMIT ?1,?2",nativeQuery = true)
    List<Osversion> findVersionByPage(int pageNum, Integer pageSize);
    @Query(value = "SELECT count(*) FROM osversion ",nativeQuery = true)
    Integer getCount();

}
