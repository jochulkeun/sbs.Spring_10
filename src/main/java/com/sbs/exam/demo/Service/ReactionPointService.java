package com.sbs.exam.demo.Service;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.vo.ResultData;

@Service
public class ReactionPointService {

	private ReactionPointRepository reactionPointRepository;
	private ArticleService articleService;

	public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService articleService) {
		this.reactionPointRepository = reactionPointRepository;
		this.articleService = articleService;
	}

	public ResultData actorCanMakeReactionPoint(int actorId, String relTypeCode, int relId) {
		if (actorId == 0) {
			return ResultData.from("F-1", "로그인 후 이용해 주세요");
		}
		int sumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(relId, relTypeCode,
				actorId);

		if (sumReactionPointByMemberId != 0) {
			return ResultData.from("F-2", "리액션 불가능", "sumReactionPointByMemberId", sumReactionPointByMemberId);
		}
		return ResultData.from("S-2", "리액션 가능", "sumReactionPointByMemberId", sumReactionPointByMemberId);
	}

	public ResultData addGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addGoodReactionPoint(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.increaseGoodReactionPoint(relId);
			break;
		}
		return ResultData.from("S-1", "좋아요 성공!");
	}

	public ResultData addBadReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.addBadReactionPoint(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.increaseBadReactionPoint(relId);
			break;
		}
		return ResultData.from("S-1", "싫어요 성공!");
	}

	public ResultData deleteGoodReactionPointRd(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.deleteReactionPointRd(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.decreaseGoodReactionPoint(relId);
			break;
		}
		return ResultData.from("S-1", "좋아요 취소 성공!");
	}

	public ResultData deleteBadReactionPointRd(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.deleteReactionPointRd(actorId, relTypeCode, relId);

		switch (relTypeCode) {
		case "article":
			articleService.decreaseBadReactionPoint(relId);
			break;
		}
		return ResultData.from("S-1", "싫어요 취소 성공!");
	}
}
