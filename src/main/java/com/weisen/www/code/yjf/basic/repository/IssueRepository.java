package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Issue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Issue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

}
