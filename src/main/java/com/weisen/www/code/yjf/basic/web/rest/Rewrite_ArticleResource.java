package com.weisen.www.code.yjf.basic.web.rest;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.yjf.basic.service.Rewrite_ArticleService;
import com.weisen.www.code.yjf.basic.service.rewrite.dto.Rewrite_ArticleDTO;
import com.weisen.www.code.yjf.basic.util.Result;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing {@link com.weisen.www.code.yjf.basic.domain.Article}.
 */
@RestController
@RequestMapping("/api")
@Api(tags = "文章管理")
public class Rewrite_ArticleResource {

	private Rewrite_ArticleService rewrite_ArticleService;

    public Rewrite_ArticleResource(Rewrite_ArticleService rewrite_ArticleService) {
        this.rewrite_ArticleService = rewrite_ArticleService;
    }
    
    @GetMapping("/public/findArticleAll")
    @ApiOperation(value = "查询文章列表")
    public ResponseEntity<Result> findArticleAll(@RequestParam("pageNmb") Integer pageNmb, @RequestParam("pagesize") Integer pagesize) {
    	Result result = rewrite_ArticleService.findAll(pageNmb, pagesize);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    @GetMapping("/public/findArticleByid")
    @ApiOperation(value = "根据文章id查询文章")
    public ResponseEntity<Result> findArticleByid(@RequestParam("id") Long id,@RequestParam("userid")Long userid) {
    	Result result = rewrite_ArticleService.finddeails(id,userid);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    
    @PostMapping("/public/Articlesave")
    @ApiOperation(value = "文章新增")
    public ResponseEntity<Result> Articlesave(@RequestBody Rewrite_ArticleDTO Article) {
    	Result result = rewrite_ArticleService.createArticle(Article.getUserid(), Article.getTitle(), Article.getImgurl(), Article.getContent());
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    @GetMapping("/public/findArticlePush")
    @ApiOperation(value = "文章推荐")
    public ResponseEntity<Result> findArticlePush() {
    	Result result = rewrite_ArticleService.findArticlePush();
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
    
    @GetMapping("/public/findArticleByUserid")
    @ApiOperation(value = "我的文章")
    public ResponseEntity<Result> findArticleByUserid(@RequestParam("userid") Long userid,@RequestParam("pageNmb") Integer pageNmb,@RequestParam("pageSize") Integer pageSize) {
    	Result result = rewrite_ArticleService.findArticleByUserid(userid,pageNmb,pageSize);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
