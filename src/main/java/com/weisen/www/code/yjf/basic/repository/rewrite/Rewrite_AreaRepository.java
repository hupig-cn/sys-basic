package com.weisen.www.code.yjf.basic.repository.rewrite;


import com.weisen.www.code.yjf.basic.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface Rewrite_AreaRepository extends JpaRepository<Area, Long> {
	
    List<Area> findByPnameOrderById(String pname);

}
