package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.alipay.api.domain.Product;
import com.weisen.www.code.yjf.basic.domain.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Spring Data  repository for the Commodity entity.
 */
@Repository
public interface Rewrite_CommodityRepository extends JpaRepository<Commodity, Long> {


    @Query(value = "select id from shopmall.commodity Where Brandid = ?1",nativeQuery = true)
    List<Long> findCommodityByBrandid(String brandid);
}
