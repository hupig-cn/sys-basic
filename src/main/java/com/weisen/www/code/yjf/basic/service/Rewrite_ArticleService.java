package com.weisen.www.code.yjf.basic.service;

import com.weisen.www.code.yjf.basic.service.dto.ArticleDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.weisen.www.code.yjf.basic.domain.Article}.
 */
public interface Rewrite_ArticleService {

	Result findAll(Integer pageNmb,Integer page);
	
	Result finddeails(Long id);
	
	Result createArticle(Long userid,String title,String imgurl,String content);
	
	Result findArticlePush();
	
	Result findArticleByUserid(Long userid,Integer pageNmb,Integer pagesize);
}
