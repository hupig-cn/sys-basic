package com.weisen.www.code.yjf.basic.service.rewrite.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class Rewrite_ArticleDTO implements Serializable {

	
	private Long userid;
	
	private String imgurl;
	
	private String content;
	
	private String title;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
	
	
	
   
}
