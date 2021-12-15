package com.sbs.exam.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class ReactionPointService {

	private ReactionPointRepository reactionPointRepository;

	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}

	public boolean actorCanMakeReactionPoint(int actorId, String relTypeCode, int id) {

		return reactionPointRepository.getSumReactionPointBymemberId(id,relTypeCode, actorId) == 0;
	}
}
