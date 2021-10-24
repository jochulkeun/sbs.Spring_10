package com.sbs.exam.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.vo.Article;

@Service
public class ArticleService {

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		makeTestData();
	}

	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			writeArticle(title, body);
		}
	}

	private ArticleRepository articleRepository;

	private void writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);

	}

	public List<Article> getArticles() {

		return articleRepository.getArticles();
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public void doDelete(int id) {
		 articleRepository.doDelete(id);
	}

	public void doModify(int id, String title, String body) {
		
		articleRepository.doModify(id,title,body);
	}

}
