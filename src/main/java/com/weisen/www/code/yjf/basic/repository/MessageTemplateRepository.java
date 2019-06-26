package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.MessageTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MessageTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {

}
