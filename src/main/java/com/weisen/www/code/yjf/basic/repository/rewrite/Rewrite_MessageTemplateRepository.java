package com.weisen.www.code.yjf.basic.repository.rewrite;

import com.weisen.www.code.yjf.basic.domain.Messagetemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface Rewrite_MessageTemplateRepository extends JpaRepository<Messagetemplate, Long> {
    @Query(value = "select * from message_template where jhi_type = ?1",nativeQuery = true)
    Messagetemplate findTemplateByType(String type);
}
