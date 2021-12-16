package com.sbs.exam.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.Service.ReactionPointService;
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
	String doGoodReaction(Model model, String relTypeCode, int relId, String replaceUri) {
		
		boolean actorCanMakeReactionPoint = reactionPointService.actorCanMakeReactionPoint(rq.getLoginedMemberId(),
				"article", relId).isSuccess();

		model.addAttribute("actorCanMakeReactionPoint", actorCanMakeReactionPoint);
		
		if(actorCanMakeReactionPoint == false) {
			return rq.HistoryBackOnView("이미 처리되었습니다.");
		}
		
		reactionPointService.addGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
		
		return rq.jsReplace("좋아요!", replaceUri);

	}

	@RequestMapping("/usr/reactionPoint/doBadReaction")
	@ResponseBody
	String dobadReaction(Model model, String relTypeCode, int relId, String replaceUri) {
		
		boolean actorCanMakeReactionPoint = reactionPointService.actorCanMakeReactionPoint(rq.getLoginedMemberId(),
				"article", relId).isSuccess();

		model.addAttribute("actorCanMakeReactionPoint", actorCanMakeReactionPoint);
		
		if(actorCanMakeReactionPoint == false) {
			return rq.HistoryBackOnView("이미 처리되었습니다.");
		}
		
		reactionPointService.addBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
		
		return rq.jsReplace("싫어요!", replaceUri);

	}
}