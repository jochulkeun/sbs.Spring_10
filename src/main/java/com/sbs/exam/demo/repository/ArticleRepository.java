package com.sbs.exam.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sbs.exam.demo.vo.Article;

@Component
public class ArticleRepository {
	int lastId;
	private List<Article> articles;

	public ArticleRepository() {
		articles = new ArrayList<>();
	}

	public List<Article> getArticles() {

		lastId = 0;
		return articles;
	}

	public void writeArticle(String title, String body) {
		int id = lastId + 1;
		Article article = new Article(id, title, body);
		articles.add(article);

		lastId = id;

	}

	public Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void doDelete(int id) {
		Article article = getArticle(id);
		articles.remove(article);

	}

	public void doModify(int id, String title, String body) {
		Article article = getArticle(id);
		article.setBody(body);
		article.setTitle(title);

	}
}
