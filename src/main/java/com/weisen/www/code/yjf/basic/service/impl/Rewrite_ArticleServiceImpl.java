package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.ArticleService;
import com.weisen.www.code.yjf.basic.service.Rewrite_ArticleService;
import com.weisen.www.code.yjf.basic.domain.Article;
import com.weisen.www.code.yjf.basic.repository.ArticleRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ArticleRepository;
import com.weisen.www.code.yjf.basic.service.dto.ArticleDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ArticleMapper;
import com.weisen.www.code.yjf.basic.util.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Article}.
 */
@Service
@Transactional
public class Rewrite_ArticleServiceImpl implements Rewrite_ArticleService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_ArticleServiceImpl.class);

    private final Rewrite_ArticleRepository rewrite_ArticleRepository;

    private final ArticleMapper articleMapper;

    public Rewrite_ArticleServiceImpl(Rewrite_ArticleRepository rewrite_ArticleRepository, ArticleMapper articleMapper) {
        this.rewrite_ArticleRepository = rewrite_ArticleRepository;
        this.articleMapper = articleMapper;
    }

	@Override
	public Result findAll(Integer pageNmb, Integer pagesize) {
		List<Map<String, Object>> data = rewrite_ArticleRepository.queryByLimit(pageNmb*pagesize, pagesize);
		return Result.suc("获取成功",data,data.size());
	}

	@Override
	public Result finddeails(Long id) {
		Article data=rewrite_ArticleRepository.findById(id).get();
		data.setAuthor(data.getAuthor()+1);
		rewrite_ArticleRepository.save(data);
		return Result.suc("获取成功",data);
	}

	@Override
	public Result createArticle(Long userid, String title, String imgurl, String content) {
		Article article=new Article();
		article.setUserid(userid);
		article.setTitle(title);
		article.setImgUrl(imgurl);
		article.setContent(content);
		article.setLogicDelete((long)1);
		article.setAuthor((long)0);
		Article data = rewrite_ArticleRepository.save(article);
		return Result.suc("发布成功",data.getId());
	}

	@Override
	public Result findArticlePush() {
		List<Article> data=rewrite_ArticleRepository.findArticlePush();
		return Result.suc("获取成功",data);
	}
	
	
    
}