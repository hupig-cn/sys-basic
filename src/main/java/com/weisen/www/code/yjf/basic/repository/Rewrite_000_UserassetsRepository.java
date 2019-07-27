package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Userassets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Rewrite_000_UserassetsRepository extends JpaRepository<Userassets, Long> {

//    @Query(value = "from Userassets where userid = ?1")
    Userassets findByUserId(String userId);
}
