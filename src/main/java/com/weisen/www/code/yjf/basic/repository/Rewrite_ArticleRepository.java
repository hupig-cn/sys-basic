package com.weisen.www.code.yjf.basic.repository;

import com.weisen.www.code.yjf.basic.domain.Article;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Article entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_ArticleRepository extends JpaRepository<Article, Long> {
	
	@Query(value = "select id,userid,title,author,img_url,createtime from article where logic_delete != 0 order by createtime desc limit ?1,?2",nativeQuery = true)
	List<Map<String,Object>> queryByLimit(Integer pageNmb,Integer pagesize);
	
	
	@Query(value = "select title,author from article order by Author desc Limit 0,5",nativeQuery = true)
	List<Map<String,Object>> findArticlePush();
	
	@Query(value = "seelct id,userid,title,author,img_url,from article where userid=?1 limit ?2 ,?3",nativeQuery = true)
	List<Article> findArticleByUserid(Long userid,Integer pageNmb,Integer pagesize);
}
