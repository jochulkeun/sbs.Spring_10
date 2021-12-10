package com.sbs.exam.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.Service.ArticleService;
import com.sbs.exam.demo.Service.BoardService;
import com.sbs.exam.demo.util.Ut;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.Board;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;

	public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {

		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}

	// 액션 메서드 시작
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(int boardId, String title, String body, String replaceUri) {

		if (Ut.Empty(title)) {
			return rq.jsHistoryBack("title을 입력해 주세요");
		}
		if (Ut.Empty(body)) {
			return rq.jsHistoryBack("body을 입력해 주세요");
		}

		ResultData writeArticleRd = articleService.writeArticle(boardId, rq.getLoginedMemberId(), title, body);
		int id = (int) writeArticleRd.getData1();

		if (Ut.Empty(replaceUri)) {
			replaceUri = Ut.f("../article/detail?id=%d", id);
		}

		return rq.jsReplace(Ut.f("%d번 글이 생성되었습니다.", id), replaceUri);
	}

	@RequestMapping("/usr/article/write")
	public String ShowWrite(HttpServletRequest req, Model model) {

		return "usr/article/write";
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model, @RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "title,body") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, @RequestParam(defaultValue = "1") int page) {

		Board board = boardService.getBoardIdById(boardId);

		if (board == null) {

			return rq.HistoryBackOnView(Ut.f("%d번 게시판은 존재하지 않습니다.", boardId));
		}
		int articlesCount = articleService.articlesCount(boardId, searchKeywordTypeCode, searchKeyword);

		int itemsCountInApage = 10;
		int pagesCount = (int) Math.ceil((double) articlesCount / itemsCountInApage);

		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId(), boardId,
				searchKeywordTypeCode, searchKeyword, itemsCountInApage, page);

		model.addAttribute("board", board);
		model.addAttribute("boardId", boardId);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("articlesCount", articlesCount);
		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public Object showDetail(Model model, int id) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		model.addAttribute("article", article);

		return "/usr/article/detail";
	}

	@RequestMapping("/usr/article/doIncreaseHitCountRd")
	@ResponseBody
	public ResultData<Integer> doIncreaseHitCountRd(int id) {

		ResultData increaseHitCountRd = articleService.increaseHitCount(id);
		if (increaseHitCountRd.isFail()) {
			
			return increaseHitCountRd;
		}
		return ResultData.newData(increaseHitCountRd, "HitCount", articleService.getArticleHitCount(id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.jsHistoryBack(Ut.f("%d번 게시물을 존재하지않습니다.", id));

		}

		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsHistoryBack("해당 게시물에 대한 권한이 없습니다");

		}

		articleService.deleteArticle(id);

		return Ut.jsReplace(Ut.f("%d번 게시물을 삭제했습니다.", id), "/usr/article/list");
	}

	@RequestMapping("/usr/article/modify")
	public String modify(Model model, int id) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.HistoryBackOnView(Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		ResultData userCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);

		if (userCanModifyRd.isFail()) {
			return rq.HistoryBackOnView(userCanModifyRd.getMsg());
		}
		model.addAttribute("article", article);
		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {

			return rq.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		ResultData userCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);

		if (userCanModifyRd.isFail()) {
			return rq.jsHistoryBack(userCanModifyRd.getMsg());
		}
		articleService.modifyArticle(rq.getLoginedMemberId(), id, title, body);
		return rq.jsReplace(Ut.f("%d 번글이 수정되었습니다.", id), Ut.f("../article/detail?id=%d", id));

	}
}