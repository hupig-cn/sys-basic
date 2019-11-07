package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.AccountStatement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountStatement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountStatementRepository extends JpaRepository<AccountStatement, Long> {

}
