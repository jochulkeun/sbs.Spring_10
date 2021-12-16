package com.sbs.exam.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.Service.ReactionPointService;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrReactionPointController {
	@Autowired
	private ReactionPointService reactionPointService;
	private Rq rq;

	public UsrReactionPointController(ReactionPointService reactionPointService, Rq rq) {

		this.reactionPointService = reactionPointService;
		this.rq = rq;
	}

	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
	String doGoodReaction(String relTypeCode, int relId, String replaceUri) {
		
		ResultData actorCanMakeReactionPointRd = reactionPointService.actorCanMakeReactionPoint(rq.getLoginedMemberId(),
				relTypeCode, relId);

		
		if(actorCanMakeReactionPointRd.isFail()) {
			return rq.jsHistoryBack(actorCanMakeReactionPointRd.getMsg());
		}
		
		reactionPointService.addGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
		
		return rq.jsReplace("좋아요!", replaceUri);

	}

	@RequestMapping("/usr/reactionPoint/doBadReaction")
	@ResponseBody
	String dobadReaction(String relTypeCode, int relId, String replaceUri) {
		
		ResultData actorCanMakeReactionPointRd = reactionPointService.actorCanMakeReactionPoint(rq.getLoginedMemberId(),
				relTypeCode, relId);

		
		if(actorCanMakeReactionPointRd.isFail()) {
			return rq.jsHistoryBack(actorCanMakeReactionPointRd.getMsg());
		}
		
		reactionPointService.addBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
		
		return rq.jsReplace("싫어요!", replaceUri);

	}
	@RequestMapping("/usr/reactionPoint/doCancelGoodReaction")
	@ResponseBody
	String doCancelGoodReaction(String relTypeCode, int relId, String replaceUri) {
		
		ResultData actorCanMakeReactionPointRd = reactionPointService.actorCanMakeReactionPoint(rq.getLoginedMemberId(),
				relTypeCode, relId);

		if(actorCanMakeReactionPointRd.isSuccess()) {
			return rq.HistoryBackOnView("이미 취소되었습니다.");
		}
		ResultData deleteGoodReactionPointRd = reactionPointService.deleteGoodReactionPointRd(rq.getLoginedMemberId(),
				relTypeCode, relId);
		
		return rq.jsReplace(deleteGoodReactionPointRd.getMsg(), replaceUri);

	}
	
	@RequestMapping("/usr/reactionPoint/doCancelBadReaction")
	@ResponseBody
	String doCancelBadReaction(Model model, String relTypeCode, int relId, String replaceUri) {
		
		ResultData actorCanMakeReactionPointRd = reactionPointService.actorCanMakeReactionPoint(rq.getLoginedMemberId(),
				relTypeCode, relId);

		if(actorCanMakeReactionPointRd.isSuccess()) {
			return rq.HistoryBackOnView("이미 취소되었습니다.");
		}
		ResultData deleteBadReactionPointRd = reactionPointService.deleteBadReactionPointRd(rq.getLoginedMemberId(),
				relTypeCode, relId);
		
		return rq.jsReplace(deleteBadReactionPointRd.getMsg(), replaceUri);

	}
}