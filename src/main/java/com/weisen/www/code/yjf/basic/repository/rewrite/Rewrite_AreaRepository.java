package com.weisen.www.code.yjf.basic.repository.rewrite;


import com.weisen.www.code.yjf.basic.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface Rewrite_AreaRepository extends JpaRepository<Area, Long> {
    @Query(value = "select  id,name,pid,status,pname,gid,gname from area where status = 0 and name LIKE concat('%',?1,'%')",nativeQuery = true)
    Area findNextAreaByName(String name);
}
