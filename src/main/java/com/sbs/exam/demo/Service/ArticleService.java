package com.sbs.exam.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.util.Ut;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class ArticleService {
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public ResultData writeArticle(int memberId, String title, String body) {

		articleRepository.writeArticle(memberId,title, body);
		int id = articleRepository.getLastInsertId();
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다",id),"id",id);
	}

	public List<Article> getForPrintArticles(int actorId) {
		
		List<Article> articles = articleRepository.getForPrintArticles();
		
		for(Article article:articles) {
			
			updatePrintForData(actorId,article);
		}
		
		return articleRepository.getForPrintArticles();
	}

	public Article getForPrintArticle(int actorId ,int id) {
		
		Article article = articleRepository.getForPrintArticle(id); 
		
		updatePrintForData(actorId,article);
		
		return article;
	}

	private void updatePrintForData(int actorId, Article article) {
		if(article == null) {
			return ;
		}
		if(article.getMemberId()==actorId) {
			article.setExtra__actorCanDelete(true);
		}
		
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public ResultData<Article> modifyArticle(int actorId,int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getForPrintArticle(actorId,id);
		return ResultData.from("S-1",Ut.f("%d번 글이 수정되었습니다.",id),"article",article);
	}

	public ResultData userCanModify(int userId, Article article) {
		if(article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		if(article.getMemberId() != userId) {
			return ResultData.from("F-2", "해당글에 수정권한이 없습니다.");
		}
		return ResultData.from("S-2", "해당은 수정가능 합니다.");
	}

}