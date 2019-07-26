package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Receiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Receiving entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_ReceivingRepository extends JpaRepository<Receiving, Long> {
    @Query(value = "select * from receiving where userid = ?1", nativeQuery = true)
    List<Receiving> getReceivingByUserId(Long id);

    @Query(value = "select * from  receiving where userid = ?1 and id = ?2", nativeQuery = true)
    Receiving findByUserIdAndId(Long userid, Long id);

    @Query(value = "select count(*) from  receiving where userid = ?1", nativeQuery = true)
    Integer findByUserId(String userid);

    @Query(value = "select * from  receiving where userid = ?1 and isdefault IS TRUE ", nativeQuery = true)
    Receiving findByUserIdAndDefault(Long userId);

}
