package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Percentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Rewrite_PercentageRepository extends JpaRepository<Percentage, Long> {

    @Query(value = "from Percentage where name = ?1 and type = ?1")
    Percentage findByName(String name, String type);
}
