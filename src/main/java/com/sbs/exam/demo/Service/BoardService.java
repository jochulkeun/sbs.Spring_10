package com.sbs.exam.demo.Service;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.BoardRepository;
import com.sbs.exam.demo.vo.Board;
@Service
public class BoardService {

	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public Board getBoardIdById(int boardId) {

		return boardRepository.getBoardIdById(boardId);
	}

}
