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

	public ResultData writeArticle(int boardId, int memberId, String title, String body) {

		articleRepository.writeArticle(boardId, memberId, title, body);
		int id = articleRepository.getLastInsertId();
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다", id), "id", id);
	}

	public List<Article> getForPrintArticles(int actorId, int boardId, int itemsCountInApage, int page) {

		int limitStart = (page - 1) * itemsCountInApage;
		int limitTake = itemsCountInApage;
		
		List<Article> articles = articleRepository.getForPrintArticles(boardId,limitStart,limitTake);

		for (Article article : articles) {

			updatePrintForData(actorId, article);
		}

		return articles;
	}

	public Article getForPrintArticle(int actorId, int id) {

		Article article = articleRepository.getForPrintArticle(id);

		updatePrintForData(actorId, article);

		return article;
	}

	private void updatePrintForData(int actorId, Article article) {
		if (article == null) {
			return;
		}
		ResultData actorCanDeleteRd = actorCanDelete(actorId, article);
		article.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());

		ResultData actorCanModifyRd = actorCanModify(actorId, article);
		article.setExtra__actorCanModify(actorCanModifyRd.isSuccess());

	}

	private ResultData actorCanModify(int actorId, Article article) {
		if (article == null) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}

		if (article.getMemberId() != actorId) {
			return ResultData.from("F-1", "권한이 없습니다.");
		}

		return ResultData.from("S-1", "해당 게시물은 수정이 가능합니다");
	}

	private ResultData actorCanDelete(int actorId, Article article) {

		if (article == null) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}

		if (article.getMemberId() != actorId) {
			return ResultData.from("F-1", "권한이 없습니다.");
		}

		return ResultData.from("S-1", "해당 게시물 삭제가 가능합니다");
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public ResultData<Article> modifyArticle(int actorId, int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);

		Article article = getForPrintArticle(actorId, id);
		return ResultData.from("S-1", Ut.f("%d번 글이 수정되었습니다.", id), "article", article);
	}

	public ResultData userCanModify(int userId, Article article) {
		if (article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		if (article.getMemberId() != userId) {
			return ResultData.from("F-1", "해당글에 수정권한이 없습니다.");
		}
		return ResultData.from("S-2", "해당은 수정가능 합니다.");
	}

	public int articlesCount(int boardId) {
		return articleRepository.articlesCount(boardId);
	}

}