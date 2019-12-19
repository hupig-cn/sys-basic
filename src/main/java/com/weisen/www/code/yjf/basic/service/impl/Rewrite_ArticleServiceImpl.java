package com.weisen.www.code.yjf.basic.service.impl;

import com.weisen.www.code.yjf.basic.service.ArticleService;
import com.weisen.www.code.yjf.basic.service.Rewrite_ArticleService;
import com.weisen.www.code.yjf.basic.domain.Article;
import com.weisen.www.code.yjf.basic.domain.Userlinkuser;
import com.weisen.www.code.yjf.basic.repository.ArticleRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_ArticleRepository;
import com.weisen.www.code.yjf.basic.repository.Rewrite_UserlinkuserRepository;
import com.weisen.www.code.yjf.basic.service.dto.ArticleDTO;
import com.weisen.www.code.yjf.basic.service.mapper.ArticleMapper;
import com.weisen.www.code.yjf.basic.service.util.TimeUtil;
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
    
    private final Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository;

    private final ArticleMapper articleMapper;

    public Rewrite_ArticleServiceImpl(Rewrite_ArticleRepository rewrite_ArticleRepository, ArticleMapper articleMapper,Rewrite_UserlinkuserRepository rewrite_UserlinkuserRepository) {
        this.rewrite_ArticleRepository = rewrite_ArticleRepository;
        this.rewrite_UserlinkuserRepository = rewrite_UserlinkuserRepository;
        this.articleMapper = articleMapper;
    }

	@Override
	public Result findAll(Integer pageNmb, Integer pagesize) {
		List<Map<String, Object>> data = rewrite_ArticleRepository.queryByLimit(pageNmb*pagesize, pagesize);
		return Result.suc("获取成功",data,data.size());
	}

	@Override
	public Result finddeails(Long id,Long userid) {
		Article data=rewrite_ArticleRepository.findById(id).get();
		
		Userlinkuser recid = rewrite_UserlinkuserRepository.findByUserid(userid.toString());
		if(recid.getRecommendid()==null||recid.getRecommendid().equals("")) {			//查看当前观看文章是否有推荐人			
			recid.setRecommendid(data.getUserid().toString());					//没有就把此文章作者作为推荐人
			rewrite_UserlinkuserRepository.save(recid);							//保存
		}
	
		data.setAuthor(data.getAuthor()+1);										//点击一次点击数量加一
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
		article.setCreatetime(TimeUtil.getDate());
		Article data = rewrite_ArticleRepository.save(article);
		return Result.suc("发布成功",data.getId());
	}

	@Override
	public Result findArticlePush() {
		List<Map<String ,Object>> data=rewrite_ArticleRepository.findArticlePush();
		return Result.suc("获取成功",data);
	}

	@Override
	public Result findArticleByUserid(Long userid,Integer pageNmb,Integer pagesize) {
		List<Map<String,Object>> data = rewrite_ArticleRepository.findArticleByUserid(userid, pageNmb*pagesize, pagesize);
		return Result.suc("获取成功",data);
	}
	
	
	
	
    
}
