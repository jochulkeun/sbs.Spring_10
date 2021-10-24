package com.sbs.exam.demo.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.Service.ArticleService;
import com.sbs.exam.demo.vo.Article;

@Controller
public class UsrArticleController {
	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	
	public List<Article> getArticles(){
		return articleService.getArticles();
		
	}
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	
	public Article getArticle(int id){
		return articleService.getArticle(id);
		
	}
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	
	public String doDelete(int id){
		Article article = articleService.getArticle(id);
		if(article == null) {
			
			return id+"번글 없음";
		}
		articleService.doDelete(id);
		return id+"번글 삭제";
	}
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	
	public String doModify(int id,String title,String body){
		Article article = articleService.getArticle(id);
		if(article == null) {
			
			return id+"번글 없음";
		}
		articleService.doModify(id,title,body);
		return id+"번글 수정";
	}
}
